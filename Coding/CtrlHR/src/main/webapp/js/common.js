//可共用状态判断-可添加
//var CONST = {
	var employeeState = [
		{
			id:1,
			state:"试用",
		},
		{
			id:2,
			state:"转正",
		},
		{
			id:3,
			state:"离职",
		}
	]
//}
//时间戳转换为自己想要的格式
// 用法  new Date(dateValue).format('yyyy-MM-dd HH:mm:ss')
Date.prototype.format = function(format) {
       var date = {
              "M+": this.getMonth() + 1,
              "d+": this.getDate(),
              "H+": this.getHours(),
              "m+": this.getMinutes(),
              "s+": this.getSeconds(),
              "q+": Math.floor((this.getMonth() + 3) / 3),
              "S+": this.getMilliseconds()
       };
       if (/(y+)/i.test(format)) {
              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
       }
       for (var k in date) {
              if (new RegExp("(" + k + ")").test(format)) {
                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
              }
       }
       return format;
} 
//时间戳过滤器
//用法  {{ yourData | timeFormat('yyyy-MM-dd HH:mm:ss') }}
Vue.filter('timeFormat', function (value,type) {
    if(value){
        return new Date(value).format(type)
    }else{
        return ""
    }    
});
//中国标准时间转换为自己想要的格式
// 用法  formatChinaTime(dateValue,'yyyy-MM-dd HH:mm:ss')
var formatChinaTime = function(time, format){
    var t = new Date(time);
    var tf = function(i){return (i < 10 ? '0' : '') + i};
    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
        switch(a){
            case 'yyyy':
                return tf(t.getFullYear());
                break;
            case 'MM':
                return tf(t.getMonth() + 1);
                break;
            case 'mm':
                return tf(t.getMinutes());
                break;
            case 'dd':
                return tf(t.getDate());
                break;
            case 'HH':
                return tf(t.getHours());
                break;
            case 'ss':
                return tf(t.getSeconds());
                break;
        }
    })
}
