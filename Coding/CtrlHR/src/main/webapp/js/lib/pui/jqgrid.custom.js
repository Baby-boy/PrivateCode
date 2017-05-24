/**
 * jqGrid 组件定制
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery','jqgrid'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    'use strict';

    var P = $.extend(true, P, {
        Messager: {
            notify: function (type, message, time) {
                var NOTIFY_STY = '<style id="notify-style" type="text/css">' +
                    '.notify-tip-box { z-index: 2299; position: absolute; width: 100%; padding-top: 2px; height: 24px; top: 43px; text-align: center; }' +
                    '.msg { padding: 3px 24px 3px; color: #fff; height: 20px; line-height: 18px; }' +
                    '.msg { background: #68af02; z-index: 99; }' +
                    '.msg.warn { background: #ef8f00; z-index: 99; }' +
                    '.msg.error { background: #ce4844; z-index: 99; }' +
                    '</style>';

                var $style = $('#notify-style');
                if (1 > $style.length) {
                    $style = $(NOTIFY_STY).prependTo(document.body);
                }

                $('.notify-tip-box').each(function (i, item) {
                    var $tip = $(item), offset = $tip.offset();
                    $tip.offset({top: offset.top - $tip.height() - 1, left: offset.left});
                });

                message = 1 == arguments.length ? type : message;
                time = null != time ? time : 3000;

                var cls = 'msg ' + (type || ''),
                    $notify = $('<div class="notify-tip-box">')
                        .append('<span class="' + cls + '">' + message + '<span>')
                        .appendTo(document.body);

                setTimeout(function () {
                    $notify.fadeOut(function () {
                        $notify.remove();
                    });
                }, time);
                return $notify;
            }
        }
    });

    /**
     * jqGrid column model template 扩展
     * {
     *    colModel: [
     *      { name: 'xxx', index:'xxx', template: 'actions2' }
     *    ]
     * }
     *
     * {@see grid.base.js#jqGrid}
     */
    $.jgrid = $.jgrid || {};
    $.jgrid.cmTemplate = $.extend($.jgrid.cmTemplate, {
        // 操作模版
        actions2: {
            sortable: false,
            resizable: false,
            fixed: true,
            search: false,
            title: false,
            formatter: 'actions2',
            align: 'center',
            width: 90,
            frozen: true
        },
        // 图片模版
        img: {sortable: false, search: false, formatter: 'img', align: 'left', type: 'S'},
        // 布尔模版
        bool: {
            formatter: 'checkbox',
            edittype: 'checkbox',
            editoptions: {value: '1:是;0:否'},
            defaultValue: '0',
            stype: 'select'/*, dataUrl, surl*/,
            searchoptions: {value: ':全部;1:是;0:否'},
            align: 'left',
            type: 'B'
        },
        // enum: { align: 'center', formatter: 'checkbox', editable: true, edittype: 'select', editoptions: { value: 'v1:Text1;v2:Text2', defaultValue: 'v2' } },
        // 数值模板
        num: {
            formatter: 'number',
            align: 'left',
            sorttype: 'number',
            searchoptions: {sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn']},
            type: 'N'
        },
        // 整数模板
        int: {
            formatter: 'integer',
            align: 'left',
            sorttype: 'number',
            searchoptions: {sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn']},
            type: 'I'
        },
        // 文本模板
        text: {
            stype: 'text',
            searchoptions: {sopt: ['cn', 'bn', 'ew', 'en', 'bw', 'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/]},
            align: 'left',
            type: 'S'
        },
        // 超链模板
        link: {
            formatter: 'link',
            formatoptions: {target: '_blank'},
            stype: 'text',
            searchoptions: {sopt: ['cn', 'bn', 'ew', 'en', 'bw', 'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/]},
            align: 'left',
            type: 'S'
        },
        // date2: { formatter: 'date2', type: 'D' },
        // 日期模板
        date: {
            align: 'left',
            formatter: 'date',
            datefmt: 'Y-m-d',
            formatoptions: {srcformat: 'ms' /*, newformat: 'd-m-Y'*/},
            stype: 'custom',
            searchoptions: {
                sopt: ['eq'],
                dopt: {dateFmt: 'yyyy-MM-dd'},
                custom_element: function (defaultValue, options) {
                    var me = this;
                    return $('<input type="text" class="Wdate" style="width: 100%;">')
                        .on('keypress change', function (e) {
                            var key = e.charCode || e.keyCode || 0;
                            if (key === 13) {
                                me.triggerToolbar();
                                return false;
                            }
                            return this;
                        }).on('focus click', function (e) {
                            window.WdatePicker && WdatePicker($.extend({
                                el: this,
                                dateFmt: 'yyyy-MM-dd',
                                readOnly: true,
                                doubleCalendar: false,
                                onpicked: $.proxy(me.triggerToolbar, me),
                                oncleared: $.proxy(me.triggerToolbar, me)
                                // maxDate:'#F{$dp.$D(\'endDate\')||\'2099-12-31\'}'
                            }, options.dopt));
                        });
                },
                custom_value: function ($el, action, value) {
                    return 'set' == action ? $el.val(value || "") : $el.val();
                }
            },
            type: 'D'
        }
    });

    /**
     * jqGrid formatter 扩展
     * {
     *  colModel: [
     *      { formatter: 'img' }
     *  ]
     * }
     * {@see grid.fmatter.js}
     */
    var DEFAULT_ACTIONS = [{
        text: '编辑',
        cls: 'btn-edit',
        style: 'margin: 0 5px',
        handler: function (evt, record, rid, grid) {
            window.location.href = 'edit?id=' + record.id;
        }
    }, {
        text: '|',
        cls: 'vline'
    }, {
        text: '删除',
        style: 'margin: 0 5px',
        cls: 'btn-del',
        handler: function (evt, record, rid, grid) {
            if (record.predefined) {
                window.alert('预定义项不能删除');
                return;
            }
            function fn() {
                // $(grid).jqGrid('delGridRow', rid, op.delOptions);
                // $grid.jqGrid('delRowData', rid);

                $.ajax({
                    url: 'delete',
                    traditional: true,
                    data: {id: record.id},
                    success: function (data) {
                        if (data.success) {
                            P.Messager.notify('删除成功')
                        } else {
                            P.Messager.notify('error', '删除失败')
                        }
                        $(grid).trigger('reloadGrid');
                    }
                });
            }

            // jqGrid(‘getGridParam’,'selarrrow’)
            /*
             Messager.confirm('警告', '您确定要删除选中的记录吗？', function (r) {
             if (!r) return;
             fn();
             });
             */
            if (window.confirm('您确定要删除选中记录吗？')) {
                fn();
            }
        }
    }];


    $.fn.fmatter = $.extend($.fn.fmatter, {
        // 图片格式化
        img: function (val, opts, record, act) {
            if (!val) return "";

            var op = {w: 100, h: 80};
            if (opts.colModel !== undefined && opts.colModel.formatoptions !== undefined) {
                op = $.extend({}, op, opts.colModel.formatoptions);
            }
            return '<img src="' + (window.contextPath || '' ) + '/' + val + '" width="' + op.w + '" height="' + op.h + '">';
        },
        // 操作格式化
        'actions2': function (value, opt, record, action) {
            var grid = this,
                p = grid.p,
                model = opt.colModel,
                actions = model.actions,
                extraActions = model.extraActions,
                action, cls, i, html = [],
                TS_FN = Object.prototype.toString;

            actions = actions || DEFAULT_ACTIONS;
            extraActions = extraActions || [];

            function toArray(acts) {
                if ('' == acts) {
                    acts = [];
                } else if ('[object Object]' == TS_FN.call(acts)) {
                    acts = [acts];
                } else if ('[object Array]' != TS_FN.call(acts)) {
                    throw new Error('actions not a object or array');
                }
                return acts;
            }

            actions = toArray(actions);
            extraActions = toArray(extraActions);
            actions = actions.slice(0, actions.length);

            for (i = 0; i < extraActions.length; i++) {
                actions.push(extraActions[i]);
            }

            p._rawData = p._rawData || {};
            p._rawData[opt.rowId] = record;

            function bindHandler(action, handler) {
                p.handlers = p.handlers || {};
                if (!p.handlers[action]) {
                    p.handlers[action] = function (event) {
                        if ('function' == typeof handler) {
                            var rid = $(this).closest("tr.jqgrow").attr('id');
                            handler.call(this, event, p._rawData[rid], rid, grid);
                        }
                    };
                    $(grid).on('click', '.grid-actions .' + action, p.handlers[action]);
                }
            }

            // 遍历所有 action
            for (i = 0; i < actions.length; i++) {
                action = actions[i];
                if ('function' == typeof action) {
                    action = {handler: action};
                }

                cls = action.id || 'action-' + i;
                action.text = action.text || 'Action-' + i;
                action.cls = action.cls || '';
                action.style = action.style || '';
                /*
                 if (record.predefined && 'btn-del' == action.cls) {
                 action.style = ';visibility:hidden';
                 }
                 */

                if (action.handler) {
                    html.push('<a class="' + cls + ' ' + action.cls + '" href="javascript:;" style="' + action.style + '" title="' + action.text + '">' + action.text + '</a>');
                    bindHandler(cls, action.handler);
                } else {
                    html.push('<span class="' + action.cls + '"' + 'style="' + action.style + '">' + action.text + '</span>');
                }

            }

            return '<div class="grid-actions">' + html.join('') + '</div>';
        }
    });

    // 替换默认的 parseDate, 提供 long --> date
    (function () {
        var _parseDate = $.jgrid.parseDate;
        // $.jgrid.fmatter.date -> $.jgrid.formatter.date.masks 提供的无法处理 ms 和 $.jgrid.parseDate
        $.jgrid.parseDate = function (format, date, newformat, opts) {
            if (/*'ms' == format && */typeof date === 'number') {
                date = new Date(date);
            }
            return _parseDate.call(this, format, date, newformat, opts);
        };
    })();


    /**
     * FilterToolbar 扩展
     * 执行搜索前将搜索转换为特定的格式: search_{field}_{op}_{dataType}
     *
     * field:    字段名
     * op:       FilterToolbar 的字段操作符
     * dataType: 该字段的数据类型
     *
     */
    $.jgrid.extend({
        _: function () {
            var $self = this,
                me = $self[0];

            // 清除自定义的 "search_" 前缀参数
            function clear() {
                var postData = me.p.postData;
                for (var p in postData) {
                    if (/^search_/.test(p) && postData.hasOwnProperty(p)) {
                        delete postData[p];
                    }
                }
            }

            // 在检索之前添加自定义格式过滤参数
            $self.on('jqGridToolbarBeforeSearch', function () {
                var me = this,
                    postData = me.p.postData,
                    filters;

                function getColModel(field) {
                    var cm = me.p.colModel, i;
                    for (i = 0; i < cm.length; i++) {
                        if (cm[i].name === field) {
                            return cm[i];
                        }
                    }
                    return undefined;
                }

                /**
                 * 获取给定字段在 colModel 中定义的 type 属性值
                 *
                 * @param field 字段名
                 * @returns {*} type 属性值, 如果属性值不存在返回 "S", 如果字段不存在返回 undefined
                 */
                function getType(field) {
                    var m = getColModel(field);
                    return m ? m.type || 'S' : undefined;
                }

                // 如果启用操作符规则
                if (postData.filters) {
                    filters = $.extend({rules: []}, $.parseJSON(postData.filters));

                    clear();
                    // 将所有规则转换为自定义格式参数
                    $.each(filters.rules, function (i, rule) {
                        var key = "search_" + rule.field + '_' + rule.op + '_' + getType(rule.field);
                        if (postData[key]) {
                            postData[key] = $.isArray(postData[key]) ? postData[key].push(rule.data) : [postData[key], rule.data];
                        } else {
                            postData[key] = rule.data;
                        }
                    });
                } else {
                    // 如果未启用操作符规则, 直接将参数转换为自定义格式参数
                    filters = {};
                    $.each(postData, function (field) {
                        var m = getColModel(field),
                            type, op, key;
                        // 如果存在该字段
                        if (m) {
                            type = m.type || 'S';
                            op = m.searchoptions && m.searchoptions.sopt ? m.searchoptions.sopt[0] : null;
                            op = op || 'eq';

                            key = 'search_' + field + '_' + op + '_' + type;
                            filters[key] = postData[field];
                        }
                    });
                    clear();
                    $.extend(postData, filters);
                }
            }).on('jqGridToolbarBeforeClear', clear);
            return this;
        }
    });

    /**
     * jqGrid defaults 以及扩展处理
     *
     * {@see }
     */
    $.jgrid.defaults = $.extend($.jgrid.defaults, {
        // 更多参数参考 http://blog.mn886.net/jqGrid/
        mtype: 'post',              // 请求方式
        height: 'auto',
        // colNames: [],
        // colModel: [{name:'显示的字段名', index: '传递到服务器的参数名', width: '宽度', sortable: '是否可排序'}],
        // sortname: '默认排序',
        gridview: true,
        rownumbers: true,
        rowNum: 10,
        rowList: [5, 10, 15, 30, 50, 100],
        viewrecords: true,          // 显示总记录数
        altRows: true,              // 隔行变色
        // pginput: false,
        recordpos: 'right',
        pagerpos: 'center',
        prmNames: {page: 'page', rows: "size", sort: "sort", order: "order"},
        sortname: 'id',         // 默认排序字段
        sortorder: "asc",
        shrinkToFit: true,     // 默认自动充满
        jsonReader: {
            root: 'data',            // 数据根
            page: 'page',            // 当前页码
            total: 'totalPages',     // 总page
            records: 'total',       // 总记录
            repeatitems: false      // 数据不可重复
        },
        /*
         treeReader 好像除了引入 .min 还必须引入 src/grid.treegrid.js
         level, isLeaf 必须
         treeReader: {
         level_field: '',
         parent_id_field: '',
         leaf_field: '',
         expanded_field: ''
         },
         */
        filterbar: true,       // 自定义配置, 控制默认是否现实 filtertoolbar
        searchOperators: false,   // 自定义配置, 是否显示过滤操作符
        respond: false,         // 自定义配置, 是否自适应宽度
        batchDelBtn: undefined,
        onInitGrid: function () {
            var me = this,
                p = me.p,
                $self = $(me);

            /*
            // 可以通过 postData 来处理, 但是没有办法合并到 filters(如果search_开头会被删除掉), 因此放在第一次加载数据前处理
            $(me).on('jqGridBeforeRequest', function() {
                $.extend($(this).p.postData, {
                    // 持久化数据
                });
            });
            */

            // 一些常规化处理
            // 如果有分页条的话在分页条上..
            p.pager && $self.jqGrid('navGrid', p.pager);
            $self.jqGrid('filterToolbar', {
                 searchOperators: p.searchOperators // 是否可以选择操作符
            }).jqGrid('_');

            if (!p.filterbar) {
                $self.each(function (i, item) {
                    item.toggleToolbar();
                });
            }

            // $self.jqGrid('setFrozenColumns'); // 冻结 colModel frozen: true 的列
            // 设置宽度 100% 时, 窗口变化, 实际宽度并不会改变, 这里处理下
            if (p.respond) {
                $(window).on('resize.jqGrid', function () {
                    var $el = p.respond == true ? $self.closest('.ui-jqgrid').parent() : $(p.respond);
                    if ($el.length > 0) {
                        $self.jqGrid('setGridWidth', 0);
                        $self.jqGrid('setGridWidth', $el.width());
                    }
                });
                $(window).triggerHandler('resize.jqGrid');
            }

            // update yangchanghe 2015-10-26
            var $delBtn = $(p.batchDelBtn);
            if (0 < $delBtn.length) {
                $delBtn.click(function () {
                    var $grid = $self,
                        keys = $grid.jqGrid('getGridParam', 'selarrrow');

                    function callback() {
                        //  Glanway.Messager.notify('', '处理中...', 9999999);
                        $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: {id: keys}
                        }).done(function (data) {
                            var removed;
                            if (data.success) {
                                removed = data.success || [];
                                $grid.trigger("reloadGrid");
                                P.Messager.notify('操作成功');
                            }
                        }).fail(function () {
                            P.Messager.notify('error', '操作失败');
                        });
                    }

                    1 > keys.length
                        ? P.Messager.notify('warn', '您至少应该选择一行记录')
                        : /*Glanway.Messager.confirm("警告", "您确定要删除选择的" + keys.length + "行记录吗？", function (r) {
                     r && callback();
                     })*/ (window.confirm('您确定要删除选中记录吗？') && callback());
                });
            }
        },
        loadComplete: function () {
            var table = this;
            setTimeout(function () {
                updatePagerIcons(table);
            }, 0);

        }
    });

    function updatePagerIcons(table) {
        // 替换 CSS 样式
        /*
         var replacement = {
         'ui-icon-seek-first' : 'icon icon-angle-double-left',
         'ui-icon-seek-prev' : 'icon icon-angle-left',
         'ui-icon-seek-next' : 'icon icon-angle-right',
         'ui-icon-seek-end' : 'icon icon-angle-double-right'
         };
         $('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon').each(function(){
         var icon = $(this);
         var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

         if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
         })
         */
    }

    /**
     * {@see grid.formedit.js#navGrid}
     */
    $.jgrid.nav = $.extend($.jgrid.nav, {
        edit: false,
        editicon: 'ui-icon-pencil',
        add: false,
        addicon: 'ui-icon-plus',
        del: false,
        delicon: 'ui-icon-trash',
        search: false,
        searchicon: 'ui-icon-search',
        refresh: true,
        refreshicon: 'ui-icon-refresh',
        view: false,
        viewicon: 'ui-icon-document',
        refreshstate: 'firstpage',
        position: "left",
        beforeRefresh: null,
        afterRefresh: null,
        cloneToTop: false
    });
}));
