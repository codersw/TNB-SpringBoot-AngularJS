//by 470502424

(function($){
	
var W = $.w = $.w || {};

$.some = $.some || function(ary, func){
	var i = 0;
	for(;i<ary;++i)
		if( func.call(ary[i], i, ary[i]) )
			return true;
	return false;
}


//easing函数
$.extend($.easing, {
	def: 'easeOutQuad',
	easeInQuad: function (x, t, b, c, d) {
		return c*(t/=d)*t + b;
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	},
	easeInOutQuad: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	},
	easeInCubic: function (x, t, b, c, d) {
		return c*(t/=d)*t*t + b;
	},
	easeOutCubic: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t + 1) + b;
	},
	easeInOutCubic: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t + b;
		return c/2*((t-=2)*t*t + 2) + b;
	},
	easeInQuart: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t + b;
	},
	easeOutQuart: function (x, t, b, c, d) {
		return -c * ((t=t/d-1)*t*t*t - 1) + b;
	},
	easeInOutQuart: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
		return -c/2 * ((t-=2)*t*t*t - 2) + b;
	},
	easeInQuint: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t*t + b;
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	},
	easeInOutQuint: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
		return c/2*((t-=2)*t*t*t*t + 2) + b;
	},
	easeInSine: function (x, t, b, c, d) {
		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	},
	easeOutSine: function (x, t, b, c, d) {
		return c * Math.sin(t/d * (Math.PI/2)) + b;
	},
	easeInOutSine: function (x, t, b, c, d) {
		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	},
	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	},
	easeOutExpo: function (x, t, b, c, d) {
		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	},
	easeInOutExpo: function (x, t, b, c, d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	},
	easeInCirc: function (x, t, b, c, d) {
		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	},
	easeOutCirc: function (x, t, b, c, d) {
		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	},
	easeInOutCirc: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	},
	easeInElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	},
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},
	easeInOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	},
	easeInBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	},
	easeOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	},
	easeInOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158; 
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	},
	easeInBounce: function (x, t, b, c, d) {
		return c - $.easing.easeOutBounce (x, d-t, 0, c, d) + b;
	},
	easeOutBounce: function (x, t, b, c, d) {
		if ((t/=d) < (1/2.75)) {
			return c*(7.5625*t*t) + b;
		} else if (t < (2/2.75)) {
			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
		} else if (t < (2.5/2.75)) {
			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
		} else {
			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
		}
	},
	easeInOutBounce: function (x, t, b, c, d) {
		if (t < d/2) return $.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
		return $.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
	}
});
$.extend($.w, {
	navigator: (function(){
		var agent = navigator.userAgent,
			ie = (ie = agent.match(/msie (\d)/i))?ie[1] : false;
		return {ie: ie ? document.documentMode||ie : false  }
	})(),
	
	
	//颜色
	toColor: function(r,g,b){
		var i = 0, n, ret = "#";
		for(; i < 3; ++i){
			n = Math.min( parseInt(arguments[i]) || 0, 255 ).toString(16);
			if(n.length < 2) n = "0" + n;
			ret += n;
		}
		return ret;
	},
	//返回#ffffff
	randomColor: function(){
		return $.w.toColor( Math.ceil( Math.random() * 256 ),
						Math.ceil( Math.random() * 256 ),
						Math.ceil( Math.random() * 256 )
					);
	},
	
	//ie6 png添加滤镜
	blankGifPath: "images/blank.gif",
	
	pngfix: function(e, mode){
		if( !this.navigator.ie || this.navigator.ie != 6 ){
			return;
		}
		var mode = mode || 'scale',
			es = e.style;
			isImg = $.nodeName(e, "img"),
			path = isImg ? e.src : e.currentStyle.backgroundImage.split('"')[1];
		if( !/\.png$/.test(path) )return;
		if(isImg){
			e.width = e.width;
			e.height = e.height;
			e.src = $.w.blankGifPath;
		}
		es.backgroundImage = 'none';
		es.filter = e.currentStyle.filter + " progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + path + "',sizingMethod=" + mode + ")";
	},
	
	//旋转
	rotation: function(elems, deg){
		elems = $(elems);
		var rotation = $.w.rotation;
		if( !rotation.exec ){
			var t, ua = navigator.userAgent, prefix = "",
				isIE = ua.match(/msie\s*(\d+)/i);
			if( isIE ){
				if( isIE[1] < 9 ){
					var deg2radians = Math.PI * 2 / 360;
					rotation.exec = function(elem, deg){
						var rad = deg * deg2radians ;
						var costheta = Math.cos(rad);
						var sintheta = Math.sin(rad);
						elem.style.filter += " progid:DXImageTransform.Microsoft.Matrix(SizingMethod = 'auto expand',"
							+ "M11 =" + costheta + ",M12 =" + -sintheta
							+ ",M21 =" + sintheta + ",M22 =" + costheta + ")";
					}
				}else
					prefix = "-ms-";
			}else if( ua.match(/AppleWebKit/i) )
				prefix = "-webkit-";
				
			if( !rotation.exec ) rotation.exec = function(elem, deg){
				elem.style[ prefix + "transform" ] = "rotate(" + deg + "deg)";
			}
		}
		elems.each(function(){
			rotation.exec(this, deg);
		});
	},
	
	//模拟ajax post 用于服务器不支持ajax的情况
	post: function(url, data, callback){
		if( $.isFunction(data) ){
			callback = data;
			data = null;
		}
		var iframe = $('<iframe style="position: absolute; left: 1px; top: 1px; '
			+'height: 1px; width: 1px; visibility: hidden">').appendTo(document.body),
			// Always write a new HTML skeleton so Webkit and Firefox don't choke on reuse
			doc = ( iframe[0].contentWindow || iframe[0].contentDocument ).document;
			
		doc.write("<!doctype html><html><body>");
		doc.close();
		var body = doc.body,
			form = $("<form action='"+url+"' method='post'></form>").appendTo(body);
			
		if (data) {
			for(var i in data){
				$("<input name='"+i+"' value='"+data[i]+"' />").appendTo(form);
			}
		}
		form.submit();
		iframe.load( function(){
			doc = ( iframe[0].contentWindow || iframe[0].contentDocument ).document;
			callback && callback(doc.body.innerHTML);
			iframe.detach();
		});
	},
	
	addFavorite: function(sURL, sTitle){ 
		var sURL = sURL || document.location.href
		var sTitle = sTitle || document.title
		try{ 
			window.external.addFavorite(sURL, sTitle); 
		} 
		catch (e){ 
			try{ 
				window.sidebar.addPanel(sTitle, sURL, ""); 
			} 
			catch (e){ 
				alert("加入收藏失败，请使用Ctrl+D进行添加"); 
			} 
		} 
	},
	setHome: function(obj,vrl){
		var vrl = vrl || document.location.host
        try{ 
			obj.style.behavior = 'url(#default#homepage)'; obj.setHomePage(vrl); 
        } 
        catch(e){ 
			if(window.netscape) { 
				try { 
						netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect"); 
				} 
				catch (e) { 
						alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。"); 
				} 
				var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch); 
				prefs.setCharPref('browser.startup.homepage', vrl); 
			} 
		} 
	},
	
	loadImage: function(imgs, finished, callback, error){
		imgs = $.makeArray(imgs);
		if( !imgs.length ) return finished && finished();
		var timeout = 5000, count = imgs.length;
		
		function onLoad(){
			var w = this.width, h = this.height;
			callback && callback.call(this.origin, w, h)
			document.body.removeChild(this);
			if(--count == 0) onFinish(this.origin, w, h);
		}
		function onError(){
			error && error.call(this.origin);
			document.body.removeChild(this);
			if(--count == 0) onFinish();
		}
		function onFinish(c, w, h){
			finished && finished.call(c, w, h);
		};
			
		for(var i = 0; i < imgs.length; ++i){
			var src = imgs[i];
			var img = new Image();
			img.origin = src;
			img.style.cssText = "position: absolute; top: -9999em; padding:0; border: none;";
			document.body.appendChild(img);
			img.onload = onLoad;
			img.onerror = onError;
			img.src = src.nodeType ? src.src : src;
		}
		
	},
	
	
	imageScale: function(img, maxWidth, maxHeight, callback, align, keep){
		var config = arguments.length == 1 ? img : {
			img: img,
			maxWidth: maxWidth,
			maxHeight: maxHeight,
			callback: callback,
			align: align,
			keep: keep
		}
		config = $.extend({
			align: "center",
			keep: true
		}, config);
		
		if( $.isArray(maxWidth) ){
			config.maxWidth = maxWidth[0];
			config.maxHeight = maxWidth[1];
		}
		if( typeof( config.callback ) == "string" ){
			 config.align = config.callback;
			 config.keep = config.margin;
			 config.callback = null;
		}
		
		function scaleHandler(w, h){
			var p = w / h;
			if(p > config.maxWidth / config.maxHeight ){
				if(config.keep || w > config.maxWidth){
					w = config.maxWidth;
					h = w / p; 
				}
			}
			else if(h > config.maxHeight){
				if(config.keep || h > config.maxHeight){
					h = config.maxHeight; 
					w = h * p;
				}
			}
			$(this).width(w).height(h);
			if( config.align == "center" ) $(this).css("marginTop", (config.maxHeight - h)/2)
			else if( config.align == "bottom" ) $(this).css("marginTop", config.maxHeight - h)
			config.callback && config.callback.call(this, w, h);
		}
		//已获取图片大小
		if( $.isArray(maxHeight) ){
			$(config.img).each(function(){
				scaleHandler.call(this, maxHeight[0], maxHeight[1]);
			});
		}
		$.w.loadImage(config.img, null, scaleHandler);
	},
	cLeft: function(s, t){
		var n = s.indexOf(t);
		if( n > 0 )return s.substr(0, n);
		return "";
	},
	cRight: function(s, t){
		var n = s.indexOf(t);
		if( n > -1 )return s.substr(n + 1);
		return "";
	},
	fixUrl: function(url){
		var prefix = url.toLowerCase().substr(0, 7);
		if(prefix != "http://")
			return "http://" + url
		return url
	},
	//图片滚动
	scroll: function(config){
		var cfg = $.extend({
			panelSel: "",
			btnSel: "",
			speed: 1.5,
			direction: "right",
			prevSel: "",
			nextSel: "",
			container: "",
			autoBtnCount: true,
			autoBtnCountTpl: "<%s>%d</%s>",
			btnHoverClass: "cur"
		}, config);
		
		
		cfg.speed = parseFloat(cfg.speed) || 1.5;
		cfg.direction = cfg.direction.toLowerCase();
		
		var context = $(cfg.container || document),
			panels = $(cfg.panelSel, context),
			container = $(cfg.container || panels.parent()),
			btns = $(cfg.btnSel, context),
			parent = panels.parent(),
			panelsWidth = 0,
			isAdd = cfg.direction == "left" || cfg.direction == "top" ? true : false,
			useCss = cfg.direction == "left" || cfg.direction == "right" ? "left" : "top",
			relCss = useCss == "left" ? "width" : "height",
			outerCss = "outer" + relCss.charAt(0).toUpperCase() + relCss.substr(1),
			float = useCss == "left" ? "left" : "none",
			wraper = panels.wrapAll("<div style='"+relCss+": 99999px; position: absolute;'>").parent(),
			timer, length = panels.length,
			duration;
			
		
		_fixContainer(parent);
		
		panels.css("float", float);
		
		
		//自动设置按钮个数
		if(cfg.autoBtnCount && btns.length && length != btns.length){
			var l = length - btns.length;
			if(l > 0){
				while(l--){
					var newBtn = $( cfg.autoBtnCountTpl.replace("%s", btns[0].tagName).replace("%d", length - l) );
					btns.eq( btns.length - 1 ).after(newBtn);
					btns = btns.add( newBtn );
				}
			}else{
				while(l++){
					btns.eq( btns.length + l - 1 ).hide();
				}
			}
		}
		
		var props = {}, pos = 0;
		
		function move(){
			if( isAdd ){
				if( pos >= 0 ){
					pos = - panelsWidth + pos;
				}
				pos += cfg.speed;
			}else{
				if( pos <= -panelsWidth ){
					pos = pos + panelsWidth;
				}
				pos -= cfg.speed;
			}
			wraper.css(useCss,  pos);
		};
		
			
		$.w.loadImage($("img", parent), function(){
			panels.each(function(){ panelsWidth += $(this)[outerCss](true);  });
			timer = setInterval(move, 13);
			container.hover(
				function(){clearInterval(timer);}
				, function(){timer = setInterval(move, 13);}
			);
		});
		
		panels.clone().appendTo(wraper);
		
		if( cfg.prevSel ){
			$(cfg.prevSel, context).click(function(){
				isAdd = true;	
			})
		}
		if( cfg.nextSel ){
			$(cfg.nextSel, context).click(function(){
				isAdd = false;	
			})
		}
		var index = 0;
		btns.mouseover(function(){
			btns.eq(index).removeClass(cfg.btnHoverClass);
			index = btns.index(this);
			btns.eq(index).addClass(cfg.btnHoverClass);
			var diff = 0;
			for(var i = 0; i < index; ++i){
				diff += panels.eq(i)[outerCss]();
			}
			var props = {};
			props[useCss] = -diff;
			wraper.stop().animate( props, 500 );
		})
	},
	//灯箱效果
	lightBox: function LightBox(config){
		var cfg = $.extend( {
			imgNodes: null,
			datas: null,
			showTitle: true
		}, config );
		
		var html = "<div class='LightBox'><div class='bg'></div><div class='container'>";
		html += "<div class='text'></div>";
		html += "<div class='close'></div>";
		html += "<div class='imgbox'><div class='loading'></div><images /></div>";
		html += "<div class='tab l'><span><a href='javascript:'></a></span></div>";
		html += "<div class='tab r'><span><a href='javascript:'></a></span></div>";
		html += "</div></div>";
		
		var imgNodes = $(cfg.imgNodes),
			box = $(html).appendTo(document.body).hide(),
			bg = box.find(".bg").css("opacity", 0.8),
			container = box.find(".container"),
			imgbox = box.find(".imgbox"),
			loading = box.find(".loading"),
			bigImg = box.find("img"),
			text = box.find(".text"),
			close = box.find(".close").click(function(){box.fadeOut();}),
			leftAw = box.find(".l a").click(prev),
			rightAw = box.find(".r a").click(next),
			extra = {
				w: container.width() - imgbox.width(),
				h: container.height() - imgbox.height(),
				cw: container.outerWidth() - container.width(),
				ch: container.outerHeight() - container.height()
			},
			anime;
		function show(){
			box.stop(1, 1).fadeIn();
			container.css({
				"left": $(window).scrollLeft() + $(window).width()/2 - container.outerWidth()/2,
				"top": $(window).scrollTop() + $(window).height()/2 - container.outerHeight()/2
			});
		}
		function showPic(){
			bigImg.show();
			loading.hide();
			imgbox.show();
			text.show();
			close.show();
			leftAw.show();
			rightAw.show();
		}
		function load(src){
			bigImg[0].src = src;
			$.w.loadImage(src, onLoad);
			bigImg.hide();
			loading.show();
			leftAw.hide();
			rightAw.hide();
		}
		function loadPic(){
			var src, title = imgNodes[index] && imgNodes[index].alt;;
			if(cfg.datas){
				src = cfg.datas[index].src;
				title = cfg.datas[index].title || title;
			}
			else{
				src = imgNodes[index].src;
			}
			if(cfg.showTitle)text.html(title);
			load(src);
		}
		var curW = imgbox.width(), curH = imgbox.height();
		function onResize(){
			box.width($(document).width());
			box.height($(document).height());
			var win = $(window),
				winW = win.width(),
				winH = win.height(),
				maxW = winW - 50,
				maxH = winH - 50,
				containerW = curW + extra.w + extra.cw,
				containerH = curH + extra.h + extra.ch,
				p = containerW / containerH;
			containerW = Math.min(containerW, maxW);
			containerH = containerW / p;
			containerH = Math.min(containerH, maxH);
			containerW = containerH * p;
			
			imgbox.hide();
			close.hide();
			text.hide();
			
			anime && anime.stop();
			anime = container.animate( {
				width: containerW, height: containerH,
				left: $(window).scrollLeft() + winW/2 - (containerW + extra.cw)/2,
				top: $(window).scrollTop() + winH/2 - (containerH + extra.ch)/2
			}, showPic);
			imgbox.width(containerW - extra.w - extra.cw).height(containerH - extra.h - extra.ch);
		}
		$(window).resize(function(){
			onResize();
		}).scroll(function(){
			onResize();
		});
		function onLoad(w, h){
			curW = w; curH = h;
			bigImg.show();
			loading.hide();	
			onResize();
		}
		var index, size = (cfg.datas && cfg.datas.length) || imgNodes.size();
		function next(){
			if(index + 1 >= size){
				alert("没有了！")
			}else{
				index++;
				loadPic();
			}
		};
		function prev(){
			if(index - 1 < 0){
				alert("没有了！")
			}else{
				index--;
				loadPic();
			}
		};
		imgNodes.css("cursor", "pointer").click(function(){
			show();
			var src = $(this).attr("src");
			if(cfg.datas){
				for(var i = 0; i < cfg.datas.length; ++i){
					if( cfg.datas[i].thumbs == src){
						index = i;
						break;
					};
				}
			}else
				index = imgNodes.index(this);
			loadPic();
		});
		
		return {load: function(src){
			if( box.is(":hidden") ){
				show();
			}
			text.html(src.alt || "");
			src = src.nodeType ? src.src : src
			load(src);
		}}
		
	},
	
	//图片切换
	tabs: function(config){
		var cfg = $.extend({
			eventType: "mouseover",
			container: null,
			tabSel: "",
			tabSelectedClass: "cur",
			panelSel: "",
			autoPlay: false,
			effect: "fade",
			interval: 3000,
			duration: 500, //切换渐变时间，0为立即
			single: true, //如果为假 当前需要隐藏的div做fadeOut 否则直接隐藏
			fixed: true, //single为false有效，自动设置pane的left和top属性 
			autoTabCount: true, //自动填充按钮
			autoTabCountTpl: "<%s>%d</%s>",
			direction: "right"
		}, config);
		
		var context = $(cfg.container || document),
			paneContainer, position, fixed,
			tabs = $(cfg.tabSel, context),
			panels = $(cfg.panelSel, context),
			length = panels.length,
			isAdd = cfg.direction == "right" ? true : false;
			
		if(!panels.length)return;
		
		//自动设置按钮个数
		if(cfg.autoTabCount && tabs.length && length != tabs.length){
			var l = length - tabs.length;
			if(l > 0){
				while(l--){
					var newTab = $( cfg.autoTabCountTpl.replace(/%s/g, tabs[0].tagName).replace("%d", length - l) );
					tabs.eq( tabs.length - 1 ).after( newTab );
					tabs = tabs.add(newTab);
				}
			}else{
				while(l++){
					tabs.eq(tabs.length + l - 1).hide();
				}
			}
		}
		
		paneContainer = $(cfg.paneContainer || cfg.container || panels[0].parentNode);
		
		//如果同时进行淡出和淡入需要设置定位
		if(cfg.single == false){
			if( paneContainer.css("position" ) == 'static'){
				paneContainer.css('relative');
			}
			//是否固定位置
			position = panels.position();
			fixed = cfg.fixed && ( position.left || position.top );
			if(fixed)
				fixed = {position: "absolute", left: position.left, top: position.top};
			else
				fixed = {position: "absolute"};
				
			panels.css(fixed);
			
		}
		
		function move(index){
			if(curIndex == index)return;
			if(curIndex > -1){
				tabs.eq(curIndex).removeClass( cfg.tabSelectedClass );
				if(panels[curIndex]){
					panels.eq(curIndex).stop(1, 1);
					if(cfg.single)
						panels.eq(curIndex).hide();
					else{
						if( cfg.effect == "fade" )
							panels.eq(curIndex).fadeOut(cfg.duration);
						else
							panels.eq(curIndex).hide();
					}
				}
			}
			curIndex = index;
			tabs.eq(curIndex).addClass( cfg.tabSelectedClass );
			if( !panels[curIndex] )return;
			panels.eq(curIndex).stop(1, 1);
			if( cfg.effect == "fade" )
				panels.eq(curIndex).fadeIn(cfg.duration);
			else
				panels.eq(curIndex).show();
		}
		
		panels.hide();
		
		tabs[cfg.eventType]( function(){ move(tabs.index(this)); } )
		
		function auto(){ 
			var index = isAdd ? curIndex + 1 : curIndex - 1;
			if(index > length - 1)index = 0 
			if(index < 0)index = length - 1;
			move(index)
		}
		function next(){
			var t = isAdd;
			isAdd = true;
			auto();
			isAdd = t;
		}
		function prev(){
			var t = isAdd;
			isAdd = false;
			auto();
			isAdd = t;
		}
		
		if( cfg.autoPlay ){
			var timer = setInterval(auto, cfg.interval);
			$(cfg.container || paneContainer).hover(
				function(){ clearTimeout(timer);}, 
				function(){ timer = setInterval(next, cfg.interval); }
			);
		}
		
		if(cfg.prevSel)$(cfg.prevSel, context).click(prev);
		if(cfg.nextSel)$(cfg.nextSel, context).click(next);
		var curIndex = 0;
		tabs.eq(0).addClass(cfg.tabSelectedClass);
		panels.eq(0).show();
	},
	
		
	dnd: function(elems, onMouseDown, onMouseMove, onMouseUp){
		elems = $(elems);
		var doc = $(document), win = $(window), current;
		function mousedown(e){
			if( e.which != 1 ) return;
			current = this;
			if( this.setCapture ){
				this.setCapture();
				e.cancelBubble = true;	
				$(this).on("losecapture", mouseup);
			}else if(window.captureEvents){
				e.stopPropagation();
				win.on("blur", mouseup);
				e.preventDefault();
			}
			doc.on('mousemove', mousemove).on('mouseup', mouseup);
			onMouseDown && onMouseDown.call(this, e);
		}
		function mousemove(e){
			onMouseMove && onMouseMove.call(this, e);
		}
		function mouseup(e){
			doc.off('mousemove', mousemove).off('mouseup', mouseup);
			if(current.releaseCapture){
				$(current).off("losecapture", mouseup);
				current.releaseCapture();
			}else if(window.releaseEvents){
				$(window).off("blur", mouseup);
			}
			onMouseUp && onMouseUp.call(this, e);
		}
		elems.bind({"mousedown.w.dnd": mousedown, "mouseup.w.dnd": mouseup });
	},
	undnd: function(elems){
		$(elems).unbind(".w.dnd");
	},
	drag: function(cfg){
		cfg = $.extend({
			panelSel: "",
			barColor: "#aaa",
			horizontal: true,
			container: null,
			onMouseDown: null,
			onMouseMove: null,
			onMouseUp: null,
			onChange: null
		}, cfg);
		
		var panes = $(cfg.panelSel, cfg.container && $(cfg.container)),
			body = $(document.body),
			current, target, l, t, offset,
			moveDiv, bar, back, isInit,
			offsetSet,
			container = cfg.container && $(cfg.container) || panes.parent();
		
		function init(){
			isInit = true;
			moveDiv = $("<div>").appendTo(body).css({
				border: "1px dashed #fff",
				background: "#66f",
				opacity: 0.8,
				cursor: "move",
				width: current.offsetWidth - 2,
				height: current.offsetHeight - 2
			}).css({"position": "absolute", zIndex: "9999"}).css(offset);
			
			var s = cfg.horizontal ? "outerHeight" : "outerWidth";
			bar = $("<div>").appendTo(body).hide().css({
				width: 2, height: 2,
				background: cfg.barColor,
				position: "absolute",
				zIndex: "9998"
			});
			bar[s](panes[s]());
		}
		function mousedown(e){
			target = null;
			current = this;
			isInit = false;
			offset = $(this).offset();
			l = e.pageX - offset.left; t = e.pageY - offset.top;
			offsetSet = panes.map(function(){
				var t = $(this), r = t.offset();
				r.target = this;
				r.width = t.outerWidth();
				r.height = t.outerHeight();
				return r;
			});
			cfg.onMouseDown && cfg.onMouseDown(current);
		}
		function mousemove(e){
			!isInit && init()
			var x = e.pageX, y = e.pageY;
			offset.left = x - l;
			offset.top = y - t;
			moveDiv.css(offset);
			target = null;
			var os, minX, minY, tx, ty, i = offsetSet.length;
			while( i-- ){
				os = offsetSet[i];
				
				if( os.top <= y && os.left <= x && os.left + os.width >= x && os.top + os.height >= y ){
					target = os.target;
					break;
				}
				//获取最近的pane
				tx = Math.min( Math.abs( os.left - x ), Math.abs( os.left + os.width - x ) );
				ty = Math.min( Math.abs( os.top - y ), Math.abs( os.top + os.height - y ) );
				
				if( minY == undefined ){
					minY = ty;
					minX = tx;
					target = os.target;
				}
				else if( ty < minY ){
					minY = ty;
					minX = tx;
					target = os.target;
					
				}else if( ty == minY ){
					if( tx < minX ){
						minX = tx;
						target = os.target;
					}else if( tx == minX ){
						var b = os.top < y && os.top + os.height >= y;
						if( b || os.left < x && os.left + os.width >= x ){
							target = os.target;
						}
					}
				}
			}
			if(target){
				var tg = $(target);
				var os = tg.offset();
				var s = cfg.horizontal ? "left" : "top";
				var s2 = cfg.horizontal ? "top" : "left";
				var barSize = bar["outer" + (cfg.horizontal ? "Width" : "Height")]();
				bar.show();
				if( ( cfg.horizontal && os.left + tg.outerWidth()/2 > x )
					||( !cfg.horizontal && os.top + tg.outerHeight()/2 > y )){
					back = false;
					bar.css(s , os[s] - barSize).css(s2, os[s2]);
				}else{
					back = true;
					bar.css(s , os[s] + tg[cfg.horizontal ? "outerWidth" : "outerHeight"]() ).css(s2, os[s2]);
				}
			}else
				bar.hide();
			cfg.onMouseMove && cfg.onMouseMove(current);
		}
		
		function mouseup(){
			cfg.onMouseUp && cfg.onMouseUp(current, target);
			
			if( !isInit ) return;
			
			moveDiv.remove();
			bar.remove();
			
			if(target && target != current){
				
				if( back && $(target).next()[0] != current )
					$(target).after(current);
				else if( !back && $(target).prev()[0] != current )
					$(target).before(current);
				else
					return;
				
				cfg.onChange && cfg.onChange(current, target)
			}
			
		}
		$.w.dnd(panes, mousedown, mousemove, mouseup)
		return {add: function(el){
			panes = panes.add(el);
			$.w.dnd(el, mousedown, mousemove, mouseup)
		}, remove: function(el){
			panes = panes.not(el);
			$.w.undnd(el);
		}};
	},
	
	
	
	//图片幻灯片
	slide: function(config){
		var cfg = $.extend({
			onChange: null,
			align: "left",
			alignDiff: 0,
			focus: "left",
			duration: 400,
			direction: "right",
			count: 1,
			container: null,
			panelSel: "",
			btnSel: "",
			prevSel: "",
			nextSel: "",
			interval: 5000,
			btnActiveCls: "active",
			paneActiveCls: "active",
			easing: null,
			autoPlay: true,
			random: false,
			btnAutofill: true, //自动填充按钮
			btnAutofillTpl: "<%s>%d</%s>", //%s: tagName
			boxSize: "fix" //尺寸 可以为数字 如果为auto则会获取容器和pane大小的较大值
		}, config);
		
				
		var t, horizontal, useCss, css, add,
			props = {}, curIndex = 0, timer,
			context = $(cfg.container || document),
			panels = $(cfg.panelSel, context),
			container = panels.parent(),
			initial = {top: parseInt( container.css("paddingTop") ),
					left:　parseInt( container.css("paddingLeft") ) },
			moveDiv = panels.wrapAll( "<div style='position: absolute;'></div>" ).parent(),
			btns = $(cfg.btnSel, context),
			length = panels.length, 
			singleSize, //单个pane的大小 自动计算 cfg.singleSize
			containerSize,
			minPosition, lastIndex,
			alignLeft = cfg.align == "left" ? 0 : container.width()/2 - panels.outerWidth(true)/2,
			result = {index: 0, reset: reset, select: move};
			
		alignLeft += cfg.alignDiff;
		
		if( !length )return;
	
		_fixContainer(container);
		
		//自动设置按钮个数
		if(cfg.btnAutofill && btns.length && length != btns.length){
			var l = length - btns.length;
			if(l > 0){
				while(l--){
					var newBtn = $( cfg.btnAutofillTpl.replace("%s", btns[0].tagName).replace("%d", length - l) );
					btns.eq( btns.length - 1 ).after(newBtn);
					btns = btns.add( newBtn );
				}
			}else{
				while(l++){
					btns.eq( btns.length + l - 1 ).hide();
				}
			}
		}
		
		function reset(config){
			cfg = $.extend(cfg, config);
			props = $.extend({}, initial);
			moveDiv.css(initial)
			horizontal = cfg.direction == "left" || cfg.direction == "right";
			useCss = horizontal ? "width": "height";
			moveDiv.css(useCss, 99999);
			if( horizontal ){
				panels.css("float", "left");
			}else{
				moveDiv.css("width", "auto");
				panels.css("float", "none");
			}
			
			css = horizontal ? "left" : "top";
			add = cfg.direction == "bottom" || cfg.direction == 'right';
			
			//单个的尺寸
			var outer = panels["outer" + useCss.charAt(0).toUpperCase() + useCss.slice(1)](true);
			containerSize = container[useCss]();
			if( cfg.boxSize == "auto" ) { 
				singleSize = Math.max(outer, containerSize);
				length = Math.ceil(outer*length / singleSize);
			}else {
				singleSize = cfg.boxSize == "fix" ? outer : cfg.boxSize;
			}
			minPosition = Math.min(-singleSize * length + containerSize, 0);
			lastIndex = Math.ceil( Math.abs( minPosition / singleSize ) );
			
			clearInterval(timer);
			if( cfg.autoPlay ) timer = setInterval(auto, cfg.interval);
			if( btns[curIndex] ) btns.eq(curIndex).addClass(cfg.btnActiveCls);
			panels.eq(curIndex).addClass(cfg.paneActiveCls);
		}
		reset();
		var pos = 0;
		function move(index){
			if( btns[curIndex] ) btns.eq(curIndex).removeClass(cfg.btnActiveCls);
			if( btns[index] ) btns.eq(index).addClass(cfg.btnActiveCls);
			panels.eq(curIndex).removeClass(cfg.paneActiveCls);
			panels.eq(index).addClass(cfg.paneActiveCls);
			curIndex = index;
			result.index = index;
			cfg.onChange && cfg.onChange(index);
			
			pos = -index * singleSize;
			pos += alignLeft; //对齐
			pos -= cfg.focus == "left" ? 0 : cfg.focus == "center" ?
				(cfg.count*singleSize - containerSize)/2 : cfg.count*singleSize - containerSize;
			pos = pos < minPosition ? minPosition : Math.min(pos, 0);
			props[css] = initial[css] + pos;
			moveDiv.stop().animate(props, null, cfg.easing, cfg.duration);
		}
		function movenext(dir){
			var index;
			if( add && dir == 1 ){
				if(curIndex == lastIndex || ( pos == minPosition))
					index = 0;
				else index = Math.min(curIndex + cfg.count, length - 1);
			}else {
				if(curIndex == 0 || ( pos == 0)) index = lastIndex;
				else index = Math.max(curIndex - cfg.count, 0);
			}
			move(index);
		};
		function auto(){
			var index;
			if( cfg.random ){
				if(length > 1){
					do{
						index = Math.floor(Math.random() * length);
					}while(index == curIndex)
					move(index);
				}
			}
			else{
				movenext(1);
			}
		}
		
		btns.size() && btns.mouseover( function(){move(btns.index(this));} );
		
		$(cfg.container || container).hover(
			function(){ clearTimeout(timer); },
			function(){ if(cfg.autoPlay) timer = setInterval(auto, cfg.interval); }
		);
			
		
		$.each({"prev": -1, "next": 1}, function(k, v){
			var arrow = $(cfg[k + "Sel"], context);
			if(!arrow.size()) return;
			arrow.click(function(){ movenext(v); })
		});
		
		return result;
	},
	
	/**
	@parm direction 0: 上下左右 1: 上下 2: 左右
	*/
	hit: function (elem, direction, count, distance){
		if(elem.length && elem[0]){
			var i = 0;
			for(;i < elem.length; ++i)
				$.w.hit( elem[i], direction, count, distance );
			return;
		}
		var a, b, c, t = 0, orig = {left: $.css(elem, "left", true), top: $.css(elem, "top", true)} ;
		
		if(!direction){
			a = ['top', 'left']; b = 4; c = 2;
		}
		else if(direction == 1){
			a = ['top']; b = 2; c = 1;
		}
		else{
			a = ['left']; b = 2; c = 1;
		}
		count = count || 7;
		distance = distance || 3;
		
		if($.css(elem, "position") == 'static'){
			$.style( elem, "position", 'relative');
		}
		
		var tm = setInterval(function(){
			$.style(elem, a[ t%c ], orig[ a[ t%c ] ] + ( t++%b < c ? -distance : distance) );
			if(t > count){
				clearInterval(tm);
				$(elem).css(orig);
			}
		}, 24)
		
	},
	zoom: function(config){
		var cfg = $.extend({
			imgNodes: "img", //图片节点或jquery对象 
			bigImags: "rel", //大图路径 可以是数组， 默认读取原图rel和src属性
			//指示器模版
			rectTpl: $("<div>").css({opacity: .2, border: "1px solid #00f", background: "#000"}),
			//大图模版 如果包含子div可以用data-attach-point标识容器
			boxTpl: $("<div data-attach-point='container' style='border: 1px solid #ccc; background: #fff'></div>"),
			width: 300,
			height: 300,
			/**大图显示方式
			1. 1 ~ n 按倍数放大
			2. [width, height]固定大小
			*/
			method: 1,
			diff: [5, 0] //box和img之间间隔
			
		}, config);
		
		var body = document.body, win = $(window), doc = $(document),
			imgNodes = $(cfg.imgNodes),
			rect = $(cfg.rectTpl).css({zIndex: 9999, position: "absolute"}).appendTo(body).hide(),
			box = $(cfg.boxTpl).css({zIndex: 9999, position: "absolute"}).width(cfg.width).height(cfg.height).appendTo(body).hide(),
			boxContainer = ( boxContainer = box.find("[data-attach-point='container']") )
							&& boxContainer.length && boxContainer || box,
			img = $("<images>").appendTo(boxContainer).css("position", "absolute"),
			offset, target, contentBox = {left: 0, top: 0, right: 0, bottom: 0}, x, y;
		
		if( boxContainer.css("position") == "static" )
			boxContainer.css({position: "relative"});
			
		boxContainer.css({overflow: "hidden"});
		
		if( $.isArray(cfg.method) )
			img.width(cfg.method[0]).height(cfg.method[1]);
		
		function mouseover(e){
			target = $(this);
			offset = target.offset();
			rect.show(); box.show();
			x = e.pageX; y = e.pageY;
			
			_resetContentBox();
			
			var src = $.isArray(cfg.bigImags) ? cfg.bigImags[ imgNodes.index(this) ]
						: $(this).attr(cfg.bigImags) || $(this).attr("src");
				
			img[0].src = src;
			
			box.css(_getBoxPosition(offset, target.outerWidth(), target.outerHeight()));
			
			$.w.loadImage(src, function(w, h){
				if( !isNaN(cfg.method) && !isNaN(w) ){
					w = w * cfg.method; h = h * cfg.method;
					img.width(w).height(h);
					var rW = Math.min( cfg.width/w, 1 ) * target.width();
					var rH = Math.min( cfg.height/h, 1 ) * target.height();
					rect.outerWidth( rW ).outerHeight( rH );
					_resetRectPosition( rW, rH );
				}
			})
			doc.on("mousemove", mousemove);
		}
		function mousemove(e){
			x = e.pageX; y = e.pageY;
			if( x < contentBox.left || x > contentBox.right || y < contentBox.top || y > contentBox.bottom){
				mouseout();
			}
			_resetRectPosition( rect.outerWidth(), rect.outerHeight() );
		}
		function mouseout(e){
			doc.off("mousemove", mousemove);
			box.hide(); rect.hide();
		}
		function add(imgNodes){
			imgNodes.mouseover(mouseover);
		}
		
		add(imgNodes);
		
		return {
			reset: function(){
				if(target){
					offset = target.offset();
					_resetRectPosition();
					_resetContentBox();
					_getBoxPosition();
				}
			}
		};
		function _resetContentBox(){
			if(target){
				contentBox.width = target.width();
				contentBox.height = target.height();
				contentBox.left = offset.left + $.css(target[0], "borderLeftWidth", true) + $.css(target[0], "paddingLeft", true);
				contentBox.top = offset.top + $.css(target[0], "borderTopWidth", true) + $.css(target[0], "paddingTop", true);
				contentBox.right = contentBox.left + contentBox.width;
				contentBox.bottom = contentBox.top + contentBox.height;
			}
		}
		function _resetRectPosition(w, h){
			var left = x - w/2, top = y - h/2;
			left = Math.min(left, contentBox.right - w);
			left = Math.max(left, contentBox.left);
			top = Math.min(top, contentBox.bottom - h);
			top = Math.max(top, contentBox.top);
			rect.css({left: left, top: top});
			
			w = img.width(); h = img.height()
			left = (left - offset.left) / contentBox.width * w ;
			top = (top - offset.top) / contentBox.height * h;
			left = Math.min(left, w - cfg.width);
			left = Math.max(left, 0);
			top = Math.min(top, h - cfg.height);
			top = Math.max(top, 0);
			img.css({left: -left, top: -top});
			
		}
		function _getBoxPosition(offset, W, H){
			var scroll = {left: win.scrollLeft(), top: win.scrollTop()},
				width = box.outerWidth(),
				height = box.outerHeight(),
				left = offset.left + W + cfg.diff[0],
				top = offset.top + cfg.diff[1],
				maxTop = scroll.top + win.height(),
				maxLeft = scroll.left + win.width();
				
				if( top + height > maxTop ){
					top = maxTop - height;
					top = Math.max(top, scroll.top);
				}
				if( left + width > maxLeft ){
					var l = offset.left - width - cfg.diff[0];
					if( l > scroll.left || scroll.left - l < maxLeft - left ){
						left = l;
					}
				}
			return {top: top, left: left};
		}
	},
	calendar: function(cfg){
		cfg = $.extend({
			width: "",
			height: "",
			target: "",
			begin: "1950-1-2",
			end: 2030,
			onSelect: null,
			showOtherMonth: true,
			weekendDay: 0
			
		}, cfg);
		var domNode = cfg.target ? $(cfg.target) : $("<div>"),
			table = $("<table>").css({width: "100%"}).appendTo(domNode),
			thead = $("<thead>").appendTo(table),
			tbody = $("<tbody>").appendTo(table),
			tfoot = $("<tfoot>").appendTo(table),
			dayNames = ["日","一","二","三","四","五","六"],
			leapYear = function(y){
				return ((y%4==0 && y%100!=0) || y%400==0) ? 1 : 0;
			},
			getMonthDaysCount = function(y){
				return [31, 28 + leapYear(y),31,30,31,30,31,31,30,31,30,31];
			},
			saturday = 6 - cfg.weekendDay,
			sunday = (7 - cfg.weekendDay >= 7) ? 0 : (7 - cfg.weekendDay),
			i = 0, j, tr, td, s, rowCount = 6
			nowDate = new Date();
			
		domNode.css({width: cfg.width, height: cfg.height}).addClass("w-calendar");
		//头部
		tr = $("<tr>").appendTo(thead);
		while(i < dayNames.length){
			s = (i == saturday || i == sunday) ? " class='w-calendar-weekend'" : ""
			tr.append("<td "+ s +">" + dayNames[i] + "</td>");
			++i;
		}
		function select(date){
			var oNow = new Date(date),
				nowY = oNow.getFullYear(), nowM = oNow.getMonth(), nowD = oNow.getDate(),
				oDate = new Date(date),
				y = oDate.getFullYear(), m = oDate.getMonth(), d = oDate.getDate(),
				daysCount = getMonthDaysCount(y)[m],
				oLastMonth = new Date(y, m - 1),
				lastMonthDaysCount = getMonthDaysCount( oLastMonth.getFullYear() )[oLastMonth.getMonth()],
				firstDay = (new Date(y, m)).getDay();
			tbody.empty();
			i = 0;
			j = firstDay;
			tr = $("<tr>").appendTo(tbody);
			//上个月
			while( j-- ){
				td = $("<td>").appendTo(tr).addClass("w-calendar-othermonth");
				if(cfg.showOtherMonth)
					td.html( lastMonthDaysCount - j );
			}
			//这个月
			j = firstDay;
			var counter = 1;
			
			for(; i < rowCount; ){
				for(; j < 7; ){
					if( j == 0 )
						tr = $("<tr>").appendTo(tbody);
					td = $("<td>").appendTo(tr);
					if( counter == d)
						td.addClass("w-calendar-selected");
					td.html( counter++ );
					++j;
					if( counter > daysCount )break;
				}
				if( counter > daysCount )break;
				j = 0;
				++i;
			}
			//下个月
			counter = 1;
			while( i++ < rowCount ){
				for(; j < 7; ++j){
					if( j == 0 )
						tr = $("<tr>").appendTo(tbody);
					td = $("<td>").appendTo(tr).addClass("w-calendar-othermonth");
					if(cfg.showOtherMonth)
						td.html( counter++ );
				}
				j = 0;
			}
		}
		select("2013,7,5")
			
	},
	datePicker: function(cfg){
		cfg = $.extend({
			date: "", //默认日期
			begin: "1950-1-2",
			end: 2030,
			input: null,
			disabled: false,
			position: "",
			format: "Y-m-d H:i:s",
			showTime: true || false,
			week: 0
		}, cfg);
		
		if( !cfg.input )return false;
		var input = $(cfg.input),
			date = "" + (cfg.date || input.val()),
			oDate;
		
		if( input.prop("disabled") ) cfg.disabled = true;
		
		if( date.length == 4 )
			oDate = new Date(date, 0, 1);
		else
			oDate = new Date(date);
		
		var html = "<div class='w-datepicker'><div class='w-datepicker-border'>" +
				"<span class='w-datepicker-btns'><span class='w-datepicker-icon'></span></span>"+ "</div></div>",
			domNode = $(html);
			
		input.addClass("w-datepicker-input").before(domNode);
		var hover = function(){$(this).toggleClass("w-datepicker-btns-hover")};
		var btns = domNode.find(".w-datepicker-border").prepend(input).find(".w-datepicker-btns");
		if( !cfg.disabled ) btns.hover(hover);
		
		return {disable: function(b){
			input.prop("disabled", b);
			if( cfg.disabled == b )return;
			if( b )
				btns.bind(hover);
			else
				btns.unbind(hover);
			cfg.disabled = b;
		}}
			
			
	},
	/**
	 * 和PHP一样的时间戳格式化函数
	 * @param  {string} format    格式
	 * @param  {int}    timestamp 要格式化的时间 默认为当前时间
	 * @return {string}           格式化的时间字符串
	 */
	dateFormat: function (format, timestamp){ 
		var a, jsdate=((timestamp) ? new Date(timestamp) : new Date());
		var pad = function(n, c){
			if((n = n + "").length < c){
				return new Array(++c - n.length).join("0") + n;
			} else {
				return n;
			}
		};
		var txt_weekdays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
		var txt_ordin = {1:"st", 2:"nd", 3:"rd", 21:"st", 22:"nd", 23:"rd", 31:"st"};
		var txt_months = ["", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]; 
		var f = {
			// Day
			d: function(){return pad(f.j(), 2)},
			D: function(){return f.l().substr(0,3)},
			j: function(){return jsdate.getDate()},
			l: function(){return txt_weekdays[f.w()]},
			N: function(){return f.w() + 1},
			S: function(){return txt_ordin[f.j()] ? txt_ordin[f.j()] : 'th'},
			w: function(){return jsdate.getDay()},
			z: function(){return (jsdate - new Date(jsdate.getFullYear() + "/1/1")) / 864e5 >> 0},
	
			// Week
			W: function(){
				var a = f.z(), b = 364 + f.L() - a;
				var nd2, nd = (new Date(jsdate.getFullYear() + "/1/1").getDay() || 7) - 1;
				if(b <= 2 && ((jsdate.getDay() || 7) - 1) <= 2 - b){
					return 1;
				} else{
					if(a <= 2 && nd >= 4 && a >= (6 - nd)){
						nd2 = new Date(jsdate.getFullYear() - 1 + "/12/31");
						return date("W", Math.round(nd2.getTime()/1000));
					} else{
						return (1 + (nd <= 3 ? ((a + nd) / 7) : (a - (7 - nd)) / 7) >> 0);
					}
				}
			},
	
			// Month
			F: function(){return txt_months[f.n()]},
			m: function(){return pad(f.n(), 2)},
			M: function(){return f.F().substr(0,3)},
			n: function(){return jsdate.getMonth() + 1},
			t: function(){
				var n;
				if( (n = jsdate.getMonth() + 1) == 2 ){
					return 28 + f.L();
				} else{
					if( n & 1 && n < 8 || !(n & 1) && n > 7 ){
						return 31;
					} else{
						return 30;
					}
				}
			},
	
			// Year
			L: function(){var y = f.Y();return (!(y & 3) && (y % 1e2 || !(y % 4e2))) ? 1 : 0},
			//o not supported yet
			Y: function(){return jsdate.getFullYear()},
			y: function(){return (jsdate.getFullYear() + "").slice(2)},
	
			// Time
			a: function(){return jsdate.getHours() > 11 ? "pm" : "am"},
			A: function(){return f.a().toUpperCase()},
			B: function(){
				// peter paul koch:
				var off = (jsdate.getTimezoneOffset() + 60)*60;
				var theSeconds = (jsdate.getHours() * 3600) + (jsdate.getMinutes() * 60) + jsdate.getSeconds() + off;
				var beat = Math.floor(theSeconds/86.4);
				if (beat > 1000) beat -= 1000;
				if (beat < 0) beat += 1000;
				if ((String(beat)).length == 1) beat = "00"+beat;
				if ((String(beat)).length == 2) beat = "0"+beat;
				return beat;
			},
			g: function(){return jsdate.getHours() % 12 || 12},
			G: function(){return jsdate.getHours()},
			h: function(){return pad(f.g(), 2)},
			H: function(){return pad(jsdate.getHours(), 2)},
			i: function(){return pad(jsdate.getMinutes(), 2)},
			s: function(){return pad(jsdate.getSeconds(), 2)},
			//u not supported yet
	
			// Timezone
			//e not supported yet
			//I not supported yet
			O: function(){
				var t = pad(Math.abs(jsdate.getTimezoneOffset()/60*100), 4);
				if (jsdate.getTimezoneOffset() > 0) t = "-" + t; else t = "+" + t;
				return t;
			},
			P: function(){var O = f.O();return (O.substr(0, 3) + ":" + O.substr(3, 2))},
			//T not supported yet
			//Z not supported yet
	
			// Full Date/Time
			c: function(){return f.Y() + "-" + f.m() + "-" + f.d() + "T" + f.h() + ":" + f.i() + ":" + f.s() + f.P()},
			//r not supported yet
			U: function(){return Math.round(jsdate.getTime()/1000)}
		};
	
		return format.replace(/[\\]?([a-zA-Z])/g, function(t, s){
			if( t!=s ){
				// escaped
				ret = s;
			} else if( f[s] ){
				// a date function exists
				ret = f[s]();
			} else{
				// nothing special
				ret = s;
			}
			return ret;
		});
	},
	dateAdd: function(interval, number, date)
	{
		var d = new Date(date);
		var k={'y':'FullYear', 'q':'Month', 'm':'Month', 'w':'Date', 'd':'Date', 'h':'Hours', 'n':'Minutes', 's':'Seconds', 'ms':'MilliSeconds'};
		var n={'q':3, 'w':7}; 
		eval('d.set'+k[interval]+'(d.get'+k[interval]+'()+'+((n[interval]||1)*number)+')');
		return d; 
	},
	dateDiff: function(interval, date1, date2){
		date1 = new Date(date1);
		date2 = new Date(date2);
		var i = {}, t = date1.getTime(), t2 = date2.getTime(); 
		i['y'] = date2.getFullYear() - date1.getFullYear(); 
		i['q'] = i['y']*4 + Math.floor(date2.getMonth()/4) - Math.floor(date1.getMonth()/4);
		i['m'] = i['y']*12 + date2.getMonth() - date1.getMonth(); 
		i['ms'] = date2.getTime() - date1.getTime(); 
		i['w'] = Math.floor((t2+345600000)/(604800000))-Math.floor((t+345600000)/(604800000));
		i['d'] = Math.floor(t2/86400000)-Math.floor(t/86400000); 
		i['h'] = Math.floor(t2/3600000)-Math.floor(t/3600000); 
		i['n'] = Math.floor(t2/60000)-Math.floor(t/60000); 
		i['s'] = Math.floor(t2/1000)-Math.floor(t/1000); 
		return i[interval];
	},
	grid: function(cfg){
		cfg = $.extend({
			domNode: null,
			columns: null,
			subRows: null,
			data: null	
		},cfg)
		var domNode = $(cfg.domNode || "<div>"),
			table = $("<table>").css({
				borderCollapse: "collapse",
				tableLayout: "fixed"
			}).appendTo(domNode),
			rowIndex, rowCount, label, formatter, field,
			i,j,tr,td,columns,fields,rowSpan,colSpan;
		if( cfg.subRows ){
			$.each(cfg.data, function(key, data){
				for(i = 0; i < cfg.subRows.length; ++i){
					tr = $("<tr>").appendTo(table);
					columns = cfg.subRows[i];
					for(j = 0; j < columns.length; ++j) {
						fields = columns[j];
						rowSpan = colSpan = 0;
						if( $.isPlainObject(fields) ) {
							field = fields['field'];
							label = fields['label'] == undefined ? field : fields['label'];
							formatter = fields['formatter'];
							colSpan = fields['colSpan'];
							rowSpan = fields['rowSpan'];
						}else if( $.isArray(fields) )
						{
							field = fields[0];
							label = fields[1] == undefined ? field : fields[1];
							formatter = fields[2];
						}else{
							field = fields;
							label = field;
							formatter = null;
						}
						td = $("<td>").appendTo(tr).addClass("field-"+field);
						if( rowSpan )td.attr("rowSpan", rowSpan);
						if( colSpan )td.attr("colSpan", colSpan);
						if( formatter ) {
							td.html( formatter(data[field], data, td) );
						}else
							td.html( data[field] );
					}
				}
			});
			
		}
		return domNode;
	}
	
});

function _fixContainer(container){
	container = $(container);
	
	//获取高度 因为可能div未先设置高度
	var height = container.height();
	var cur = container;
	while(height == 0 && (cur = cur.parent())){
		height = cur.height();
	}
	
	while( container[0].nodeName.toLowerCase() in {"td": 1, "th": 1, "tr": 1} && container[0].parentNode){
		container = container.parent();
	}
	
	if( container.css("position") == 'static' ){
		container.css("position", 'relative');
	}
	container.height(height).css("overflow", "hidden");
}

})(jQuery);
