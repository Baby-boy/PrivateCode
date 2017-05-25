var uploadCode = "";
var imagesUrl = [];
var imagesName = "";
var _goodsPics = [];
var _goodsContentPics = [];
var xmlHttpRequest;
var _urlGoods = "https://woadby.oss-cn-beijing.aliyuncs.com/dby/";
var _urlOther = "https://woadby.oss-cn-beijing.aliyuncs.com/dby/";

//XmlHttpRequest对象  
function createXmlHttpRequest() {
    if (window.ActiveXObject) { //如果是IE浏览器
        return new ActiveXObject("Microsoft.XMLHTTP");
    } else if (window.XMLHttpRequest) { //非IE浏览器
        return new XMLHttpRequest();
    }
}
var POLICY_JSON = {
    "expiration": "2020-12-01T12:00:00.000Z",
    "conditions": [
        ["starts-with", "$key", ""],
        {"bucket": 'woadby'},
        ["starts-with", "$Content-Type", ""],
        ["content-length-range", 0, 524288000]
    ]
};
var secret = 'HLsGQc8BYaiblPM5aIPgxRfNGjnGzl';
var policyBase64 = Base64.encode(JSON.stringify(POLICY_JSON));
console.log(policyBase64)
var signature = b64_hmac_sha1(secret, policyBase64);
console.log(signature);

function uploadProgress(evt) {
    if (evt.lengthComputable) {
        var percentComplete = Math.round(evt.loaded * 100 / evt.total);
        // document.getElementById('progressNumber').innerHTML = percentComplete.toString() + '%';
    }
    else {
        // document.getElementById('progressNumber').innerHTML = 'unable to compute';
    }
}
function uploadComplete(evt) {
    // console.log( evt )
    /* This event is raised when the server send back a response */
    // alert("Done - " + evt.target.responseText );
    switch (uploadCode) {
        case "goodsCover":
            uGoodsCoverSuccess();
            break;
        case "goodsPics":
            uGoodsPicsSuccess();
            break;
        case "refundPics":
            uRefundPicsSuccess();
            break;
        case "ctcPics":
            uCtcPicsSuccess();
            break;
        case "commentPics":
            uCommentPicsSuccess();
            break;
        case "goodsContentPics":
            uGoodsContentPicsSuccess();
            break;
        case "storeSlide":
            uStoreSlideSuccess();
            break;
        case "companyBusinessLicence":
            uCompanyBusinessLicence();
            break;
        case "companyOrganizationCode":
            uCompanyOrganizationCode();
            break;
        case "legalPersonPositive":
            uLegalPersonPositive();
            break;
        case "legalPersonNegative":
            uLegalPersonNegative();
            break;
        default :
            alert("请求地址错误");
            break;
    }
}
function uploadFailed(evt) {
    alert("There was an error attempting to upload the file." + evt);
}
function uploadCanceled(evt) {
    alert("The upload has been canceled by the user or the browser dropped the connection.");
}
function uploadFile(id) {
    var file = document.getElementById(id).files[0];
    var fd = new FormData();
    imagesName = guid() + suffix(file.name);
    var uploadType = $("#" + id).attr("data-upload-type");
    var key = "";
    key = "dby/" + imagesName;
//    switch( uploadType ) {
//	  	case "other":
//	  		key = "dby/uploads/other/" + imagesName;
//	  		break;
//	  	default :
//	  		key = "dby/uploads/goods/" + imagesName;
//	  		break;
//	}

    if ($("#" + id).attr("data-upload-code") == "storeSlide") {
        if ($(".store-branner-thumb-list li").length >= 5) {
            alert("最多只能上传5张轮播图");
            return;
        }
    } else if ($("#" + id).attr("data-upload-code") == "goodsPics") {
        if ($(".up-image-goods-pics ul li").length >= 5) {
            alert("最多只能上传5张图片");
            return;
        }
    }

    fd.append('key', key);
    fd.append('Content-Type', file.type);
    fd.append('OSSAccessKeyId', 'LTAIQqrJHVmXJ7Vp');
    fd.append('policy', policyBase64)
    fd.append('signature', signature);
    fd.append("file", file);
    var xhr = createXmlHttpRequest()
    xhr.upload.addEventListener("progress", uploadProgress, false);
    xhr.addEventListener("load", uploadComplete, false);
    xhr.addEventListener("error", uploadFailed, false);
    xhr.addEventListener("abort", uploadCanceled, false);

    xhr.open('POST', 'https://woadby.oss-cn-beijing.aliyuncs.com', true); //MUST BE LAST LINE BEFORE YOU SEND 
    xhr.send(fd);
}


