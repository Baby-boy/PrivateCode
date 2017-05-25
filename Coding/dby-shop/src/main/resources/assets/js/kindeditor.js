Date.prototype.format = function(format){ 
    var o =  { 
    "M+" : this.getMonth()+1, //month 
    "d+" : this.getDate(), //day 
    "h+" : this.getHours(), //hour 
    "m+" : this.getMinutes(), //minute 
    "s+" : this.getSeconds(), //second 
    "q+" : Math.floor((this.getMonth()+3)/3), //quarter 
    "S" : this.getMilliseconds() //millisecond 
    };
    if(/(y+)/.test(format)){ 
    	format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
    }
    for(var k in o)  { 
	    if(new RegExp("("+ k +")").test(format)){ 
	    	format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
	    } 
    } 
    return format; 
};

var YD = DBY = {
	// 编辑器参数
	kingEditorParams : {
		filePostName  : "uploadFile",
		uploadJson : '/pic/upload',
		dir : "image"
	},
	// 格式化时间
	formatDateTime : function(val,row){
		var now = new Date(val);
    	return now.format("yyyy-MM-dd hh:mm:ss");
	},
	// 格式化连接
	formatUrl : function(val,row){
		if(val){
			return "<a href='"+val+"' target='_blank'>查看</a>";			
		}
		return "";
	},
	// 格式化价格
	formatPrice : function(val,row){
		return (val/100).toFixed(2);
	},
	// 格式化商品的状态
	formatGoodsStatus : function formatStatus(val,row){
        if (val == 1){
            return '<span style="color:green;">已上架</span>';
        } else if(val == 0){
        	return '<span style="color:red;">已下架</span>';
        } else {
        	return '<span style="color:blue;">未知</span>';
        }
    },
    //格式化店铺的自营
    formatStoreFlag2 : function formatFlag2(val,row){
        if (val == 1){
            return '<span style="color:green;">不是品牌店是自营店</span>';
        }else if(val == 2){
        	return '<span style="color:red;">是品牌店不是自营店</span>';
        }else if(val == 3){
        	return '<span style="color:blue;">是品牌是自营</span>';
        }else if(val == 4){
        	return '<span style="color:red;">不是品牌不是自营</span>';
        } else {
        	return '<span style="color:blue;">未知</span>';
        }
    },
    //格式化是否是品牌店
    formatStoreBrand : function formatBrand(val,row){
    	if (val == 0){
    		return '<span style="color:green;">普通店</span>';
    	} else if(val == 1){
    		return '<span style="color:red;">品牌店</span>';
    	} else {
    		return '<span style="color:blue;">未知</span>';
    	}
    },

    init : function(data){
    	this.initPicUpload(data);
    	this.initItemCat(data);
    	this.initArticleClass(data);
    	this.initBrandClass(data);
    	this.initInfoClass(data);
    },
    // 初始化图片上传组件
    initPicUpload : function(data){
    	$(".picFileUpload").each(function(i,e){
    		var _ele = $(e);
    		_ele.siblings("div.pics").remove();
    		_ele.after('\
    			<div class="pics">\
        			<ul></ul>\
        		</div>');
    		// 回显图片
        	if(data && data.pics){
        		var imgs = data.pics.split(",");
        		for(var i in imgs){
        			if($.trim(imgs[i]).length > 0){
        				_ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");
        			}
        		}
        	}
        	$(e).unbind('click').click(function(){
        		var form = $(this).parentsUntil("form").parent("form");
        		KindEditor.editor(YD.kingEditorParams).loadPlugin('multiimage',function(){
        			var editor = this;
        			editor.plugin.multiImageDialog({
						clickFn : function(urlList) {
							var imgArray = [];
							KindEditor.each(urlList, function(i, data) {
								imgArray.push(data.url);
								form.find(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='50' /></a></li>");
							});
							form.find("[name=image]").val(imgArray.join(","));
							editor.hideDialog();
						}
					});
        		});
        	});
    	});
    },
    //初始化选择资讯类型组件
    initInfoClass:function(data){
    	$(".selectInfoClass").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.classifyId){
    			_ele.after("<span style='margin-left:10px;'>"+data.classifyId+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类型',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/ydclassify',
    			    		animate:true,
    			    		method:"GET",
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				_ele.parent().find("input[name='classifyId']").val(node.id);
    			    				_ele.next().text(node.text).attr("classifyId",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    //初始化选择品牌类型组件
    initBrandClass:function(data){
    	$(".selectBrandClass").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.classifyId){
    			_ele.after("<span style='margin-left:10px;'>"+data.classifyId+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类型',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/ydclassify',
    			    		animate:true,
    			    		method:"GET",
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				_ele.parent().find("input[name='classifyId']").val(node.id);
    			    				_ele.next().text(node.text).attr("classifyId",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    // 初始化选择文章类目组件
    initArticleClass : function(data){
    	$(".selectArtcleClass").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.classifyId){
    			_ele.after("<span style='margin-left:10px;'>"+data.classifyId+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类目',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/ydclassify',
    			    		animate:true,
    			    		method:"GET",
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				_ele.parent().find("input[name='acId']").val(node.id);
    			    				_ele.next().text(node.text).attr("acId",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    //初始化分类
    initItemCat : function(data){
    	$(".selectItemCat").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.classifyId){
    			_ele.after("<span style='margin-left:10px;'>"+data.classifyId+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类目',
    			    onOpen : function(){
    			
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/ydclassify',
    			    		animate:true,
    			    		method:"GET",
    			    		onClick : function(node){
    			    			
    			    			if($(this).tree("isLeaf",node.target)){
    			    				// 填写到acId中
    			    				_ele.parent().find("input[name='acId']").val(node.id);
    			    				_ele.next().text(node.text).attr("acId",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					//执行传入到fun的方法
    			    					//.call的第一个参数是固定的，一般都是this，从第二个参数开始时自定义参数
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },
    //初始化类目
    /* initItemCat : function(data){
    	$(".selectItemCat").each(function(i,e){
    		var _ele = $(e);
    		if(data && data.cid){
    			_ele.after("<span style='margin-left:10px;'>"+data.cid+"</span>");
    		}else{
    			_ele.after("<span style='margin-left:10px;'></span>");
    		}
    		_ele.unbind('click').click(function(){
    			$("<div>").css({padding:"5px"}).html("<ul>")
    			.window({
    				width:'500',
    			    height:"450",
    			    modal:true,
    			    closed:true,
    			    iconCls:'icon-save',
    			    title:'选择类目',
    			    onOpen : function(){
    			    	var _win = this;
    			    	$("ul",_win).tree({
    			    		url:'/classify',
    			    		animate:true,
    			    		method:"GET",
    			    		onClick : function(node){
    			    			if($(this).tree("isLeaf",node.target)){
    			    				// 填写到cid中
    			    				_ele.parent().find("[name=cid]").val(node.id);
    			    				_ele.next().text(node.text).attr("cid",node.id);
    			    				$(_win).window('close');
    			    				if(data && data.fun){
    			    					//执行传入到fun的方法
    			    					//.call的第一个参数是固定的，一般都是this，从第二个参数开始时自定义参数
    			    					data.fun.call(this,node);
    			    				}
    			    			}
    			    		}
    			    	});
    			    },
    			    onClose : function(){
    			    	$(this).window("destroy");
    			    }
    			}).window('open');
    		});
    	});
    },*/
    createEditor : function(select){
    	return KindEditor.create(select, YD.kingEditorParams);
    },
    
    /**
     * 创建一个窗口，关闭窗口后销毁该窗口对象。<br/>
     * 
     * 默认：<br/>
     * width : 80% <br/>
     * height : 80% <br/>
     * title : (空字符串) <br/>
     * 
     * 参数：<br/>
     * width : <br/>
     * height : <br/>
     * title : <br/>
     * url : 必填参数 <br/>
     * onLoad : function 加载完窗口内容后执行<br/>
     * 
     * 
     */
    createWindow : function(params){
    	$("<div>").css({padding:"5px"}).window({
    		width : params.width?params.width:"80%",
    		height : params.height?params.height:"80%",
    		modal:true,
    		title : params.title?params.title:" ",
    		href : params.url,
		    onClose : function(){
		    	$(this).window("destroy");
		    },
		    onLoad : function(){
		    	if(params.onLoad){
		    		params.onLoad.call(this);
		    	}
		    }
    	}).window("open");
    },
    
    closeCurrentWindow : function(){
    	$(".panel-tool-close").click();
    },
    
    changeItemParam : function(node,formId){
    	$.ajax({
    		url:"/item/param/"+ node.id,
    		method:"GET",
    		statusCode:{
    			200:function(data){
    				$("#"+formId+" .params").show();
   				 	var paramData = JSON.parse(data.paramData);
   				 	var html = "<ul>";
   				 	for(var i in paramData){
	   					 var pd = paramData[i];
	   					 html+="<li><table>";
	   					 html+="<tr><td colspan=\"2\" class=\"group\">"+pd.group+"</td></tr>";
	   					 
	   					 for(var j in pd.params){
	   						 var ps = pd.params[j];
	   						 html+="<tr><td class=\"param\"><span>"+ps+"</span>: </td><td><input autocomplete=\"off\" type=\"text\"/></td></tr>";
	   					 }
	   					 
	   					 html+="</li></table>";
   				 	}
   				 	html+= "</ul>";
   				 	$("#"+formId+" .params td").eq(1).html(html);
    			},
    			404:function(){
    				 $("#"+formId+" .params").hide();
    				 $("#"+formId+" .params td").eq(1).empty();
    			}
    	
    		}
    	});
    },
    getSelectionsIds : function (select){
    	var list = $(select);
    	var sels = list.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    },
    
    /**
     * 初始化单图片上传组件 <br/>
     * 选择器为：.onePicUpload <br/>
     * 上传完成后会设置input内容以及在input后面追加<img> 
     */
    initOnePicUpload : function(){
    	$(".onePicUpload").click(function(){
			var _self = $(this);
			KindEditor.editor(YD.kingEditorParams).loadPlugin('image', function() {
				this.plugin.imageDialog({
					showRemote : false,
					clickFn : function(url, title, width, height, border, align) {
						var input = _self.siblings("input");
						input.parent().find("img").remove();
						input.val(url);
						input.after("<a href='"+url+"' target='_blank'><img src='"+url+"' width='80' height='50'/></a>");
						this.hideDialog();
					}
				});
			});
		});
    }
};

