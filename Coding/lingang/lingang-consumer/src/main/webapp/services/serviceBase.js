/**
 * Created by Administrator on 2014/9/29 0029.
 */
define(["appConfig","mySession"], function (appConfig,mySession) {
    return {
        handleSuccess: function (response) {
        	 //返回正常数据，预处理
        	if(response.data.stateCode == 00 || response.data.stateCode == 1005){
        		mySession.logout();
        		window.location="login.html";
        		return;
        	}
        	return ( response.data);
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