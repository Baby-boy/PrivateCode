(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define('', factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.Slider = factory();
    }
}(function () {
    /**
     * 默认配置
     * @type {{slider: null, triggers: null, elements: null, prev: null, next: null, event: string, autoplay: boolean, loop: boolean, keys: boolean, speed: number, delay: number, effect: string, duration: number, direction: string}}
     */
    var TRIGGER_DEFAULTS = {
        slider: null,
        triggers: null,
        elements: null,
        prev: null,
        next: null,
        event: 'hover',
        autoplay: false,    // 自动播放
        loop: true,         // 是否循环
        keys: true,
        speed: 100,         // 动画速度
        delay: 4500,        // 每个 slide 停留时间
        effect: 'none',
        duration: 500,
        direction: 'X'
    };

// 检测是否支持 Transition 动画
    var supportsTransitions = (function () {
            var docBody = document.body || document.documentElement;
            var styles = docBody.style;
            var prop = "transition";
            if (typeof styles[prop] === "string") {
                return true;
            }
            // Tests for vendor specific prop
            var vendor = ["Moz", "Webkit", "Khtml", "O", "ms"];
            prop = prop.charAt(0).toUpperCase() + prop.substr(1);
            var i;
            for (i = 0; i < vendor.length; i++) {
                if (typeof styles[vendor[i] + prop] === "string") {
                    return true;
                }
            }
            return false;
        })(),
    // 动画效果
        none = function ($target, hide, active) {
            $target.eq(active).show().siblings().hide();
        },
        visible = {"float": "left", "position": "relative", "opacity": 1, "zIndex": 2},
        hidden = {"float": "none", "position": "absolute", "opacity": 0, "zIndex": 1},
        fade = function ($target, hide, active, direction, duration, forward) {
            if (supportsTransitions) {
                $target.css({
                    // -ms prefix isn't needed as IE10 uses prefix free version
                    "-webkit-transition": "opacity " + duration + "ms ease-in-out",
                    "-moz-transition": "opacity " + duration + "ms ease-in-out",
                    "-o-transition": "opacity " + duration + "ms ease-in-out",
                    "transition": "opacity " + duration + "ms ease-in-out"
                });

                $target.css(hidden).eq(active).css(visible);
            } else {
                $target.stop(true, true).fadeOut(duration, function () {
                    $(this).css(hidden).css("opacity", 1);
                }).eq(active).fadeIn(duration, function () {
                    $(this).css(visible);
                });
            }
        },
        translate = function ($target, hide, active, direction, duration, forward) {
            $target.css({position: 'absolute', left: 0, 'visibility': 'hidden'});
            $target.eq(hide).css('visibility', 'visible');
            $target.eq(active).css('visibility', 'visible');
            if (hide == active) {
                return;
            }
            var percent = (!!forward * 2 - 1) * 100,
                transition = function ($target, ms) {
                    var transform = ms > 0 ? 'transform ' + ms + 'ms linear' : 'none';

                    return $target.css({
                        // -ms prefix isn't needed as IE10 uses prefix free version
                        "-webkit-transition": transform,
                        "-moz-transition": transform,
                        "-o-transition": transform,
                        "transition": transform
                    });
                },
                transform = function ($target, direction, offset) {
                    return $target.css({
                        "-webkit-transform": "translate" + direction + "(" + offset + ")",
                        "-moz-transform": "translate" + direction + "(" + offset + ")",
                        "-o-transform": "translate" + direction + "(" + offset + ")",
                        "transform": "translate" + direction + "(" + offset + ")"
                    })
                };
            if (supportsTransitions) {
                var trigger = 0;

                if (typeof callback == 'function') {
                    $target.one('transitionend', function (event) {
                        trigger++;
                        if (2 == trigger) {
                            callback();
                        }
                    });
                    setTimeout(function () {
                        trigger < 2 && callback();
                    }, duration + 50);
                }

                // 移动显示对象到到右/左侧准备移动
                transition($target, 0);  // 清除动画效果
                transform($target.eq(hide), direction, '0%');     // 隐藏元素移动到视口
                transform($target.eq(active), direction, (percent) + '%');   //


                setTimeout(function () {
                    transition($target, duration);

                    // 移出隐藏移入显示
                    transform($target.eq(hide), direction, (-1 * percent) + '%');
                    transform($target.eq(active), direction, '0%');
                }, 10);
            } else {
                //$target.stop(true, true);
                $target.stop(true, false);
                $target.eq(hide).css('left', 0);
                $target.eq(active).css('left', (percent) + '%');
                $target.eq(hide).show().animate({ left: -percent + '%' }, duration);
                $target.eq(active).show().animate({ left: '0%' }, duration);
            }
        };

    var slider = Dom.get('.slideTxtBox'),
//var slider = Dom.get('.slideBox'),
        vis = 1,
        _interval;

    function Trigger(opts) {
        var me = this;
        if (!(me instanceof Trigger)) {
            return new Trigger(opts);
        }

        opts = $.extend({}, TRIGGER_DEFAULTS, opts);

        var triggers = me.triggers = Dom.query(opts.triggers),
            elements = me.elements = Dom.query(opts.elements),
            eventType = me.eventType = opts.event,
            prevEl = me.prevEl = Dom.get(opts.prev),
            nextEl = me.nextEl = Dom.get(opts.next),
            autoplay = me.autoplay = opts.autoplay,
            loop = me.loop = opts.loop,
            direction = me.direction = opts.direction,
            duration = me.duration = opts.duration,
            effect = me.effect = opts.effect,
            index = me.index = 0;

        if (typeof effect == 'string') {
            switch (effect) {
                case 'fade':
                    effect = me.effect = fade;
                    break;
                case 'translate':
                    effect = me.effect = translate;
                    break;
                default :
                    effect = me.effect = none;
            }
        } else if (typeof effect != 'function') {
            effect = me.effect = translate;
        }

        // 绑定 trigger 触发事件
        util.each(triggers, function (i, trigger) {
            var type = 'hover' == eventType ? 'mouseover' : eventType;

            Dom.on(trigger, type, function () {
                // stop(); // 停止自动播放
                me.to(i);
            });
        });

        prevEl && Dom.on(prevEl, 'click', util.proxy(me.prev, me));
        nextEl && Dom.on(nextEl, 'click', util.proxy(me.next, me));

        // me.to(index);
        // 自动播放
        if (autoplay) {
            if (slider) {
                Dom.on(slider, 'mouseover', util.proxy(me.stop, me));
                Dom.on(slider, 'mouseover', util.proxy(me.stop, me));
                Dom.on(slider, 'mouseout', util.proxy(me.play, me));
            }
            // me.to(index);
            me.play();
        }

        //  Keypresses
        if (opts.keys) {
            $(slider).keydown(function (e) {
                switch (e.which) {
                    case 37:
                        me.prev(); // Left
                        break;
                    case 39:
                        me.next(); // Right
                        break;
                    case 27:
                        me.stop(); // Esc
                        break;
                }
            });
        }
    }

    Trigger.prototype = {
        // 跳转到给定索引
        to: function (index) {
            var me = this,
                triggers = me.triggers,
                elements = me.elements,
                current = me.index,
                loop = me.loop,
                trigger,
                forward;

            if (index >= triggers.length) {
                if (!loop) {
                    return;
                }
                index = 0;
                forward = true;
            } else if (0 > index) {
                if (!loop) {
                    return;
                }
                forward = false;
                index = triggers.length - 1;
            } else {
                forward = index > current;
            }

            me.index = index;
            trigger = triggers[index];

            if (elements.length > vis) {
                Dom.addClass(trigger, 'on');
                each(Dom.getSiblings(trigger), function (i, item) {
                    Dom.removeClass(item, 'on');
                });

                me.effect($(elements), current, index, me.direction, me.duration, forward);
            }
        },
        prev: function () {
            var me = this;
            me.to(me.index - 1);
        },
        next: function () {
            var me = this;
            me.to(me.index + 1);
        },
        play: function () {
            var me = this;
            me.stop();

            me._interval = setInterval(function () {
                me.to(me.index + 1);
            }, 4000);
        },
        stop: function () {
            var me = this;
            if (me._interval) {
                clearInterval(me._interval);
                me._interval = null;
            }
        }
    };

    trigger2 = Trigger({
        container: '.slideTxtBox',
        triggers: '.banner-slider .slider-dot li',
        elements: '.banner-slider .slider-cnt li',
        //elements: '.bd ul',
        loop: false,
        prev: 'a.prev',
        next: 'a.next',
        event: 'click',
        autoplay: true,
        duration: 300,
        effect: 'translate',
        direction: 'X'
    });

    return Trigger;
}));
