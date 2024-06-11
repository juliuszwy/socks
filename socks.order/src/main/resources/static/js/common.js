if (!window.console || !console.firebug) {
	var names = ["log", "debug", "info", "warn", "error", "assert", "dir", "dirxml", "group", "groupEnd", "time", "timeEnd", "count", "trace", "profile", "profileEnd"];
	window.console = {};
	for (var i = 0; i < names.length; ++i) {
		window.console[names[i]] = function() {
		};
	}
}

/*
 * =========== MARKET ===============
 */
var market = {};

/*
 * 页面的通用的一些提示语常量
 */
market.content = {
	title : '温馨提示',
	tips : '请先选择要操作的数据！',
	confirm : '您确定要删除该条记录吗？',
	submitting : '正在提交数据...',
	networkError : '由于网络或服务器太忙，提交失败，请重试！'
};
$(function() {
    var oDiv = document.getElementsByTagName('body')[0];
	if(oDiv.id=='sgeBody')
		$('body').append('<div id="myWindow" class="easyui-dialog" fit="true" closed="true"></div>');
	else
	$('body').append('<div id="myWindow" class="easyui-dialog"  closed="true"></div>');
});
function showMsg(title, msg, isAlert) {
	if (isAlert !== undefined && isAlert) {
		$.messager.alert(title, msg);
	} else {
		$.messager.show({
			title : title,
			msg : msg,
			showType : 'show'
		});
	}
}
function showMyWindow(title, href, width, height, modal, minimizable, maximizable) {
	$('#myWindow').window({
		title : title,
		width : width === undefined ? 600 : width,
		height : height === undefined ? 400 : height,
		content : '<iframe scrolling="yes" frameborder="0" src="' + href + '" style="width:100%;height:100%;"></iframe>',
		// href: href === undefined ? null : href,
		modal : modal === undefined ? true : modal,
		minimizable : minimizable === undefined ? false : minimizable,
		maximizable : maximizable === undefined ? false : maximizable,
		shadow : false,
		cache : false,
		closed : false,
		collapsible : false,
		resizable : false,
		loadingMessage : '正在加载数据，请稍等片刻......'
	});
}
function closeMyWindow() {
	$('#myWindow').window('close');
}
function showConfirm(title, msg, callback) {
	$.messager.confirm(title, msg, function(r) {
		if (r) {
			if (jQuery.isFunction(callback))
				callback.call();
		}
	});
}
function showProcess(isShow, title, msg) {
	if (!isShow) {
		$.messager.progress('close');
		return;
	}
	var win = $.messager.progress({
		title : title,
		msg : msg
	});
}
var CityName = {
		'0' : "全国",
		'1' : "上海市",
		'2' : "云南省",
		'3' : "内蒙古",
		'4' : "北京市",
		'5' : "吉林省",
		'6' : "四川省",
		'7' : "天津市",
		'8' : "宁夏",
		'9' : "安徽省",
		'10' : "山东省",
		'11' : "山西省",
		'12' : "广东省",
		'13' : "广西",
		'14' : "新疆",
		'15' : "江苏省",
		'16' : "江西省",
		'17' : "河北省",
		'18' : "河南省",
		'19' : "浙江省",
		'20' : "海南省",
		'21' : "湖北省",
		'22' : "湖南省",
		'23' : "甘肃省",
		'24' : "福建省",
		'25' : "西藏",
		'26' : "贵州省",
		'27' : "辽宁省",
		'28' : "重庆市",
		'29' : "陕西省",
		'30' : "青海省",
		'31' : "黑龙江"
	};
function submitForm(url) {
	$('#ff').form('submit',	{
		url : url,
		onSubmit : function() {
			var flag = $(this).form('validate');
			if (flag) {
				showProcess(true, market.content.title, market.content.submitting);
			}
			return flag;
		},
		success : function(data) {
			showProcess(false);
			var data = eval('(' + data + ')');
			if (data.code == '0') {
				top.showMsg(market.content.title, data.message, alert);
				if (parent !== undefined) {
					if ($.isFunction(parent.reloadParent)) {
						parent.reloadParent.call();
						parent.closeMyWindow();
					} else {
						parent.$("#tt").datagrid('reload');
						parent.closeMyWindow();
					}
				}
			} else {
				$.messager.alert(market.content.title, data.message);
			}
		},
		onLoadError : function() {
			showProcess(false);
			$.messager.alert(market.content.title, market.content.networkError);
		}
	});
}
function clearForm(){
    $('#ff').form('clear');
}

