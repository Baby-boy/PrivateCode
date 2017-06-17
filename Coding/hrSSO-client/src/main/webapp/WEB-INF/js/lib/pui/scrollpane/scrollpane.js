var Mux = {
    createNamespace: function (ns) {
        var pairs = ns.split("."), p = window, i, pair, value;
        for (i = 0; i < pairs.length; i++) {
            pair = pairs[i];
            value = p[pair];
            if (value === undefined) {
                p[pair] = value = {}
            }
            p = value;
        }
        return p;
    },
    browser: (function () {
        var browser = {};

        function uaMatch(ua) {
            ua = ua.toLowerCase();
            var g = /(chrome)[ \/]([\w.]+)/.exec(ua) || /(webkit)[ \/]([\w.]+)/.exec(ua) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(ua) || /(msie) ([\w.]+)/.exec(ua) || /(trident).*rv\:([\w.]+)/.exec(ua) || ua.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(ua) || [];
            return {
                browser: g[1] || "",
                version: g[2] || "0"
            }
        }

        function mobileMatch(k) {
            var mobile = {},
                andorid = k.match(/(Android)\s+([\d.]+)/),
                ipad = k.match(/(iPad).*OS\s([\d_]+)/),
                iphone = !ipad && k.match(/(iPhone\sOS)\s([\d_]+)/),
                mui = k.match(/(MiuiBrowser)\/([\d.]+)/i);
            if (andorid) {
                mobile.android = true;
                mobile.version = andorid[2]
            } else {
                if (iphone) {
                    mobile.ios = true;
                    mobile.version = iphone[2].replace(/_/g, ".");
                    mobile.iphone = true
                } else {
                    if (ipad) {
                        mobile.ios = true;
                        mobile.version = ipad[2].replace(/_/g, ".");
                        mobile.ipad = true
                    }
                }
            }
            if (mui) {
                mobile.miui = true
            }
            return mobile;
        }

        var ua = navigator.userAgent,
            uaMatcher = uaMatch(ua),
            mobile = mobileMatch(ua);

        if (uaMatcher.browser) {
            browser[uaMatcher.browser] = true;
            browser.version = uaMatcher.version
        }
        if (browser.chrome) {
            browser.webkit = true
        } else {
            if (browser.webkit) {
                browser.safari = true
            } else {
                if (browser.trident) {
                    browser.msie = true
                }
            }
        }
        // mobile
        if (mobile.iphone) {
            browser.isPhone = mobile.iphone
        } else {
            if (mobile.android) {
                var w = window.screen.width;
                if (w > window.screen.height) {
                    w = window.screen.height
                }
                browser.isPhone = (w / window.devicePixelRatio) < 768;
                if (mobile.miui) {
                    browser.miui = true
                }
            }
        }
        browser.android = mobile.android;
        browser.iOS = mobile.ios;
        browser.osVersion = mobile.version;
        browser.isTouch = (browser.android || browser.iOS) && !!("ontouchstart" in window || (window["$setting"] && $setting["common.simulateTouch"]));

        if (browser.chrome) {
            try {
                document.createEvent("TouchEvent")
            } catch (g) {
                browser.isTouch = false
            }
        }
        browser.version = parseInt(browser.version);
        return browser;
    })()
};

k = {};
var DEFAULT_PREFIX = "#UnnamedClass";
generateName = function (prefix) {
    prefix = prefix || DEFAULT_PREFIX;

    var index = 1;
    while (k[prefix + index]) {
        index++;
    }
    return prefix + index
};
$class = function (definition) {
    var ctor = definition.constructor;
    // 如果传递的是一个对象
    ctor = ctor === Object ? new Function() : ctor;
    ctor.className = definition.$className || generateName();
    delete definition.$className;
    /*
     for (var prop in definition) {
     if (definition.hasOwnProperty(prop)) {
     var value = definition[prop];
     if (typeof value == "function") {
     if (!value.declaringClass) {
     value.declaringClass = ctor;
     value.methodName = prop;
     }
     }
     }
     }
     */
    k[ctor.className] = ctor;   // store
    ctor.prototype = definition;
    return ctor
};

