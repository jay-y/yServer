/**
 * yjs.utils.core.js
 *
 * Created by ysj on 2014
 */

var yjs = {};
!function (a, b, c) {
    /**
     * 检查空
     * @param obj
     * @returns {boolean}
     */
    c.checkEmpty = function (obj) {
        if (obj == null
            || obj == undefined
            || obj == "") {
            return true;
        }
        return false;
    }
    /**
     * 非空
     * @param obj
     * @returns {boolean}
     */
    c.checkNotEmpty = function (obj) {
        if (c.checkEmpty(obj)) {
            return false;
        }
        return true;
    };

    /**
     * 将form中的值转换为键值对
     * @param {Object} frm
     */
    c.serializeForm = function (frm) {
        var o = {};
        var a = $(frm).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };

    /**
     * 获取Request_Get值
     * @param {Object} url
     */
    c.readGetRequest = function (url) {
        var theRequest = new Object();
        if (url.indexOf("?") != -1) {
            var str = url.substr(1);
            strs = str.split("&");
            for (var i = 0; i < strs.length; i++) {
                theRequest[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);
            }
        }
        return theRequest;
    };

    /**
     * 生成一个随机数
     * @param {Object} str
     */
    c.hashCode = function (str) {
        var hash = 0;
        if (!str || str.length == 0) return hash.toString();
        for (i = 0; i < str.length; i++) {
            char = str.charCodeAt(i);
            hash = ((hash << 5) - hash) + char;
            hash = hash & hash; // Convert to 32bit integer
        }
        return hash.toString();
    };

    /**
     * 通过递归实现异步阻塞
     * @param {Object} list
     * @param {Object} callbackExec
     * @param {Object} callbackEnd
     */
    c.asynBlock = function (list, callbackExec, callbackEnd) {
        var each = function (_list, callback) {
            if (_list.length < 1) {
                return callbackEnd && callbackEnd();
            }
            callback(_list.shift(), function () {
                each(list, callback);
            })
        }
        each(list, callbackExec)
    };

    /**
     * 转键值对
     *
     * @param array
     * @returns {{}}
     */
    c.toMap = function (array) {
        var map = {};
        $.each(array, function () {
            if (map[this.name] !== undefined) {
                if (!map[this.name].push) {
                    map[this.name] = [map[this.name]];
                }
                map[this.name].push(this.value || '');
            }
            else {
                map[this.name] = this.value || '';
            }
        });
        return map;
    };
    c.core = c;
    a.yjs = c;
    b.yjs = c;
}(window, document, yjs);