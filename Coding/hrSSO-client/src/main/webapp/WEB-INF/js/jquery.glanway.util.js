'use strict';
(function ($, window) {


    var util = {};

    util.keyHelper = {
        isDelete: function (e) {
            return e.keyCode === 46;
        },
        isBackspace : function (e) {
            return e.keyCode === 8;
        },
        isEnter : function (e) {
            return e.keyCode === 13;
        }
    };

    /*
     ** Returns the caret (cursor) position of the specified text field.
     ** Return value range is 0-oField.value.length.
     */
    util.getCaretPosition = function (oField) {

        // Initialize
        var iCaretPos = 0;

        // IE Support
        if (document.selection) {

            // Set focus on the element
            oField.focus();

            // To get cursor position, get empty selection range
            var oSel = document.selection.createRange();

            // Move selection start to 0 position
            oSel.moveStart('character', -oField.value.length);

            // The caret position is selection length
            iCaretPos = oSel.text.length;
        }

            // Firefox support
        else if (oField.selectionStart || oField.selectionStart == '0')
            iCaretPos = oField.selectionStart;

        // Return results
        return iCaretPos;
    }

    util.randomData = function (data) {
        var result = [];
        $.each(data, function (i, val) {
            if (Math.random() > 0.5) {
                result.push(val);
            }
        });

        return result;
    }

    //根据文件类型获取文件icon图片路径
    util.getExtIcon = function (fileName,root) {
        root = root || "../../";
        var extIcon = '';
        var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
        //console.log(fileExt);
        switch (fileExt) {
            case "doc":
                extIcon = 'icon_file_docx.png';
                break;
            case "docx":
                extIcon = 'icon_file_docx.png';
                break;
            case "xls":
                extIcon = 'icon_file_xls.png';
                break;
            case "xlsx":
                extIcon = 'icon_file_xls.png';
                break;
            case "ppt":
                extIcon = 'icon_file_ppt.png';
                break;
            case "pptx":
                extIcon = 'icon_file_ppt.png';
                break;
            case "rar":
                extIcon = 'icon_file_rar.png';
                break;
            case "zip":
                extIcon = 'icon_file_rar.png';
                break;
            case "7z":
                extIcon = 'icon_file_rar.png';
                break;
            case "txt":
                extIcon = 'icon_file_txt.png';
                break;
            case "rp":
                extIcon = 'icon_file_rp.png';
                break;
            case "psd":
                extIcon = 'icon_file_psd.png';
                break;
            case "pdf":
                extIcon = 'icon_file_pdf.png';
                break;
            case "jpg":
                extIcon = 'icon_file_jpg.png';
                break;
            case "png":
                extIcon = 'icon_file_png.png';
                break;
            case "gif":
                extIcon = 'icon_file_gif.png';
                break;
            case "bmp":
                extIcon = 'icon_file_bmp.png';
                break;
            case "jpge":
                extIcon = 'icon_file_jpg.png';
                break;
            case "html":
                extIcon = 'icon_file_html.png';
                break;
            case "htm":
                extIcon = 'icon_file_html.png';
                break;
            case "mp3":
                extIcon = 'icon_file_mp3.png';
                break;
            default:
                extIcon = 'icon_file_default.png';
                break;
        }
        return root + "js/plugin/plupload/src/" + extIcon;
    }


    util.formatPercent = function (num, length) {
        if (isNaN(length)) length = 2;
        return (+num * 100).toFixed(length) + "%";
    }
    
    


    window.util = util;
})(jQuery, window);



