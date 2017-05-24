window.CityPicker = (function ($) {
    var TAB_ITEM_TPL = '<li data-weight="tab-item" style="display: none;"><a href="javascript:;"><em></em><i></i></a></li>',
        CONTENT_TPL = '<div class="mc" data-widget="tab-content"></div>',
        TPL = '<div >'
            + '  <div class="text" style="min-width: 70px;text-align: center;"><div></div><b></b></div>'
            + '  <div class="content">'
            + '    <div data-widget="tabs" class="tabs">'
            + '      <div class="mt">'
            + '        <ul class="tab"></ul>'
            + '        <div class="stock-line"></div>'
            + '      </div>'
            + '    </div>'
            + '  </div>'
            + '  <div class="close"><div>'
            + '</div>',
        PLACEHOLDER = '请选择',
        locate = function (path) {
            var root = loc, data = [];

            path = path || [];
            for (var i = 0; i < path.length; i++) {
                root = root[path[i].value]
            }

            for (var key in root) {
                if ('n' !== key && root.hasOwnProperty(key)) {
                    data.push({ id: key, name: root[key].n });
                }
            }
            return data;
        },
        DEFAULTS = {
            defPath: [],    //
            path: [],
            nodeConfig: [
                { data: locate },
                { data: locate },
                {data: locate }
            ]
        },
        COOKIE_NAME = 'ctyph';

    function buildList(result) {
        var html = ["<ul class='area-list'>"];
        var longhtml = [];
        var longerhtml = [];
        if (result && result.length > 0) {
            for (var i = 0, j = result.length; i < j; i++) {
                result[i].name = result[i].name.replace(" ", "");
                if (result[i].name.length > 12) {
                    longerhtml.push("<li class='longer-area'><a href='#none' data-value='" + result[i].id + "'>" + result[i].name + "</a></li>");
                }
                else if (result[i].name.length > 5) {
                    longhtml.push("<li class='long-area'><a href='#none' data-value='" + result[i].id + "'>" + result[i].name + "</a></li>");
                }
                else {
                    html.push("<li><a href='#none' data-value='" + result[i].id + "'>" + result[i].name + "</a></li>");
                }
            }
        }
        html.push(longhtml.join(""));
        html.push(longerhtml.join(""));
        html.push("</ul>");
        return html.join("");
    }

    function CityPicker(config) {
        var me = this;

        if (!(me instanceof CityPicker)) {
            return new CityPicker(config);
        }
        config = $.extend({}, DEFAULTS, config);

        me.$dom = $(config.dom);
        me.defPath = config.defPath || [];
        me.path = config.path || [];
        me.cache = null != config.cache ? config.cache : true;
        me.handler = config.handler || $.noop;
        me.config = config.nodeConfig;

        if (!config.nodeConfig|| config.nodeConfig.length < 1) {
            throw new Error('illegal state config.nodeConfig');
        }

        me.$dom.unbind('mouseover').bind('mouseover', function () {
            me.$dom.addClass('hover');
        });

        var $view = $(TPL),
            $tabContainer = $view.find('.tab'),
            $contentContainer = $view.find('.tabs'),
            i;

        $view.find('.text div').html(PLACEHOLDER);
        $view.find('.close').click(function () {
            me.$dom.removeClass('hover');
        });

        for (i = 0; i < me.config.length; i++) {
            // tab
            $(TAB_ITEM_TPL).attr('data-index', i)
                .appendTo($tabContainer)
                .find('em')
                .html(PLACEHOLDER);

            // content
            $(CONTENT_TPL).attr('data-index', i).appendTo($contentContainer);
        }

        $view.children().appendTo(me.$dom);

        me._$tabs = $tabContainer.find('li');
        me._$contents = $contentContainer.find('[data-widget=tab-content]');
        me._cachedData = {};

        me._init();
    }

    $.extend(CityPicker.prototype, {
        _init: function () {
            var me = this,
                $tabs = me._$tabs,
                $contents = me._$contents;

            // tab 切换
            $tabs.first()
                .addClass('curr')
                .show()
                .end()
                .find("a")
                .click(function () {
                    var $li = $(this).closest('li'),
                        index = $li.index();

                    $contents.eq(index)
                        .siblings('[data-widget=tab-content]')
                        .hide()
                        .end()
                        .show();

                    $tabs.removeClass("curr")
                        .eq(index)
                        .addClass("curr");
                });

            // 初始化第一个容器
            // 如果没有指定从 cookie 中获取
            if (!me.path || me.path.length < 1) {
                var path = me._gc(COOKIE_NAME);
                path && (me.path = path.split('-'));
            }

            if (!me.path || me.path.length < 1) {
                me.path = me.defPath;
            }

            // 路径处理
            for (i = 0; i < me.path.length; i++) {
                var type = typeof me.path[i];
                if ('string' === type || 'number' === type) {
                    me.path[i] = {value: me.path[i]};
                }
            }
            me.initContent(0);
        },
        // 初始化内容
        initContent: function (index) {
            var me = this,
                path = me.path,
                config = me.config,
                $contents = me._$contents,
                opt = config[index],
                data = opt.data,
                url = opt.url;

            $contents.eq(index).html("<div class='iloading'>正在加载中，请稍候...</div>");

            function cb(data) {
                $contents.eq(index)
                    .html(buildList(data))
                    .find('a')
                    .click(function () {
                        me._itemClick($(this));
                    });

                // 如果路径中存在当前值,应该是初始化,直接选中
                if (index < path.length) {
                    var $dom = $('[data-value="' + path[index].value + '"]');
                    // 存在给定节点
                    if (0 < $dom.length) {
                        // 如果没有 dom 元素
                        path[index].text = $dom.html();
                        path[index].dom = $dom[0];

                        me._doChoose(index, path)
                    } else {
                        // 不存在, 删除后续路径
                        for (var i = path.length - 1; i > index; i--) path.pop();
                    }
                }
            }


            // 如果存在 data 直接使用 data 初始化
            if (data) {
                cb(('function' === typeof data ? data(path) : data) || []);
            } else if (url) {
                function load(path, index, url, cb) {
                    var cache = me.cache,
                        cachedData = me._cachedData,
                        data,
                        pindex = index - 1,
                        key = path[pindex] && path[pindex].value;
                    // 启用缓存, 且缓存存在相关数据
                    if (cache && (data = cachedData[pindex] && cachedData[pindex][key])) {
                        cb(data);
                    } else {
                        url = 'function' === typeof url ? url(path) : url;
                        $.get(url, function (data) {
                            cachedData[pindex] = cachedData[pindex] || {};
                            cachedData[pindex][key] = data;
                            cb(data);
                        });
                    }
                }

                load(path, index, url, function (data) {
                    cb(data);
                });
            } else {
                throw new Error('illegal state: "data" and "url" can not both be empty')
            }
        },
        // 选择当前选择路径中的第几个
        _doChoose: function (index) {
            var me = this,
                path = me.path,
                $tabs = me._$tabs,
                $contents = me._$contents,
                node = me.path[index],
                text = node.text,
                value = node.value;

            // 修改选中的内容
            $tabs.eq(index).find('em').html(text);

            me.onChoose(path.slice(0, index + 1));

            // 最后一个选项
            if (index + 1 == $contents.length) {
                return;
            }

            // 1. 移除选中 tab 的激活状态
            // 2. 将下个 tab 标注为激活状态并显示,修改内容为"请选择"
            // 3. 将下个 tab 之后所有的 tab 隐藏
            $tabs.eq(index)
                .removeClass('curr')
                .next()
                .addClass('curr')
                .show()
                .nextAll()
                .hide()
                .end()
                .find('em')
                .html(PLACEHOLDER);

            // 激活内容切换为下一个
            $contents.eq(index)
                .hide()
                .next()
                .show();

            // 初始化下一个标签页内容
            me.initContent(index + 1, path);
        },
        onChoose: function (path) {
            var me = this,
                $dom = me.$dom,
                $tabs = me._$tabs,
                n = path[path.length - 1],
                handler = me.handler;
            if (path.length == $tabs.length) {
                $dom.removeClass('hover');
                var t = $.map(path, function (n) {
                        return n.text;
                    }).join(''),
                    v = $.map(path, function (n) {
                        return n.value;
                    }).join('-');

                $dom.find('.text div').html(t);

                me._sc(COOKIE_NAME, v, 3 * 30 * 24 * 60 * 60 * 1000);

                if (handler) {
                    handler.apply(this, [path]);
                }
            }
        },
        _itemClick: function ($el) {
            var me = this,
                $dom = me.$dom,
                path = me.path,
                $li = $el.closest('[data-widget=tab-content]'),
                index = $li.index('[data-widget=tab-content]');

            $dom.unbind("mouseout");

            path = path || [];
            // 清空索引之后的元素
            for (var i = path.length - 1; i > index; i--) {
                path.pop();
            }
            path[index] = {
                text: $el.html(),
                value: $el.attr('data-value'),
                dom: $el[0]
            };

            me._doChoose(index);
        },
        _gc: function (name, def) {
            var ret = document.cookie.match(new RegExp('(?:^| )' + name + '(?:(?:=([^;]*))|;|$)'));
            return ret != null ? decodeURIComponent(ret[1]) : def;
        },
        _sc: function (name, value) {
            var args = arguments,
                len = arguments.length,
                expires = (len > 2) ? args[2] : null,
                path = (len > 3) ? args[3] : '/',
                domain = (len > 4) ? args[4] : null,
                secure = (len > 5) ? args[5] : false;

            if ('number' === typeof expires) {
                var date = new Date();
                date.setTime(date.getTime() + expires);
                expires = date;
            }

            document.cookie = name + "=" + encodeURIComponent(value) + ((expires === null) ? "" : ("; expires=" + expires.toGMTString())) + ((path === null) ? "" : ("; path=" + path)) + ((domain === null) ? "" : ("; domain=" + domain)) + ((secure === true) ? "; secure" : "");
        }
    });
    return CityPicker;
})(jQuery);

