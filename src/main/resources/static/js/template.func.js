/**
 * template.js模板插件专用
 */

/**
 * 小数点后面的位数
 * @param {Object} number 需格式化数值
 * @param {Object} dot 保留小数点位数
 */
template.helper('dotcount', function (number,dot) {
	if(number){
		if(dot){
			return (new Number(number)).toFixed(dot);
		}else{
			return (new Number(number)).toFixed(2);
		}
	}else{
		return 0;
	}
});

/**
 * 去掉所有的html标记多文本溢出处理
 * @param {Object} str 需格式化数值
 * @param {Object} len 截取的长度
 * @param {Object} tag 代替的字符
 */
template.helper('delHtmlTag',function(str,len,tag){
    str= str.replace(/<[^>]+>/g,"");
    if(!len)len=100;
    if(!tag)tag="";
    if(str.length>len){
        str=str.substring(0,len)+tag;
    }
    return str;
});

/**
 * 日期格式转换
 * @param {Object} date 毫秒数
 * @param {Object} format 需转换的格式
 */
template.helper('dateFormat', function (date, format) {
    if (typeof date === "string") {
        var mts = date.match(/(\/Date\((\d+)\)\/)/);
        if (mts && mts.length >= 3) {
            date = parseInt(mts[2]);
        }
    }
    date = new Date(date);
    if (!date || date.toUTCString() == "Invalid Date") {
        return "";
    }

    var map = {
        "M": date.getMonth() + 1, //月份 
        "d": date.getDate(), //日 
        "h": date.getHours(), //小时 
        "m": date.getMinutes(), //分 
        "s": date.getSeconds(), //秒 
        "q": Math.floor((date.getMonth() + 3) / 3), //季度 
        "S": date.getMilliseconds() //毫秒 
    };
    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
        var v = map[t];
        if(v !== undefined){
            if(all.length > 1){
                v = '0' + v;
                v = v.substr(v.length-2);
            }
            return v;
        }
        else if(t === 'y'){
            return (date.getFullYear() + '').substr(4 - all.length);
        }
        return all;
    });
    return format;
});

/**
 * 小数转换成百分比
 * @param {Object} number 需转换的数值
 */
template.helper('percent',function(number){
	if(number){
		return precisionLoss((new Number(number)).toFixed(2)*100,1);
	}else{
		return 0;
	}
});

/**
 * 防止计算时精度丢失（f表示底数，digit表示幂数）
 * @param f
 * @param digit
 * @returns {number}
 */
function precisionLoss(f, digit){
	var m = Math.pow(10, digit); 
    return parseInt(f * m, 10) / m; 
}

