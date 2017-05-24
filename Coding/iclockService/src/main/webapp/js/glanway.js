// begin below line code annotated by andy
(function (factory) {
    // 如果是 amd 规范， 则加载 jQuery 后执行
    if (typeof define === 'function' && define.amd) {
        require(['jquery'], factory)
    } else {
        // 否则直接执行
        factory();
    }
})(function () {
//设置iframe高度,
    $(document).ready(function () {
        var main = $(window.parent.document).find("#yzid");//找到iframe对象
        var thisheight;
        if ($(".listPage").length > 0) {//列表页面
            thisheight = $(".listPage").height() + 100;//获取页面高度
        } else {//编辑页面
            thisheight = $("form").height() + 145;//获取页面高度
        }
        parent.setFrameHeight && parent.setFrameHeight(thisheight);
    });
// end with above line code annotated by andy 

//弹出框显示隐藏
    var CMS_Pop = {
        show: function (str) {
            $(".pop-mask").show();
            $(".cms-pop").show();
            $("." + str).show();
        },
        hide: function (str) {
            $("." + str).hide();
            $(".pop-mask").hide();
        }
    };

// 对话框
    (function ($, window) {
        window.Glanway = window.Glanway || {};
        window.Glanway.Messager = {
            alert: function (title, msg) {
                var msg = $.isArray(msg) ? msg : [msg],
                    $title = $('<div>').addClass('cms-pop-title').append($('<b>').html(title)),
                    $close = $('<a>').addClass('icon-close').attr('href', 'javascript:;').click(function () {
                        $mask.hide();
                        $dialog.hide().remove();
                    }),
                    $btns = $('<div>').addClass('cms-pop-btns'),
                    $content = $('<div>').addClass('cms-pop-content')
                        // .css({ margin: 'auto auto 40px' })
                        .css({margin: 'auto auto 20px'})
                        .append($('<s class="icon-edit-alert"></s>'))
                        .append($('<h5>').html(msg[0]))
                        .append($('<p>').html(msg[1])),
                    $body = $('<div>').addClass('cms-pop-body')
                        // .css({padding: '60px 110px'})
                        .css({padding: '25px 65px'})
                        .append($content)
                        .append($btns),
                    $dialog = $('<div>').addClass('cms-pop')
                        .css({
                            width: 'auto',
                            height: 'auto',
                            display: 'none'
                        })
                        .append($('<div>').addClass('cms-pop-t').append($title).append($close))
                        .append($body),
                    $mask = $('.pop-mask');


                $('<span>').addClass('btn-sure-wrap').append($('<button>').addClass('btn-sure btn').html('确定').click(function () {
                    $mask.hide();
                    $dialog.hide().remove();
                })).appendTo($btns);

                $dialog.appendTo(document.body);
                if ($mask.length < 1) {
                    $mask = $('<div class="pop-mask">').appendTo(document.body);
                }

                $mask.show();
                $dialog.show();
            },

            confirm: function (title, msg, callback) {
                var msg = $.isArray(msg) ? msg : [msg],
                    $title = $('<div>').addClass('cms-pop-title').append($('<b>').html(title)),
                    $close = $('<a>').addClass('icon-close').attr('href', 'javascript:;').click(function () {
                        $.isFunction(callback) && callback(false);
                        $mask.hide();
                        $dialog.hide().remove();
                    }),
                    $btns = $('<div>').addClass('cms-pop-btns'),
                    $content = $('<div>').addClass('cms-pop-content')
                        // .css({ margin: 'auto auto 40px' })
                        .css({margin: 'auto auto 20px'})
                        .append($('<s class="icon-edit-alert"></s>'))
                        .append($('<h5>').html(msg[0]))
                        .append($('<p>').html(msg[1])),
                    $body = $('<div>').addClass('cms-pop-body')
                        // .css({padding: '60px 110px'})
                        .css({padding: '25px 65px'})
                        .append($content)
                        .append($btns),
                    $dialog = $('<div>').addClass('cms-pop')
                        .css({
                            width: 'auto',
                            height: 'auto',
                            display: 'none'
                        })
                        .append($('<div>').addClass('cms-pop-t').append($title).append($close))
                        .append($body),
                    $mask = $('.pop-mask');


                $('<span>').addClass('btn-sure-wrap').append($('<button>').addClass('btn-sure btn').html('确定').click(function () {
                    $.isFunction(callback) && callback(true);
                    $mask.hide();
                    $dialog.hide().remove();
                })).appendTo($btns);

                $('<span>').addClass('btn-cancel-wrap').append($('<button>').addClass('btn btn-cancel').html('取消').click(function () {
                    $.isFunction(callback) && callback(false);
                    $mask.hide();
                    $dialog.hide().remove();
                })).appendTo($btns);

                $dialog.appendTo(document.body);
                if ($mask.length < 1) {
                    $mask = $('<div class="pop-mask">').appendTo(document.body);
                }

                $mask.show();
                $dialog.show();
            }
        };

    })(jQuery, window);
//
    (function ($) {
    })(jQuery);

    var util = util || {};
    $.extend(util, {
        formatDate: function (date) {
            if (!date) {
                return '';
            }

            if (typeof date === 'number') {
                date = new Date(date);
            }

            var y = date.getFullYear(),
                m = date.getMonth() + 1,
                d = date.getDate(),
                value = y + '-' + (m < 10 ? '0' + m : m) + '-' + (d < 10 ? '0' + d : d);
            return value;
        },

        parseDate: function (value) {
            if (!value) return null;
            if (typeof value === 'string') {
                var s = value.split('-'),
                    y = parseInt(s[0], 10),
                    m = parseInt(s[1], 10),
                    d = parseInt(s[2], 10);
                if (!isNaN(y) && !isNaN(m) && !isNaN(d)) {
                    return new Date(y, m - 1, d);
                }
            }
            value = +value;
            if (!isNaN(value)) {
                return new Date(value);
            }
            return null;
        },

        /**
         * 解析后使用当前 formatter 格式化
         */
        fmtDate: function (v) {
            v = util.parseDate(v);
            return !v ? '' : $.fn.datebox.defaults.formatter(v);
        },

        fmtBoolean: function (v) {
            return v === null || v === undefined || v === '' ? v : (v === false ? 'N' : 'Y');
        }
    });

    $(function () {
        var url = window.location.href;
        var arr = url.split("/");
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == "admin") {
                var action = arr[i + 1];
                $('#' + action).addClass("on");
                //$('#'+action).attr("class","on");
            }
        }
    });

    /* utility js */
    $(function () {
        // 表格列删除
        $(document).off('click', 'tr .js-del-row').on('click', 'tr .js-del-row', function (e) {
            var $self = $(this);
            Glanway.Messager.confirm('提示', '您确定删除这行记录吗？', function (r) {
                r && $self.closest('tr').remove();
            });
        });

        // 表格 - 列统计
        $('tr .js-sum').each(function (i, el) {
            var me = this,
                $self = $(me),
                selector = $self.data('selector');

            if (!selector) return;

            $(document).off('blur', selector).on('blur', selector, function (e) {
                var count = 0;
                $(selector).each(function (i, el) {
                    var $el = $(el),
                        tag = el.tagName.toLowerCase(),
                        val = '';

                    if ('input' == tag || 'textarea' == tag || 'select' == tag) {
                        val = $el.val();
                    } else {
                        val = $el.text();
                    }
                    count += Number(val) || 0;
                });
                $self.text(count);
            });
        });

    });

    (function ($, exports) {
        var Glanway = exports.Glanway = exports.Glanway || {};
        Glanway.Dialog = Glanway.Dialog || {};

        var ctx = exports.contextPath || '',
            PCAT_URL = ctx + '/admin/category/tree2',
            PCAT_DEFAULTS = {
                onClickRow: $.noop
            },
            PMODEL_URL = ctx + '/admin/category/models',
            PMODEL_DEFAULTS = {
                onClickRow: $.noop
            };

        $.extend(true, Glanway.Dialog, {
            showPcat: function (opts) {
                $.isFunction(opts) && (opts = {onClickRow: opts});
                var opts = $.extend(true, PCAT_DEFAULTS, opts),
                    $catWrap = $('#cat-grid-wrap'),
                    $catGrid = $('<table id="cat-grid">'),
                    $catPagination = $('<div id="cat-grid-pagination">'),
                    layerId = null;

                $catWrap.length < 1 && ($catWrap = $('<div id="cat-grid-wrap">'));
                $catWrap.hide().appendTo(document.body);
                $catWrap.empty().append($catGrid).append($catPagination);

                $catGrid.jqGrid({
                    url: PCAT_URL,
                    datatype: 'json',
                    colNames: ['id', '分类', 'mid', '模型', '类型'],
                    colModel: [
                        {name: 'id', index: 'id', hidden: true, key: true},
                        {name: 'name', index: 'name', search: true},
                        {name: 'model.id', index: 'model.id', hidden: true},
                        {name: 'model.name', index: 'model.name'},
                        {
                            name: 'type',
                            index: 'type',
                            template: 'bool',
                            search: false,
                            formatter: function (val, opts, rwd, act) {
                                return 'SYSTEM' == val ? '系统分类' : '商家分类'
                            }
                        }
                    ],
                    //
                    treeGrid: true,
                    treeGridModel: 'adjacency',
                    ExpandColumn: "name",
                    rownumbers: false,
                    viewrecords: false,
                    treeReader: {
                        level_field: "level",
                        parent_id_field: "parent_id",
                        leaf_field: "isLeaf",
                        expanded_field: "expanded"
                    },
                    multiselect: true,
                    autowidth: true,
                    height: 'auto',
                    pager: '#' + $catPagination.attr('id'),
                    onInitGrid: function () {
                        var me = this,
                            p = me.p,
                            $self = $(me);
                        p.pager && $self.jqGrid('navGrid', p.pager);
                        // 分页有问题
                        // $self.jqGrid('filterToolbar', { searchOperators: false })/*.jqGrid('_')*/;
                    }
                }).bind('jqGridDblClickRow', function (jqEvent, id, row, col, rawEvent) {
                    var $grid = $(this),
                        record = $grid.jqGrid('getRowData', id);

                    layer.close(layerId);
                    opts.onClickRow(id, row, col, record);
                }).trigger('reloadGrid');

                layerId = $.layer({
                    type: 1,
                    title: opts.title || '选择分类',
                    border: [5, 0.5, '#666'],
                    area: ['auto', 'auto'],
                    shadeClose: true,
                    page: {dom: $catWrap[0]}
                });
            },
            showPmodel: function (opts) {
                $.isFunction(opts) && (opts = {onClickRow: opts});
                var opts = $.extend(true, PMODEL_DEFAULTS, opts),
                    $modWrap = $('#mod-grid-wrap'),
                    $modGrid = $('<table id="mod-grid">'),
                    $modPagination = $('<div id="mod-grid-pagination">'),
                    layerId = null;

                // 模型选择
                $modWrap.length < 1 && ($modWrap = $('<div id="mod-grid-wrap">'));
                $modWrap.hide().appendTo(document.body);
                $modWrap.empty().append($modGrid).append($modPagination);

                $modGrid.jqGrid({
                    url: PMODEL_URL,
                    datatype: 'json',
                    colNames: ['id', '模型名称', '是否启用属性', '是否启用参数', '模型类型'],
                    colModel: [
                        {name: 'id', index: 'id', hidden: true, key: true},
                        {name: 'name', index: 'name', template: 'text'},
                        {name: 'useAttribute', index: 'useAttribute', template: 'bool'},
                        {name: 'useParameter', index: 'useParameter', template: 'bool'},
                        {
                            name: 'type',
                            index: 'type',
                            template: 'bool',
                            search: false,
                            formatter: function (val, opts, rwd, act) {
                                return 'SYSTEM' == val ? '系统模型' : '商家模型'
                            }
                        }
                    ],
                    pager: '#' + $modPagination.attr('id'),
                    multiselect: false,
                    autowidth: true,
                    height: 'auto'
                }).bind('jqGridDblClickRow', function (jqEvent, id, row, col, rawEvent) {
                    var $grid = $(this),
                        record = $grid.jqGrid('getRowData', id);

                    layer.close(layerId);
                    opts.onClickRow(id, row, col, record);
                }).trigger('reloadGrid');

                layerId = $.layer({
                    type: 1,
                    title: opts.title || '选择模型',
                    border: [5, 0.5, '#666'],
                    area: ['auto', 'auto'],
                    shadeClose: true,
                    page: {dom: $modWrap[0]}
                });
            }

        });
    })(jQuery, window);
});