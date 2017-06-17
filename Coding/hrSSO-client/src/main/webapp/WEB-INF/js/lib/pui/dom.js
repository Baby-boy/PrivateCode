/* ***********************************************
 *
 * ***********************************************/
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.Dom = factory();
    }
}(function () {
    var GET_BOUNDING_CLIENT_RECT = 'getBoundingClientRect',
        DOCUMENT_NODE = 9;

    function getDoc(node) {
        if (!node) {
            return document;
        }
        if (node.nodeType == DOCUMENT_NODE) {
            return node;
        }
        return (node.ownerDocument || node.document || document);
    }

    function getWin(node) {
        var doc;
        if (!node) {
            return window;
        }
        if (node.constructor == Window) {
            return node;
        }
        doc = getDoc(node);
        return doc.parentWindow || doc.defaultView || window;
    }

    function isNode(node) {
        if (typeof Node === 'object') {
            return node instanceof Node;
        }
        return node && typeof node === 'object' && typeof node.nodeType === 'number' && typeof node.nodeName === 'string';
    }

    function isElement(element) {
        if (typeof HTMLElement === 'object') {
            return element instanceof HTMLElement;
        }

        return element && typeof element === 'object' && element.nodeType === 1 && typeof  element.nodeName === 'string';
    }

    function get(selector, context) {
        var node = null;
        context = context || document;
        if (selector && context.querySelector) {
            node = context.querySelector(selector);
        } else if (window.jQuery) {
            node = jQuery(selector, context)[0];
            node = undefined !== node ? node : null;
        } else {
            node = query(selector, context)[0];
            node = undefined !== node ? node : null;
            /*
             } else if ('#' == selector.substring(0, 1)) {
             node = getDoc(context).getElementById(selector.substring(1));
             } else {
             // fallback
             node = getDoc().getElementById(selector);
             */
        }
        return node;
    }

    /**
     * 在给定上下文中查找 css 选择器匹配的元素
     * @param selector
     * @param context
     * @returns {Array}
     */
    function query(selector, context) {
        var nodes = [], nodeList, i;

        context = context || document;
        if (selector && context.querySelectorAll) {
            nodeList = context.querySelectorAll(selector);
            for(i = 0; i < nodeList.length; i++) {
                nodes.push(nodeList.item(i));
            }
        } else if (window.jQuery) {
            nodes = jQuery(selector, context);
        } else {
            nodes = queryAll(selector, context);
        }
        return nodes;
    }

    /* ****************************************
     *
     * ****************************************/
    // 根据给定的 html 创建元素
    function create(_html, ownDoc) {
        if (typeof _html != 'string') {
            return _html;
        }

        ownDoc = ownDoc || document;

        var div = ownDoc.createElement('tbody'),
            fragment = ownDoc.createDocumentFragment(),
            nodes;

        html(div, _html);
        nodes = children(div);

        for (var i = 0, j = nodes.length; i < j; i++) {
            append(fragment, nodes[i]);
        }

        return fragment;
    }

    // 在给定上下文中, 使用给定模板创建 HTML Element
    function createTo(context, template, args) {
        // 解析并设置属性
        function setAttributes(el, props) {
            var prop, value;
            // 遍历所有属性
            for (prop in props) {
                if (!props.hasOwnProperty(prop)) {
                    continue;
                }
                value = props[prop];
                switch (prop) {
                    case "tagName":
                    case "content":
                    case "contentText":
                        continue;
                    case "contextKey":
                        if (value && context instanceof Object && typeof value == "string") {
                            context[value] = el;
                        }
                        continue;
                    case "data":
                        value = typeof value == 'string' ? {value: value} : value;
                        for (var p in value) {
                            if (!value.hasOwnProperty(p)) {
                                continue;
                            }
                            el.setAttribute('data-' + p, value[p]);
                        }
                        break;
                    case "style":
                        // 如果是字符串直接设置
                        if (typeof value == "string") {
                            el.setAttribute('style', value);
                        } else {
                            // 对象
                            for (var styleName in value) {
                                if (value.hasOwnProperty(styleName)) {
                                    el.style[styleName] = (styleName.match(/^width$|^height$|^top$|^left$|^right$|^bottom$/) && isFinite(value[styleName]) ? value[styleName] + 'px' : value[styleName]);
                                }
                            }
                        }
                        break;
                    case "class":
                        prop = "className";
                    default:
                        if ('on' == prop.substring(0, 2)) {
                            value = typeof value != 'function' ? new Function(value) : value;
                            DomEvent.on(el, prop.substring(2), value);
                        } else {
                            el[prop] = value
                        }
                }
            }
            return el;
        }

        // 设置内容
        function setContent(el, content, isText) {
            if (true !== isText && el.tagName.toUpperCase() != "TEXTAREA") {
                html(el, content);
            } else {
                text(el, content);
            }
            return el
        }

        // 追加子元素
        function appendChild(parentEl, el) {
            if (parentEl.nodeName.toUpperCase() == "TABLE" && el.nodeName.toUpperCase() == "TR") {
                var tbody;
                if (parentEl && parentEl.tBodies[0]) {
                    tbody = parentEl.tBodies[0]
                } else {
                    tbody = parentEl.appendChild(document.createElement("tbody"))
                }
                parentEl = tbody
            }
            parentEl.appendChild(el)
        }

        // S --
        // 如果只有一个参数, 则只传递了模板
        if (undefined == template) {
            template = context;
            context = undefined;
        }

        // 模板为字符串
        if (typeof template == 'string') {
            return create(template);
        }

        if (typeof template == "function") {
            template = template(args || window)
        }

        if (template instanceof Array) {
            var elements = [];
            for (var i = 0; i < template.length; i++) {
                elements.push(createTo(context, template[i], args))
            }
            return elements
        }

        var tagName = template.tagName || "DIV",
            content = template.content,
            el;

        tagName = tagName.toUpperCase();

        //
        if (tagName == "INPUT" && template.type) {
            el = document.createElement("<" + tagName + ' type="' + template.type + '"/>')
        } else {
            el = document.createElement(tagName)
        }

        el = setAttributes(el, template);

        if (null != content) {
            // 如果内容是字符串
            if (typeof content == 'string') {
                if (content.charAt(0) == '^') {
                    appendChild(el, document.createElement(content.substring(1)))
                } else {
                    el = setContent(el, content)
                }
            } else if (content instanceof Array) {
                for (var i = 0; i < content.length; i++) {
                    var c = content[i];
                    if (c.constructor == String) {
                        if (c.charAt(0) == '^') {
                            appendChild(el, document.createElement(c.substring(1)))
                        } else {
                            appendChild(el, document.createTextNode(c))
                        }
                    } else {
                        appendChild(el, this.create(context, c, args))
                    }
                }
            } else if (content.nodeType) {
                appendChild(el, content)
            } else {
                appendChild(el, createTo(context, content, args))
            }
        } else {
            var contentText = template.contentText;
            if (contentText != null && typeof contentText == 'string') {
                el = setContent(el, contentText, true)
            }
        }
        return el;
    }

    function html(element, html) {
        if (typeof html !== 'undefined') {
            element.innerHTML = html;
        }
        return element.innerHTML;
    }

    function text(element, text) {
        if (typeof text !== 'undefined') {
            if (element.innerText) {
                element.innerText = text;
            } else if (element.textContent) {
                element.textContent = text;
            } else {
                element.innerHTML = text.replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;").replace(/\n/g, "<br />\n");
            }
        }

        if (element.innerText) {
            return element.innerText;
        } else if (element.textContent) {
            return element.textContent;
        }
        return element.innerHTML.replace(/&/g, "&amp;").replace(/>/g, "&gt;").replace(/</g, "&lt;").replace(/\n/g, "<br />\n");
    }

    // 获取所有子元素或给定标记子元素,
    // 默认忽略文本和注释节点, 当 tag = false, 不忽略
    function children(element, tag) {
        if (false === tag) {
            return element.childNodes;
        }

        var nodes = [], i, j;
        if (typeof tag === 'string') {
            for (i = 0, j = element.childNodes.length; i < j; i++) {
                if (element.childNodes[i].nodeName.toLowerCase() === tag.toLowerCase()) {
                    nodes.push(element.childNodes[i]);
                }
            }
            return nodes;
        }

        for (i = 0, j = element.childNodes.length; i < j; i++) {
            if (element.childNodes[i].nodeType === 1) {
                nodes.push(element.childNodes[i]);
            }
        }
        return nodes;
    }
    /* ************************************
     *
     * ************************************/
    function append(el, html) {
        html = create(html, getDoc(el));
        el.appendChild(html);
        return html;
    }

    function prepend(el, html) {
        html = create(html);
        el.insertBefore(html, el.firstChild);
        return html;
    }

    function before(el, html) {
        html = create(html);
        el.parentNode.insertBefore(html, el);
        return html;
    }

    function after(el, html) {
        html = create(html);
        el.parentNode.insertBefore(html, el.nextSibling);
        return html;
    }

    function replace(el, html) {
        html = create(html);
        el.parentNode.replaceChild(html, el);
        return html;
    }

    function remove(node) {
        return node.parentNode.removeChild(node);
    }


    /* *******************************************
     *
     * *******************************************/

    function id(id) {
        return document.getElementById(id);
    }

    function findByTagName(name) {

    }

    function findByClass() {

    }


    /* ***********************************
     *
     * ***********************************/


    /* **********************************
     *             CSS Style
     * **********************************/

// 获取节点的 css 样式
    var getStyle = function (node, styleName) {
        if (node.currentStyle) { // ie, opera
            return node.currentStyle[styleName];
        } else if (window.getComputedStyle) { // ff, chrome, safari
            return window.getComputedStyle(node, null)[styleName];
        }
    };

// 获取节点是否存在给定 class name
    var hasClass = function (node, className) {
        if (node.classList) {
            return node.classList.contains(className);
        }

        if (!node.className) {
            return false;
        }

        var regExp = new RegExp("(^|\\s+)" + className + "(\\s+|$)");
        return regExp.test(node.className);
    };

// 添加节点class name
    var addClass = function (node, className) {
        if (node.classList) {
            node.classList.add(className);
        } else if (!hasClass(node, className)) {
            node.className = !node.className ? className : node.className.replace(/\s+$/, '') + ' ' + className;
        }
    };

// 移除节点 class name
    var removeClass = function (node, className) {
        if (node.classList) {
            node.classList.remove(className);
        } else if (node.className) {
            var regExp = new RegExp("(^|\\s+)" + className + "(\\s+|$)");
            node.className = node.className.replace(regExp, function ($0, $1, $2) {
                return $1 === ' ' && $2 === ' ' ? ' ' : '';
            });
        }
    };

    var getSiblings = function(elem) {
        var r = [],
            n = elem.parentNode.firstChild;
        for(; n; n = n.nextSibling) {
            if ( n.nodeType === 1 && n !== elem ) {
                r.push(n);
            }
        }
        return r;
    };

// 获取节点在页面中的位置
    var getPos = function (node, root) {
        var x = 0,
            y = 0,
            parent,
            doc = document,
            docElem = 'CSS1Compat' === doc.compatMode ? doc.documentElement : doc.body,
            nodeRect,
            rootRect;

        // node = node;
        root = root || doc.body;

        function getRectPos(node) {
            var bodyElm, rect, x = 0, y = 0;

            if (node) {
                rect = node[GET_BOUNDING_CLIENT_RECT]();
                bodyElm = doc.compatMode === "CSS1Compat" ? doc.documentElement : doc.body;
                x = rect.left + bodyElm.scrollLeft;
                y = rect.top + bodyElm.scrollTop;
            }

            return { x: x, y: y };
        }

        // A-Grade Browsers 都已支持 getBoundingClientRect 方法，不用再考虑传统的实现方式
        if (doc.body != node && docElem != node && node[GET_BOUNDING_CLIENT_RECT]) {
            // if (node && node.getBoundingClientRect && (!!window.ActiveXObject || "ActiveXObject" in window) && (!doc.documentMode || doc.documentMode < 8)) {
            nodeRect = getRectPos(node);
            rootRect = getRectPos(root);

            return {
                x: nodeRect.x - rootRect.x,
                y: nodeRect.y - rootRect.y
            };
        }

        parent = node;
        while (parent && parent != root && parent.nodeType) {
            x += parent.offsetLeft || 0;
            y += parent.offsetTop || 0;
            parent = parent.offsetParent;
        }

        parent = node.parentNode;
        while (parent && parent != root && parent.nodeType) {
            x -= parent.scrollLeft || 0;
            y -= parent.scrollTop || 0;
            parent = parent.parentNode;
        }

        return { x: x, y: y };
    };

// 获取元素的可见区域大小
    var getSize = function (node) {
        return {
            w: node.offsetWidth || node.clientWidth,
            h: node.offsetHeight || node.clientHeight
        };
    };


    /* ***********************************************
     *
     * ***********************************************/

// 添加事件监听
    var addEvent = document.addEventListener ? function (elem, type, fn) {
        elem.addEventListener(type, fn, false);
        return fn;
    } : (document.attachEvent ? function (elem, type, fn) {
        elem.attachEvent('on' + type, fn);
        return fn;
    } : function (elem, type, fn) {
        elem['on' + type] = fn;
    });

// 移除事件监听
    var removeEvent = document.removeEventListener ? function (elem, type, fn) {
        elem.removeEventListener(type, fn);
        return fn;
    } : (document.attachEvent ? function (elem, type, fn) {
        elem.detachEvent('on' + type, fn);
        return fn;
    } : function (elem, type, fn) {
        elem['on' + type] = null;
    });

    var delegateEvent = function(elem, type, selector, fn) {
        addEvent(elem, type, function(evt) {
            var event = getEvent(evt),
                src = event.target || event.srcElement;
            if (is(src, selector)) {
                fn.apply(src, fn);
            }
        });
    };

    var on = function(elem, type, selector, fn) {
        if (typeof selector == 'function') {
            addEvent(elem, type, selector);
        } else {
            delegateEvent(elem, type, selector, fn);
        }
    };

// 获取事件对象
    var getEvent = function (evt) {
        return window.event ? window.event : (evt ? evt : (function (o) {
            do {
                o = o.caller;
            } while (o && !/^\[object[ A-Za-z]*Event\]$/.test(o.arguments[0]));
        })(this.getEvent));
    };

// 获取鼠标在页面的位置
    var mousePosition = function (e) {
        e = e || getEvent();
        var bodyElem = document.compatMode === 'CSS1Compat' ? document.documentElement : document.body;
        return {
            x: bodyElem.scrollLeft + e.clientX,
            y: bodyElem.scrollTop + e.clientY
        };
    };

    function parseJSON(str) {
        if (typeof(str) === 'object') {
            return str;
        } else {
            if (window.JSON) {
                return JSON.parse(str);
            } else {
                return eval('(' + str + ')');
            }
        }
    }


    function _each(obj, fn) {
        if (_isArray(obj)) {
            for (var i = 0, len = obj.length; i < len; i++) {
                if (fn.call(obj[i], i, obj[i]) === false) {
                    break;
                }
            }
        } else {
            for (var key in obj) {
                if (obj.hasOwnProperty(key)) {
                    if (fn.call(obj[key], key, obj[key]) === false) {
                        break;
                    }
                }
            }
        }
    }

    function _isArray(val) {
        if (!val) {
            return false;
        }
        return Object.prototype.toString.call(val) === '[object Array]';
    }

    function _contains(nodeA, nodeB) {
        // Node.DOCUMENT_NODE == 9
        if (nodeA.nodeType == 9 && nodeB.nodeType != 9) {
            return true;
        }
        while ((nodeB = nodeB.parentNode)) {
            if (nodeB == nodeA) {
                return true;
            }
        }
        return false;
    }

// ------------------
    function queryAll(selector, context) {
        var selectors = selector.split(',');

        // 如果存在多个选择器
        if (selectors.length > 1) {
            var mergedResults = [];

            function _each() {
                // TODO
            }

            _each(selectors, function () {
                _each(queryAll(this, context), function () {
                    if (_inArray(this, mergedResults) < 0) {
                        mergedResults.push(this);
                    }
                });
            });
            return mergedResults;
        }

        context = context || document;

        function escape(str) {
            return 'string' != str ? str : str.replace(/([^\w\-])/g, '\\$1');
        }

        function stripslashes(str) {
            return str.replace(/\\/g, '');
        }

        function cmpTag(tagA, tagB) {
            return tagA === '*' || tagA.toLowerCase() === escape(tagB.toLowerCase());
        }

        function byId(id, tag, context) {
            var arr = [], doc = context.ownerDocument || context,
                el = doc.getElementById(stripslashes(id));
            if (el && cmpTag(tag, el.nodeName) && _contains(doc, el)) {
                arr.push(el);
            }
            return arr;
        }

        function byClass(className, tag, context) {
            var arr = [], els, i, len, el;
            context = context || context.ownerDocument;
            if (context.getElementsByClassName) {
                els = context.getElementsByClassName(stripslashes(className));
                for (i = 0, len = els.length; i < len; i++) {
                    el = els[i];
                    if (cmpTag(tag, el.nodeName)) {
                        arr.push(el);
                    }
                }
            } else if (doc.querySelectorAll) {
                els = doc.querySelectorAll((context.nodeName !== '#document' ? context.nodeName + ' ' : '') + tag + '.' + className);
                for (i = 0, len = els.length; i < len; i++) {
                    el = els[i];
                    if (_contains(context, el)) {
                        arr.push(el);
                    }
                }
            } else {
                els = context.getElementsByTagName(tag);
                className = ' ' + className + ' ';
                for (i = 0, len = els.length; i < len; i++) {
                    el = els[i];
                    if (el.nodeType == 1) {
                        var cls = el.className;
                        if (cls && (' ' + cls + ' ').indexOf(className) > -1) {
                            arr.push(el);
                        }
                    }
                }
            }
            return arr;
        }

        function byName(name, tag, root) {
            var arr = [], doc = root.ownerDocument || root,
                els = doc.getElementsByName(stripslashes(name)), el;
            for (var i = 0, len = els.length; i < len; i++) {
                el = els[i];
                if (cmpTag(tag, el.nodeName) && _contains(root, el)) {
                    if (el.getAttribute('name') !== null) {
                        arr.push(el);
                    }
                }
            }
            return arr;
        }

        function byAttr(key, val, tag, root) {
            var arr = [], els = root.getElementsByTagName(tag), el;
            for (var i = 0, len = els.length; i < len; i++) {
                el = els[i];
                if (el.nodeType == 1) {
                    if (val === null) {
                        if (_getAttr(el, key) !== null) {
                            arr.push(el);
                        }
                    } else {
                        if (val === escape(_getAttr(el, key))) {
                            arr.push(el);
                        }
                    }
                }
            }
            return arr;
        }

        function select(expr, root) {
            var arr = [], matches;
            matches = /^((?:\\.|[^.#\s\[<>])+)/.exec(expr);
            var tag = matches ? matches[1] : '*';
            if ((matches = /#((?:[\w\-]|\\.)+)$/.exec(expr))) {
                // ID 查找
                arr = byId(matches[1], tag, root);
            } else if ((matches = /\.((?:[\w\-]|\\.)+)$/.exec(expr))) {
                // Class 查找
                arr = byClass(matches[1], tag, root);
            } else if ((matches = /\[((?:[\w\-]|\\.)+)\]/.exec(expr))) {
                // 属性查找
                arr = byAttr(matches[1].toLowerCase(), null, tag, root);
            } else if ((matches = /\[((?:[\w\-]|\\.)+)\s*=\s*['"]?((?:\\.|[^'"]+)+)['"]?\]/.exec(expr))) {
                var key = matches[1].toLowerCase(), val = matches[2];
                if (key === 'id') {
                    arr = byId(val, tag, root);
                } else if (key === 'class') {
                    arr = byClass(val, tag, root);
                } else if (key === 'name') {
                    arr = byName(val, tag, root);
                } else {
                    arr = byAttr(key, val, tag, root);
                }
            } else {
                var els = root.getElementsByTagName(tag), el;
                for (var i = 0, len = els.length; i < len; i++) {
                    el = els[i];
                    if (el.nodeType == 1) {
                        arr.push(el);
                    }
                }
            }
            return arr;
        }

        var parts = [], arr, re = /((?:\\.|[^\s>])+|[\s>])/g;
        while ((arr = re.exec(selector))) {
            if (arr[1] !== ' ') {
                parts.push(arr[1]);
            }
        }

        var matched = [];
        if (parts.length == 1) {
            return select(parts[0], context);
        }
        var isChild = false, part, els, subResults, val, v, i, j, k, length, len, l;
        for (i = 0, lenth = parts.length; i < lenth; i++) {
            part = parts[i];
            if (part === '>') {
                isChild = true;
                continue;
            }
            if (i > 0) {
                els = [];
                for (j = 0, len = matched.length; j < len; j++) {
                    val = matched[j];
                    subResults = select(part, val);
                    for (k = 0, l = subResults.length; k < l; k++) {
                        v = subResults[k];
                        if (isChild) {
                            if (val === v.parentNode) {
                                els.push(v);
                            }
                        } else {
                            els.push(v);
                        }
                    }
                }
                matched = els;
            } else {
                matched = select(part, context);
            }
            if (matched.length === 0) {
                return [];
            }
        }
        return matched;
    }



    function DNode(node) {
        this.init.apply(this, arguments);
    }

    DNode.prototype = {
        init: function (node) {
            var me = this;
            me.node = node;
        },
        get: function (i) {
            return 1;
        },
        html: function (val) {
            var me = this;
            if (val === undefined) {
                /*
                 if (self.length < 1 || self.type != 1) {
                 return '';
                 }
                 */
                return Dom.html(me.node);
            }
            /*
             self.each(function() {
             _setHtml(this, val);
             });
             */
            Dom.html(me.node, val);
            return me;
        }
    };

    return {
        getDoc: getDoc,
        getWin: getWin,
        isNode: isNode,
        isElement: isElement,
        //
        get: get,
        query: query,
        //
        create: function() {
            if (typeof arguments[0] == 'string') {
                return create.apply(this, arguments);
            } else {
                return createTo.apply(this, arguments);
            }
        },
        html: html,
        text: text,
        children: children,
        //
        append: append,
        prepend: prepend,
        before: before,
        after: after,
        replace: replace,
        remove: remove,
        //
        getStyle: getStyle,
        getPos: getPos,
        getSize: getSize,
        hasClass: hasClass,
        addClass: addClass,
        removeClass: removeClass,
        on: on,
        off: removeEvent,
        getEvent: getEvent,
        mousePosition: mousePosition,
        getSiblings: getSiblings
    };
}));


