/*
 * psa client using AJAX XMLHttpRequest.
 *
 * DESCRIPTION
 * This file provides self-contained support for using the psa protocol through AJAX-technology. 
 * The XMLHttpRequest is used to exchange the psa protocol XML messages and JSON in later versions).
 * Currently only HTTP GET/POST is used in asynchronous mode
 *
 * since: psa-client.js,v 1.0 2013/01/06 13:44:08  include com
 *        psa-client.js v 2.0 2013/08/12 13:12:36  include usb com
 * 
 * 
 */

//0.PAS FrameWork define

/**
 * 判断数组中是否存在元素
 * @param item 元素
 * @return true 存在 false 不存在 
 */
Array.prototype.contains = function(item){
	for (var i=0; i < this.length; i++) {
		if (this[i] === item){
            return true;
		}
    }
    return false;
};

//2.Begin Use Init PSA
//PSA._addEvent(window, 'load', PSA._init, false);
