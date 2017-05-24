(function ($) {
    'use strict';

    var P = $.extend(true, P, {
        Messager: {
            notify: function (type, message, time) {
                var NOTIFY_STY = '<style id="notify-style" type="text/css">' +
                    '.notify-tip-box { font-size:12px; z-index: 2299; position: absolute; width: 100%; padding-top: 2px; height: 24px; top: 110px; text-align: center; }' +
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
     * jqGrid column model template
     * {
     *    colModel: [
     *      { name: 'xxx', index:'xxx', template: 'actions2' }
     *    ]
     * }
     *
     * {@see grid.base.js#jqGrid}
     */
    $.jgrid.cmTemplate = $.extend($.jgrid.cmTemplate, {
        leave: { sortable: false, resizable: false, search: false, formatter: 'leave', align: 'center', width: 90 },
        orderz: { sortable: false, resizable: false, search: false, formatter: 'orderz', align: 'center', width: 90 },
        actions2: { sortable: false, resizable: false, search: false, formatter: 'actions2', align: 'center', width: 90 },
        actions3: { sortable: false, resizable: false, search: false, formatter: 'actions3', align: 'center', width: 90 },
        //只有编辑
        actions5: { sortable: false, resizable: false, search: false, formatter: 'actions5', align: 'center', width: 120 },
        actions6: { sortable: false, resizable: false, search: false, formatter: 'actions6', align: 'center', width: 120 },
        actions4: { sortable: false, resizable: false, search: false, formatter: 'actions4', align: 'center', width: 90 },
        actions7: { sortable: false, resizable: false, search: false, formatter: 'actions7', align: 'center', width: 120 },
        actions8: { sortable: false, resizable: false, search: false, formatter: 'actions8', align: 'center', width: 90 },
        actions9: { sortable: false, resizable: false, search: false, formatter: 'actions9', align: 'center', width: 90 },
        actions10: { sortable: false, resizable: false, search: false, formatter: 'actions10', align: 'center', width: 90 },
        actions11: { sortable: false, resizable: false, search: false, formatter: 'actions11', align: 'center', width: 90 },
        actions12: { sortable: false, resizable: false, search: false, formatter: 'actions12', align: 'center', width: 120 },
        actions13: { sortable: false, resizable: false, search: false, formatter: 'actions13', align: 'center'},
        actions14: { sortable: false, resizable: false, search: false, formatter: 'actions14', align: 'center', width: 90 },
        actions15: { sortable: false, resizable: false, search: false, formatter: 'actions15', align: 'center', width: 90 },
        actions16: { sortable: false, resizable: false, search: false, formatter: 'actions16', align: 'center', width: 90 },
        actions17: { sortable: false, resizable: false, search: false, formatter: 'actions17', align: 'center'},
        actions18: { sortable: false, resizable: false, search: false, formatter: 'actions18', align: 'center'},
        actions19: { sortable: false, resizable: false, search: false, formatter: 'actions19', align: 'center', width: 150 },
        img: { sortable: false, search: false, formatter: 'img', align: 'center', type: 'S' },
        img2: { sortable: false, search: false, formatter: 'img2', align: 'center', type: 'S' },
        bool: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '1:是;0:否'}, defaultValue: '0', stype: 'select'/*, dataUrl, surl*/, searchoptions: { value: ':全部;1:是;0:否' }, align: 'center', type: 'B' },
        auditsatus: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '0:待审核;1:审核通过;2:审核未通过'}, defaultValue: '0', stype: 'select'/*, dataUrl, surl*/, searchoptions: { value: ':全部;0:待审核;1:审核通过;2:审核未通过' }, align: 'center', type: 'B' },
        logType: { formatter: 'checkbox', edittype: 'checkbox', editoptions: {value: '1:操作日志;2:登录日志;3:修改日志'}, defaultValue: '1', stype: 'select', searchoptions: { value: ':全部;1:操作日志;2:登录日志;3:修改日志' }, align: 'center', type: 'S' }, 
        // enum: { align: 'center', formatter: 'checkbox', editable: true, edittype: 'select', editoptions: { value: 'v1:Text1;v2:Text2', defaultValue: 'v2' } },
        num: { formatter: 'number', align: 'right', sorttype: 'number', searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn'] }, type: 'N' },
        int: { formatter: 'integer', align: 'right', sorttype: 'number', searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge', 'nu', 'nn'] }, type: 'I' },
        text: { stype: 'text', searchoptions: { sopt: ['cn', 'bw', 'ew', 'en', 'bn', 'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/] }, align: 'left', type: 'S' },
        link: { formatter: 'link', formatoptions: { target: '_blank' }, stype: 'text', searchoptions: { sopt: ['bw', 'bn', 'ew', 'en', 'cn', 'nc', 'nu', 'nn', 'eq', 'ne' /*, 'in', 'ni'*/] }, align: 'left', type: 'S' },
        // date2: { formatter: 'date2', type: 'D' },
        date: {
            align: 'center', formatter: 'date', formatoptions: { srcformat: 'Y-m-d H:i:s' ,newformat:'Y-m-d H:i:s'/*, newformat: 'd-m-Y'*/ },
            searchoptions: {
                sopt: ['eq']
            }, type: 'D'
        }
    });

    /**
     * jqGrid formatter
     * {
     *  colModel: [
     *      { formatter: 'img' }
     *  ]
     * }
     * {@see grid.fmatter.js}
     */
    $.fn.fmatter = $.extend($.fn.fmatter, {
        img: function (val, opts, rwd, act) {
            if (!val) return "";

            var op = { w: 100, h: 80 };
            if (opts.colModel !== undefined && opts.colModel.formatoptions !== undefined) {
                op = $.extend({}, op, opts.colModel.formatoptions);
            }
            val = /^http/.test(val) ? val : (window.contextPath || '') + "/" + val;
            return '<img src="' +  val + '" width="' + op.w + '" height="' + op.h + '">';
        },
        img2: function (val, opts, rwd, act) {
            if (!val) return "";

            var op = { w: 50, h: 50 };
            if (opts.colModel !== undefined && opts.colModel.formatoptions !== undefined) {
                op = $.extend({}, op, opts.colModel.formatoptions);
            }
            val = /^http/.test(val) ? val : (window.contextPath || '') + "/" + val;
            return '<img title="点击放大" class="ad-img" style="cursor: pointer;" src="' +  val + '" width="' + op.w + '" height="' + op.h + '">';
        },
        orderz: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Edit" alt="Edit">' +
                '</div>';
        },
        leave: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/admin/icon-search.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        actions2: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions2: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'del':
                	// debugger;
                	/*$grid.jqGrid('delGridRow', rid, op.delOptions);
                    $grid.jqGrid('delRowData', rid);*/
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data && data.success) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }else if(data && data.lower){
                            	$.gritter.add({
                                    title:'提示',
                                    text:'有下级代理商存在，请先删除下级',
                                    sticky: false,
                                    time: 3000
                                });
                            }else if(data && data.message){
                            	$.gritter.add({
                                    title:'提示',
                                    text:data.message,
                                    sticky: false,
                                    time: 3000
                                });
                            }else{
                            	$.gritter.add({
                                    title:'提示',
                                    text:'没有相关权限！',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                            // TODO ERROR MSG
                        });
                    });
                    break;
                case 'edit':
                    window.location.href = "edit/" + rid;
                    break;
            }
        },
        actions4: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions2.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '</div>';
        },
        rowactions4: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'edit':
                    window.location.href = "edit/" + rid;
                    break;
            }
        },
        actions3: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions3.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions3.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        // 好吧，那个留给你们了, 我再加个吧...
        rowactions3: function(act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id");
            if ('edit' === act) {
                window.location.href = "edit?id=" + rid;
            } else {
                $.fn.fmatter.rowactions2.apply(this, arguments);
            }
        },
        actions5: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
            	'<img onclick="jQuery.fn.fmatter.rowactions5.call(this, \'build\');" src="' + ctx + '/images/icon-edit-alert.png" width="15" height="15" title="创建" alt="创建" />' +
                '<img onclick="jQuery.fn.fmatter.rowactions5.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="编辑" alt="编辑" />' +
                '<img onclick="jQuery.fn.fmatter.rowactions5.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="删除" alt="删除" />' +
                '</div>';
        },
        rowactions5: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
	        if ('build' === act) {
	            window.open("build/" + rid);
	        } else {
	            $.fn.fmatter.rowactions2.apply(this, arguments);
	        }
        },
        actions6: function (value, opts) {
            var ctx = (window.Besture || window)['contextPath'] || '';
            return '<div class="operateBox">' +
            '<img onclick="jQuery.fn.fmatter.rowactions6.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit" />' +
            	'<img onclick="jQuery.fn.fmatter.rowactions6.call(this, \'auth\');" src="' + ctx + '/images/icon-edit-alert.png" width="15" height="15" title="通过认证" alt="通过认证" />' +
                '<img onclick="jQuery.fn.fmatter.rowactions6.call(this, \'dauth\');" src="' + ctx + '/images/icon-edit-alert.png" width="15" height="15" title="驳回" alt="驳回" />' +
                '</div>';
        },
        rowactions6: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
	        if ('auth' === act) {
	            window.location.href = "auth/" + rid;
	        }else if('dauth' === act){
	        	window.location.href = "dauth/" + rid;
	        }else {
	            $.fn.fmatter.rowactions2.apply(this, arguments);
	        }
        },
        actions7: function (value, opts) {
            var ctx = (window.Besture || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions6.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions7: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };
            switch (act) {
            case 'del':
            	// debugger;
            	/*$grid.jqGrid('delGridRow', rid, op.delOptions);
                $grid.jqGrid('delRowData', rid);*/
            	Besture.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                	r && $.ajax({
                        url: 'delete',
                        type: 'post',
                        traditional: true,
                        data: { id: rid }
                    }).done(function (data) {
                        var removed;
                        if (data && data.success) {
                            removed = data.result || [];
                            $grid.trigger("reloadGrid");
                            $.gritter.add({
                                title:'提示',
                                text:'操作成功',
                                sticky: false,
                                time: 3000
                            });
                        }else if(data && data.lower){
                        	$.gritter.add({
                                title:'提示',
                                text:'有下级代理商存在，请先删除下级',
                                sticky: false,
                                time: 3000
                            });
                        }else if(data && data.message){
                        	$.gritter.add({
                                title:'提示',
                                text:data.message,
                                sticky: false,
                                time: 3000
                            });
                        }else{
                        	$.gritter.add({
                                title:'提示',
                                text:'没有相关权限！',
                                sticky: false,
                                time: 3000
                            });
                        }
                    }).fail(function () {
                        // TODO ERROR MSG
                    });
                });
                break;
            }
        },
        actions8: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions8.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions8.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions8: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'del':
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'deleteAdminPageByIds',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
                    break;
                case 'edit':
                	window.location.href = "edit?id=" + rid;
                    break;
            }
        },
        actions9: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions9.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions9.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions9: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'del':
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'deleteAdminUserByIds',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
                    break;
                case 'edit':
                	window.location.href = "edit?id=" + rid;
                    break;
            }
        },
        actions10: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions10.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions10.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions10: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'del':
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'deleteRoleByIds',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
                    break;
                case 'edit':
                	window.location.href = "edit?id=" + rid;
                    break;
            }
        },
        actions11: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions11.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit">' +
                '<img onclick="jQuery.fn.fmatter.rowactions11.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions11: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };

            switch (act) {
                case 'del':
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'deleteModuleByIds',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
                    break;
                case 'edit':
                	window.location.href = "edit?id=" + rid;
                    break;
            }
        },
        actions12: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
            	'<img onclick="jQuery.fn.fmatter.rowactions12.call(this, \'build\');" src="' + ctx + '/images/icon-edit-alert.png" width="15" height="15" title="审核意见" alt="审核意见" />' +
                '<img onclick="jQuery.fn.fmatter.rowactions12.call(this, \'edit\');" src="' + ctx + '/images/icon-edit01.png" width="15" height="15" title="Edit" alt="Edit" />' +
                '<img onclick="jQuery.fn.fmatter.rowactions12.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete" />' +
                '</div>';
        },
        actions13: function (value, opts, record) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            if(record['isWhite'] == '' || record['isWhite'] == 1) {
            	return '<div class="operateBox">' +
                '<a class ="showMember" style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions13.call(this, \'view\');">查看</a>' +
                '</div>';
            } else {
            	return '<div class="operateBox">' +
                '<a class ="showMember" style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions13.call(this, \'view\');">查看</a>' +
                '<a>  |  </a>' +
                '<a class ="addWhite" style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions13.call(this, \'addWhite\');">加入白名单</a>' +
                '</div>';
            }
        },
        actions14: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<input type="button" value="审核" style="width: 60px;height: 20px;border-radius: 6px;background-color: #7aa1c3;color: #fff;" onclick="jQuery.fn.fmatter.rowactions14.call(this, \'approval\');" title="审核" alt="" />' +
                '</div>';
        },
        actions16: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<input type="button" value="审核" disabled="disabled" style="width: 60px;height: 20px;border-radius: 6px;background-color: #c1c1c1;color: #fff;" title="审核" alt="" />' +
                '</div>';
        },
        actions15: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<a class ="showAgent" onclick="jQuery.fn.fmatter.rowactions15.call(this, \'view\');">查看</a>' +
                '</div>';
        },
        
        actions17: function (value, opts) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            return '<div class="operateBox">' +
                '<img onclick="jQuery.fn.fmatter.rowactions17.call(this, \'del\');" src="' + ctx + '/images/icon-delete01.png" width="15" height="15" title="Delete" alt="Delete">' +
                '</div>';
        },
        rowactions17: function (act) {
            var $tr = $(this).closest("tr.jqgrow"),
                rid = $tr.attr("id"),
                $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
                $grid = $("#" + $id),
                op = {
                    extraparam: {}
                };
            switch (act) {
                case 'del':
                	Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'updateWhite',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data.result) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
                    break;
            }
        },
        actions18: function (value, opts, record) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
        	return '<div class="operateBox">' +
            '<a class ="showConsumption" style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions18.call(this, \'view\');">查看</a>' +
            '</div>';
        },
        rowactions18: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
        	if ('view' === act) {
        		var html = '<div class="cms-pop viewConsumption" id="viewConsumption" style="left: 26%;width: 90%;height: 500px;top: 168px;">'+
			        	    '<div class="cms-pop-t">'+
			                '<div class="cms-pop-title">'+
			                '<b>消费习惯</b>'+
			                '</div>'+
			                '<a title="关闭" class="icon-close close-viewConsumption"></a>'+
			                '</div>'+
			                '<div class="cms-pop-body" style="overflow: scroll;height: 460px">'+
			                '<div class="table-module03" >'+
		                    '<div id="main" style="height:350px;padding:10px;width:100%;display: inline-block;"></div>'+
		                    '<table id="ad-cat-grid-consumption"></table>'+
		                    '<div id="pagination"></div>'+
		                    '</div>'+
		                    '</div>'+
		                    '</div>';
        		
        		$('.main').append(html);
        		
        		$('.pop-mask').show();
        		$('#viewConsumption').show();
        		
        		var myChart1;
        		var myOption1 =  {
        			title : {
        			    text: '消费习惯分析',
        			    x:'center',
        			    subtext: '最常去5家店铺的访问次数'
        			},
        		    tooltip : {
        		        trigger: 'axis'
        		    },
        		    toolbox: {
        		        show : false,
        		        feature : {
        		            mark : {show: false},
        		            dataView : {show: false, readOnly: false},
        		            magicType : {
        		                show: true, 
        		                type: ['pie', 'funnel']
        		            },
        		            restore : {show: true},
        		            saveAsImage : {show: true}
        		        }
        		    },
        		    xAxis : [
        		             {
	    		                 type : 'category',
	    		                 data : [],
	    		                 /*axisLabel:{
	    		                	 rotate:45
	    		                 },*/
	    		                 axisLine:{
	    		                     show:true,
	    		                     lineStyle:{
	    		                     	color:'rgb(124, 181, 236)'
	    		                     }
	    		                 }
        		             }
        		    ],
    		        yAxis : [
    		             {
    		                 type : 'value',
    		                 name:'消费次数',
    		                 axisLine:{
    		                     show:true,
    		                     lineStyle:{
    		                     	color:'rgb(124, 181, 236)'
    		                     }
    		                 }
    		             }
    		        ],
        		    calculable : true,
        		    series : [
        		        {
        		        	name:'来访次数',
        		            type:'bar',
        		            data:[],
        		            markPoint : {
        		                data : [
        		                    {type : 'max', name: '最大值'},
        		                    {type : 'min', name: '最小值'}
        		                ],
        		                itemStyle: {
            		        		normal: {
            		          		color:'rgb(124, 181, 236)'
            		        	  	}
            		    		}
        		            },
        		            itemStyle: {
        		        		normal: {
        		          		color:'rgb(124, 181, 236)'
        		        	  	}
        		    		}
        		        }
        		    ]
        		};
        		
        		$.post("listMemberVisitBus",{memberId:rid},function(msg){
        			   if(msg.result == false){
        				   return;
        			   }
        			   	var visitData = msg.memberList;
        			    var visit_xAxis = [];
        		        var visit_datas = [];
        		        var flag = true;
        		        
        		        var mapMain = document.getElementById('main');
        		        myChart1 = echarts.init(mapMain);
        		        
        		        if(visitData.length > 0) {
        		        	for (var i = 0; i < visitData.length; i++) {
        		            	visit_xAxis.push(visitData[i].maxVisits + "(" + visitData[i].business.tradesType + ")");        	
        		            	visit_datas.push(visitData[i].visitsByBus);
        		            }
        		        } else {
        		        	visit_xAxis.push('无数据');
        		        	visit_datas.push('无数据');
        		        }
    		        	
        		        
        		        myOption1.xAxis[0].data=visit_xAxis;
        		        myOption1.series[0].data=visit_datas;
        		        myChart1.setOption(myOption1);
        		});
        		
        		$('#ad-cat-grid-consumption').jqGrid({
                    url: 'listMemberConsumption',
                    datatype: 'json',
                    postData: {memberId : rid},
                    colNames: ['店铺名称', 'id','行业','所在地区','成为会员时间','最近光顾时间','来访次数'],
                    colModel: [
                        { name: 'business.companyName', index: 'B_COMPANY_NAME', template: 'text', align:'center'},
                		{ name: 'business.id', index: 'B_ID', hidden: true, key: true, align:'center'},
                		{ name: 'business.tradesType', index: 'B_TRADES_TYPE', template: 'text', align:'center'},
                		{ name: 'business.busCircle', index: 'B_BUS_CIRCLE', template: 'text', align:'center'},
                		{ name: 'firstLoginTime', index: 'firstLoginTime', template: 'text', align:'center',formatter:function(value){
	            				if(value==null){
		            					return "";
		            				}else{
		                        		var date = new Date(value); 
		                        		var year = date.getFullYear().toString(); 
		                        		var month = (date.getMonth() + 1); 
		                        		var day = date.getDate().toString(); 
		                        		var hour = date.getHours().toString(); 
		                        		var minutes = date.getMinutes().toString(); 
		                        		var seconds = date.getSeconds().toString(); 
		                        		if (month < 10) { 
		                        		    month = "0" + month; 
		                        		} 
		                        		if (day < 10) { 
		                        		    day = "0" + day; 
		                        		}
                                    if (hour < 10) {
                                        hour = "0" + hour;
                                    }
                                    if (minutes < 10) {
                                        minutes = "0" + minutes;
                                    }
                                    if (seconds < 10) {
                                        seconds = "0" + seconds;
                                    }
                                    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
		            				}
		                    	}},
                		{ name: 'lastLogoutTime', index: 'lastLogoutTime', template: 'text', align:'center',formatter:function(value){
	            				if(value==null){
		            					return "";
		            				}else{
		                        		var date = new Date(value); 
		                        		var year = date.getFullYear().toString(); 
		                        		var month = (date.getMonth() + 1); 
		                        		var day = date.getDate().toString(); 
		                        		var hour = date.getHours().toString(); 
		                        		var minutes = date.getMinutes().toString(); 
		                        		var seconds = date.getSeconds().toString(); 
		                        		if (month < 10) { 
		                        		    month = "0" + month; 
		                        		} 
		                        		if (day < 10) { 
		                        		    day = "0" + day; 
		                        		}
                                    if (hour < 10) {
                                        hour = "0" + hour;
                                    }
                                    if (minutes < 10) {
                                        minutes = "0" + minutes;
                                    }
                                    if (seconds < 10) {
                                        seconds = "0" + seconds;
                                    }
                                    return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
		            				}
		                    	}},
                		{ name: 'visitsByBus', index: 'visitsByBus', align:'center', template: 'text'}
                    ],
                    multiselect: false,
                    autowidth: true,
                    height: 'auto',
                    pager: '#pagination'
        		}).trigger("reloadGrid");
        		$('#ad-cat-grid-consumption').setGridWidth($('#viewConsumption').width()-30);
        	}
        },
        
        actions19: function (value, opts,record) {
            var ctx = (window.Glanway || window)['contextPath'] || '';
            if(record['status'] == '' || record['status'] == 0) {
            	return '<div class="operateBox">' +
                '<a style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions19.call(this, \'on\');">启用</a>' +
                '<a>  |  </a>' +
                '<a style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions19.call(this, \'del\');">删除</a>' +
                '</div>';
            } else {
            	return '<div class="operateBox">' +
                '<a style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions19.call(this, \'off\');">关闭</a>' +
                '<a>  |  </a>' +
                '<a style="cursor:pointer" onclick="jQuery.fn.fmatter.rowactions19.call(this, \'del\');">删除</a>' +
                '</div>';
            }
        },
        rowactions19: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id"),
            status = $tr.attr("status"),
            $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
            $grid = $("#" + $id),
            op = {
                extraparam: {}
            };
        switch (act) {
            case 'del':
            
            		Glanway.Messager.confirm('警告', '您确定要删除选中的记录吗？',function (r) {
                    	r && $.ajax({
                            url: 'delete',
                            type: 'post',
                            traditional: true,
                            data: { id: rid }
                        }).done(function (data) {
                            var removed;
                            if (data.success) {
                                removed = data.result || [];
                                $grid.trigger("reloadGrid");
                                $.gritter.add({
                                    title:'提示',
                                    text:'操作成功',
                                    sticky: false,
                                    time: 3000
                                });
                            } else {
                            	Glanway.Messager.alert("提示", "该模板已经启用，不能删除");
                            }
                        }).fail(function () {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        });
                    });
            	
                break;
            case 'on': Glanway.Messager.confirm('警告', '您确定要启用该模板？',function (r) {
            	r && $.ajax({
                    url: 'onAndOff',
                    type: 'post',
                    traditional: true,
                    data: { id: rid,status:1 }
                }).done(function (data) {
                    var removed;
                    if (data.success) {
                        removed = data.result || [];
                        $grid.trigger("reloadGrid");
                        $.gritter.add({
                            title:'提示',
                            text:'操作成功',
                            sticky: false,
                            time: 3000
                        });
                    } else {
                    	Glanway.Messager.alert("提示", "请关闭其他模板后再开启该模板");
                    }
                }).fail(function () {
                	$.gritter.add({
                        title:'提示',
                        text:'操作失败',
                        sticky: false,
                        time: 3000
                    });
                });
            });
    	
        	break;
            case 'off': Glanway.Messager.confirm('警告', '您确定要关闭该模板？',function (r) {
            	r && $.ajax({
                    url: 'onAndOff',
                    type: 'post',
                    traditional: true,
                    data: { id: rid,status:0 }
                }).done(function (data) {
                    var removed;
                    if (data.success) {
                        removed = data.result || [];
                        $grid.trigger("reloadGrid");
                        $.gritter.add({
                            title:'提示',
                            text:'操作成功',
                            sticky: false,
                            time: 3000
                        });
                    }
                }).fail(function () {
                	$.gritter.add({
                        title:'提示',
                        text:'操作失败',
                        sticky: false,
                        time: 3000
                    });
                });
            });
    	
        	break;
        }
        },
        rowactions12: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
	        if ('build' === act) {
	            window.location.href = "build/" + rid;
	        } else {
	            $.fn.fmatter.rowactions2.apply(this, arguments);
	        }
        },
        rowactions14: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
        	if ('approval' === act) {
            	
        		$.ajax({
             		async: false,
             		type : "POST",
             		url : "agentAudtiByAjax/" + rid,
             		dataType : 'json',
             		success : function(data) {
             			if(data.result == true) {
             				$tr.find('input').attr('disabled', true);
             				$tr.find('input').css('background-color', '#c1c1c1');
             				$tr.find("td[aria-describedby='ad-cat-grid_auditSatus']:eq(1)").attr('title', '审核通过');
             				$tr.find("td[aria-describedby='ad-cat-grid_auditSatus']:eq(1)").text('审核通过');
             			} else {
             				Glanway.Messager.alert("提示", data.msg);
             				$('.pop-mask').show();
             			}
             		}
             	});
        	}
        },
        rowactions15: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id");
            if ('view' === act) {
            } 
        },
        rowactions13: function (act) {
        	var $tr = $(this).closest("tr.jqgrow"),
            rid = $tr.attr("id"),
            $id = $(this).closest("table.ui-jqgrid-btable").attr('id').replace(/_frozen([^_]*)$/, '$1'),
            $grid = $("#" + $id),
            op = {
                extraparam: {}
            };
	        if ('view' === act) {
	        	$.ajax( {    
	        	    url:'findMemberById/' + rid,// 跳转到 action    
	        	    type:'get',    
	        	    cache:false,   
	        	    dataType:'json',    
	        	    success:function(data) {
	        	    	Validate_Pop($('.customNum'), data.member.customerNumber, true);
	        	    	Validate_Pop($('.customName'), data.member.customName, true);
	        	    	if(data.member.gender === 1) {
	        	    		Validate_Pop($('.gender'), '男', true);
	        	    	} else if(data.member.gender === 0) {
	        	    		Validate_Pop($('.gender'), '女', true);
	        	    	} else {
	        	    		Validate_Pop($('.gender'), data.member.gender, true);
	        	    	}
	        	    	
	        	    	Validate_Pop($('.nickName'), data.member.nickName, true);
	        	    	Validate_Pop($('.mobilePhoneNo'), data.member.mobilePhoneNo, true);
	        	    	Validate_Pop($('.address'), data.member.address, true);
	        	    	Validate_Pop($('.detailAddress'), data.member.detailAddress, true);
	        	    	Validate_Pop($('.microSignal'), data.member.microSignal, true);
	        	    	Validate_Pop($('.qqNo'), data.member.qqNo, true);
	        	    	Validate_Pop($('.mobileMAC'), data.member.mobileMAC, true);
	        	    	Validate_Pop($('.phone'), data.member.phone, true);
	        	    	Validate_Pop($('.backupPhone'), data.member.backupPhone, true);
	        	    	if(data.member.isOnline === 1) {
	        	    		Validate_Pop($('.isOnline'), '离线', true);
	        	    	} else if(data.member.isOnline === 0) {
	        	    		Validate_Pop($('.isOnline'), '在线', true);
	        	    	} else {
	        	    		Validate_Pop($('.isOnline'), data.member.isOnline, true);
	        	    	}
	        	    	Validate_Pop($('.lastLogoutTime'), Handle_Date(data.member.lastLogoutTime), true);
	        	    	Validate_Pop($('.firstLoginTime'), Handle_Date(data.member.firstLoginTime), true);
	        	    	Validate_Pop($('.routerName'), data.member.routerName, true);
	        	    	Validate_Pop($('.email'), data.member.email, true);
	        	    	Validate_Pop($('.level'), data.member.level, true);
	        	    	Validate_Pop($('.phoneModel'), data.member.phoneModel, true);
	        	    	Validate_Pop($('.visitsByBus'), data.visitsByBus, true);
	        	    	Validate_Pop($('.mostVisits'), data.mostVisits, true);
	        	    	$('#viewMember').css('height','inherit')
	    	        	$('#viewMember').show();
	    	        	$('.pop-mask').show();
	        	     },    
	        	     error : function() {    
	        	          // view("异常！");    
	        	          alert("异常！");    
	        	     }
	        	});
	        	 
	        } else if('addWhite' === act) {
	        	Glanway.Messager.confirm('警告', '您确定要加入白名单?',function (r) {
                	r && $.ajax({
                        url: 'addWhite/' + rid,
                        type: 'post',
                        traditional: true
                    }).done(function (data) {
                        var removed;
                        if (data.result) {
                            removed = data.result || [];
                            $grid.trigger("reloadGrid");
                            $.gritter.add({
                                title:'提示',
                                text:'操作成功',
                                sticky: false,
                                time: 3000
                            });
                        } else {
                        	$.gritter.add({
                                title:'提示',
                                text:'操作失败',
                                sticky: false,
                                time: 3000
                            });
                        }
                    }).fail(function () {
                    	$.gritter.add({
                            title:'提示',
                            text:'操作失败',
                            sticky: false,
                            time: 3000
                        });
                    });
                });
	        } else {
	            $.fn.fmatter.rowactions2.apply(this, arguments);
	        }
        }
    });

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

    // 执行搜索前将搜索转换为特定的格式
    $.jgrid.extend({
        _: function () {
            var $self = this,
                me = $self[0];

            function clear() {
                var postData = me.p.postData;
                for (var p in postData) {
                    if (/^search_/.test(p) && postData.hasOwnProperty(p)) {
                        delete postData[p];
                    }
                }
            }

            $self.on('jqGridToolbarBeforeSearch', function () {
                var me = this,
                    postData = me.p.postData,
                    filters = $.extend({rules: []}, $.parseJSON(postData.filters));

                function getType(field) {
                    var cm = me.p.colModel, i;
                    for (i = 0; i < cm.length; i++) {
                        if (cm[i].index === field) {
                            return cm[i].type || 'S';
                        }
                    }
                    return 'S';
                }

                clear();
                $.each(filters.rules, function (i, rule) {
                    var key = "search_" + rule.field + '_' + rule.op + '_' + getType(rule.field);
                    if (postData[key]) {
                        postData[key] = $.isArray(postData[key]) ? postData[key].push(rule.data) : [postData[key], rule.data];
                    } else {
                        postData[key] = rule.data;
                    }
                });
            }).on('jqGridToolbarBeforeClear', clear);
            return this;
        }
    });
    /**
     * jqGrid defaults
     *
     */
    $.jgrid.defaults = $.extend($.jgrid.defaults, {
        mtype: 'post',
        // height: 'auto',
        altRows: true,
        gridview: true,
        rownumbers: true,
        /*add by begin chensheng 表格宽度自适应*/
        shrinkToFit:false,
        /*add by end chensheng 表格宽度自适应*/
        rowNum: 10,
        rowList: [5, 10, 15, 30, 50],
        height: 'auto',
        viewrecords: true,
        // pginput: false,
        recordpos: 'right',
        pagerpos: 'center',
        prmNames: { page: 'page', rows: "size", sort: "sort", order: "order" },
        sortname: 'id', // 默认排序字段
        sortorder: "asc",
        jsonReader: {
            root: 'data',            // 数据根
            page: 'page',            // 当前页码
            total: 'totalPages',     // 总page
            records: 'total',       // 总记录
            repeatitems: false      // 数据不可重复
        },
        /*gridComplete*/onInitGrid: function () {
            var me = this,
                p = me.p,
                $self = $(me);
            /*add by begin chensheng 表格宽度自适应*/
            var $listPanel = $(".listPage");
            if($listPanel.length > 0){
                $self.setGridWidth($listPanel.width()-22);
                $(window).resize(function(){
                    $self.setGridWidth($listPanel.width()-22);
                });
            }
            /*add by begin chensheng 表格宽度自适应*/
            
            // 一些常规化处理
            // 如果有分页条的话在分页条上..
            p.pager && $self.jqGrid('navGrid', p.pager);
            $self.jqGrid('filterToolbar', { searchOperators: true }).jqGrid('_');

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
                        : Glanway.Messager.confirm("警告", "您确定要删除选择的" + keys.length + "行记录吗？", function (r) {
                     r && callback();
                     })/* (window.confirm('您确定要删除选中记录吗？') && callback())*/;
                });
            }
        }
    });

    /**
     * {@see grid.formedit.js#navGrid}
     */
    $.jgrid.nav = $.extend($.jgrid.nav, {
        edit: false,
        add: false,
        del: false,
        search: false,
        refresh: true,
        refreshicon: "ui-icon-refresh",
        refreshstate: 'firstpage',
        position: "left",
        beforeRefresh: null,
        afterRefresh: null,
        cloneToTop: false
    });

})(jQuery);