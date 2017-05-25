define(['jCookie'],
    function () {
        var ticketName = "lingangTicket";
        var loginUserInfo = "LoginUserInfo";
        var powerList="powerList";
        var parList="parList";
        /**
         * @class mySessionModule
         * @static
         */
        var mySession = {
            setTicket: function (ticketObj) {
                $.cookie(ticketName, JSON.stringify(ticketObj));
            },
            getTicket: function(){
                var myTicket = $.cookie(ticketName);
                if (myTicket != null && myTicket != "") {
                    return eval('(' + myTicket + ')');
                }
                else {
                    return null;
                }
            },
            //
            setPowerList: function (powerObj) {
                $.cookie(powerList, JSON.stringify(powerObj));
            },
            getPowerList: function(){
                var myPower = $.cookie(powerList);
                if (myPower != null && myPower != "") {
                    return eval('(' + myPower + ')');
                }
                else {
                    return null;
                }
            },
            setParList: function (parObj) {
                $.cookie(parList, JSON.stringify(parObj));
            },
            getParList: function(){
                var parPower = $.cookie(parList);
                if (parPower != null && parPower != "") {
                    return eval('(' + parPower + ')');
                }
                else {
                    return null;
                }
            },
            //
            checkIsLogged: function () {
                if (this.getTicket() == null) {
                    return false;
                }
                return true;
            },
            rememberLoginUser:function(userInfo){
                $.cookie(loginUserInfo, angular.toJson(userInfo));
            },
            getLoginUserInfo:function(){
                var user = $.cookie(loginUserInfo);
                if (user != null && user != "") {
                    return eval('(' + user + ')');
                }
                else {
                    return null;
                }
            },
            logout: function () {
                $.removeCookie(ticketName);
            }
        };
        return mySession;
    });