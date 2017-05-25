//$.noConflict();
	$(window).bind('load', function(){
		$().prepare_slider(); 
		$('#slider_list > li').over();
		//Download by http://www.codefans.net
		//=======intro================
		var slider_link = $('#right_but');
		var slider_link_index = 1;
		var slider_count = $('#slider_list > li').size();	

		function slider_intro(){
			if(slider_link_index <= slider_count){
				slider_link.trigger('click');
				slider_link_index++;
				setTimeout(function(){slider_intro()}, 5000); //select change time
			}
		}
		setTimeout(function(){slider_intro()}, 5000)
	  //===============
		
		$('#left_but').hover(
		   function () {
			 $(this).addClass("over");
		   },
		   function () {
			 $(this).removeClass("over");
		   })
		
		$('#right_but').hover(
		   function () {
			 $(this).addClass("over");
		   },
		   function () {
			 $(this).removeClass("over");
		   })
		
		$('#slider_list li').hover(
		   function () {
			 $(this).find('.product-name').stop(true, true).slideDown("slow");
		   },
		   function () {
			 $(this).find('.product-name').hide("slow");
		   })
	});	