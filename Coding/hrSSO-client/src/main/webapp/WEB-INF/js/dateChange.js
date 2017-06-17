(function(window,$) {
	var DateEdit = {};
	/**
    *当前时间戳
     */
	DateEdit.CurTime = function(){
                return Date.parse(new Date())/1000;
           }
    /**              
    * 日期转成时间戳            
    */
   DateEdit.DateToUnix = function(string) {
                var f = string;
                var d = (f[0] ? f[0] : '').split('-', 3);
                var t = (f[1] ? f[1] : '').split(':', 3);
                return (new Date(
                        parseInt(d[0], 10) || null,
                        (parseInt(d[1], 10) || 1) - 1,
                        parseInt(d[2], 10) || null,
                        parseInt(t[0], 10) || null,
                        parseInt(t[1], 10) || null,
                        parseInt(t[2], 10) || null
                        )).getTime() / 1000;
            }
  	/**              
    * 时间戳转成日期             
    */
    DateEdit.UnixToDate = function(unixTime, isFull, timeZone) {
        if (typeof (timeZone) == 'number')
            {
                unixTime = parseInt(unixTime) + parseInt(timeZone) * 60 * 60;
            }
            var time = new Date(unixTime * 1000);
            var ymdhis = "";
            ymdhis += time.getUTCFullYear() + "-";
            ymdhis += (time.getUTCMonth()+1) + "-";
            ymdhis += time.getUTCDate();
            if (isFull === true)
            {
                ymdhis += " " + time.getUTCHours() + ":";
                ymdhis += time.getUTCMinutes() + ":";
                ymdhis += time.getUTCSeconds();
            }
            return ymdhis;
        }
    window.DateEdit = DateEdit;
})(window,jQuery);
