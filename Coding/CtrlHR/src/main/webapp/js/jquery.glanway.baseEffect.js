'use strict';
(function ($, window) {
	// 下拉组件
	function getPosition($target) {

        var offset = $target.offset();
        var parentOffset = $target.offsetParent().offset();
        return {
            "top": offset.top - parentOffset.top,
            "left": offset.left-parentOffset.left
        };
    }

    function activeItemOp($btn) {
        $btn.next(".dropdown-list").show();
        $btn.addClass("active");
    }

    function unActiveItemOp($item) {
        $item.find(".dropdown-list").hide();
        $item.find(".dropdown-btn").removeClass("active");
    }

    function getOverflow($container,$list) {
        var conHeight = $container.outerHeight();
        //var conWidth = $container.outerWidth();
        var conOffset = $container.offset();

        var listHeight = $list.outerHeight();
        var listWidth = $list.outerWidth();
        var listOffset = $list.offset();

        return {
            left: listWidth - (listOffset.left - conOffset.left),

            bottom: (listOffset.top - conOffset.top + listHeight) -conHeight
        };

    }

    $.fn.extend({
        "dropOperation": function(params) {
            //params = {
            //    "operation": function (type, id) {
            //        alert($(this).find("[data-id]").html() + " " + type + " " + id);
            //    }
            //};

            var $container = $(this);

            $container.on("click", ".dropdown-btn", function () {
                $(".dropdown-list").hide();

                var $me = $(this);
                if ($me.hasClass("active")) {
                    unActiveItemOp($container);
                } else {
                    var $item = $me.parents("[data-id]");
                    if ($item.length > 1) $item = $item.first();
                    unActiveItemOp($container);
                    activeItemOp($me);

                    var $list = $item.find(".dropdown-list");
                    $list.css({
                        "margin-left": 0,
                        "margin-top": 0
                    });

                    var offset = getPosition($(this));
                    $list.css({
                        "top": offset.top,
                        "left": offset.left
                    });

                    var overflow = getOverflow($container, $list);
                    if (params.calcPosition && overflow.bottom > 0) {
                        $list.css("margin-top", (0 - $list.outerHeight() + $me.outerHeight()));
                    } else {
                        $list.css("margin-top", 0);
                    }

                    if (params.calcPosition && overflow.left > 0) {
                        $list.css("margin-left", $me.outerWidth());
                    } else {
                        $list.css("margin-left", (0 - $list.outerWidth()));
                    }
                }
                return false;
            });

            $container.on("click", "[dropdown-op]", function (e) {
                var $me = $(this);
                var op = $me.attr("dropdown-op");
                var $opItem = $me.parents("[data-id]");
                unActiveItemOp($opItem);

                if (params.operation) {
                    params.operation.call($container, op, $opItem.data("id"),$me);
                }
				/*
                 *  2016.12.8
                 * by zhangming
                 * 是否阻止冒泡事件
                 *
                 */
                if(params.prevent){
                	e.stopPropagation();
                }
            });

            $(".dropdown-list").windowFocus(function() {
                var $me = $(this);
                var $opItem = $me.parents("[data-id]");
                unActiveItemOp($opItem);
            });
        }
    });


    /*
    *当点击一个元素非当前元素本身的时候
    */
    var blurList = [];
    $(window).on("click", function(e) {
        //顶部账号信息弹框
        var $target = $(e.target);

        $.each(blurList, function(i, me) {
            if (me.callback) {
                if ($target.closest(me).length === 0) {
                    //console.log("window clicked, run focus method.");
                    //$(me).hide();

                    me.callback.call(me);
                }

            }
        });

    });

    $.fn.extend({
        "windowFocus": function(callback) {
            var $list = $(this);
            $list.each(function() {
                if (callback) {
                    this.callback = callback;
                }

                blurList.push(this);
            });
        }
    });

    /* 2016.12.3
     * zhangming
     * 修改点击其他位置隐藏本身bug  windowFocus事件委托存在bug
     */
    $(window).on("click", function(e) {
		//debugger
		if (!$(e.target).is(".operateBox,.gw-lt-operateBox")) {
			$(".dropdown-list").hide();
			$(".dropdown-btn").removeClass("active")
		};
	});


    //点击一个元素，触发另一个元素的显示隐藏
    $.fn.extend({
        "toggleTarget": function(targetSelector, callback) {
            var $me = $(this);
            var me = this;
            $me.click(function() {
                var $target = $(targetSelector);
                $target.toggle();
                if (callback) {
                    callback.call(me, $target[0], $target.is(":visible"));
                }

                return false;
            });
        }
    });

    //点击一个元素，触发另一个元素的显示隐藏，当点击window的时候弹窗也需要隐藏
    $.fn.extend({
        "popTarget": function(targetSelector, callback) {

            var $trigger = $(this);
            var $targetSelector = $(targetSelector);
            $(this).toggleTarget(targetSelector, function(target, visible) {
                if (callback) {
                    callback.call($trigger, target, visible);
                }

            });

            $targetSelector.windowFocus(function() {
                $targetSelector.hide();
                if (callback) {
                    callback.call($trigger, this, false);
                }
            });

        }
    });

    //鼠标进入一个元素，触发另一个元素的显示隐藏
    $.fn.extend({
        "hoverTarget": function(targetSelector, callback) {
            var timer;
            var $me = $(this);
            var me = this;
            var $target = $(targetSelector);
            $me.hover(function() {
                clearTimeout(timer);
                $target.show();
                if (callback) {
                    callback.call(me, $target[0], true);
                }
            }, function() {
                timer = setTimeout(function() {
                    $target.hide();
                    if (callback) {
                        callback.call(me, $target[0], false);
                    }
                }, 200);
            });

            $target.hover(function() {
                clearTimeout(timer);
            }, function() {
                $target.hide();
                if (callback) {
                    callback.call(me, $target[0], false);
                }
            });

        }
    });

    //tab切换
    $.fn.extend({
        "tabChange": function(targetSelector, activeClass, callback) {
            var $me = $(this);
            var $target = $(targetSelector);

            $me.each(function(i) {
                var $currentTab = $(this);
                $currentTab.click(function() {
                    $target.hide();
                    var $currentTarget = $target.eq(i);
                    $currentTarget.show();

                    if (activeClass) {
                        $me.removeClass(activeClass);
                        $currentTab.addClass(activeClass);
                    }

                    if (callback) {
                        callback.call($currentTab[0], $currentTarget);
                    }
                });
            });
        }
    });

    //全选
    $.fn.extend({
        "checkAll": function(params) {
            var _default = {
            	"disabledCls": "disabled",
                "text": "全选",
                "activeText": "取消全选",
                "activeAllClass": "on",
                "activeClass": "on",
                "checkbox": ".checkSelectBox",
                "checked": false,
                "beforeCheckAll": function() {},
                "beforeCheck": function() {},
                "afterCheckAll": function() {},
                "afterCheck": function() {}
            };

            $.extend(_default, params);

            var $me = $(this);

            //全选按钮的事件
            function check(isChecked) {
                if (_default.beforeCheckAll) {
                    _default.beforeCheckAll.call($me, isChecked);
                }

                if (isChecked) {
                    $me.addClass(_default.activeAllClass);
                    $me.text(_default.activeText);
                    $(_default.checkbox).addClass(_default.activeClass);
                } else {
                    $me.removeClass(_default.activeAllClass);
                    $me.text(_default.text);
                    $(_default.checkbox).removeClass(_default.activeClass);
                }

                if (_default.afterCheckAll) {
                    _default.afterCheckAll.call($me, isChecked);
                }
            }

            check(_default.checked);

            $me.click(function() {
            	if (!$me.hasClass(_default.disabledCls)) {
                	check(!$me.hasClass(_default.activeAllClass));
                }
            });

            function bindCheckboxEvent() {

                $("body").on("click", _default.checkbox, function(e) {
                    var $checkbox = $(this);
                    var isChecked = !$checkbox.hasClass(_default.activeClass);
                    if (_default.beforeCheck) {
                        _default.beforeCheck.call($checkbox, isChecked);
                    }

                    if (isChecked) {
                        $checkbox.addClass(_default.activeClass);
                        if ($(_default.checkbox).not("." + _default.activeClass).length === 0) {
                            $me.addClass(_default.activeAllClass);
                            $me.text(_default.activeText);
                        }
                    } else {
                        $checkbox.removeClass(_default.activeClass);
                        $me.removeClass(_default.activeAllClass);
                        $me.text(_default.text);
                    }

                    if (_default.afterCheck) {
                        _default.afterCheck.call($checkbox, isChecked);
                    }
                });

                //$(_default.checkbox).each(function() {
                //    var $checkbox = $(this);
                //    if ($checkbox.attr("check-bind")) return;


                //    $checkbox.off("click");
                //    $checkbox.click(function() {
                //        var isChecked = !$checkbox.hasClass(_default.activeClass);
                //        if (isChecked) {
                //            $checkbox.addClass(_default.activeClass);
                //            if ($(_default.checkbox).not("." + _default.activeClass).length === 0) {
                //                $me.addClass(_default.activeAllClass);
                //                $me.text(_default.activeText);
                //            }
                //        } else {
                //            $checkbox.removeClass(_default.activeClass);
                //            $me.removeClass(_default.activeAllClass);
                //            $me.text(_default.text);
                //        }

                //        if (_default.afterCheck) {
                //            _default.afterCheck.call($checkbox, isChecked);
                //        }

                //    });

                //    $checkbox.attr("check-bind", 1);
                //});


            }

            bindCheckboxEvent();

            $me.reset = function() {
                check(_default.checked);
                //bindCheckboxEvent();
            }

            $me.getCheckedVal = function() {
                var result = "";
                $(_default.checkbox).filter("." + _default.activeClass).each(function(i) {
                    result = result + $(this).parents("[data-id]").data("id");
                });

                return result;
            }

					$me.checked=function(){
						return	$me.hasClass(_default.activeAllClass);
					}

            return $me;
        }
    });

	//垂直居中
    $.fn.extend({
        "verticalCenter": function(offsetTop) {
            var $me = $(this);
            $me.show();
            var leftNavHeight = $me.outerHeight();
            $me.css({
                "margin-top": -leftNavHeight / 2 + (offsetTop / 2),
                "position": "fixed",
                "top":"50%"
            });
            return $me;
        }
    });

    //环形进度条
    $.fn.extend({
    	"annular":function(val,color){
    	    var $me = $(this);
    	    var barColor="#ffa800";
    	    if(typeof(color)!="undefined"&&color!=null){
    	    	barColor=color;
    	    }

    	    if($me.find("canvas").length==1){
    	    	$me.find("canvas").remove();
    	    }
    	    /* #72
    	     * chenyanxi
    	     * 进度条插件替换
    	     */

    	    /*var num = val * 3.6;
    		$me.find("span").text(val);
    		if(num<=180){
    		    $(this).find('.pie_right').css('transform', "rotate(" + num + "deg)");
    		}else{
    		    $(this).find('.pie_right').css('transform', "rotate(180deg)");
    		    $(this).find('.pie_left').css('transform', "rotate(" + (num - 180) + "deg)");
    		}*/

    	    $me.radialIndicator({
                barColor: barColor,    //进度条颜色
                barWidth: 5,           //宽度
                roundCorner: true,      //进度条是否圆角
                percentage: true,        //是否百分比，true百分比，false数字
                radius:15
		    });
		    var radialObj = $me.data('radialIndicator');
		    radialObj.animate(val);
    	}

    });


    //没有搜索条件
    $.fn.extend({
    	"noSeacherData":function(params){
    		var $this=$(this);
    		var nodataHtml='<div class="notData"><img src="../images/icon-zero-null.png"></div>';
    		$this.html(nodataHtml);
    	}
    });

    $.fn.extend({
        gwCustomScrollbar: function() {
            if ($.fn.mCustomScrollbar) {
                var settings = {
                    advanced: { autoExpandHorizontalScroll: true },
                    theme: "ctrlteam",
                    scrollButtons: {
                        enable: true,
                        scrollType: "pixels",
                        scrollAmount: 200
                    },
                    mouseWheel: {
                        enable: true,
                        scrollType: "pixels",
                        scrollAmount: 200
                    }
                };


                $(this).mCustomScrollbar(settings);
            }
        }
    });





	/*
	 * 2016.12.1
	 * 任务汇报弹窗进度条设置
	 */
	 $.fn.extend({
	 	"progress":function(setProgress,callback){
	 		var me = this;
	 		var $me = $(me);
	 		var $container = $me;
	 		var $target = $container.find(".select-user-input");
	 		var $selectMenu = $container.find(".select-user-droplist");
	 		var $btn = $(".select-user-ico");
	 		var tpl ='';
	 		var html = "";
	 		var progressVal ="";
	 		var length = 100/setProgress;
	 		var $progress = $container.find(".progress");
	 		// 初始化UI.
	 		for (var i = 0; i < length; i++) {
	 			tpl = '<div class="select-user-dropitem" data-id='+ i +'><span>'+ (i + 1) * setProgress +'%</span></div>';
	 			html += tpl
	 		}
	 		$selectMenu.html(html);

	 		// 初始化事件
	 		$container.on('click', '.select-user-dropitem', function () {
	 			var me = this,
	 				$self = $(me),
	 				value = $self.attr('data-id');
	 				progressVal =  $self.text().replace("%","")
				$target.val(progressVal);
				$progress.width(progressVal+"%");
				if(callback){
					callback.call(this, value,progressVal);
				};
	 		});

	 		//输入
	 		$target.focus(function(){
	 			$target.select();
	 		});
	 		$target.blur(function () {
	 			var me = this,
					$self = $(me);
	 			progressVal = $self.val();
	 			if(progressVal>100){
	 				progressVal=100;
	 				$self.val(100);
	 			}
	 			$progress.width(progressVal+"%");
	 			if(callback){
	 			callback.call(this, progressVal);
	 			};
	 		});
	 		$target.keydown(function(e){
	 			if(e.keyCode == 13){
	 				$target.blur();
	 			}
	 		});

	 		//下拉操作
	 		$btn.click(function(){
	 			if($btn.hasClass("on")){
	 				$btn.removeClass("on");
	 				$selectMenu.hide();
	 			}else{
	 				$btn.addClass("on");
	 				$selectMenu.show();
	 			}
	 		});
	 		$btn.windowFocus(function() {
	            $selectMenu.hide();
	            $btn.removeClass("on");
	        });
	 	}
	 });

	 //没有搜索条件
    $.fn.extend({
    	"noSeacherData":function(params){
    		var $this=$(this);
    		var nodataHtml='<div class="notData"><img src="../../images/icon-zero-null.png"></div>';
    		$this.html(nodataHtml);
    	}
    });

    //限制输入框只能输入数字
    $.fn.extend({
        "numberInput": function (params) {
            if (!params.input) return;

            function filterInput() {
                if (!$(this).attr("readonly")) {
                    var value = this.value;
                    value = value.replace(/[^\Z0-9]/g, "");
                    $(this).val(value);
                }
            }

            //输入计划量
            var $this = $(this);
            $this.on("keyup", params.input, function (e) {
                if (params.beforeParse)
                    params.beforeParse.call(this, e, 'keyup');

                filterInput.call(this);
            });

            $this.on("paste", params.input, function (e) {
                if (params.beforeParse)
                    params.beforeParse.call(this, e, 'paste');

                filterInput.call(this);
            });

            $this.on("contextMenu", params.input, function (e) {
                if (params.beforeParse)
                    params.beforeParse.call(this, e, 'contextMenu');

                filterInput.call(this);
            });
        }
    });

	 //签字
	 $.fn.extend({
	 	"sign":function(params){
	 		var me = this,
	 			$me =$(me);

	 		var _default = {
            	"src": "../../images/duke.png",
                "name": "duke",
                "id": "1",
	 		    "isShow":0,
                "disbaled":false,
                "tip":"去签名",
                "callback": function() {}
            };

            $.extend(_default, params);
	 		if(!_default.disbaled){
	 			$me.find("select").selectric({
					optionsItemBuilder:function(){
						return '<img src="'+_default.src+'" title="'+ _default.name +'" data-id = "'+ _default.id +'" >'
					},
					onInit:function(){
						$me.find(".selectric-items li").first().html(_default.tip);
					},
					onChange:function(elm){
						//console.log(elm);
						var html = $me.find(".selectric-items li.selected").html();
						if($me.find(".selectric-items li.selected").attr("data-index") == 0){
							$me.find(".label").html("");
						}else{
						    $me.find(".label").html(html);
						    _default.isShow = 1;
						}

						if (_default.callback) {
						    _default.callback.call(me, params);
						}
					}
	 			});
			 }else{
	 			if(!_default.src && !_default.name && !_default.id){

	 			} else {
				     if (_default.isShow === 0) {

				     } else {
				         $me.html('<img src="' + _default.src + '" title="' + _default.name + '" data-id = "' + _default.id + '" >');
				     }
	 			}
	 		}

	 	}
	 });



})(jQuery, window);
