/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

/**
 * Created by vacoor on 9/12/14.
 */

(function ($) {
    var DEFAULT = 'default',
        NONE = 'none',
        SCROLL = 'scroll',
        TOUCH_MOVE = "touchmove",
        RESIZE = 'resize',
        DURATION = 100,
//		PLACEHOLDER = '../img/lazyload-placeholder.gif',
//        PLACEHOLDER = '../img/s.gif',
        PLACEHOLDER = '../images/loading.gif',
        win = window;


    function LazyLoad(opts) {
        var me = this,
            container = (opts || {}).container;

        // factory or constructor
        if (!(me instanceof LazyLoad)) {
            return new LazyLoad(opts);
        }

        container = container || document;
        if (container == container.window) { // window
            container = container.document;
        } else if ('body' === container.nodeName.toLowerCase()) {
            container = container.ownerDocument;
        }
        me.placehoder = opts.placehoder || PLACEHOLDER;
        me.errorPlaceholder = opts.errorPlaceholder;

        me.container = container;
        me._containerIsNotDocument = 9 !== me.container.nodeType;

        me.initLoadEvent();
//		me.addElements(container);
        me._loadFn();

        $.ready(function () {
            me._loadFn();
        });

        me.resume();
    }

    $.extend(LazyLoad.prototype, {

        initLoadEvent: function () {
            var me = this;

            //
            me._loadFn = util.buffer(function () {
                me.loadItems();
            }, DURATION, me);
        },

        /**
         * force datalazyload to recheck constraints and load lazyload
         */
        refresh: function () {
            this._loadFn();
        },

        loadItems: function () {
            var me = this,
                container = me.container;

            // container is not document and container is display none
            if (me._containerIsNotDocument && !container.offsetWidth) {
                return;
            }

            me._windowRegion = getBoundingRect();
            if (me._containerIsNotDocument) {
                me._containerRegion = getBoundingRect(container);
            }

            $('img', container).each(function (i, el) {
                me.loadItem(el);
            });
        },

        loadItem: function (el) {
            var me = this,
                src,
                img;

            // if (force == )
            if (elementInViewport(el, me._windowRegion, me._containerRegion)) {
                src = el.getAttribute('lz-src');
                el.removeAttribute('lz-src');
                if (src) {
//                    console.log(src)
                    el.src = me.placehoder;
                    img = new Image();
                    img.onload = function () {
                        el.src = src;
//                        $(el).hide().fadeIn();
                    };
                    img.onerror = function() {
                        window.console = window.console || {log:function() {}};
                        window.console.log('load error:', src);
                        if (me.errorPlaceholder) {
                            el.src = me.errorPlaceholder;
                        }
                        //el.src = src;
                    };
                    img.src = src;
                }
            }
        },

        /**
         * pause lazyload
         */
        pause: function () {
            var self = this,
                load = self._loadFn;

            if (self._destroyed) {
                return;
            }

            Event.remove(win, SCROLL, load);
            Event.remove(win, TOUCH_MOVE, load);
            Event.remove(win, RESIZE, load);
            load.stop();

            if (self._containerIsNotDocument) {
                var c = self.get('container');
                Event.remove(c, SCROLL, load);
                Event.remove(c, TOUCH_MOVE, load);
            }
        },

        /**
         * resume lazyload
         */
        resume: function () {
            var me = this,
                load = me._loadFn;

            if (me._destroyed) {
                return;
            }

            /*
             // scroll 和 resize 时，加载图片
             Event.on(win, SCROLL, load);
             Event.on(win, TOUCH_MOVE, load);
             Event.on(win, RESIZE, load);
             */
            $(win).on(SCROLL, load);
            $(win).on(TOUCH_MOVE, load);
            $(win).on(RESIZE, load);

            if (me._containerIsNotDocument) {
                var c = me.get('container');
                Event.on(c, SCROLL, load);
                Event.on(c, TOUCH_MOVE, load);
            }
        }
    });

    /* **************************
     *      HELPER METHOD
     * **************************/

    /**
     * 获取 document viewport / 给定元素边界区域
     *
     * 如果不传递则返回 document viewport 区域
     * 否则返回给定元素边界区域
     * @param elem
     * @returns {{x: *, y: *, w: *, h: *}}
     */
    function getBoundingRect(elem) {
        var x, y, w, h;

        if (undefined !== elem) {
//            x = Dom.getPos(elem).x;
//            y = Dom.getPos(elem).y;
            var offset = $(elem).offset();
            x = offset.left;
            y = offset.top;
            w = $(elem).outerWidth();
            h = $(elem).outerHeight();
        } else {
            x = Dom.scrollLeft();
            y = Dom.scrollTop();
            w = Dom.viewportWidth();
            h = Dom.viewportHeight();
//			x = $doc.scrollLeft();
//			y = $doc.scrollTop();
//			w = $doc.innerWidth();
//			h = $doc.innerHeight();
        }

        return {
            x: x,
            y: y,
            w: w,
            h: h
        };
    }

    /**
     * 两个区域是否相交
     * { x: *, y: *, w: *, h: * }
     * @param r1
     * @param r2
     * @returns {boolean}
     */
    function isCrossRect(r1, r2) {
        var r = {};
        r.top = Math.max(r1.y, r2.y);
        r.bottom = Math.min(r1.y + r1.h, r2.y + r2.h);
        r.left = Math.max(r1.x, r2.x);
        r.right = Math.min(r1.x + r1.w, r2.x + r2.w);

        return r.bottom >= r.top && r.right >= r.left;
    }

    /**
     * whether part of elem can be seen by user.
     * note: it will not handle display none.
     * @ignore
     */
    function elementInViewport(elem, windowRegion, containerRegion) {
        // it's better to removeElements,
        // but if user want to append it later?
        // use addElements instead
        // if (!inDocument(elem)) {
        //    return false;
        // }
        // display none or inside display none
        if (!elem.offsetWidth) {
            return false;
        }
        var elemOffset = $(elem).offset(),
            inContainer = true,
            inWin,
            top = elemOffset.top,
            left = elemOffset.left,
            elemRegion = {
                x: left,
                y: top - 300,
                w: $(elem).width(),// cacheWidth(elem),
                h: $(elem).height() + 300// cacheHeight(elem)
            };

        inWin = isCrossRect(windowRegion, elemRegion);

        if (inWin && containerRegion) {
            inContainer = isCrossRect(containerRegion, elemRegion); // maybe the container has decode scroll bar, so do this.
        }

        // 确保在容器内出现
        // 并且在视窗内也出现
        return inContainer && inWin;
    }

    var DOCUMENT = 'document',
        BODY = 'body',
        DOC_ELEMENT = 'documentElement',
        VIEWPORT = 'viewport',
        SCROLL = 'scroll',
        CLIENT = 'client',
        CSS1Compat = 'CSS1Compat',
        compatMode = 'compatMode',
        Dom = window.Dom || {};

    $.each(['Width', 'Height'], function (i, name) {
        Dom[VIEWPORT + name] = function (refWin) {
            // refWin = Dom.get(refWin);
            // var win = getWindow(refWin);
            var win = Dom.getWin(refWin);
            var ret = win['inner' + name];
            // http://www.quirksmode.org/mobile/viewports.html
            /*
             if (UA.mobile && ret) {
             return ret;
             }
             */
            // pc browser includes scrollbar in window.innerWidth
            var prop = CLIENT + name,
                doc = win[DOCUMENT],
                body = doc[BODY],
                documentElement = doc[DOC_ELEMENT],
                documentElementProp = documentElement[prop];
            // 标准模式取 documentElement
            // backcompat 取 body
            return doc[compatMode] === CSS1Compat && documentElementProp ||
                body && body[prop] || documentElementProp;
        };

    });
    var getWindow = Dom.getWin;

    $.each(['Left', 'Top'], function (i, name) {
        var method = SCROLL + name;

        Dom[method] = function (elem, v) {
            if (typeof elem === 'number') {
                /*jshint noarg: false*/
                return arguments.callee(win, elem);
            }
            // elem = Dom.get(elem);
            var ret,
                left,
                top,
                w,
                d;
            if (elem && elem.nodeType === NodeType.ELEMENT_NODE) {
                if (v !== undefined) {
                    elem[method] = parseFloat(v);
                } else {
                    ret = elem[method];
                }
            } else {
                w = getWindow(elem);
                if (v !== undefined) {
                    v = parseFloat(v);
                    // 注意多 window 情况，不能简单取 win
                    left = name === 'Left' ? v : Dom.scrollLeft(w);
                    top = name === 'Top' ? v : Dom.scrollTop(w);
                    w.scrollTo(left, top);
                } else {
                    //标准
                    //chrome === body.scrollTop
                    //firefox/ie9 === documentElement.scrollTop
                    ret = w['page' + (i ? 'Y' : 'X') + 'Offset'];
                    if (typeof ret !== 'number') {
                        d = w[DOCUMENT];
                        //ie6,7,8 standard mode
                        ret = d[DOC_ELEMENT][method];
                        if (typeof ret !== 'number') {
                            //quirks mode
                            ret = d[BODY][method];
                        }
                    }
                }
            }
            return ret;
        };
    });

    $.fn.extend({
        'lazyload': function(opt) {
            this.each(function(i, el) {
                var loader = $.data(el, 'lz-loader');
                if (!loader) {
                    loader = new LazyLoad($.extend({container: this}, opt));
                    $.data(el, 'lz-loader', loader);
                }
                return loader;
            });
        }
    });
})(jQuery);