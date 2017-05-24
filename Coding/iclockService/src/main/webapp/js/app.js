/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
var ctx = window['contextPath'] || '';
// var UEDITOR_HOME_URL = ctx + '/js/lib/ueditor/';
requirejs.config({
    baseUrl: ctx + '/js/lib',
    paths: {
        app: '../app',
        i18n: '../lib/i18n',
//        domReady: '../lib/domReady',
        jquery: [
            //'http://cdn.bootcss.com/jquery/1.9.1/jquery.min',
            //'http://lib.sinaapp.com/js/jquery/1.9.1/jquery-1.9.1.min',
            '../jquery-1.11.1.min'
            //'jquery'
        ]
        , grid: 'pui/jqgrid.custom'
        , jqgrid: 'jqgrid/js/jquery.jqGrid.min'
        , 'jqgrid.locale': 'jqgrid/js/i18n/grid.locale-cn'
        , 'jqgrid.custom': 'jqgrid/js/jqgrid.custom'
        , dom: '../widget/dom'
        , util: 'pui/util'
        , validate: 'pui/validate.custom'
        //, 'jquery.validate': ['jquery-validation-1.13.0/jquery.validate.min']
        , 'jquery.validate': ['jquery-validation-1.13.0/localization/messages_zh']
        , 'jquery.form': '../jquery.form'
        /**/
        , chosen: [
            'http://cdn.bootcss.com/chosen/1.4.2/chosen.jquery.min'
        ]
        , spinner: 'pui/spinner/spinner'
        , datepicker: ['My97DatePicker/WdatePicker']
//        , uploader: 'pui/uploader2/uploader2'
        , uploader2: 'pui/uploader2/uploader2'
        , treepicker: 'pui/treepicker/js/treepicker'

        , kindeditor: [
            //'http://kindeditor.net/ke4/kindeditor-all-min',
            'kindeditor/kindeditor-all-min'
        ]
        , plupload: 'plupload-2.1.2/plupload.full.min'
        , jstree: 'pui/treepicker/js/jstree.min'
        , 'layer': 'layer/layer'
    }
    , shim: {
        jquery: {
            exports: 'jQuery'
        }
        , jqgrid: {
            deps: ['jquery', 'jqgrid.locale']
            , exports: 'jQuery.fn.jqGrid'
            // , init: function () { require(['pui/jqgrid.custom']) }
        }
        , 'jqgrid.custom': {
            deps: ['jqgrid']
            ,exports: 'jQuery.jgrid.cmTemplate.actions2'
        }
        , 'jqgrid.locale': ['jquery']
        , 'jquery.validate': {
            deps: ['jquery', 'jquery-validation-1.13.0/jquery.validate.min']/* ,
            init: function() {
                require(['jquery-validation-1.13.0/localization/messages_zh.min'])
            }
            */
        }
        , chosen: {deps: ['jquery'], exports: 'jQuery.fn.chosen'}
        , uploader: ['plupload']
        , treepicker: ['jstree']
        , kindeditor: {exports: 'KindEditor'}

        , plupload: {exports: 'plupload'}
        , jstree: ['jquery']
        , 'layer': {
              deps: ['jquery']
            , exports: 'layer'
        }
    }
});
