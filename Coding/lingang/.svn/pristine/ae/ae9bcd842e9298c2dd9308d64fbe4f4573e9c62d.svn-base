(function(){
	var an = $('.news-detai .an');
	if(an.length>0){
		an.click(function(){
			var _this = $(this);
			var state = _this.find('.state');
			if(state.text() == '展开'){
				state.text('收起');
				$(this).find('img').prop('src','img/Pack-up.png');
				var list = _this.prev('.series-list');
				var height = Math.ceil(list.find('li').length/4)*160;
				list.css('height',height);
			}else{
				state.text('展开');
				$(this).find('img').prop('src','img/an.png');
				_this.prev('.series-list').css('height','160px');
			}
		});
	}
	var addBookBtn = $('.addBookBtn');
	if(addBookBtn.length>0){
		var isAnima = false;
		var offset = $('.pack').offset();
		var offsetTop = offset.top - window.scrollY;
		var offsetLeft = offset.left+20;
		addBookBtn.click(function(e){
			if(isAnima){return;}
			isAnima = true;
			var clientX = e.clientX;
			var clientY = e.clientY;
			var addBook = $(this).find('.addBook');
			addBook.show();
			addBook.css('position','fixed')
			.css('left',clientX)
			.css('top',clientY)
			.css("opacity",1);
			addBook.animate({
				left:offsetLeft,
				top:offsetTop
			},1000);
			setTimeout(function(){
				isAnima = false;
				addBook.css('opacity',0);
				addBook.hide();
			},1000);
		});
	}

	var box = $(".box-con ul");
	if(box.length>0){
		var prev=$(".prev");
	    var next=$(".next");
	    var box_li=$(".box-con li");
	    var box_ul=$(".box-con ul");
	    box_li.eq(0).stop().animate({left:0},500);
	    box_li.eq(1).css('z-index',50);
	    box_li.eq(1).stop().animate({
	    	width:'40%',
	      	height:300,
	      	left:'30%',
	      	top:0
	    },500);
	   	box_li.eq(2).stop().animate({
	     	width:'35%',
	     	height:240,
	     	left:'65%',
	     	top:35
	    },500).next().css("z-index",30);
	  	next.click(function(){
	  	 	box_li=$(".box-con li");
	  	 	box_li.eq(2).css('z-index',50);
	     	box_li.eq(0).stop().animate({
	     		right:0,
	     		width:'35%',
	     		left:'65%',
	     		'z-index':30
	     	},500,function(){
	     		box_li.eq(0).css("left",'65%').appendTo(box_ul);
	     	});
	     	box_li.eq(1).stop().animate({
	     		height:240,
	     		left:0,
	     		width:'35%',
	     		top:35,
	     		'z-index':30
	     	},500);
	     	box_li.eq(2).stop().animate({
	      		height:300,
	      		left:'30%',
	      		width:'40%',
	      		top:0
	     	},500);
	  	});
	  	prev.click(function(){
	  		box_li=$(".box-con li");
	  		$(".box-con li:last").prependTo(box_ul).css("left",-320).stop().animate({left:0},500);
	     	box_li.eq(0).css('z-index',50);
	     	box_li.eq(2).stop().animate({
	     		width:'35%',
	      		height:240,
	      		left:'0',
	      		top:35
	    	 },500).css("z-index",30);
	     	box_li.eq(1).stop().animate({
	     		width:'35%',
	        	height:240,
	        	left:'65%',
	        	top:35
	     	},500).css("z-index",30);
	     	box_li.eq(0).stop().animate({
	     		width:'40%',
	      		height:300,
	      		left:'30%',
	      		top:0
	     	},500);
	  	});
	}
	/*var dialog = $('.dialog');
	if(dialog.length>0){
		dialog.bind('mousedown',function(e){
			var _this = $(this);
			var x = parseInt(e.clientX - _this.offset().left);
			var y = parseInt(e.clientX - _this.offset().top);
			console.log(x);
			console.log(y)
			$(document).bind('mousemove',function(e){
				_this.css('left',e.clientX-x).css('top',e.clientY-y);
			});
			$(document).bind('mouseup',function(){
				$(document).unbind('mousemove');
				$(document).unbind('mouseup');
			});
		});
	}*/
})();
var check = function(){
	this.checkChange = function(t,n){
		var _this = $(t);
		var checkbox = _this.find('input[type="checkbox"]');
		var isCheck = checkbox.prop('checked');
		if(isCheck == true){
			_this.removeClass('check');
			checkbox.prop('checked',false);
		}else{
			if(n){
				$('.check-i.'+n).removeClass('check');
				$('.check-i.'+n).find('input[type="checkbox"]').prop('checked',false);
				_this.addClass('check');
				checkbox.prop('checked',true);
				return;
			}
			$('.checkAll').removeClass('check');
			$('.checkAll').find('input[type="checkbox"]').prop('checked',false);
			_this.addClass('check');
			checkbox.prop('checked',true);
		}
	}
	this.itemChange = function(t){
		var _this = $(t);
		var checkbox = _this.find('input[type="checkbox"]');
		var isCheck = checkbox.prop('checked');
		if(isCheck == true){
			_this.removeClass('check');
			checkbox.prop('checked',false);
			$('.checkAll').removeClass('check');
			$('.checkAll').find('input[type="checkbox"]').prop('checked',false);
		}else{
			_this.addClass('check');
			checkbox.prop('checked',true);
		}
	}
	this.addChange = function(t){
		var _this = $(t);
		var checkbox = _this.find('input[type="checkbox"]');
		var isCheck = checkbox.prop('checked');
		if(isCheck == true){
			$('.check-i').removeClass('check');
			$('.check-i').find('input[type="checkbox"]').prop('checked',false);
			$('.checkAll').removeClass('check');
			$('.checkAll').find('input[type="checkbox"]').prop('checked',false);
		}else{
			$('.check-i').addClass('check');
			$('.check-i').find('input[type="checkbox"]').prop('checked',true);
			_this.addClass('check');
			checkbox.prop('checked',true);
		}
	}
}
var mycheck = new check();
var dialog = function(){
	this.show = function(){
		$('.dialog-fade').show();
		$('.dialog').show(500);
	}
	this.hide = function(){
		$('.dialog-fade').hide();
		$('.dialog').hide(500);
	}
	this.yhshow = function(){
		$('.dialog-fade').show();
		$('.dialog-yh').show(500);
	}
	this.personSub = function(){
		$('.dialog-fade').hide();
		$('.dialog-person').hide(500);
		var check = $('.check-i.person.check');
		if(check.length<1){return;}
		var name = check.parents('.row').find('.name').text();
		var card = check.parents('.row').find('.card').text();
		$('.s-name').text(name);
		$('.phonenum').text(card);
	}
	this.yhSub = function(){
		$('.dialog-fade').hide();
		$('.dialog-yh').hide(500);
		var check = $('.check-i.yh.check');
		if(check.length<1){return;}
		var name = check.parents('.row').find('.yhCard').text();
		$('.s-yhCard').text(name);
	}
}
var mdialog = new dialog();