function uGoodsCoverSuccess() {
    //$(".goods-cover").css("display", "inline-block");
    //$(".goods-image .btn-file a").html("重新上传");
    $("input[name='goods_cover']").val(imagesName);
    $(".goods-cover img").attr("src", _urlGoods + imagesName);
    var str = '<li data-img="' + imagesName + '"><img src="' + _urlGoods + imagesName + '" /><a class="del" href="javascript:void(0)">X</a></li>';
    $(".up-image-goods-pics ul").append(str);
}

function uGoodsPicsSuccess() {
    _goodsPics.push(imagesName);
    var str = '<li data-img="' + imagesName + '"><img src="' + _urlGoods + imagesName + '" /><a class="del" href="javascript:void(0)">X</a><a href="javascript:void(0)" class="btn-g btn-goods-left prev">←</a><a href="javascript:void(0)" class="btn-g btn-goods-right next">→</a></li>';
    $(".up-image-goods-pics ul").append(str);
}

function uCtcPicsSuccess() {
    _goodsPics.push(imagesName);
    var str = '<dd>' +
        '<img src="' + _urlGoods + imagesName + '" />' +
        '<i data-img="' + imagesName + '"></i>' +
        '</dd>';
    $("#ossfile").append(str);
}

function uCommentPicsSuccess() {
    var objectCommentList = $(".comment-list[data-id='" + clickId + "']");
    var val = objectCommentList.find("input[name='commentPicsList']").val();
    objectCommentList.find("input[name='commentPicsList']").val(val + "," + imagesName);
    var str = '<dd>' +
        '<img src="' + _urlGoods + imagesName + '" />' +
        '<i data-img="' + imagesName + '"></i>' +
        '</dd>';
    objectCommentList.find(".commentPicsList").append(str);
}

function uGoodsContentPicsSuccess() {
    _goodsContentPics.push(imagesName);
    var str = '<li data-img="' + imagesName + '"><img src="' + _urlGoods + imagesName + '" /><a class="del" href="javascript:void(0)">X</a><a href="javascript:void(0)" class="btn-g btn-goods-left prev">←</a><a href="javascript:void(0)" class="btn-g btn-goods-right next">→</a></li>';
    $(".up-image-goods-content-pics ul").append(str);
}

function uStoreSlideSuccess() {
    var strB = '<li><a target="_blank" href="http://"><img src="' + _urlOther + imagesName + '" alt="" title="" /></a></li>';
    var strT = '<li>'
        + '<div class="img-all">'
        + '<img class="img" src="' + _urlOther + imagesName + '" />'
        + '</div>'
        + '<div class="img-info">'
        + '<p><input type="hidden" name="slide_src" value="' + imagesName + '" /><input type="text" name="slide_href" placeholder="http://" class="text" /></p>'
        + '</div>'
        + '</li>';
    $(".store-banner-slides").append(strB);
    $(".store-branner-thumb-list").append(strT);
}

function uCompanyBusinessLicence() {
    $(".z-company-business-licence .Z-lefttext").html("<img src='" + _urlOther + imagesName + "' />");
    $(".z-company-business-licence .Z-lefttext").next("input").val(imagesName);
}

function uCompanyOrganizationCode() {
    $(".z-company-organization-code .Z-lefttext").html("<img src='" + _urlOther + imagesName + "' />");
    $(".z-company-organization-code .Z-lefttext").next("input").val(imagesName);
}

function uLegalPersonPositive() {
    $(".z-legal-person-positive .Z-lefttext").html("<img src='" + _urlOther + imagesName + "' />");
    $(".z-legal-person-positive .Z-lefttext").next("input").val(imagesName);
}
function uLegalPersonNegative() {
    $(".z-legal-person-negative .Z-lefttext").html("<img src='" + _urlOther + imagesName + "' />");
    $(".z-legal-person-negative .Z-lefttext").next("input").val(imagesName);
}
function uRefundPicsSuccess() {
    var str = "<img src='" + _urlGoods + imagesName + "?x-oss-process=image/resize,m_pad,h_100,w_100' />";
    _goodsPics.push(imagesName);
    $(".refund-pics-list").append(str);
}