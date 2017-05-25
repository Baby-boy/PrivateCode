$(function () {
 	// banner
 	var options={
 		imgpar:$(".banner-img"),
 		btnbox:$(".btn-box"),
 		cgwidth:$(window).width(),
 		clearDom:$(".banner-box")
 	};
 	lunbos(options);
 function lunbos(options){
 	var btns="<li></li>";
    for(var i=0;i<options.imgpar.length;i++){
        $(options.btnbox).append(btns);
    }
    // $(options.btnbox).find("li").eq(0).addClass("now");
    $(options.btnbox).find("li").eq(0).css({"width":"36px","background":"#3CC8B4"});
    	var cwed=options.cgwidth;
    	if(cwed<=1200){cwed=1200};
		var $img=options.imgpar;
		var $dian=$(options.btnbox).find("li");
		$img.css("left",cwed+"px").eq(0).css("left","0");
		var num=0;
		var next=0;


		function move(){
			next++;
			if(next>=$img.length){
				next=0;
			}
			$img.eq(next).css({"left":cwed+"px"});
			$img.eq(num).animate({"left":-cwed+"px"},700);
			$img.eq(next).animate({"left":"0"},700,function(){
				flag2=true;
			});
			$dian.css({"background":"#fff"}).animate({"width":"10px"}).eq(next).css({"background":"#3CC8B4"}).animate({"width":"36px"});
			num=next;
		}
		var t=setInterval(move,6000);


		function moveR(){
			next--;
			if(next<=-1){
				next=$img.length-1;
			}
			$img.eq(next).css({"left":-cwed+"px"});
			$img.eq(num).animate({"left":cwed+"px"},700);
			$img.eq(next).animate({"left":"0"},700,function(){
				flag2=true;
			});
			$dian.css({"background":"#fff"}).animate({"width":"10px"}).eq(next).css({"background":"#3CC8B4"}).animate({"width":"36px"});
			num=next;
		}


		options.clearDom.hover(function(){
			clearInterval(t);
		},function(){
			t=setInterval(move,6000);
		})

		var flag=true;
		$dian.click(function(){			
			if(flag){
				flag=false;
				var index=$(this).index();
				$dian.css({"background":"#fff"}).animate({"width":"10px"}).eq(index).css({"background":"#3CC8B4"}).animate({"width":"36px"});
				if(next==index){
					flag=true;
					return;
					
				}
				if(next<index){
					$img.eq(index).css({"left":cwed+"px"});
					$img.eq(next).animate({"left":-cwed+"px"},700);
					$img.eq(index).animate({"left":"0"},700,function(){
						flag=true;
					});
				}else{
					$img.eq(index).css({"left":-cwed+"px"});
					$img.eq(next).animate({"left":cwed+"px"},700);
					$img.eq(index).animate({"left":"0"},700,function(){
						flag=true;
					});
				}
				
				next=index;
				num=next;
			}
			
		})


		// $dian.mouseover(function(){
		// 	var mousei=$(this).index();
		// 	$dian.removeClass("now").eq(next).addClass("now");
		// 	$dian.eq(mousei).addClass("now");
		// });
		// $dian.mouseout(function(){
		// 	$dian.removeClass("now").eq(next).addClass("now");
		// })



		// var flag2=true;
		// $(".left").click(function(){
		// 	if(flag2){
		// 		flag2=false;
		// 		moveR();
		// 	}
			
		// });
		// $(".right").click(function(){
		// 	if(flag2){
		// 		flag2=false;
		// 		move();
		// 	}
			
		// });
 }

	$(window).scroll(function () {
		var top=$(window).scrollTop();
		var tops=$(".deal-box").offset().top-$(window).height()+200;
		console.log(top+":"+tops);
		if(top>=tops){
			$(".deal-box li").addClass("active");
		}
	})
});