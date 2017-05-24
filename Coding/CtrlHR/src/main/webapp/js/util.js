(function(window, $) {
    var Util = {};
    /**
     * 作者 林立
     * 日期 20170311
     * 
     */
    Util.copyObject = function(target, source) {
        for (var p in target) {
            target[p] = source[p]
        }
        // keys.forEach(function(key) {
        //     target[key] = source[key]
        // }, this);
    }
    /**
     * 作者 林立
     * 日期 20170311
     * 为一个Vue的data添加一个新的属性,如果是数组, 则为每一项都添加一个新的属性
     */
    Util.addVueDataProperty = function($vue, source, key, defaultValue) {
        if (source instanceof Array) {
            for (var idx = 0; idx < source.length; idx++) {
                var item = source[idx];
                $vue.$set(item, key, defaultValue)
            }
        } else {
            $vue.$set(source, key, defaultValue)
        }
    }
    /**
     * 作者 林立
     * 日期 20170310
     * 处理所有的vue-resource的response 并判断是否成功
     * 2个回调函数, 处理成功和失败
     * 20170321新增判断接口请求未登录
     */
    Util.processRes = function(response, sucessCallback, failCallback) {
        response.json().then(function(responseData) {
            if (responseData.success) {
                if (sucessCallback)
                    sucessCallback.call(response, responseData)
            } else {
                if (responseData.isLogin === 0) {
                    layer.open({
                        shadeClose: true,
                        title: false,
                        closeBtn: '2',
                        area: ['408px', '258px'],
                        content: "<h2 style='text-align:center;padding-top:20px;'>请重新登录</h2><p style='text-align:center;padding-top:30px;'>由于长时间未操作，为了您的信息安全，请重新登录</p>",
                        btn: ["确 定"],
                        yes: function() {
                            window.open(ctx + "/userLogin/login");
                        }
                    });
                }
                if (failCallback)
                    failCallback.call(response, responseData)
            }
        })
    }
    /**
     * 作者 林立
     * 日期 20170310
     * 把json对象转成URL形式
     * param: json
     * key: 转成url之后,给每个参数附加一个父节点, 比如 key.p1=1
     */
    Util.parseParam = function(param, key) {
            var paramStr = "";
            var me = this;
            if (param instanceof String || param instanceof Number || param instanceof Boolean) {
                var value = encodeURIComponent(param);
                if (value)
                    paramStr += "&" + key + "=" + value;
            } else {
                $.each(param, function(i) {
                    var k = key == null ? i : key + (param instanceof Array ? "[" + i + "]" : "." + i);
                    var value = me.parseParam(this, k)
                    if (value)
                        paramStr += '&' + value;
                });
            }
            return paramStr.substr(1);
        }
        /**
         * 作者 刘德华
         * 日期 20170310
         * 取出url需要的字段名 = 后面的属性
         * 用法 Util.getUrlParam(id);
         */
        //获取url参数
    Util.getUrlParam = function(key) {
        // 获取URL中?之后的字符
        var str = location.search;
        str = str.substring(1, str.length);
        // 以&分隔字符串，获得类似name=xiaoli这样的元素数组
        var arr = str.split("&");
        var obj = new Object();
        // 将每一个数组元素以=分隔并赋给obj对象         
        for (var i = 0; i < arr.length; i++) {
            var tmp_arr = arr[i].split("=");
            obj[decodeURIComponent(tmp_arr[0])] = decodeURIComponent(tmp_arr[1]);
        }
        return obj[key];
    }
    Util.getFullImagePath = function(path, basePath) {
        if (path.indexOf("http") > -1) {
            return path;
        } else {
            return basePath + path;
        }
    }
    window.Util = Util;
})(window, jQuery);