/* *******************************************
 *
 * *******************************************/

(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['dom', 'jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        factory(Dom, jQuery);
    }
}(function (Dom, $) {
    var SCROLLER_SIZE = 5,
        SCROLLER_EXPANDED_SIZE = 10,
        HOVER_CLASS = "scroller-hover",
        EXPAND_CLASS = "scroller-expand";
    var a = 0,
        SLIDER_MIN_SIZE = SCROLLER_EXPANDED_SIZE,
        m = 2;

    window.Scroller = $class({
        constructor: function (viewport, direction) {
            this.viewport = viewport;
            this.direction = direction;
        },
        destroy: function () {
            if (this.dom) this.dom.parentNode.removeChild(this.dom);
            delete this.dom;
            delete this.doms;
            delete this.viewport
        },
        createDom: function () {
            var me = this,
                doms = me.doms = {},
                dom = me.dom = Dom.create(doms, {
                    tagName: "div",
                    className: 'scroller',
                    style: "position: absolute;",
                    content: [{
                        tagName: "div",
                        contextKey: "track",
                        className: "track",
                        style: {
                            width: "100%",
                            height: "100%"
                        }
                    }, {
                        tagName: "div",
                        contextKey: "slider",
                        className: "slider",
                        style: "position: absolute"
                    }]
                }),
                slider = doms.slider,
                track = doms.track,
                $dom = $(dom),
                $slider = $(slider),
                $track = $(track),
                handler = {
                    containment: "parent",
                    start: function () {
                        me.dragging = true
                    },
                    stop: function () {
                        me.dragging = false;
                        (me.hover) ? me.doMouseEnter() : me.doMouseLeave()
                    },
                    drag: function () {
                        var container = me.viewport;
                        if (me.direction == "h") {
                            container.scrollLeft = Math.round(slider.offsetLeft * me.positionRatio)
                        } else {
                            container.scrollTop = Math.round(slider.offsetTop * me.positionRatio)
                        }
                    }
                };

            if (me.direction == "h") {
                dom.style.height = SCROLLER_SIZE + "px";
                slider.style.top = "0";
                slider.style.height = "100%";
                handler.axis = "x"
            } else {
                dom.style.width = SCROLLER_SIZE + "px";
                slider.style.left = "0";
                slider.style.width = "100%";
                handler.axis = "y"
            }

            $slider.draggable(handler);

            $dom.hover(function () {
                    me.update();
                    me.doMouseEnter()
                },
                function () {
                    me.doMouseLeave()
                });

            $track.click(function (evt) {
                var viewport = me.viewport;
                if (me.direction == "h") {
                    if (evt.offsetX > slider.offsetLeft) {
                        viewport.scrollLeft += viewport.clientWidth;
                    } else {
                        viewport.scrollLeft -= viewport.clientWidth;
                    }
                } else {
                    if (evt.offsetY > slider.offsetTop) {
                        viewport.scrollTop += viewport.clientHeight;
                    } else {
                        viewport.scrollTop -= viewport.clientHeight;
                    }
                }
            });
            return dom;
        },
        doMouseEnter: function () {
            var me = this;
            me.hover = true;
            if (!me.dragging) {
                $(me.dom).addClass(HOVER_CLASS);
                me.expand();
            }
        },
        doMouseLeave: function () {
            var me = this;
            me.hover = false;
            if (!me.dragging) {
                $(me.dom).removeClass(HOVER_CLASS);
                me.collapse();
            }
        },
        expand: function () {
            var me = this;
            me.expanded = true;
            me._timeout && clearTimeout(me._timeout);
            $(me.dom).addClass(EXPAND_CLASS);
        },
        collapse: function () {
            var me = this;
            //me._timeout = setTimeout(function () {
            $(me.dom).removeClass(EXPAND_CLASS);
            //delete me._timeout;
            //}, 600);
        },
        update: function () {
            var me = this,
                viewport = me.viewport,
                dom = me.dom,
                trackSize,
                sliderSize,
                size = me.expanded ? SCROLLER_EXPANDED_SIZE : SCROLLER_EXPANDED_SIZE,
                browser = Mux.browser;

            if (!viewport) {
                return
            }

            // 水平方向滚动
            if (me.direction == "h") {
                if (viewport.scrollWidth > (viewport.clientWidth + m) && viewport.clientWidth > 0) {
                    if (!dom) {
                        dom = me.createDom();
                        dom.style.zIndex = 9999;
                        dom.style.bottom = 0;
                        dom.style.left = 0;
                        if (!browser.msie || browser.version != 6) {
                            dom.style.width = "100%";
                        }
                        viewport.parentNode.appendChild(dom)
                    } else {
                        dom.style.display = ""
                    }
                    if (browser.msie && browser.version == 6) {
                        dom.style.width = viewport.offsetWidth + "px"
                    }
                    slider = me.doms.slider;
                    trackSize = viewport.offsetWidth - a * 2;
                    sliderSize = (trackSize * viewport.clientWidth / viewport.scrollWidth);
                    if (sliderSize < SLIDER_MIN_SIZE) {
                        trackSize -= (SLIDER_MIN_SIZE - sliderSize);
                        sliderSize = SLIDER_MIN_SIZE
                    }
                    me.positionRatio = viewport.scrollWidth / trackSize;
                    slider.style.left = Math.round(viewport.scrollLeft / me.positionRatio) + "px";
                    slider.style.width = Math.round(sliderSize) + "px"
                } else {
                    // 不需要滚动条
                    if (browser.msie && browser.version == 9 && viewport.offsetWidth > 0) {
                        setTimeout(function () {
                            me.update()
                        }, 0)
                    }
                    if (dom) {
                        dom.style.display = "none";
                    }
                }
            } else {
                // 垂直方向需要滚动条
                if (viewport.scrollHeight > (viewport.clientHeight + m) && viewport.clientHeight > 0) {
                    if (!dom) {
                        dom = me.createDom();
                        dom.style.zIndex = 9999;
                        dom.style.top = 0;
                        dom.style.right = 0;
                        if (!browser.msie || browser.version != 6) {
                            dom.style.height = "100%"
                        }
                        viewport.parentNode.appendChild(dom)
                    } else {
                        dom.style.display = ""
                    }
                    if (browser.msie && browser.version == 6) {
                        dom.style.height = viewport.offsetHeight + "px"
                    }
                    slider = me.doms.slider;
                    trackSize = viewport.offsetHeight - a * 2;
                    sliderSize = (trackSize * viewport.clientHeight / viewport.scrollHeight);

                    if (sliderSize < SLIDER_MIN_SIZE) {
                        trackSize -= (SLIDER_MIN_SIZE - sliderSize);
                        sliderSize = SLIDER_MIN_SIZE
                    }
                    me.positionRatio = viewport.scrollHeight / trackSize;
                    slider.style.top = Math.round(viewport.scrollTop / me.positionRatio) + "px";
                    slider.style.height = Math.round(sliderSize) + "px"
                } else {
                    if (browser.msie && browser.version == 9 && viewport.offsetHeight > 0) {
                        setTimeout(function () {
                            me.update()
                        }, 0)
                    }
                    if (dom) {
                        dom.style.display = "none"
                    }
                }
            }
        }
    });

    window.S = $class({
        constructor: function (viewport) {
            var $viewport = $(viewport),
                parentDom = viewport.parentNode,
                $parentDom = $(parentDom);
            var overflowX = $viewport.css("overflowX"),
                overflowY = $viewport.css("overflowY"),
                w = $viewport.css("width"),
                h = $viewport.css("height"),
                xScroller, yScroller;

            if (!(overflowX == "hidden" || overflowX != "scroll" && (h == "" || h == "auto"))) {
                $viewport.css("overflowX", "hidden");
                xScroller = new Scroller(viewport, "h");
            }
            if (!(overflowY == "hidden" || overflowY != "scroll" && (w == "" || w == "auto"))) {
                $viewport.css("overflowY", "hidden");
                yScroller = new Scroller(viewport, "v")
            }
            if (!xScroller && !yScroller) {
                // throw new AbortException()
                throw new Error();
            }
            this.viewport = viewport;
            this.xScroller = xScroller;
            this.yScroller = yScroller;

            var s = $parentDom.css("position");
            if (s != "relative" && s != "absolute") {
                $parentDom.css("position", "relative")
            }

            s = $viewport.css("position");
            if (s != "relative" && s != "absolute") {
                $viewport.css("position", "relative")
            }

            this.update();

            var me = this;
            if ($viewport.mousewheel) {
                $viewport.mousewheel(function (y, B) {
                    if (viewport.scrollHeight > viewport.clientHeight) {
                        // var z = viewport.scrollTop - B * 25;
                        var z = viewport.scrollTop - B;
                        if (z <= 0) {
                            z = 0
                        } else {
                            if (z + viewport.clientHeight > viewport.scrollHeight) {
                                z = viewport.scrollHeight - viewport.clientHeight
                            }
                        }
                        var A = viewport.scrollTop - z;
                        if (A) {
                            viewport.scrollTop = z;
                            if (Math.abs(A) > m) {
                                return false
                            }
                        }
                    }
                })
            }
            $viewport.bind("scroll mousemove", function (z) {
                me.update();
                var y = {
                    scrollLeft: viewport.scrollLeft,
                    scrollTop: viewport.scrollTop,
                    scrollWidth: viewport.scrollWidth,
                    scrollHeight: viewport.scrollHeight,
                    clientWidth: viewport.clientWidth,
                    clientHeight: viewport.clientHeight
                };
                $(viewport).trigger("modernScrolling", y).trigger("modernScrolled", y)
            }).resize(function (y) {
                me.update()
            })
        },
        update: function () {
            var me = this,
                viewport = me.viewport;

            if (me.destroyed || me.dragging) {
                return;
            }
            if (me.xScroller) {
                me.xScroller.update()
            }
            if (me.yScroller) {
                me.yScroller.update()
            }
            me.currentClientWidth = viewport.clientWidth;
            me.currentClientHeight = viewport.clientHeight;
            me.currentScrollWidth = viewport.scrollWidth;
            me.currentScrollHeight = viewport.scrollHeight
        },
        setScrollLeft: function (left) {
            this.viewport.scrollLeft = left
        },
        setScrollTop: function (top) {
            this.viewport.scrollTop = top
        },
        scrollToElement: function (x) {
            var n = this.viewport,
                z = $fly(x).offset(),
                y = $fly(n).offset();
            var s = z.left - y.left,
                q = z.top - y.top;
            var w = s + x.offsetWidth,
                u = q + x.offsetHeight;
            var t = n.scrollLeft,
                p = n.scrollTop;
            var r = t + n.clientWidth,
                o = p + n.clientHeight;
            if (s < t) {
                if (w <= r) {
                    this.setScrollLeft(s)
                }
            } else {
                if (w > r) {
                    this.setScrollLeft(w + x.offsetWidth)
                }
            }
            if (q < p) {
                if (u <= o) {
                    this.setScrollTop(q)
                }
            } else {
                if (u > o) {
                    this.setScrollTop(u + x.offsetHeight)
                }
            }
        },
        destroy: function () {
            if (this.xScroller) {
                this.xScroller.destroy()
                delete this.xScroller;
            }
            if (this.yScroller) {
                this.yScroller.destroy()
                delete this.yScroller;
            }
        }
    });

}));


