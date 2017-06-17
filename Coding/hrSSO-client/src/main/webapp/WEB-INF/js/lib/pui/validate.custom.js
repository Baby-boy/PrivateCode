/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
;
(function (factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery', 'jquery.validate'], factory);
    } else if (typeof exports === 'object') {
        // Node/CommonJS style for Browserify
        module.exports = factory;
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function (jQuery) {
    jQuery.validator.addMethod("integer", function (value, element) {
        return this.optional(element) || /^-?\d+$/.test(value)
    }, "A positive or negative non-decimal number please");

    jQuery.validator.addMethod("positive", function (value, element) {
        return this.optional(element) || value > 0;
    }, "A positive number please");

    jQuery.validator.addMethod("negative", function (value, element) {
        return this.optional(element) || value < 0;
    }, "A negative number please");

    jQuery.validator.addMethod("decimal", function (value, element, param) {
        return this.optional(element) || new RegExp("^-?\\d{1," + (param.integer != null ? param.integer : "") + "}" + (param.fraction != null ? (param.fraction > 0 ? "(\\.\\d{1," + param.fraction + "})?$" : "$") : "(\\.\\d+)?$")).test(value);
    }, "numeric value out of bounds");

    jQuery.validator.addMethod("pattern", function (value, element, param) {
        return this.optional(element) || (typeof param === 'string' ? new RegExp(param) : param).test(value);
    }, "Invalid format");

    jQuery.validator.addMethod("extension", function (value, element, param) {
        return this.optional(element) || ($.trim(param) != "" && new RegExp("^\\S.*\\.(" + param.replace(/,/g, "|") + ")$", "i").test(value));
    }, "Invalid extension");
}));