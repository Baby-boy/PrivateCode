/**
 * Created by Administrator on 2014/9/29 0029.
 */
define(["appConfig","mySession"], function (appConfig,mySession) {
    return {
        handleSuccess: function (res) {
            //返回正常数据，预处理
        	if(res.data.stateCode == '1011'){
        		$.cookie("p","");
            	$.cookie("redirectUrlPlat","");
        		window.location="login.html";
        		return;
        	}
            return ( res.data );
        },
        handleError: function (response) {
            //网络连接失败处理
        },
        url: appConfig.serviceBaseUrl,
        postConfig: {
            headers: {
                'Content-Type': 'application/json',
                'Accept':'application/json',
                "Cache-Control":"no-cache"},
            transformRequest: function (data) {
                return JSON.stringify(data);
            }
        }
    };
});