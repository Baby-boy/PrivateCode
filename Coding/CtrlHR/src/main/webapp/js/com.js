//代码规范 模板
////项目公用方法
//(function(window, $) {
//	var Util = {};
//	/**
//   * 作者 刘德华
//   * 日期 20170310
//   * 取出url需要的字段名 = 后面的属性
//   * 用法 Util.getUrlParam(id);
//   */
//  //获取url参数
//	Util.getUrlParam = function(key) {
//      // 获取URL中?之后的字符
//      var str = location.search;
//      str = str.substring(1, str.length);
//      // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
//      var arr = str.split("&");
//      var obj = new Object();
//      // 将每一个数组元素以=分隔并赋给obj对象         
//      for (var i = 0; i < arr.length; i++) {
//          var tmp_arr = arr[i].split("=");
//          obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
//      }
//      return obj[key];
//  }
//	/**
//   * 作者 林立
//   * 日期 20170310
//   * 处理所有的vue-resource的response 并判断是否成功
//   * 2个回调函数, 处理成功和失败
//   * 20170321新增判断接口请求未登录
//   */
//  Util.processRes = function(response, sucessCallback, failCallback) {
//      response.json().then(function(responseData) {
//          if (responseData.success) {
//              if (sucessCallback)
//                  sucessCallback.call(response, responseData)
//          } else {
//              if (responseData.isLogin === 0) {
//                  layer.open({
//                      shadeClose: true,
//                      title: false,
//                      closeBtn: '2',
//                      area: ['408px', '258px'],
//                      content: "<h2 style='text-align:center;padding-top:20px;'>请重新登录</h2><p style='text-align:center;padding-top:30px;'>由于长时间未操作，为了您的信息安全，请重新登录</p>",
//                      btn: ["确 定"],
//                      yes: function() {
//                          window.open(ctx + "/userLogin/login");
//                      }
//                  });
//              }
//              if (failCallback)
//                  failCallback.call(response, responseData)
//          }
//      })
//  }
//  /**
//   * 作者 刘德华
//   * 创建日期 20170305
//   * 更新日期
//   * 中国标准时间转换为自己想要的格式
//   * 用法  formatChinaTime(dateValue,'yyyy-MM-dd HH:mm:ss')
//   */
//	Util.formatChinaTime = function(time, format){
//	    var t = new Date(time);
//	    var tf = function(i){return (i < 10 ? '0' : '') + i};
//	    return format.replace(/yyyy|MM|dd|HH|mm|ss/g, function(a){
//	        switch(a){
//	            case 'yyyy':
//	                return tf(t.getFullYear());
//	                break;
//	            case 'MM':
//	                return tf(t.getMonth() + 1);
//	                break;
//	            case 'mm':
//	                return tf(t.getMinutes());
//	                break;
//	            case 'dd':
//	                return tf(t.getDate());
//	                break;
//	            case 'HH':
//	                return tf(t.getHours());
//	                break;
//	            case 'ss':
//	                return tf(t.getSeconds());
//	                break;
//	        }
//	    })
//	}
//	window.Util = Util;
//})(window,jQuery);
//
///**
//	 * 作者 刘德华
//	 * 作用 项目所使用的api
//	 * 创建时间 20170310
//	 * 更新时间 20170410
//	 * 
// */
//var api = {
//	//获取群组列表
//	getGroup: "${ctx}/api/signGroup/simpleList",
//	//获取部门列表
//	getDepartment: "${ctx}/api/dept/simpleList",
//	//设置考勤点
//	setNewPlace: "${ctx}/api/signPoint/simpleList",
//	//确定添加的考勤点
//	setUpdate: "${ctx}/api/device/update",
//	//获取员工列表
//	getListUrl: "${ctx}/api/employee/list",
//	//获取信息采集
//	getMsgBrand:"${ctx}/api/employee/signInfo"
//}
//
///**
//	 * 作者 刘德华
//	 * 作用 项目时间过滤器
//	 * 创建时间 20170312
//	 * 更新时间 20170420
//	 * 用法  yourData | timeFormat('yyyy-MM-dd HH:mm:ss') 
// */
//
//Vue.filter('timeFormat', function (value,type) {
//  if(value){
//      return new Date(value).format(type)
//  }else{
//      return ""
//  }    
//});