// extends jquery function for scroll to top
$.fn.DynamicToTop = function(options) {
	var defaults = {
		text: "",
		min: "200",
		fade_in: 600,
		fade_out: 400,
		speed: "1000",
		easing: "",
		version: "",
		id: 'dynamic-to-top'
	};
	var settings = $.extend(defaults, options);
	if (settings.version == "") {
		settings.text = '';
	}
	var $toTop = $('<a href=\"#\" id=\"' + settings.id + '\"></a>').html(settings.text);
	$toTop.appendTo(document.body);
	$toTop.hide().click(function() {
		$('html, body').stop().animate({
			scrollTop: 0
		},
		settings.speed, settings.easing);
		return false;
	});
	$(window).scroll(function() {
		var sd = $(window).scrollTop();
		if (typeof document.body.style.maxHeight === "undefined") {
			$toTop.css({
				'position': 'absolute',
				'top': $(window).scrollTop() + $(window).height() - mv_dynamic_to_top.margin
			});
		}
		if (sd > settings.min) {
			$toTop.fadeIn(settings.fade_in);
		} else {
			$toTop.fadeOut(settings.fade_out);
		}
	});


};

function formatDateTime(str){
	var oDate = new Date(str),
		oYear = oDate.getFullYear(),
		oMonth = oDate.getMonth()+1,
		oDay = oDate.getDate(),
		oHour = oDate.getHours(),
		oMin = oDate.getMinutes(),
		oSen = oDate.getSeconds(),
		oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);
	return oTime;
};

function formatDate(str){
	var oDate = new Date(str),
		oYear = oDate.getFullYear(),
		oMonth = oDate.getMonth()+1,
		oDay = oDate.getDate(),
		oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay);//最后拼接时间
	return oTime;
};
//补0操作
function getzf(num){
	if(parseInt(num) < 10){
		num = '0'+num;
	}
	return num;
};


$.fn.datagrid.defaults.view = $.extend({}, $.fn.datagrid.defaults.view, {
	renderRow: function (target, fields, frozen, rowIndex, rowData) {
		var opts = $.data(target, 'datagrid').options;
		var cc = [];
		if (frozen && opts.rownumbers) {
			var rownumber = rowIndex + 1;
			if (opts.pagination) {
				rownumber += (opts.pageNumber - 1) * opts.pageSize;
			}
			cc.push('<td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">' + rownumber + '</div></td>');
		}
		for (var i = 0; i < fields.length; i++) {
			var field = fields[i];
			var col = $(target).datagrid('getColumnOption', field);
			var fieldSp = field.split(".");
			var dta = rowData[fieldSp[0]];
			for (var j = 1; j < fieldSp.length; j++) {
				dta = dta[fieldSp[j]];
			}
			if (col) {
				// get the cell style attribute
				var styleValue = col.styler ? (col.styler(dta, rowData, rowIndex) || '') : '';
				var style = col.hidden ? 'style="display:none;' + styleValue + '"' : (styleValue ? 'style="' + styleValue + '"' : '');

				cc.push('<td field="' + field + '" ' + style + '>');

				var style = 'width:' + (col.boxWidth) + 'px;';
				style += 'text-align:' + (col.align || 'left') + ';';
				style += opts.nowrap == false ? 'white-space:normal;' : '';

				cc.push('<div style="' + style + '" ');
				if (col.checkbox) {
					cc.push('class="datagrid-cell-check ');
				} else {
					cc.push('class="datagrid-cell ');
				}
				cc.push('">');

				if (col.checkbox) {
					cc.push('<input type="checkbox"/>');
				} else if (col.formatter) {
					cc.push(col.formatter(dta, rowData, rowIndex));
				} else {
					cc.push(dta);
				}

				cc.push('</div>');
				cc.push('</td>');
			}
		}
		return cc.join('');
	}
});