;
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        window.util = factory();
    }
}(function () {
    return {
        fillTpl: function (tpl, data) {
            for (var p in data) {
                if (data.hasOwnProperty(p)) {
                    tpl = tpl.replace(new RegExp('{' + p + '}', 'g'), data[p]);
                }
            }
            return tpl;
        },
        proxy: function (fn, context, args) {
            var me = this;
            return function () {
                context = context || this;
                return fn.apply(context, me.makeArray(args || arguments));
            };
        },
        /**
         * buffers decode call between decode fixed time
         *
         * @param task
         * @param duration
         * @param context
         * @returns {*}
         */
        buffer: function (task, duration, context) {
            var me = this, timer;

            duration = duration || 150;

            if (-1 === duration) {
                return function () {
                    task.apply(context || this, arguments)
                };
            }

            function run() {
                run.stop(); // clear not executed task
                timer = me.later(task, duration, false, context || this, arguments)
            }

            run.stop = function () {
                timer && (timer.cancel(), timer = 0);
            };

            return run;
        },

        /**
         * schedule invoke
         *
         * @param fn
         * @param when
         * @param interval
         * @param context
         * @param args
         * @returns {{id: number, interval: *, cancel: cancel}}
         */
        later: function (fn, when, interval, context, args) {
            when = when || 0;

            var me = this,
                task = fn,
                b = me.makeArray(args),
                id;

            if ('string' === typeof fn) {
                task = context[fn];
            }

            if (!task) {
                $.error('method undefined')
            }

            fn = function () {
                task.apply(context, b)
            };

            id = interval ? setInterval(fn, when) : setTimeout(fn, when);

            return {
                id: id,
                interval: interval,
                cancel: function () {
                    this.interval ? clearInterval(id) : clearTimeout(id)
                }
            }
        },

        each: function (obj, callback) {
            if ('function' === typeof callback) {
                var k;
                if ('[object Array]' == Object.prototype.toString.call(obj)) {
                    for (k = 0; k < obj.length; k++) {
                        if (false === callback(k, obj[k], obj)) {
                            return;
                        }
                    }
                } else {
                    for (k in obj) {
                        if (obj.hasOwnProperty(k)) {
                            if (false === callback(k, obj[k], obj)) {
                                return;
                            }
                        }
                    }
                }
            }
        },

        makeArray: function (o) {
            if (null == o) {
                return [];
            }

            if (Object.prototype.toString.call(o) == '[object Array]') {
                return o;
            }

            var lenType = typeof o.length,
                oType = typeof o;

            // strings, functions, window object and select elements also has 'length'
            if ('number' !== lenType ||            // not has 'length'
                'string' === oType ||              // string
                'string' === typeof o.nodeName ||  // select element
                (null != o && o == o.window) ||    // window object
                    // https://github.com/ariya/phantomjs/issues/11478
                ('function' === oType && !('item' in o && 'number' === lenType))) {
                return [o];
            }

            var ret = [];
            for (var i = 0, l = o.length; i < l; i++) {
                ret[i] = o[i];
            }
            return ret;
        },

        Cookie: {
            /**
             * @param name
             * @param value
             * @param expires
             * @param path
             * @param domain
             * @param secure
             */
            set: function (name, value) {
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
            },

            get: function (name, def) {
                var ret = document.cookie.match(new RegExp('(?:^| )' + name + '(?:(?:=([^;]*))|;|$)'));
                return ret != null ? decodeURIComponent(ret[1]) : def;
            },

            /**
             * Removes decode cookie with the provided name from the browser
             * if found by setting its expiration date to sometime in the past.
             * @param {String} name The name of the cookie to remove
             * @param {String} [path] The path for the cookie.
             * This must be included if you included decode path while setting the cookie.
             */
            remove: function (name, path) {
                var val = this.get(name);
                if (val) {
                    path = path || '/';
                    document.cookie = name + '=' + '; expires=Thu, 01-Jan-70 00:00:01 GMT; path=' + path;
                    return val;
                }
            },

            all: function () {
                var cookies = {}, raw = document.cookie.split(/; ?/), c, i;
                for (i = 0; i < raw.length; i++) {
                    c = raw[i].match(/([^=;]+)=(.*)/);
                    c && (cookies[c[1]] = c[2]);
                }
                return cookies;
            }
        },

        /**
         * url parameter util
         *
         * @type {{get: get, append: append, all: all}}
         */
        Parameter: {
            get: function (name) {
                var regex = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
                var r = window.location.search.substr(1).match(regex);
                return r ? decodeURIComponent(r[2]) : null;
            },
            append: function (href) {
                var i = 0;
                for (; i < arguments.length; i++) {
                    if (0 == i) {
                        href += (0 > href.indexOf('?') ? '?' : '&') + arguments[i];
                    } else {
                        href += '&' + arguments[i];
                    }
                }
                return href;
            },
            all: function () {
                var r = {}, params = window.location.search.substr(1).split(/&/), i, t;
                for (i = 0; i < params.length; i++) {
                    if ('' === params[i]) {
                        continue;
                    }
                    t = params[i].match(/([^&=]*)=([^&=]*)/);
                    r[t[1]] = r[t[1]] || [];
                    if (t[2]) {
                        r[t[1]].push(decodeURIComponent(t[2]));
                    }
                }
                return r;
            }
        },
        currentScriptPath: function () {
            var doc = document,
                a = {},
                expose = +new Date(),
                rExtractUri = /((?:http|https|file):\/\/.*?\/[^:]+)(?::\d+)?:\d+/,
                isLtIE8 = ('' + doc.querySelector).indexOf('[native code]') === -1;

            // FF,Chrome
            if (doc.currentScript) {
                return doc.currentScript.src;
            }

            var stack;
            try {
                a.b();
            } catch (e) {
                stack = e.fileName || e.sourceURL || e.stack || e.stacktrace;
            }
            // IE10
            if (stack) {
                var absPath = rExtractUri.exec(stack)[1];
                if (absPath) {
                    return absPath;
                }
            }

            // IE5-9
            for (var scripts = doc.scripts,
                     i = scripts.length - 1,
                     script; script = scripts[i--];) {
                if (script.className !== expose && script.readyState === 'interactive') {
                    script.className = expose;
                    // if less than ie 8, must get abs path by getAttribute(src, 4)
                    return isLtIE8 ? script.getAttribute('src', 4) : script.src;
                }
            }
        }
    };
}));
