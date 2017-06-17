/**
 * 一个基于 plupload (mOxie) 的上传工具 及 缩略图
 */
;
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery', 'plupload'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.Uploader2 = factory(jQuery, plupload, mOxie);
    }
}(function ($, plupload, o) {
    o = o || mOxie;
    // 模板
    var ENTRY_TPL = '' +
        '<li class="upload-entry" id="{id}-entry">' +
        '  <div class="upload-thumb-wrap">' +
        '    <div class="upload-thumb"></div>' +
        '  </div>' +
        '  <div class="upload-entry-actions">' +
        '    <a href="javascript:void(0);" target="_blank" class="upload-download">下载</a>' +
        '    <a href="javascript:void(0);" class="upload-retry">重试</a>' +
        '    <a href="javascript:void(0);" class="upload-remove">删除</a>' +
        '  </div>' +
        '  <div class="upload-entry-name" title="{name}">{name}</div>' +
        '  <div class="upload-status">' +
        '    <div class="waiting-status">待上传,请稍后...</div>' +
        '    <div class="process-status">' +
        '      <div class="upload-progress">' +
        '        <span class="upload-progress-bar"><span class="upload-progress-bar-inner"></span></span>' +
        '        <span class="upload-progress-text">0%,  ??:??:??</span>' +
        '      </div>' +
        '    </div>' +
        '    <div class="success-status">上传成功</div>' +
        '    <div class="error-status">失败</div>' +
        '    <div class="saved-status">已存储</div>' +
        '  </div>' +
        '  <div class="upload-tip"></div>' +
        '</li>';

    var FILE_NAME_ERROR = -9002,    // 文件名错误
        MODE_LIST = 'l',            // 列表模式 - 列表
        MODE_THUMB = 't';           // 列表模式 - 缩略图

    /**
     * plupload 默认配置
     */
    var DEFAULTS = {
        runtimes: 'html5,flash,silverlight',    // 'html5,flash,silverlight,html4
        // 以下两个如果配置不正确在不支持html5的浏览器无法上传
        flash_swf_url : (window.contextPath || window.ctx||'') + '/js/lib/plupload-2.1.2/Moxie.swf',
        silverlight_xap_url : (window.contextPath || window.ctx||'') + '/js/lib/plupload-2.1.2/Moxie.xap',
        file_data_name: 'file',                 // 文件上传 form element name
        url: undefined,                         // 文件上传 url
        multi_selection: true,                  // 允许一次选择多个文件
        browse_button: undefined,               // 浏览按钮(必须)
        drop_element: undefined,                // 拖放监听元素
        browse_button_active: 'active',         // 浏览按钮激活时 class
        browse_button_hover: 'hover',           // 浏览按钮 hover class
        filters: {
            mime_types: [
                {title: 'Allowd Files', extensions: '*'}
            ]
        },
        // container: undefined,
        // multipart_params: {}                 // 文件上传的附加参数
        // 定制参数 --
        // [containerId]:[mode('+ MODE_LIST + '/' + MODE_THUMB +')]:[[countx]size]:[upload_file_data_name][@url]:[valueField]:[auto]"');
        baseUrl: '',            // 当路径为相对路径时的基准url
        name: null,             // 创建表单域名称
        list: '',               // 列表容器
        mode: MODE_LIST,        // 展示模式
        max_file_count: '10',  // 最大文件数量
        max_file_size: '5G',    // 最大文件
        value: 'url',           // 服务器相应属性值,作为表单域值
        auto: true,             // 自动开始
        remove_on_fail: false,
        download: false,
        thumbFn: function (url, data, file, up) {
            var value = up.getOption('value') || 'url',
                url = getUrl(up.getOption('baseUrl') || window.contextPath || '', url);
            return url;// + '_40x40';
        }
    };

    /**
     * 上传状态样式
     */
    var STATUS_CLASS = {
        WAITING: 'upload-waiting',          // 等待上传
        UPLOADING: 'upload-uploading',      // 上传中
        ERROR: 'upload-fail',                // 上传失败
        SUCCESS: 'upload-success',          // 上传成功
        SAVED: 'upload-saved',              // 历史上传
        DOWNLOAD: 'upload-complete'         // 上传完成
    };

    /**
     * Uploader 构造器
     *
     * @param settings
     * @returns {Uploader}
     * @constructor
     */
    function Uploader(settings) {
        var me = this, uploader, btn;

        if (!(me instanceof Uploader)) {
            return new Uploader(settings);
        }
        if (!settings.value || 'null' == settings.value) {
        	delete settings.value;
        }

        settings = o.extend({}, DEFAULTS, settings || {});

        // 文件大小和数量过滤器配置
        o.extend(settings['filters'], {
            max_file_size: settings['max_file_size'],
            max_file_count: settings['max_file_count']
        });

        delete settings['max_file_size'];
        delete settings['max_file_count'];

        // 查找列表元素
        if (settings['list']) {
            settings['list'] = o.get(settings['list']);
        }

        // 浏览按钮
        btn = o.get(settings['browse_button']);
        // 如果该按钮已经注册 uploader, 尝试销毁
        if (btn && btn.uploader && 'function' === typeof btn.uploader.destroy) {
            btn.uploader.destroy();
        }
        
        // 创建 pluploader 对象
        uploader = new plupload.Uploader(settings);

        // 绑定处理事件
        uploader.bind('PostInit', onInit);
        uploader.bind('FileFiltered', onFiltered);
        uploader.bind('FilesRemoved', onRemoved);
        uploader.bind('QueueChanged', onQueueChanged);
        uploader.bind('UploadFile', onUpload);
        uploader.bind('UploadProgress', onProgress);
        uploader.bind('FileUploaded', onUploaded);
        uploader.bind('Error', onError);

        // 所有浏览按钮
        o.each(uploader.getOption('browse_button'), function (btn) {
            btn.uploader = uploader;
        });

        this.uploader = uploader;
        uploader.init();
    }

    /**
     * 暴露事件绑定
     */
    o.extend(Uploader.prototype, {
        bind: function (event, callback) {
            this.uploader.bind(event, callback);
            return this;
        },
        clearQueue: function () {
            this.uploader.splice();
        }
    });

    /**
     * 初始化后将 uploader 绑定到所有的浏览按钮
     *
     * @param up
     */
    function onInit(up) {
        // 拖拽
        if (up.features.dragdrop) {
            o.each(up.getOption('drop_element'), function (el) {
                o.addEvent(el, 'dragenter', function (e) {
                    o.addClass(el, 'dragover');
                });
                o.addEvent(el, 'dragover', function (e) {
                    o.addClass(el, 'dragover');
                });
                o.addEvent(el, 'dragleave', function (e) {
                    o.removeClass(el, 'dragover');
                });
                o.addEvent(el, 'drop', function (e) {
                    o.removeClass(el, 'dragover');
                });
            });
        }


        // 初始化历史(已保存的)上传
        var settings = up.getOption(),
            $queue = $(settings['list']);

        var _files = up._files = up._files || [],
            idx;
        /*-
         * update 2015.12.08 修改默认的el元素为input为包含 data-saved-url 属性的元素
         * 以便兼容附加表单项的提交, TODO 这个还不能这么搞, input 是隐藏
         */
        //$queue.find('[data-saved-url]').each(function (i, el) {
         $queue.find('input[name="' + settings['name'] + '"]').each(function (i, el) {
             var val = $(el).attr('data-saved-url') || $(el).val(),
                file = {
                    remote: true,
                    url: getUrl(settings['baseUrl'] || window.contextPath || '', val),
                    name: el.getAttribute('data-saved-name') || val,
                    // name: el.getAttribute('data-name') || el.value,
                    el: el
                };

            // $(el).hide();
            _files.push(file);
        });

        $queue.empty();
        for (idx = 0; idx < _files.length; idx++) {
            up.trigger("FileFiltered", _files[idx]);
        }
    }

    /**
     * 文件添加成功时, 添加相应 UI 元素
     *
     * @param up
     * @param file
     */
    function onFiltered(up, file) {
        var queue = up.getOption('list'),
            auto = up.getOption('auto');

        // 如果容器元素存在, 则添加 UI 元素
        queue && queue.appendChild(createEntryEl(up, file));
        // hash(file.getSource());

        // 如果设置自动开始上传则启动上传
        (1 == auto || true == auto) && up.start();
    }

    /**
     * 删除文件时, 删除对应的 UI 元素
     *
     * @param up
     * @param files
     */
    function onRemoved(up, files) {
        o.each(files, function (f) {
            var $entry = $('#' + f.id + '-entry');
            $entry.fadeOut(500, function () {
                $entry.remove();
            });
        });
    }

    /**
     * 队列改变确认禁用按钮
     */
    function onQueueChanged() {
        var up = this,
            maxCount = up.getOption('filters')['max_file_count'],
            queueSize = up.files.length + (up._files || []).length,
            disableBrowse;

        maxCount = maxCount || 0;

        // 如果最大允许数量大于1 且 文件数大于等于允许的最大值, 则禁止浏览
        disableBrowse = 1 < maxCount && queueSize >= maxCount;
        up.disableBrowse(disableBrowse);

        o.each(up.getOption('browse_button'), function (el) {
            el.disabled = disableBrowse;
            disableBrowse ? o.addClass(el, 'disabled') : o.removeClass(el, 'disabled');
        });
    }

    // 开始上传时, 修改 UI 元素样式, 状态
    function onUpload(up, file) {
        setStatus(file.id + '-entry', STATUS_CLASS.UPLOADING);
    }

    // 上传进度
    function onProgress(up, file) {
        var $entry = $('#' + file.id + '-entry'),
            $progress = $entry.find('.upload-progress'),
            $tip = $('#' + file.id + '-entry .upload-tip'),

            now = new Date().getTime(),
            time = now - (file.startTime || 0),
            speed, remain;

        /*
         if(o.hasClass($entry[0], STATUS_CLASS.WAITING)) {
         $progress.find('.upload-progress-bar-inner').width(0);
         setStatus(file.id + '-entry', STATUS_CLASS.UPLOADING);
         }
         */


        file.startTime = file.startTime || now;

        if (100 != file.percent) {
            speed = file.loaded / time;
            remain = Math.round((file.size - file.loaded) / speed);

            speed = plupload.formatSize(Math.round(speed * 1E3)) + '/s';
            remain = formatTime(Math.round(remain / 1E3));

            $progress.find('.upload-progress-bar-inner').width(file.percent + '%');
            $progress.find('.upload-progress-text').text(file.percent + '%, ' + remain);
            $tip.html(file.name + ', ' + speed);
        }
    }

    // 上传成功后
    function onUploaded(up, file, result) {
        var name = up.getOption('name'),
            value = up.getOption('value') || 'url',
            mode = up.getOption('mode'),
            data = $.parseJSON(result.response),
            $entry, $imgWrap, img, size, url;

        // 如果存在错误
        if (data.error || false == data.success) {
            up.trigger('Error', {
                code: plupload.HTTP_ERROR,
                message: data.message || '服务器内部错误',
                file: file
            });
            return;
        }

        $entry = $('#' + file.id + '-entry');
        if (up.getOption('download')) {
            setStatus($entry[0], [STATUS_CLASS.DOWNLOAD, STATUS_CLASS.SUCCESS]);
        } else {
            setStatus($entry[0], [STATUS_CLASS.SUCCESS]);
        }
        // $entry.removeClass('upload-entry-proc').addClass('upload-complete').addClass('upload-success');
        $entry.find('.upload-entry-status').html(plupload.formatSize(file.size) + ' 上传完成');

        url = getUrl(up.getOption('baseUrl') || window.contextPath || '', data[value]);
        if (url) {
            $entry.find('.upload-download').attr('href', plupload.buildUrl(url, {download: true}));
        }

        // 如果设置了名称且提供了值字段, 则创建相应隐藏域
        if (name && value) {
            $('<input type="hidden">').attr('name', name).val(data[value]).appendTo($entry);
        }

        // 如果是缩略图模式
        if (MODE_THUMB == mode) {
            file.getSource() && (file.getSource().rendered = true);
            url = up.getOption('thumbFn').call(up, data[value], result, file, up);
            setEntryImage($entry[0], url);
        }
    }

    /**
     * 错误消息处理
     *
     * {@see plupload.dev.js}
     * @param up  plupload.Uploader 实例
     * @param err 错误对象
     */
    function onError(up, err) {
        // 初始化错误
        if (plupload.INIT_ERROR == err.code) {
            var name = up.getOption('name'),
                btn = up.getOption('browse_button') || [];

            setTimeout(function () {
                // 还原 name 属性并
                o.each(btn, function (btn) {
                    name && btn.setAttribute('name', name);
                    if (btn.uploader) {
                        btn.uploader.destroy();
                        delete btn.uploader;
                    }
                });
                up.destroy();
            }, 1);

            // $('.error').html('组件初始化失败').show();
            console.log('plupload init error', err);
            return;
        }

        if (plupload.FILE_EXTENSION_ERROR == err.code) {
            alert('无效的文件格式');
            return;
        }
        if (plupload.FILE_SIZE_ERROR == err.code) {
            alert('文件过大');
            return;
        }

        if (err.file) {
            var $entry = $('#' + err.file.id + '-entry');

            //$entry.removeClass('upload-entry-proc').addClass('upload-fail');
            setStatus(err.file.id + '-entry', STATUS_CLASS.ERROR);
            if (up.getOption('remove_on_fail')) {
                if (up._files) {
                    if (!err.file.getSource) {
                        for (var i = up._files.length - 1; i >= 0; i--) {
                            if (up._files[i].id === err.file.id) {
                                //return this.splice(i, 1)[0];
                                up._files.splice(i, 1);
                                break;
                            }
                        }
                        up.trigger('FilesRemoved', [err.file]);
                    } else {
                        up && up.removeFile(err.file);
                    }
                }
                // up.trigger('FilesRemoved', [err.file]);
            }
            //$entry.find('.upload-entry-status').html('上传失败');
            return;
        }
        // HTTP 错误
        if (plupload.HTTP_ERROR == err.code) {
            return;
        }
        plupload.GENERIC_ERROR
        plupload.HTTP_ERROR
        plupload.IO_ERROR
        plupload.SECURITY_ERROR
        plupload.FILE_DUPLICATE_ERROR
        plupload.IMAGE_FORMAT_ERROR
        plupload.MEMORY_ERROR
        plupload.IMAGE_DIMENSIONS_ERROR
    }

    function setStatus(entry, statusClass) {
        var status = ['upload-waiting', 'upload-uploading', 'upload-fail', 'upload-success', 'upload-saved'],
            i;
        entry = 'string' == typeof entry ? o.get(entry) : entry;
        if (entry) {
            for (i = 0; i < status.length; i++) {
                o.removeClass(entry, status[i]);
            }
            if ('[object Array]' != Object.prototype.toString.apply(statusClass)) {
                statusClass = [statusClass];
            }
            for (i = 0; i < statusClass.length; i++) {
                o.addClass(entry, statusClass[i]);
            }
        }
    }

    function createEntryEl(up, file) {
        file = file || {};
        file.id = file.id || ('_' + o.guid());

        var $entry = $(renderTpl(ENTRY_TPL, file)),
            status = [];

        if (file.remote) {
            status.push(STATUS_CLASS.SAVED);
            setStatus($entry[0], STATUS_CLASS.SAVED);
            if (up.getOption('download') && file.url) {
                status.push(STATUS_CLASS.DOWNLOAD);
                $entry.find('.upload-download').attr('href', plupload.buildUrl(file.url, {download: true}));
            }
        } else {
            status.push(STATUS_CLASS.WAITING);
        }
        setStatus($entry[0], status);

        // 如果是 根据 html input 元素创建, 则将该 input 移动到 entry 下
        if (file.el) {
            $entry.append(file.el);
        }

        // 删除
        $entry.find('.upload-remove').click(function () {
            if (!file.getSource) {
                for (var i = up._files.length - 1; i >= 0; i--) {
                    if (up._files[i].id === file.id) {
                        //return this.splice(i, 1)[0];
                        up._files.splice(i, 1);
                        break;
                    }
                }
                up.trigger('FilesRemoved', [file]);
            } else {
                up && up.removeFile(file);
            }
        });

        // 重试
        $entry.find('.upload-retry').click(function () {
            file.status = plupload.QUEUED;
            //setStatus($entry[0], STATUS_CLASS.WAITING);
            up && up.start();
        });

        // 这里先添加, 是为了防止无法获取 size
        // document.body.appendChild($entry[0]);

        // 缩略图
        if (up && MODE_THUMB == up.getOption('mode')) {
            $entry.addClass('upload-image-entry');
            setEntryImage($entry[0], file, up)
        }

        $entry.hover(function () {
            $entry.addClass('hover')
        }, function () {
            $entry.removeClass('hover')
        });
        return $entry[0];
    }

    function getUrl(baseUrl, url) {
        baseUrl = baseUrl || window.contextPath || '';
        if (url && null != baseUrl && !/^\//.test(url) && !/^(https?|file):/.test(url)) {
            url = /^\//.test(url) ? url : ("/" + url);
            url = /\/$/.test(baseUrl) ? (baseUrl.substring(1) + url) : baseUrl + url;
        }
        return url;
    }

    /**
     * 设置给定 entry 的缩略图
     *
     * @param entry
     * @param image url / file
     */
    function setEntryImage(entry, image) {
        var $entry = $(entry),
            wrap = $entry.find('.upload-thumb')[0],
            nativeSource, img, url;

        if ('function' == (typeof image.getSource)) {
            nativeSource = image.getSource();
        }

        function loadImageFailure() {
            $(wrap).html('<span style="color:#c73e3e;">生成缩略图失败</span>');
        }

        $(wrap).empty();
        // 如果是历史上传
        if (!nativeSource) {
            img = new Image();
            img.onload = function () {
                $(wrap).empty().append(img);
            };
            img.onerror = loadImageFailure;
            url = ('string' == typeof image) ? image : image.url;
            img.src = url;
        } else {
            img = new o.Image;
            img.bind('load', function () {
                var size = o.getSize(wrap);
                if (!nativeSource.rendered) { // 如果没有渲染服务端图片才渲染
                    img.embed(wrap, {width: size.w, height: size.h, crop: false});
                }
            });
            img.bind('error', loadImageFailure);
            img.load(image.getSource());
        }
    }

    /**
     * 文件数量控制 filter
     * 配置样例: { filter: { max_file_count: 5, mime_types: ... } }
     */
    plupload.addFileFilter('max_file_count', function (maxCount, file, cb) {
        var up = this,
            queueSize = up.files.length + (up._files || []).length;

        if (maxCount > 1) {  // allowed count > 1
            // 当前队列数量未满则添加
            cb(queueSize < maxCount);

            // 如果当前队列添加完当前元素刚满
            if (maxCount == queueSize + 1) {
                up.disableBrowse(true);
                o.each(up.getOption('browse_button'), function (el) {
                    el.disabled = true;
                    o.addClass(el, 'disabled');
                });
            }
        } else {
            // 只有一个文件时清空后添加
            up.splice();
            $(up.getOption('list')).empty();

            cb(true);   // allow added
        }
    });

    function renderTpl(tpl, data) {
        for (var p in data) {
            if (data.hasOwnProperty(p)) {
                tpl = tpl.replace(new RegExp('{' + p + '}', 'g'), data[p]);
            }
        }
        return tpl;
    }

    /**
     * 格式化时间
     *
     * @param seconds
     * @returns {string}
     */
    function formatTime(seconds) {
        var h = '00', m = '00', seconds = seconds || 0;

        if (seconds >= 60 * 60) {
            h = Math.round(seconds / 60 / 60);
            h = h > 9 ? h : '0' + h;
            seconds = seconds % (60 * 60);
        }

        if (seconds >= 60) {
            m = Math.round(seconds / 60);
            m = m > 9 ? m : '0' + m;
            seconds = seconds % 60;
        }

        seconds = seconds > 9 ? seconds : '0' + seconds;
        return h + ':' + m + ':' + seconds;
    }

    // ///////////////////////////////////////////////////
    //                     结束
    // //////////////////////////////////////////////////

    /**
     * full mode:
     * <button data-target="file-list:t:3*500m:fname@img-mgt/upload:url:1" data-suffix="jpg,png" data-drop-target="drop-zone">Upload<button>
     * <button data-target=".:l:3*500m:fname@img-mgt/upload:url:1" data-suffix="jpg,png" data-drop-target="drop-zone">Upload<button>
     * simple:
     * <li data-target=".::1x30m:file::" data-suffix="zip"></li>
     * <li data-target=".::1x30m:::" data-suffix="zip"></li>
     * <p />
     * data-target: container:mode:countxsize:uploadName@url:valueField:auto
     *
     * @param node
     * @returns {Uploader}
     */
    Uploader.embed = function (node) {
        var CONTEXT_PATH = (window.N || window)['contextPath'] || '',
            IMG_UPLOAD_URL = CONTEXT_PATH + '/storage/images/ul',
            FILE_UPLOAD_URL = CONTEXT_PATH + '/storage/files/ul',
            name = node.getAttribute('name'),
            opts = node.getAttribute('data-target'),
            dropTarget = node.getAttribute('data-drop-target'),
            extensions = node.getAttribute('data-suffix') || '*',
            config = {},
            tmp;

        // fix 如果存在空白过滤无效
        extensions = extensions.replace(' ', '');

        if (node.uploader && 'function' === typeof node.uploader.destroy) {
            name = name || node.uploader.getOption('name');
            node.uploader.destroy();
        }

        opts = opts ? opts.split(':') : [];
        if (6 != opts.length) {
            throw new Error('Invalid config, data-target format: "[containerId]:[mode(' + MODE_LIST + '/' + MODE_THUMB + ')]:[[countx]size]:[upload_file_data_name][@url]:[valueField]:[auto]"');
        }

        opts[0] && (config['list'] = opts[0]);
        opts[1] && (config['mode'] = opts[1]);

        if (opts[2]) {
            tmp = opts[2].split('x').reverse();
            tmp[0] && (config['max_file_size'] = tmp[0]);
            config['max_file_count'] = tmp[1] ? tmp[1] : 1;
        }

        config['file_data_name'] = 'file';
        if (opts[3]) {
            tmp = opts[3].split('@');
            tmp[0] && (config['file_data_name'] = tmp[0]);
            tmp[1] && (config['url'] = tmp[1]);
        }
        tmp = config['file_data_name'];
        config['url'] = ('img' == tmp ? IMG_UPLOAD_URL : ('file' == tmp ? FILE_UPLOAD_URL : config['url']));

        opts[4] && (config['value'] = opts[4]);
        config['auto'] = !('0' == opts[5] || 'false' == opts[5]);

        if (config['list']) {
            config['list'] = ('.' == config['list'] ? node : ('..' == config['list'] ? node.parentNode : config['list']));
        }

        if (dropTarget) {
            dropTarget = ('.' == dropTarget ? node : ('..' == dropTarget ? node.parentNode : dropTarget));
        }

        o.extend(config, {
            browse_button: node,
            name: name,
            drop_element: dropTarget,
            filters: {
                mime_types: [
                    {title: 'Allowed Files', extensions: extensions}
                ]
            }
        });

        node.removeAttribute('name');
        return new Uploader(config);
    };

    $(function () {
        $('.J-plupload').each(function (i, el) {
            Uploader.embed(el);
        });
    });

// -------------------------

    return Uploader;
}));
