/**
 * Tree-Picker
 * <input type="text" style="visibility:hidden">
 * $(el).treepicker({
 *   multiple: true,
 *   selected: ['0/10', '2/21'],
 *   // rootProp: 'data',
 *   // textProp: 'name',
 *   // valueProp: 'id',
 *   // nodeParam: 'nid',
 *   // rootId: '10000',
 *   // data: function(node, callback) {}
 *   data: 'tree.json'
 * })
 * @author vacoor
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery', 'jstree'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.TreePicker = factory(jQuery);
    }
}(function ($) {
    var SINGLE = '<a class="tree-picker-single" tabindex="-1"><span>请选择</span><b></b></a>',
        MULTL = '<ul class="tree-picker-choices"></ul>',// +
        TEMPLATE = '' +
            '<div class="tree-picker-container tree-picker-container-active">' +
            '   <!-- pl -->' +
            '   <div class="tree-picker-drop">' +
            '      <div class="tree-picker-toolbar">' +
            '         <a href="javascript:;" class="drop-refresh" title="重新加载数据">[刷新]</a>&nbsp;' +
            '         <a href="javascript:;" class="drop-ok" title="确认当前选中">[确定]</a>&nbsp;' +
            '         <a href="javascript:;" class="drop-clear" title="清除当前选中">[清除]</a>' +
            '         <a href="javascript:;" class="close-own">X</a>' +
            '      </div>' +
            '      <div class="tree-view" style="outline: none;"></div>' +
            '      <div class="loader" style="text-align:center;margin-bottom:14px;">加载中...</div>' +
            '   </div>' +
            '</div>',
        MULTI_CLASS = 'tree-picker-container-multi',
        SINGLE_CLASS = 'tree-picker-container-single';

    /**
     * 默认配置
     */
    var DEFAULTS = {
            multiple: false,    // 是否多选
            selected: [],       // 已选择的节点路径列表
            rootProp: '',       // 根树形
            textProp: 'name',   // 文本属性
            valueProp: 'id',    // 值属性
            nodeParam: 'nid',   // 节点参数
            extraParams: {},    // 扩展参数
            rootId: '#',
            data: [],           // 数据: 数组/function(node, callback)/url
            setValue: null      // 设置值回调
        },
        $DOC = $(document);

    var $class = function (definition) {
        var ctor = definition.constructor;
        ctor = ctor === Object ? new Function() : ctor;
        ctor.prototype = definition;
        return ctor
    };

    var TreePicker = $class({
        constructor: function () {
            this.init.apply(this, arguments);
        },
        init: function (placeholder, options) {
            var me = this,
                settings = $.extend({}, DEFAULTS, options),
                multiple = me.multiple = settings.multiple,
                rootProp = settings.rootProp,
                textProp = settings.textProp,
                valueProp = settings.valueProp,
                nodeParam = settings.nodeParam,
                rootId = settings.rootId,
                $dom = $(TEMPLATE),
                $placeholder = me.$placeholer = $(placeholder),
                $container = me.$container = $dom,//.find('.tree-picker-container'),
                $choices = me.$choices = $(multiple ? MULTL : SINGLE).prependTo($container),
                $treeView = me.$treeView = $dom.find('.tree-picker-drop .tree-view').on('ready.jstree', function () {
                    me._initSelected(settings.selected);
                    // }).on('dblclick.jstree', '.jstree-node', function() {
                }).on('click.jstree keydown.jstree', '.jstree-anchor', function (e) {
                    var $tree = $.jstree.reference(this);
                    if (!multiple && ('click' == e.type || 13 == e.keyCode)) {
                        if (!$tree.is_disabled(this)) {
                            me.setValue($tree.get_node(this).id);
                            me.collapse();
                        }
                        return false;
                    }
                }).jstree({
                    core: {
                        multiple: multiple,
                        data: function (node, callback) {
                            var me = this,
                                udfData = settings.data,
                                extra = settings.extraParams;

                            // 获取附加参数
                            if (typeof extra == 'function') {
                                extra = $.extend({}, extra.call(this, node));
                            } else {
                                extra = $.extend({}, extra);
                            }

                            /**
                             * 用户定义数据
                             */
                            if (typeof udfData == 'string') {       // URL
                                extra[nodeParam] = '#' == node.id ? rootId : node.id;
                                $.ajax({
                                    url: udfData,
                                    data: extra
                                }).done(function (data) {
                                    var i, path;
                                    if (rootProp) {
                                        path = rootProp.split('.');
                                        for (i = 0; i < path.length; i++) {
                                            data = data[path[i]];
                                        }
                                    }

                                    $.each(data, function (i, item) {
                                        // item.icon = 'tree';
                                        item.id = item[valueProp] + ''; // '' 是为了防止 00100 这种被转换为 100
                                        item.text = item[textProp];
                                        if (null == item.children) {
                                            item.children = true;
                                        }
                                        // item.state = {selected: true};
                                        // item.parent = node.id;
                                        delete item.parent; // 要么没有, 要么正确
                                    });

                                    callback.call(this, data);
                                });
                            } else if (typeof udfData == 'function') {  // function
                                udfData.call(this, node, callback);
                            } else {    // data
                                callback.call(this, udfData);
                            }
                        }
                    },
                    // 是BUG么? 一旦使用 checkbox, 即便 multiple 为 false, 展开节点时选择父节点,也会选中所有子节点变成多选
                    plugins: multiple ? ['wholerow', 'search', 'checkbox'] : ['wholerow', 'search'],
                    checkbox: {visible: multiple},
                    // checkbox: { visible: multiple, three_state: false }
                    search: {
                        show_only_matches: true,            // 只显示包含匹配元素的
                        show_only_matches_children: true    // 只显示匹配到的子节点
                    }
                }),
                $jstree = me.$jstree = $treeView.jstree(false);

            me._callback = settings.setValue;
            $placeholder.before($dom).hide();
            $dom.css({margin: $placeholder.css('margin')});
            // $container.addClass(multiple ? MULTI_CLASS : SINGLE_CLASS).width($placeholder.width());
            $container.addClass(multiple ? MULTI_CLASS : SINGLE_CLASS).width($placeholder.outerWidth());

            me._hover = false;
            me._monitor = null;

            $container.hover(function () {
                me._hover = true;
            }, function () {
                me._hover = false;
            }).on('click', function () {
                me._hover = true;
            }).on('click', '.choice-close', function () {
                var $choice = $(this).closest('.choice-item'),
                    value = $choice.attr('data-value');
                me.removeValue(value);
            });

            $container.find('.tree-picker-choices,.tree-picker-single').on('click', function (evt) {
                if ($(evt.target).is('.choice-close')) {
                    return;
                }
                me.toggle();
            });

            $container.find('.drop-refresh').on('click', proxy(me.refresh, me));
            $container.find('.drop-ok').on('click', function () {
                me.save();
                me.collapse();
            });
            $container.find('.drop-clear').on('click', proxy(me.clear, me));
            $container.find('.close-own').on('click', proxy(me.collapse, me));

            // S 初始显示内容
            var value = $placeholder.val(),
                text = $placeholder.attr('data-text');
            if (value && text) {
                $choices.empty();
                var html = '';
                if (multiple) {
                    html += '<li class="choice-item" title="' + text + '" data-value="' + value + '">';
                    html += '<span>' + text + '</span>';
                    html += '<a class="choice-close" data-option-array-index="2" style="font-size: 14px;font-weight:bold;text-decoration: none;">&times;</a>' +
                        '</li>';
                } else {
                    html += '<span data-value="' + value + '">' + text + '</span><b class="linked-icon"></b>'
                }
                $choices.append(html);
            }
            // E 初始显示内容

            me.addPlaceholderIfNecessary();
        },
        toggle: function () {
            var me = this,
                $container = me.$container;
            if ($container.hasClass('tree-picker-with-drop')) {
                me.collapse();
            } else {
                me.expand();
            }
        },
        expand: function () {
            var me = this,
                $container = me.$container,
                $treeView = me.$treeView;

            $treeView.focus();
            if (!$container.hasClass('tree-picker-with-drop')) {
                $container.addClass('tree-picker-with-drop');

                me.load();
                me._monitor = me._monitor || function () {
                        (!me._hover) && (setTimeout(proxy(me.collapse, me), 0));
                    };
                setTimeout(function () {
                    $DOC.on('click', me._monitor);
                });
            }
        },
        collapse: function () {
            var me = this;
            me.$container.removeClass('tree-picker-with-drop');
            $DOC.off('click', me._monitor);
        },
        addPlaceholderIfNecessary: function () {
            var me = this,
                $choices = me.$choices,
                multi = me.$jstree.settings.core.multiple;
            if (!me.hasValue()) {
                if (!multi) {
                    $choices.empty().append('<span class="tree-picker-pl">请选择</span><b class="linked-icon"></b>')
                } else {
                    $choices.empty().append('<li class="tree-picker-pl"><span>请选择</span></li>')
                }
            }
        },
        refresh: function () {
            this.$jstree.refresh();
        },
        clear: function () {
            this.clearValue();
            // this.$treeView.focus();
            this.collapse();
        },
        save: function () {
            var me = this,
                $jstree = me.$jstree,
                selected = $jstree.get_selected();
            // selected = selected.splice(0, 5);   // TODO
            me.setValue(selected);
        },
        load: function () {
            var me = this,
                $jstree = me.$jstree;

            $jstree.deselect_all();
            $jstree.uncheck_all && $jstree.uncheck_all();
            $jstree.select_node(me.getValue());
        },
        //
        clearValue: function () {
            var me = this,
                $jstree = me.$jstree,
                $choices = me.$choices,
                $placeholder = me.$placeholer;

            $jstree.deselect_all();
            $choices.empty();
            me.addPlaceholderIfNecessary();
            $placeholder.val('');
            me._doSetValue([], []);
        },
        getValue: function () {
            var me = this,
                nids = [];

            if (me.multiple) {
                me.$container.find('li.choice-item[data-value]').each(function (i, item) {
                    nids.push(item.getAttribute('data-value'));
                });
            } else {
                nids.push(me.$placeholer.val());
            }
            return nids;
        },
        setValue: function (ids) {
            var me = this;
            me.clearValue();
            me.addValue(ids);
            me.addPlaceholderIfNecessary();
        },
        addValue: function (ids) {
            var me = this,
                $jstree = me.$jstree,
                $choices = me.$choices,
                $placeholder = me.$placeholer,
                multi = $jstree.settings.core.multiple,
                nodes = [],
                node, html, i;

            ids = ids instanceof Array ? ids : [ids];
            for (i = 0; i < ids.length; i++) {
                node = $jstree.get_node(ids[i], false);
                $jstree.select_node(ids[i]);
                if (node) {
                    nodes.push(node.original);
                    if (me.hasValue(ids[i])) {
                        return;
                    }
                    if (!me.hasValue()) {
                        $choices.empty();
                    }
                    html = '';
                    if (multi) {
                        html += '<li class="choice-item" title="' + node.text + '" data-value="' + node.id + '">';
                        html += '<span>' + node.text + '</span>';
                        html += '<a class="choice-close" data-option-array-index="2" style="font-size: 14px;font-weight:bold;text-decoration: none;">&times;</a>' +
                            '</li>';
                    } else {
                        html += '<span data-value="' + node.id + '">' + node.text + '</span><b class="linked-icon"></b>'
                    }
                    $choices.append(html);
                }
            }
            me._doSetValue(ids, nodes);
        },
        removeValue: function (ids) {
            if (ids == null) return;
            var me = this,
                $jstree = me.$jstree,
                $choices = me.$choices,
                $placeholder = me.$placeholer,
                nodes = [],
                selected,
                i;
            ids = ids instanceof Array ? ids : [ids];
            for (i = 0; i < ids.length; i++) {
                $jstree.deselect_node(ids[i]);
            }
            selected = $jstree.get_selected();
            $placeholder.val(selected);

            var $items = $choices.find('li.choice-item[data-value]'); //
            for (i = 0; i < selected.length; i++) {
                $items = $items.not('[data-value="' + selected[i] + '"]');
                nodes.push($jstree.get_node(selected[i], false).original);
            }
            $items.remove();
            me.addPlaceholderIfNecessary();
            me._doSetValue(selected, nodes);
        },
        hasValue: function (value) {
            var me = this,
                multi = me.$jstree.settings.core.multiple,
                $choices = me.$choices;
            if (multi) {
                value = undefined == value ? 'li.choice-item[data-value]' : 'li.choice-item[data-value="' + value + '"]';
            } else {
                value = undefined == value ? 'span[data-value]' : 'span[data-value="' + value + '"]';
            }
            return $choices.find(value).length > 0;
        },
        selectPathLeaf: function (path, offset) {
            var me = this,
                $jstree = me.$jstree;
            offset = null != offset ? offset : 0;
            if (path[offset]) {
                if (path.length == offset + 1) {
                    $jstree.select_node(path[offset]);
                    me.save();
                } else {
                    $jstree.load_node(path[offset], function (node, bool) {
                        if (node.children && -1 < node.children.indexOf(path[offset + 1])) {
                            me.selectPathLeaf(path, offset + 1);
                        }
                    });
                }
            }
        },
        _initSelected: function (paths, separator) {
            var me = this, multi = me.multiple, i;

            separator = separator || '/';
            paths = paths instanceof Array ? paths : [paths];
            paths = multi ? paths : paths.slice(0, 1);
            for (i = 0; i < paths.length; i++) {
                me.selectPathLeaf(paths[i].split(separator));
            }
        },
        _doSetValue: function (ids, nodes) {
            var me = this,
                $placeholder = me.$placeholer,
                callback = me._callback;
            typeof callback == 'function' ? callback.call(me, ids, nodes) : $placeholder.val(ids);
        }
    });

    function proxy(fn, context) {
        context = context || this;
        return function() { fn.apply(context, arguments); };
    }

    // ------------ jquery ---------------
    $.extend($.fn, {
        treepicker: function (options) {
            var pickers = [];
            this.each(function () {
                pickers.push(get(this, options));
            });
            return pickers[0];
        }
    });

    function get(el, options) {
        var picker = $.data(el, 'treepicker');
        if (!picker) {
            picker = new TreePicker(el, options);
            $.data(el, 'treepicker', picker);
        }
        return picker;
    }

    return TreePicker;
}));
