$(function(){
	$('.yd-page ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});

	$(".work-select").select({
		change:function () {
			// 下拉菜单内容改变事件
		}
	});
	$(".work-select2").select();
	$(".work-select3").select();
	$(".work-select4").select();

	$(".yd-clearing").click(function () {
		$(".yd-type select").attr("data-value","");
		$(".yd-type select").val("");
		$(".select_text").each(function (i) {
			$(".select_text").eq(i).text($(".yd-type select").eq(i).find("option").eq(0).text())
		})
	});

	/*var pagedate={
		pageCount:3,
		current:1,
		backFn:function(p){
			//console.log(p);
		}
	};
	$(".tcdPageCode").createPage(pagedate);*/
	// 基于准备好的dom，初始化echarts实例
	var myChart = [];
	var option=[];
	var workdata=[];
	$(".yd-echarts").each(function(i,obj){
		workdata.push([
			{
				value:parseInt($(obj).attr("data-val")),
				name:"任务完成"
			},
			{
				value:100-parseInt($(obj).attr("data-val")),
				name:"完成进度"
			}
		])
	})
	
	for(var i=0;i<workdata.length;i++){
		$(".echarts-list:eq("+i+") .yd-p1 span").html(workdata[i][0].value);
		$(".echarts-list:eq("+i+") .yd-p1 i").html("%");
		if(workdata[i][0].value==100){
			$(".echarts-list:eq("+i+") .yd-p2").html(workdata[i][0].name);
		}else{
			$(".echarts-list:eq("+i+") .yd-p3").html(workdata[i][1].name);
		}

	}

	$(".yd-echarts").each(function (i) {
		myChart.push(echarts.init($(".yd-echarts").eq(i)[0]));
	});
	for(var i=0;i<myChart.length;i++){
		option.push({
			tooltip: {
				show:false
			},
			legend: {
				show:false,
				orient: 'vertical',
				x: 'left',
				width:'107',
				height:'107',
				data:['任务完成','完成进度']
			},
			color:['#3dc8b4',"#EEEEEE"],
			series: [
				{
					type:'pie',
					radius: ['90%', '100%'],
					avoidLabelOverlap: false,
					hoverAnimation:false,
					silent:true,
					label: {
						normal: {
							show: false,
							position: 'center'
						},
						emphasis: {
							show: false,
							textStyle: {
								fontSize: '12'
							}
						}
					},
					labelLine: {
						normal: {
							show: false
						}
					},
					data:workdata[i]
				}
			]
		});
	}
	// 指定图表的配置项和数据
	// 使用刚指定的配置项和数据显示图表。
	for(var i=0;i<workdata.length;i++){
		myChart[i].setOption(option[i]);
	}

});