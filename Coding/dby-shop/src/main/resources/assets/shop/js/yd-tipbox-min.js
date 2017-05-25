(function($){
	var img_warning = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAMAAADyHTlpAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAylpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QTAxQzNEQTQ5NTE1MTFFNkIzNkI4RjQ1Nzk2NDgwMTgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QTAxQzNEQTM5NTE1MTFFNkIzNkI4RjQ1Nzk2NDgwMTgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuZGlkOmVhMmVhYzc4LTViOGItNTk0ZS04YTJkLTc5YWY4ZDQ2ODk3YiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDplYTJlYWM3OC01YjhiLTU5NGUtOGEyZC03OWFmOGQ0Njg5N2IiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5jO7fvAAABgFBMVEX/++v/99X/8r//1Cf/8bn/5oH/43P/43X/2kf/32H/0yP/0Rj/0Bb/zgj//ff/9c//8bv/6pb/6pT/54n/4Wn/20z/20r/1jH/0BP/zgv/zxH///7/0Rr//vr///3//fX//vn/1jT//vj//PD/zQP/++z/9Mn/4Wv/4GT/++n//O7/3ln/zgz/zQX/9c3/1zf//vz/317/+ub/5Hj/8LL/0Rv//PH/8bf//fT/zw//+Nz/54b/43H/2D7/+eH/3E7/8rz/0yT/77H/4Wf/0h3//vv/2UP/zQT/8Lb/4m3/3lj/+eD/8sD/1Cr/0iD/0hz/zw7/3Vb/1zn/4GP/0h7/6ZL/5oT/3lr/1S3/88P/9tD/5oP/0yH/7KL/8LT/99n/5Xz/+eP/4m//2Dv/+Nv/7J7/7aX/4nD/65v/653/2UD/6Iv/5Hr/0BT/76//1Sv/+N7/2kX/zAL/88H/7af/zw3/6Iz/6I7/5X7/++3/9cz/9Mj/2T//4Wr/////zACtXTHJAAACBklEQVR42nyVZ1vbMBCAjywgIUCgYSm2Q0LCCARIIIS9955dQAstdJcOZgvSX69jF/DpFO6T/fq1H99JugPuDKbllw+9S1v1ZdOhPxDrQQ/Bca0brp/CEYPXY5pajU+9E3J8eB+lqpZsFqoY7pBV46MoErdVDKmJUlE8FphDhVbxVEw9qnkfetKwuuoOIrJyr7IaxGdOTTbS6ERNQ7bK0sjcsT7Bt+edcDNmqbFRpLbZiSbdiHoKqn6Ocyi31Zs5RP15UzWkdFO2+hJ/VZzowN9I6ri9lN4WjDujoC9K6tlfS+2Vi9sOldLb4tfrglk5LqsHUELWxsrLsybjbggRNZwz1SzBF0D+SawPmOo0wRWg2KVeUy0ntB8CVD3mvL2M0Ag0UXWR84EZhTpJ1V6N77Uo1Dqqjv7giq2+Dm8p/OZRqY3gURyQZ32KugQAVIdpXsF8MFtP4Nrw1fMuQl8B6yOLnTbrWkpKYACPS6i5qrBdOlISXpoFrrVJVbVPQfU+xkMMOFsWqmPo8iP61e4DnxCcS1hqFid2ZKvxz04YTBV6X+0gMr+z/92lpNOJu15kQ2FExGXPQ3tz4d8NbuD7u/hjJ2S/G55ohL4EasVpf1HzS05q8BmfWpxciJKxEZvoV22yjK6YMMxISidqozYTVc8tc8KN7YbdgYgZgUj3yEQOzbh/AgwAatoUR6OdeCsAAAAASUVORK5CYII=";
	
	var img_doubt = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqCAMAAADyHTlpAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA3FpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDphMjM2OTQ4ZC01ZTRhLTU5NGMtODViYS1lYWQ2NWE5NWIxOTUiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6RTE3MjUwQTQ5NTE1MTFFNjkwNDJENzcwOEY3NDNGMjMiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6RTE3MjUwQTM5NTE1MTFFNjkwNDJENzcwOEY3NDNGMjMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjMwMTE0MGI3LWY0NmMtNjQ0Yi1iOGFkLTVhNjYwZDcxNDFmNCIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDphMjM2OTQ4ZC01ZTRhLTU5NGMtODViYS1lYWQ2NWE5NWIxOTUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4N1CTIAAAAIVBMVEX/3Ev/0RP/5oH/99n+/v7/1Sb29vXz8/P/6p3/////zQCyRu6AAAABK0lEQVR42oyV0ZaFIAhFD2KK/v8Hj+VUiHiNN3EvQEBAMSICEBEg0dxgOHGoSgLzCuWjWgnRQx3wgmc01IUc0aBLsgkP6C/yZvGBrDU+qCYJIim17JJmb5SVDukthsnDiaosDRWRMVwM7skUeQihoeosJ5BaF0D6o7VZ6EhJ3VuzoaHBRJqUAx1BhPZPyiksKtCn2hqV6sJqa2G/PrDPaiof7ZWQUUce2VOVBx25RnOZbboouWRDaeF90i/R2ZuXrMu/lxVxVK2xnSeA60cByvERZWy/4NPbKHHKQM5ZaJ4yKFMExU1WPFFrNnlo6D827NHjHhn7APhGeVeC8M6sTR2CnoS8J5/5ylvyndpxUbWDnbXBv1fBuIymzaEXjNlbha/91uV/xD3yJ8AAFVk+TZgX0wcAAAAASUVORK5CYII=";
	
	var img_error = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFgAAABYCAMAAABGS8AGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDowNzdENTFEMzk1MTgxMUU2QTMzODk1ODAyMDcxRjdFRSIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDowNzdENTFENDk1MTgxMUU2QTMzODk1ODAyMDcxRjdFRSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjA3N0Q1MUQxOTUxODExRTZBMzM4OTU4MDIwNzFGN0VFIiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjA3N0Q1MUQyOTUxODExRTZBMzM4OTU4MDIwNzFGN0VFIi8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+TPBTUQAAAGlQTFRF/efn9JeX9JmZ/evr+MPD9aCg9aWl85OT85WV9Jub//f3+tTU9aOj/OPj+9zc9J6e+tDQ+9jY+s3N+L+/9q2t/e/v+cnJ97a2+cbG97Gx/fPz9qio97Oz+Ly8+Lm59qur+9/f85GR//v78x+txAAAA5RJREFUeNq0mVmigyAMRaM4oVZbh9aOr7L/Rb4mVK04K+ZXOUpyCSGAmDY4WlfbNL2y9EzTvqZHmDNo4jk7Z0bZMSMrYAuYWXY5aHGarwQfnXLCwmAF+BzX493wYZ0YuwmRMzhZj9CtH/Gzvwx8NGt3Xo+dsX5wrR1vJAvArHICf0VDM4pe1ZQcmAu2vlN1DuOBP9y/6HQW2M/k23Y0LdYolO/++dPgXCosDsQsC6RDYjYFfhtjEemVD8XZiMbBCbnXzsUCy6U/zmPgF72S+WKZXWnYZRj8GozxhFk08DEETvCpF4gVdvJwbNEPfqN/jYNYZcCRHPWBc9SDt5L7AeE/G6wL9km/gVhtJ9Kz3wFnK+PWWIGEuwqmuGZik5Hqnm0ww8DZ/jawwJXiQguMedLIN3JFjqvb+QUfcRKJ2GzEOTZgH78UCw2G0uIN+LxRaYrmkhqMOdUWWsypfhkqz0R6wO/ql+H7FUdosr/v7D9ghh856AID0oDAli5JNKvkSWCUyEsf+CnDB9ITkT7wQXoWSMSG0GiY8q0PGPPlVSf4ghXMB2zUy1uTncgFgPJwfZ1gH1MwAC67UGg1rBUDSNWCQI+TLcgohloNV9wVcHmc9IID9C5gjmd6wagHsx+cuOV88wr1pEFgLGBuKngJ90NW91QUMmW5zmTKZdY3vB+87Y8luNcV1hKya/W4wttDFWxYFTrkttMCsfdb0jsloZTSprNH2twh0XuU6GlrCnSCI7k1UU300O1i3EyLvbZ/KjDeepcHFSxUHT/1gVG/JtVuqb6yu10U7lDGHmThHVIYNVnWFN77HRV8rm9Z32XovqemQttxDFedTJbyAGnoEgaGy/CbI2+i6cgbNJyfQ7q5+ZB+403pCj8Hh83xw99z3+1GyLPcnuRev903+NXJts3v3Jp1Db5R93PDfh1hkcNv3fYYo/YYrOWqw0HpNfCVZMaVRQZqf8tY5Y3IULtrrR98lGqHcm7c3E53rT3zR7lGdf63OyyGwdIbpXNbtN7oDsJVuoxqrGS/li9IdQHv7Q53RMDi2XcKFDXZR++Kqauu233eLQilmOrdru9goEdC0ZjQNHxvTJTu/DBYQHUlFKaDbEjD6qXeamdgYFJfYvFLz4qJLnzVJRZpnjfHovulaK7disvdqx+Z1sJrN0LHUwexeGQ3G40PvPgw1XyOymYqmR2sv77L2D9rSoyzboLXXB//CzAAszbUYm6LypwAAAAASUVORK5CYII=";
	
	var img_success = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAFgAAABYCAMAAABGS8AGAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyFpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNS1jMDE0IDc5LjE1MTQ4MSwgMjAxMy8wMy8xMy0xMjowOToxNSAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDowMzJEQTZDMzk1MTgxMUU2ODczQ0IzRDEyQjc0NDU3NCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDowMzJEQTZDNDk1MTgxMUU2ODczQ0IzRDEyQjc0NDU3NCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjAzMkRBNkMxOTUxODExRTY4NzNDQjNEMTJCNzQ0NTc0IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjAzMkRBNkMyOTUxODExRTY4NzNDQjNEMTJCNzQ0NTc0Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+8/05KAAAAYBQTFRF0ufFos+Iq9OT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";
	
	var defaults = {
		color:"#ff4c00",
		title:"提示",
		type:1,
		text:"",
		intro:"",
		callback:"",
		url:"images/loading.gif"
	}
	
	$.extend({
		alert:function(option){
			var options= $.extend(defaults,option);
			var html = [];
			html.push("<div class=\"yd-tipbox-mask\">");
			html.push("	<div class=\"yd-alert yd-show-tip\">");
			html.push("		<div class=\"title\"><p>提示</p><span>×</span></div>");
			html.push("		<div class=\"content\">");
			switch(options.type){
				case 1:
					html.push("			<div class=\"bg-pic\" style=\"background-image:url(" + img_warning + "\"></div>");
					break;
				case 2:
					html.push("			<div class=\"bg-pic\" style=\"background-image:url(" + img_doubt + "\"></div>");
					break;
				case 3:
					html.push("			<div class=\"bg-pic\" style=\"background-image:url(" + img_error + "\"></div>");
					break;
				case 4:
					html.push("			<div class=\"bg-pic\" style=\"background-image:url(" + img_success + "\"></div>");
					break;
			}
			html.push("			<div class=\"text\">"+options.info+"</div>");
			html.push("			<div class=\"btn\">");
			html.push("				<div class=\"sure\">确定</div>");
			html.push("			</div>");
			html.push("		</div>");
			html.push("	</div>");
			html.push("</div>");
			$("body").append(html.join("\n")).on("click",".yd-alert .title span",function(){
				$(".yd-alert").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
			}).one("click",".yd-alert div.sure",function(){
				$(".yd-alert").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
                if(options.callback){
                	options.callback();
                }
			});
		},
		confirm:function(option){
			var options= $.extend(defaults,option);
			var html = [];
			html.push("<div class=\"yd-tipbox-mask\">");
			html.push("<div class=\"yd-confirm yd-show-tip\">");
			html.push("	<div class=\"title\"><p>" + options.title + "</p><span>×</span></div>");
			html.push("	<div class=\"content\">");
			html.push("		<dl>");
			switch(options.type){
				case 1:
					html.push("			<dt style=\"background-image:url(" + img_warning + "\"></dt>");
					break;
				case 2:
					html.push("			<dt style=\"background-image:url(" + img_doubt + "\"></dt>");
					break;
				case 3:
					html.push("			<dt style=\"background-image:url(" + img_error + "\"></dt>");
					break;
				case 4:
					html.push("			<dt style=\"background-image:url(" + img_success + "\"></dt>");
					break;
			}
			html.push("			<dd class=\"text\">" + options.text + "</dd>");
			html.push("			<dd class=\"intro\">" + options.intro + "</dd>");
			html.push("			<dd class=\"btn\">");
			html.push("				<div class=\"sure\" style=\"background:" + options.color + ";border:1px solid " + options.color + "\">确定</div>");
			html.push("				<div class=\"cancel\">取消</div>");
			html.push("			</dd>");
			html.push("		</dl>");
			html.push("	</div>");
			html.push("</div>");
			$("body").append(html.join("\n")).on("click",".yd-confirm .title span",function(){
				$(".yd-confirm").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
			}).one("click",".yd-confirm div.sure",function(){
				$(".yd-confirm").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
                if(options.callback){
                	options.callback();
                }
			}).one("click",".yd-confirm .cancel",function(){
				$(".yd-confirm").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
                if(options.cancel){
                	options.cancel();
                }
			});
			
		},
		payment:function(option){
			var options= $.extend(defaults,option);
			var html = [];
			html.push("<div class=\"yd-tipbox-mask\">");
			html.push("<div class=\"yd-payment yd-show-tip\">");
			html.push("	<div class=\"title\"><p>" + options.title + "</p><span>×</span></div>");
			html.push("	<div class=\"content\">");
			html.push("		<dl>");
			switch(options.type){
				case 1:
					html.push("			<dt style=\"background-image:url(" + img_warning + "\"></dt>");
					break;
				case 2:
					html.push("			<dt style=\"background-image:url(" + img_doubt + "\"></dt>");
					break;
				case 3:
					html.push("			<dt style=\"background-image:url(" + img_error + "\"></dt>");
					break;
				case 4:
					html.push("			<dt style=\"background-image:url(" + img_success + "\"></dt>");
					break;
			}
			html.push("			<dd class=\"text\">" + options.text + "</dd>");
			html.push("			<dd class=\"intro\">付款完成前请不要关闭此窗口。</dd>");
			html.push("			<dd class=\"intro\">完成付款后请根据您的情况点击下面的按钮</dd>");
			html.push("			<dd class=\"btn\">");
			html.push("				<div class=\"sure\" style=\"background:" + options.color + ";border:1px solid " + options.color + "\">已完成付款</div>");
			html.push("				<div class=\"cancel\">付款遇到问题</div>");
			html.push("			</dd>");
			html.push("		</dl>");
			html.push("	</div>");
			html.push("</div>");
			$("body").append(html.join("\n")).on("click",".yd-payment .title span",function(){
				$(".yd-payment").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
			}).one("click",".yd-payment div.sure",function(){
				$(".yd-payment").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
                if(options.callback){
                	options.callback();
                }
			}).one("click",".yd-payment .cancel",function(){
				$(".yd-payment").removeClass("yd-show-tip");
				$(".yd-tipbox-mask").fadeOut(250);
                setTimeout(function(){
                    $(".yd-tipbox-mask").remove();
                },250);
                if(options.paycallback){
                	options.paycallback();
                }
			});
			
		},
		loading:function(option){
			var options= $.extend(defaults,option);
			var html = [];
			html.push("<div class=\"yd-loading\">");
			html.push("	<img src='" + options.url + "' />");
			html.push("</div>");
			$("body").append(html.join("\n"));
		},
		removeLoading:function(){
			$(".yd-loading").remove();
		}
	});
	
	
})(jQuery);
