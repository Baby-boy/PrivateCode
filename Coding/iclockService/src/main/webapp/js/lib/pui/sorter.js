/*
 * Copyright (c) 2005, 2014 vacoor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
var Class = {
//创建类
	create: function () {
		return function () {
			this.initialize.apply(this, arguments);
		};
	}
};

var $A = function (a) {
//转换数组
	return a ? Array.apply(null, a) : new Array;
};

var $ = function (id) {
//获取对象
	return document.getElementById(id);
};

Object.extend = function (a, b) {
//追加方法
	for (var i in b) a[i] = b[i];
	return a;
};

Function.prototype.bind = function () {
//绑定事件
	var wc = this, a = $A(arguments), o = a.shift();
	return function () {
		wc.apply(o, a.concat($A(arguments)));
	};
};

// -------------------------------

var Sorter = Class.create();
Sorter.IE = /MSIE/.test(window.navigator.userAgent);
var SORT_PL_CLASS = 'sort-pl';
//var SELECTOR = '.js-plupload';
var SELECTOR = '.upload-entry,.upload-image-entry';

Sorter.prototype = {
	initialize: function () {
		//初始化成员
		var me = this;
		me._container = document;
        me._elements = SELECTOR;
		me._movingHandler = me._endMoveHandler = null;

		me.target = {
			dom: null,  // 移动 dom 对象
			left: 0,    // 移动原始 left
			top: 0      // 移动原始 top
		};
		me.placeholder = {
			fly: null,                           // 浮动对象(拖动对象 copy)
			dom: document.createElement("div")   // 占位符 dom 元素
		};

		me.placeholder.dom.setAttribute(Sorter.IE ? "className" : "class", SORT_PL_CLASS);
		me.placeholder.dom.innerHTML = "&nbsp;";

		jQuery(me._container).on('mousedown', me._elements, function () {
			var self = this;
            me.onStartMove(self);
		})
	},
	// 开始移动
	onStartMove: function (node) {
		var me = this;
		if (me._movingHandler || me._endMoveHandler) return;

		var mouse = Dom.mousePosition(),
			target = me.target,
			placeholder = me.placeholder,
			pos = Dom.getPos(node);

		target.dom = node;
		target.left = mouse.x - pos.x;  // 设置起始位置 x
		target.top = mouse.y - pos.y;   // 设置起始位置 y


		// 设置复制对象
		placeholder.fly = document.body.appendChild(node.cloneNode(true)); //复制预拖拽对象
		placeholder.fly.style.position = "absolute";
		placeholder.fly.style.left = mouse.x - target.left + "px";
		placeholder.fly.style.top = mouse.y - target.top + "px";
		placeholder.fly.style.zIndex = 100;
		placeholder.fly.style.opacity = "0.5";
		placeholder.fly.style.filter = "alpha(opacity:50)";

		//设置占位对象
        placeholder.dom.style.height = (target.dom.clientHeight - 2) + "px";    // 减去边框
        placeholder.dom.style.width = (target.dom.clientWidth - 2) + "px";
        placeholder.dom.style.display = Dom.getStyle(node, 'display');
        placeholder.dom.style.float = Dom.getStyle(node, 'float');
        placeholder.dom.style.margin = Dom.getStyle(node, 'margin');

		// 占位符和当前元素互换
		node.parentNode.replaceChild(placeholder.dom, node);

		// 开始移动, 添加移动监听
		me._movingHandler = Dom.on(document, "mousemove", me.onMoving.bind(me));
		me._endMoveHandler = Dom.on(document, "mouseup", me.onEndMove.bind(me));
		document.onselectstart = new Function("return false");
	},

	onEndMove: function () {
		//当鼠标释放时设置参数
		var me = this,
			placeholder = me.placeholder,
			target = me.target,
			dom = target.dom;

		// 占位符替换为拖动元素
		placeholder.dom.parentNode.replaceChild(dom, placeholder.dom);
		// 移除拖动元素的拷贝
		placeholder.fly.parentNode.removeChild(placeholder.fly);
		placeholder.fly = null;

		// 移动结束, 移除监听
		Dom.off(document, "mousemove", me._movingHandler);
		Dom.off(document, "mouseup", me._endMoveHandler);
		document.onselectstart = me._movingHandler = me._endMoveHandler = null;
	},

	onMoving: function () {
		//当鼠标移动时设置参数
		var me = this,
			elements = jQuery(me._elements, me.container),
			mouse = Dom.mousePosition(),
			target = me.target,
			placeholder = me.placeholder,
			fly = placeholder.fly;

		// 修改浮动元素位置
		fly.style.left = mouse.x - target.left + "px";
		fly.style.top = mouse.y - target.top + "px";

		var size = Dom.getSize(fly);
		var pos = Dom.getPos(fly);
		var domPos = Dom.getPos(placeholder.dom);

		for (var i = 0; i < elements.length; i++) {
			var e = elements[i];
			if (e == fly) {
				continue;
			}
			var p = Dom.getPos(e);
			if (domPos.x == p.x && domPos.y == p.y) return;

			var w = Math.abs((p.x ) - (pos.x ));
			var h = Math.abs((p.y ) - (pos.y));

			if ((w < size.w / 2 ) && (h < size.h / 2 )) {
				var p = placeholder.dom.parentNode;
				var n = placeholder.dom.nextSibling;
				if (e == n) {
					p.insertBefore(n, placeholder.dom);
				} else {
					e.parentNode.insertBefore(placeholder.dom, e);
					n ? p.insertBefore(e, n) : p.appendChild(e);
				}
				var t = target.dom.getAttribute('data-order');
				target.dom.setAttribute('data-order', e.getAttribute('data-order'));
				e.setAttribute('data-order', t);
				break;
			}
		}
	}
};

