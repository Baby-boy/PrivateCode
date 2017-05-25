$(function(){
	var map = new BMap.Map("l-map");        
	map.enableScrollWheelZoom();   //启用滚轮放大缩小，默认禁用
	map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	
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
	local.searchNearby("银行", "朝阳区");
});
