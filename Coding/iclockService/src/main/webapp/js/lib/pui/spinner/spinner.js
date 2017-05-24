/**
 *
 */
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.Spinner = factory(jQuery);
    }
}(function ($) {
    var DEFAULT = {
        min: 1,             // 最小值
        max: 10,            // 最大值
        step: 1,            // 增量
        allowEmpty: false,  // 是否允许空
        container: null,    // spinner 容器
        minusBtn: '.minus', // 减少按钮
        plusBtn: '.plus'    // 增加按钮
    };

    function Spinner(opt) {
        var me = this;

        opt = $.extend({}, DEFAULT, opt);

        var $el = me.$el = $(opt.el),
            min = me._min = opt.min,
            max = me._max = opt.max,
            step = me._step = opt.step,
            allowEmpty = me._allowEmpty = opt.allowEmpty,
            $wrap, $minusBtn, $plusBtn;

        // 指定 input, 没有指定 container 此时创建元素
        if (opt.el && !opt.container) {
            $wrap = $('<span class="num-input"></span>').insertBefore($el);
            $minusBtn = me.$minusBtn = $('<span class="minus">-</span>');
            $plusBtn = me.$plusBtn = $('<span class="plus">+</span>');
            $wrap.append($minusBtn).append($el).append($plusBtn);
        } else {
            $wrap = $(opt.container);
            $minusBtn = me.$minusBtn = $wrap.find(opt.minusBtn);
            $plusBtn = me.$plusBtn = $wrap.find(opt.plusBtn);
            if ($el.length < 1) {
                $el = me.$el = $wrap.find('input');
            }
        }

        $minusBtn.click($.proxy(me.minus, me));
        $plusBtn.click($.proxy(me.plus, me));
        $el.on('keyup', function () {
            this.value && me.set(this.value.replace(/^[^0-9]+$/, ''));
        }).blur(function () {
            (!allowEmpty && !this.value) && me.set();
        });
        // me.set(me.get());
        $el.blur();
    }

    Spinner.prototype = {
        plus: function () {
            var me = this;
            me.set(me.get() + me._step);
        },
        minus: function () {
            var me = this;
            me.set(me.get() - me._step)
        },
        set: function (val) {
            var me = this,
                $el = me.$el,
                allowEmpty = me._allowEmpty;


            setTimeout(function () {
                me.$el.focus();
            });

            me.$minusBtn.removeClass('disabled');
            me.$plusBtn.removeClass('disabled');

            if (allowEmpty && ('' == val || null == val)) {
                $el.val(val);
                return
            }

            val = parseInt(val);
            if (isNaN(val) || me._min >= val) {
                val = me._min;
                me.$minusBtn.addClass('disabled');
            }
            if (me._max <= val) {
                val = me._max;
                me.$plusBtn.addClass('disabled');
            }
            $el.val(val);
        },
        get: function () {
            var me = this,
                $el = me.$el,
                allowEmpty = me._allowEmpty,
                val = $el.val();
            if (allowEmpty && ('' == val || null == val)) {
                return val;
            } else {
                val = parseInt(val);
                if (isNaN(val) || me._min >= val) {
                    return me._min;
                } else if (me._max <= val) {
                    return me._max;
                } else {
                    return val;
                }
            }
        }
    };
    $.fn.extend({
        spinner: function (opt) {
            this.each(function (i, el) {
                if (el._spinner) {
                    return;
                }

                if ('INPUT' == el.nodeName) {
                    el._spinner = new Spinner($.extend({
                        el: el
                    }, opt));
                } else {
                    el._spinner = new Spinner($.extend({
                        container: el
                    }, opt));
                }
            });
        }
    });

    return Spinner;
}));