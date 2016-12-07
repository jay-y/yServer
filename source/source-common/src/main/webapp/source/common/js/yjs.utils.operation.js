/**
 * yjs.utils.operation.js
 * 依赖于:yjs.utils.core.js
 *
 * Created by ysj on 2016/11/9.
 */
!function (a, b, c) {
    c.operation = {
        /**
         * 除法函数
         *
         * @param arg1
         * @param arg2
         * @returns {number}
         */
        div: function (arg1, arg2) {
            var t1 = 0, t2 = 0, r1, r2;
            try {
                t1 = arg1.toString().split(".")[1].length
            } catch (e) {
            }
            try {
                t2 = arg2.toString().split(".")[1].length
            } catch (e) {
            }
            with (Math) {
                r1 = Number(arg1.toString().replace(".", ""));
                r2 = Number(arg2.toString().replace(".", ""));
                return (r1 / r2) * pow(10, t2 - t1);
            }
        },
        /**
         * 乘法函数
         *
         * @param arg1
         * @param arg1
         * @returns {number}
         */
        mul: function (arg1, arg2) {
            var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
            try {
                m += s1.split(".")[1].length
            } catch (e) {
            }
            try {
                m += s2.split(".")[1].length
            } catch (e) {
            }
            return Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
        },
        /**
         * 加法函数
         *
         * @param arg1
         * @param arg2
         * @returns {number}
         */
        add: function (arg1, arg2) {
            var r1, r2, m;
            try {
                r1 = arg1.toString().split(".")[1].length
            } catch (e) {
                r1 = 0
            }
            try {
                r2 = arg2.toString().split(".")[1].length
            } catch (e) {
                r2 = 0
            }
            m = Math.pow(10, Math.max(r1, r2));
            return (arg1 * m + arg2 * m) / m;
        },
        /**
         * 减法函数
         *
         * @param arg1
         * @param arg2
         * @returns {number}
         */
        sub: function (arg1, arg2) {
            var r1, r2, m;
            try {
                r1 = arg1.toString().split(".")[1].length
            } catch (e) {
                r1 = 0
            }
            try {
                r2 = arg2.toString().split(".")[1].length
            } catch (e) {
                r2 = 0
            }
            m = Math.pow(10, Math.max(r1, r2));
            return (arg2 * m - arg1 * m) / m;
        }
    };

    Number.prototype.div = function (arg) {
        return c.operation.div(this, arg);
    };

    Number.prototype.mul = function (arg) {
        return c.operation.mul(arg, this);
    };

    Number.prototype.add = function (arg) {
        return c.operation.add(arg, this);
    };

    Number.prototype.sub = function (arg) {
        return c.operation.sub(arg, this);
    };
}(window, document, yjs);