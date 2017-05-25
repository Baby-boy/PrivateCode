
define(['library/jquery/cookie/jquery.cookie'],
    function () {
        var ticketName = "lingangToken";
        /**
         * @class mySessionModule
         * @static
         */
        var mySession = {
            setTicket: function (ticketObj) {
                $.cookie(ticketName, angular.toJson(ticketObj));
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
            checkIsLogged: function () {
                if (this.getTicket() == null) {
                    return false;
                }
                return true;
            },
            logout: function () {
                $.removeCookie(ticketName);
            },
            set: function (key,value) {
                $.cookie(key, angular.toJson(value));
            },
            get: function(key){
                var myTicket = $.cookie(key);
                if (myTicket != null && myTicket != "") {
                    return eval('(' + myTicket + ')');
                }
                else {
                    return null;
                }
            },
        };
        return mySession;
    });
