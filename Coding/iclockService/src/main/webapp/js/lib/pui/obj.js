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
        window._$ = factory();
    }
}(function () {
    var REGISTRY = {};

    var DEFAULT_PREFIX = "#UnnamedClass";
    generateName = function (prefix) {
        prefix = prefix || DEFAULT_PREFIX;

        var index = 1;
        while (REGISTRY[prefix + index]) {
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
        REGISTRY[ctor.className] = ctor;   // store
        ctor.prototype = definition;
        return ctor
    };

    $instance = function(definition) {
        var clazz = definition.className,
            instance;
        clazz = null != clazz ? clazz : 'object';

        if ('object' === clazz) {
            instance = {};
        } else {
            clazz = REGISTRY[clazz];

            if (null == clazz) {
                throw new Error('can not found class: ' + definition.className);
            }
            instance = new clazz(definition);
        }
        for (var prop in definition) {
            if ('clazzName' !== prop && definition.hasOwnProperty(prop)) {
                instance[prop] = definition[prop];
            }
        }
        return instance;
    };

    return {
        define: function (definition) {
            return $class(definition);
        },
        instantiate: function(definition) {
            return $instance(definition);
        }
    };
}));