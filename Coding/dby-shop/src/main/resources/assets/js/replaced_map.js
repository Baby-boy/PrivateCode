var map = "";
var lat,lng = 0;
$(function(){
	
	// 获取附近店铺
	
	map = new BMap.Map("l-map");
	var point = new BMap.Point(116.404, 39.915);
	map.centerAndZoom(point, 12);
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	
	
	// 点击地位
	map.addEventListener("click",function(e){
		lng = e.point.lng;
		lat = e.point.lat;
		getNearbyStore();
	});
	
	function myFun(result){
		var cityName = result.name;
		map.setCenter(cityName);
	}
	var myCity = new BMap.LocalCity();
	myCity.get(myFun);
	
	// 定位
	var geolocation = new BMap.Geolocation();
	geolocation.getCurrentPosition(function(r){
		if(this.getStatus() == BMAP_STATUS_SUCCESS){
			var mk = new BMap.Marker(r.point);
			map.addOverlay(mk);
			map.panTo(r.point);
			lat = point.lat;
			lng = point.lng;
			getNearbyStore();
		}
		else {
			alert('failed'+this.getStatus());
		}        
	},{enableHighAccuracy: true})
	
	// 随机向地图添加25个标注
//	var bounds = map.getBounds();
//	var sw = bounds.getSouthWest();
//	var ne = bounds.getNorthEast();
//	var lngSpan = Math.abs(sw.lng - ne.lng);
//	var latSpan = Math.abs(ne.lat - sw.lat);
//	for (var i = 0; i < 25; i ++) {
//		var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
//		addMarker(point);
//	}
	
	
	
	/*
	var options = {
		renderOptions: {map: map},
		onSearchComplete: function(results){
			// 判断状态是否正确
			if (local.getStatus() == BMAP_STATUS_SUCCESS){
				var s = [];
				var html = "";
				for (var i = 0; i < results.getCurrentNumPois(); i ++){
					s.push(results.getPoi(i).title + ", " + results.getPoi(i).address);
					html += "<dl>";
					html += "	<dt>";
					html += "		<img src='' \/>";
					html += "	<dd class='title'>" + results.getPoi(i).title + "<\/dd>";
					html += "	<dd class='address'>" + results.getPoi(i).address + "<\/dd>";
					html += "	<dd class='tel'>" + results.getPoi(i).phoneNumber + "<\/dd>";
					html += "<\/dl>";
				}
				$(".total-store").html("共有"+results.getCurrentNumPois()+"家门店");
				$("#r-result").html(html);
			}
		}
	};
	var local = new BMap.LocalSearch(map, options);
	local.searchNearby("饭店", "朝阳区");*/
});


/**
 * 获取附近店铺
 */
var latLngArray = [];
function getNearbyStore() {
	$(".store-list").html('<p class="load-code">定位中...</p>');
	$.ajax({
		type: "post",
		url: "/store/nearby",
		data: {lat: lat, lng: lng, p: 1},
		success: function(data) {
			var dataList = data.data;
			var str = "";
			for(var ele in dataList) {
				str += '<dl><a target="_blank" href="/store/'+ dataList[ele]["store_id"] +'">'+
				  '<dt><img src="' + _urlGoods + dataList[ele]["store_logo"] +'" /></dt>'+
				  '<dd class="title" >'+ dataList[ele]["store_name"] +'</dd>'+
				  '<dd class="address">'+ dataList[ele]["store_address"] +'</dd>'+
				  '<dd class="tel">'+ dataList[ele]["store_mobile"] +'</dd>'+
				'</a></dl>';
				
//				// 添加标注
//				var point = new BMap.Point(dataList[ele]["store_longitude"], dataList[ele]["store_latitude"]);
//				addMarker(point);
//				var arr = [dataList[ele]["store_longitude"], dataList[ele]["store_latitude"], "地址："+dataList[ele]["store_address"]];
//				latLngArray.push( arr );
				
				var longitude = dataList[ele]["store_longitude"];
				var latitude = dataList[ele]["store_latitude"];
				var address = "店铺名称："+ dataList[ele]["store_name"] +"<br/>地址："+dataList[ele]["store_address"];
				
				var marker = new BMap.Marker(new BMap.Point(longitude, latitude));  // 创建标注
			    map.addOverlay(marker);               // 将标注添加到地图中
			    addClickHandler(address, marker);
			}
			
			
			$(".store-list").html( str );
		},
		error: function() {
			alert("错误")
		}
	});
}


var opts = {
    width : 250,     // 信息窗口宽度
    height: 80,     // 信息窗口高度
    title : "店铺信息：" , // 信息窗口标题
    enableMessage:true//设置允许信息窗发送短息
};

/**
 * 添加标注
 */
function addMarker(point){
	var marker = new BMap.Marker(point);
	map.addOverlay(marker);
}

/**
 * 给标注添加点击事件
 * @param content
 * @param marker
 */
function addClickHandler(content,marker){
	marker.addEventListener("click",function(e){
      openInfo(content,e)}
    );
}

/**
 * 点击标注弹出窗口
 * @param content
 * @param e
 */
function openInfo(content,e){
    var p = e.target;
    var point = new BMap.Point(p.getPosition().lng, p.getPosition().lat);
    var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
    map.openInfoWindow(infoWindow,point); //开启信息窗口
  }