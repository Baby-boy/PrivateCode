$.ajaxSetup({
	cache: false,
	beforeSend: function(xhr) {
		xhr.setRequestHeader('Authorization', store.get('Authorization'));
	}
});

var SellerLogin = function() {
	$.ajax({
		'url': "/SellerLogin",
		'type': 'POST',
		'data': JSON.stringify({
			"username": $("#username").val(),
			"password": $("#password").val(),
			"verifyToken": $("#verifyToken").val(),
			"verifyCode": $("verifyCode").val()
		}),
		'dataType': 'json',
		'contentType': 'application/json'
	}).always(function(f98e09fd) {
		store.set('Authorization', f98e09fd.body);
		SellerTokenLogin();
	})
};

var GetSellerImageVerifyCode = function() {
	$.ajax({
		'url': "/SellerImageVerifyCode",
		'type': 'POST'
	}).always(function(f98e09fd) {
		var vs = navigator.userAgent.toLowerCase().match(/msie ([\d.]+)/);
		if(vs && vs[1] * 1 < 8) {
			$("#verifyCode").attr("src", "/Base64ToImage?" + f98e09fd.body.image);
		} else {
			$("#verifyCode").attr("src", "data:image/png;base64," + f98e09fd.body.image);
		}
	})
};

var SellerLogout = function() {
	$.ajax({
		'url': "/SellerLogout",
		'type': 'POST'
	}).always(function(f98e09fd) {
		store.remove('Authorization');
		$("body").html(f98e09fd);
	})
};

var SellerTokenLogin = function() {
	$.ajax({
		'url': "/SellerTokenLogin",
		'type': 'POST'
	}).always(function(data, status, response) {
		if(response.responseText !== "{}")
		{
			$("body").html(response.responseText);
		}
	});
};

$(function() {
	SellerTokenLogin();
})