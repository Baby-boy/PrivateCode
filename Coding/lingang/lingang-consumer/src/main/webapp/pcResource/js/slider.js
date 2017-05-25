$.fn.prepare_slider = function(){	
		var x_pos = 0;
		var li_items_n = 0;	
		var right_clicks = 0;		
		var left_clicks = 0;					
		var li_col = $("#slider_list > li");		
		var li_width = li_col.outerWidth(true);		
		var viewWindow = Math.round($('.block-slider').width()/li_width);
		
		li_col.each(function(index){			
			x_pos += $(this).outerWidth(true);
			li_items_n++;								
		})	
		
		right_clicks = li_items_n - viewWindow;
		total_clicks = li_items_n - viewWindow;		
		
		$('#slider_list').css('position','relative');
		$('#slider_list').css('left','0px');
		$('#slider_list').css('width', x_pos+'px');
		
		var is_playing = false;
		var completed = function() { is_playing = false; }
		
		$('#left_but').click( function(){									
			cur_offset = $('#slider_list').position().left;
			if (!is_playing){						
				if (left_clicks > 0) {
						is_playing = true; $('#slider_list').animate({'left': cur_offset + li_width + 'px'}, 700, "linear", completed); 
						right_clicks++; 
						left_clicks--;
					} 
					else {
						is_playing = true;
						$('#slider_list').animate({'left':    -li_width*total_clicks	+ 'px'}, 700, "linear", completed); 
						right_clicks = 0;
						left_clicks = total_clicks;
					}
			}
		});		

		$('#right_but').click( function(){
			if (!is_playing){			
				cur_offset = $('#slider_list').position().left;			
			 	if (right_clicks > 0) {
						is_playing = true; 
						$('#slider_list').animate({'left': cur_offset - li_width + 'px'},700, "linear", completed );
						right_clicks--; left_clicks++; 
				} 
				else { 
						is_playing = true; $('#slider_list').animate({'left':    0	+ 'px'},700, "linear", completed ); 
						left_clicks = 0;
						right_clicks = total_clicks;
					}			 
			}
		});	
		
	}

$.fn.over = function(){						
	$(this).hover(
	   function () {
	 	 $(this).addClass("over");
	   },
	   function () {
	 	 $(this).removeClass("over");
	   }
	 );		
   }