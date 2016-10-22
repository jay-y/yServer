eval(function (p, a, c, k, e, r) {
    e = function (c) {
        return (c < 62 ? '' : e(parseInt(c / 62))) + ((c = c % 62) > 35 ? String.fromCharCode(c + 29) : c.toString(36))
    };
    if ('0'.replace(0, e) == 0) {
        while (c--)r[e(c)] = k[c];
        k = [function (e) {
            return r[e] || e
        }];
        e = function () {
            return '([3-59cf-hj-mo-rt-yCG-NP-RT-Z]|[12]\\w)'
        };
        c = 1
    }
    ;
    while (c--)if (k[c])p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
    return p
}('5 $$,$$B,$$A,$$F,$$D,$$E,$$CE,$$S;(3(){5 O,B,A,F,D,E,CE,S;O=3(id){4"1V"==1B id?M.getElementById(id):id};O.emptyFunction=3(){};O.extend=3(I,12,1n){9(1n===1W)1n=13;J(5 N K 12){9(1n||!(N K I)){I[N]=12[N]}}4 I};O.deepextend=3(I,12){J(5 N K 12){5 1f=12[N];9(I===1f)continue;9(1B 1f==="c"){I[N]=C.callee(I[N]||{},1f)}P{I[N]=1f}}4 I};O.wrapper=3(me,1Y){5 1C=3(){me.Q(14,C)};5 1D=3(){};1D.15=1Y.15;1C.15=new 1D;4 1C};B=(3(R){5 b={17:/17/.G(R)&&!/1E/.G(R),1E:/1E/.G(R),1Z:/webkit/.G(R)&&!/1F/.G(R),20:/20/.G(R),1F:/1F/.G(R)};5 1o="";J(5 i K b){9(b[i]){1o="1Z"==i?"1g":i;1G}}b.1g=1o&&1H("(?:"+1o+")[\\\\/: ]([\\\\d.]+)").G(R)?1H.$1:"0";b.ie=b.17;b.21=b.17&&1J(b.1g,10)==6;b.ie7=b.17&&1J(b.1g,10)==7;b.22=b.17&&1J(b.1g,10)==8;4 b})(1K.navigator.userAgent.toLowerCase());A=3(){5 l={isArray:3(23){4 Object.15.toString.1h(23)==="[c 1L]"},1p:3(w,V,f){9(w.1p){4 1q(f)?w.1p(V):w.1p(V,f)}P{5 T=w.1i;f=1q(f)?0:f<0?1r.24(f)+T:1r.25(f);J(;f<T;f++){9(w[f]===V)4 f}4-1}},1s:3(w,V,f){9(w.1s){4 1q(f)?w.1s(V):w.1s(V,f)}P{5 T=w.1i;f=1q(f)||f>=T-1?T-1:f<0?1r.24(f)+T:1r.25(f);J(;f>-1;f--){9(w[f]===V)4 f}4-1}}};3 Z(c,t){9(1W===c.1i){J(5 k K c){9(x===t(c[k],k,c))1G}}P{J(5 i=0,T=c.1i;i<T;i++){9(i K c){9(x===t(c[i],i,c))1G}}}};Z({26:3(c,t,r){Z(c,3(){t.Q(r,C)})},map:3(c,t,r){5 l=[];Z(c,3(){l.27(t.Q(r,C))});4 l},1t:3(c,t,r){5 l=[];Z(c,3(28){t.Q(r,C)&&l.27(28)});4 l},every:3(c,t,r){5 l=13;Z(c,3(){9(!t.Q(r,C)){l=x;4 x}});4 l},some:3(c,t,r){5 l=x;Z(c,3(){9(t.Q(r,C)){l=13;4 x}});4 l}},3(29,k){l[k]=3(c,t,r){9(c[k]){4 c[k](t,r)}P{4 29(c,t,r)}}});4 l}();F=(3(){5 18=1L.15.18;4{bind:3(1u,r){5 19=18.1h(C,2);4 3(){4 1u.Q(r,19.2a(18.1h(C)))}},bindAsEventListener:3(1u,r){5 19=18.1h(C,2);4 3(g){4 1u.Q(r,[E.1j(g)].2a(19))}}}})();D={1v:3(m){5 1a=m?m.2b:M;4 1a.2c.2d||1a.2e.2d},1w:3(m){5 1a=m?m.2b:M;4 1a.2c.2f||1a.2e.2f},2g:M.1k?3(a,b){4!!(a.compareDocumentPosition(b)&16)}:3(a,b){4 a!=b&&a.2g(b)},v:3(m){5 o=0,U=0,W=0,X=0;9(!m.2h||B.22){5 n=m;while(n){o+=n.offsetLeft,U+=n.offsetTop;n=n.offsetParent};W=o+m.offsetWidth;X=U+m.offsetHeight}P{5 v=m.2h();o=W=D.1w(m);U=X=D.1v(m);o+=v.o;W+=v.W;U+=v.U;X+=v.X};4{"o":o,"U":U,"W":W,"X":X}},clientRect:3(m){5 v=D.v(m),1M=D.1w(m),1N=D.1v(m);v.o-=1M;v.W-=1M;v.U-=1N;v.X-=1N;4 v},curStyle:M.1k?3(p){4 M.1k.2i(p,2j)}:3(p){4 p.1x},getStyle:M.1k?3(p,k){5 h=M.1k.2i(p,2j);4 k K h?h[k]:h.getPropertyValue(k)}:3(p,k){5 h=p.1x;9(k=="11"){9(/1O\\(11=(.*)\\)/i.G(h.1t)){5 11=parseFloat(1H.$1);4 11?11/2k:0}4 1};9(k=="2l"){k="2m"}5 l=h[k]||h[S.1P(k)];9(!/^\\-?\\d+(px)?$/i.G(l)&&/^\\-?\\d/.G(l)){h=p.h,o=h.o,2o=p.1Q.o;p.1Q.o=p.1x.o;h.o=l||0;l=h.pixelLeft+"px";h.o=o;p.1Q.o=2o}4 l},setStyle:3(1l,h,1b){9(!1l.1i){1l=[1l]}9(1B h=="1V"){5 s=h;h={};h[s]=1b}A.26(1l,3(p){J(5 k K h){5 1b=h[k];9(k=="11"&&B.ie){p.h.1t=(p.1x.1t||"").2p(/1O\\([^)]*\\)/,"")+"1O(11="+1b*2k+")"}P 9(k=="2l"){p.h[B.ie?"2m":"cssFloat"]=1b}P{p.h[S.1P(k)]=1b}}})}};E=(3(){5 1c,1d,y=1;9(1K.2q){1c=3(u,j,q){u.2q(j,q,x)};1d=3(u,j,q){u.removeEventListener(j,q,x)}}P{1c=3(u,j,q){9(!q.$$y)q.$$y=y++;9(!u.Y)u.Y={};5 H=u.Y[j];9(!H){H=u.Y[j]={};9(u["on"+j]){H[0]=u["on"+j]}}H[q.$$y]=q;u["on"+j]=1y};1d=3(u,j,q){9(u.Y&&u.Y[j]){2r u.Y[j][q.$$y]}};3 1y(){5 1z=13,g=1j();5 H=14.Y[g.j];J(5 i K H){14.$$1y=H[i];9(14.$$1y(g)===x){1z=x}}4 1z}}3 1j(g){9(g)4 g;g=1K.g;g.pageX=g.clientX+D.1w(g.1S);g.pageY=g.clientY+D.1v(g.1S);g.target=g.1S;g.1T=1T;g.1U=1U;5 1A={"mouseout":g.toElement,"mouseover":g.fromElement}[g.j];9(1A){g.1A=1A}4 g};3 1T(){14.cancelBubble=13};3 1U(){14.1z=x};4{"1c":1c,"1d":1d,"1j":1j}})();CE=(3(){5 y=1;4{1c:3(c,j,q){9(!q.$$$y)q.$$$y=y++;9(!c.L)c.L={};9(!c.L[j])c.L[j]={};c.L[j][q.$$$y]=q},1d:3(c,j,q){9(c.L&&c.L[j]){2r c.L[j][q.$$$y]}},fireEvent:3(c,j){9(!c.L)4;5 19=1L.15.18.1h(C,2),H=c.L[j];J(5 i K H){H[i].Q(c,19)}}}})();S={1P:3(s){4 s.2p(/-([a-z])/ig,3(all,2s){4 2s.toUpperCase()})}};9(B.21){try{M.execCommand("BackgroundImageCache",x,13)}catch(e){}};$$=O;$$B=B;$$A=A;$$F=F;$$D=D;$$E=E;$$CE=CE;$$S=S})();', [], 153, '|||function|return|var||||if|||object|||from|event|style||type|name|ret|node||left|elem|handler|thisp||callback|element|rect|array|false|guid||||arguments||||test|handlers|destination|for|in|cusevents|document|property||else|apply|ua||len|top|elt|right|bottom|events|each||opacity|source|true|this|prototype||msie|slice|args|doc|value|addEvent|removeEvent||copy|version|call|length|fixEvent|defaultView|elems||override|vMark|indexOf|isNaN|Math|lastIndexOf|filter|fun|getScrollTop|getScrollLeft|currentStyle|handleEvent|returnValue|relatedTarget|typeof|ins|subclass|opera|chrome|break|RegExp||parseInt|window|Array|sLeft|sTop|alpha|camelize|runtimeStyle||srcElement|stopPropagation|preventDefault|string|undefined||parent|safari|firefox|ie6|ie8|obj|ceil|floor|forEach|push|item|method|concat|ownerDocument|documentElement|scrollTop|body|scrollLeft|contains|getBoundingClientRect|getComputedStyle|null|100|float|styleFloat||rsLeft|replace|addEventListener|delete|letter'.split('|'), 0, {}));

var ImageZoom = function (image, viewer, options) {
    this._initialize(image, viewer, options);
    this._initLoad();
};

ImageZoom.prototype = {
    //初始化程序
    _initialize: function (image, viewer, options) {
        this._image = $$(image);//原图
        this._zoom = document.createElement("img");//显示图
        this._viewer = $$(viewer);//显示框
        this._viewerWidth = 0;//显示框宽
        this._viewerHeight = 0;//显示框高
        this._preload = new Image();//预载对象
        this._rect = null;//原图坐标
        this._repairLeft = 0;//显示图x坐标修正
        this._repairTop = 0;//显示图y坐标修正
        this._rangeWidth = 0;//显示范围宽度
        this._rangeHeight = 0;//显示范围高度
        this._timer = null;//计时器
        this._loaded = false;//是否加载
        this._substitute = false;//是否替换

        var opt = this._setOptions(options);

        this._scale = opt.scale;
        this._max = opt.max;
        this._min = opt.min;
        this._originPic = opt.originPic;
        this._zoomPic = opt.zoomPic;
        this._rangeWidth = opt.rangeWidth;
        this._rangeHeight = opt.rangeHeight;

        this.delay = opt.delay;
        this.autoHide = opt.autoHide;
        this.mouse = opt.mouse;
        this.rate = opt.rate;

        this.onLoad = opt.onLoad;
        this.onStart = opt.onStart;
        this.onMove = opt.onMove;
        this.onEnd = opt.onEnd;

        var oThis = this, END = function () {
            oThis._end();
        };
        this._END = function () {
            oThis._timer = setTimeout(END, oThis.delay);
        };
        this._START = $$F.bindAsEventListener(this._start, this);
        this._MOVE = $$F.bindAsEventListener(this._move, this);
        this._MOUSE = $$F.bindAsEventListener(this._mouse, this);
        this._OUT = $$F.bindAsEventListener(function (e) {
            if (!e.relatedTarget) this._END();
        }, this);

        $$CE.fireEvent(this, "init");
    },
    //设置默认属性
    _setOptions: function (options) {
        this.options = {//默认值
            scale: 0,//比例(大图/原图)
            max: 10,//最大比例
            min: 1.5,//最小比例
            originPic: "",//原图地址
            zoomPic: "",//大图地址
            rangeWidth: 0,//显示范围宽度
            rangeHeight: 0,//显示范围高度
            delay: 20,//延迟结束时间
            autoHide: true,//是否自动隐藏
            mouse: false,//鼠标缩放
            rate: .2,//鼠标缩放比率
            onLoad: $$.emptyFunction,//加载完成时执行
            onStart: $$.emptyFunction,//开始放大时执行
            onMove: $$.emptyFunction,//放大移动时执行
            onEnd: $$.emptyFunction//放大结束时执行
        };
        return $$.extend(this.options, options || {});
    },
    //初始化加载
    _initLoad: function () {
        var image = this._image, originPic = this._originPic,
            useOrigin = !this._zoomPic && this._scale,
            loadImage = $$F.bind(useOrigin ? this._loadOriginImage : this._loadImage, this);
        //设置自动隐藏
        this.autoHide && this._hide();
        //先加载原图
        if (originPic && originPic != image.src) {//使用自定义地址
            image.onload = loadImage;
            image.src = originPic;
        } else if (image.src) {//使用元素地址
            if (!image.complete) {//未载入完
                image.onload = loadImage;
            } else {//已经载入
                loadImage();
            }
        } else {
            return;//没有原图地址
        }
        //加载大图
        if (!useOrigin) {
            var preload = this._preload, zoomPic = this._zoomPic || image.src,
                loadPreload = $$F.bind(this._loadPreload, this);
            if (zoomPic != preload.src) {//新地址重新加载
                preload.onload = loadPreload;
                preload.src = zoomPic;
            } else {//正在加载
                if (!preload.complete) {//未载入完
                    preload.onload = loadPreload;
                } else {//已经载入
                    this._loadPreload();
                }
            }
        }
    },
    //原图放大加载程序
    _loadOriginImage: function () {
        this._image.onload = null;
        this._zoom.src = this._image.src;
        this._initLoaded();
    },
    //原图加载程序
    _loadImage: function () {
        this._image.onload = null;
        if (this._loaded) {//大图已经加载
            this._initLoaded();
        } else {
            this._loaded = true;
            if (this._scale) {//有自定义比例才用原图放大替换大图
                this._substitute = true;
                this._zoom.src = this._image.src;
                this._initLoaded();
            }
        }
    },
    //大图预载程序
    _loadPreload: function () {
        this._preload.onload = null;
        this._zoom.src = this._preload.src;
        if (this._loaded) {//原图已经加载
            //没有使用替换
            if (!this._substitute) {
                this._initLoaded();
            }
        } else {
            this._loaded = true;
        }
    },
    //初始化加载设置
    _initLoaded: function (src) {
        //初始化显示图
        this._initSize();
        //初始化显示框
        this._initViewer();
        //初始化数据
        this._initData();
        //开始执行
        $$CE.fireEvent(this, "load");
        this.onLoad();
        this.start();
    },
    //初始化显示图尺寸
    _initSize: function () {
        var zoom = this._zoom, image = this._image, scale = this._scale;
        if (!scale) {
            scale = this._preload.width / image.width;
        }
        this._scale = scale = Math.min(Math.max(this._min, scale), this._max);
        //按比例设置显示图大小
        zoom.width = Math.ceil(image.width * scale);
        zoom.height = Math.ceil(image.height * scale);
    },
    //初始化显示框
    _initViewer: function () {
        var zoom = this._zoom, viewer = this._viewer;
        //设置样式
        var styles = {padding: 0, overflow: "hidden"}, p = $$D.getStyle(viewer, "position");
        if (p != "relative" && p != "absolute") {
            styles.position = "relative";
        }
        ;
        $$D.setStyle(viewer, styles);
        zoom.style.position = "absolute";
        //插入显示图
        if (!$$D.contains(viewer, zoom)) {
            viewer.appendChild(zoom);
        }
    },
    //初始化数据
    _initData: function () {
        var zoom = this._zoom, image = this._image, viewer = this._viewer,
            scale = this._scale, rangeWidth = this._rangeWidth, rangeHeight = this._rangeHeight;
        //原图坐标
        this._rect = $$D.rect(image);
        //修正参数
        this._repairLeft = image.clientLeft + parseInt($$D.getStyle(image, "padding-left"));
        this._repairTop = image.clientTop + parseInt($$D.getStyle(image, "padding-top"));
        //设置范围参数和显示框大小
        if (rangeWidth > 0 && rangeHeight > 0) {
            rangeWidth = Math.ceil(rangeWidth);
            rangeHeight = Math.ceil(rangeHeight);
            this._viewerWidth = Math.ceil(rangeWidth * scale);
            this._viewerHeight = Math.ceil(rangeHeight * scale);
            $$D.setStyle(viewer, {
                width: this._viewerWidth + "px",
                height: this._viewerHeight + "px"
            });
        } else {
            var styles;
            if (!viewer.clientWidth) {//隐藏
                var style = viewer.style;
                styles = {
                    display: style.display,
                    position: style.position,
                    visibility: style.visibility
                };
                $$D.setStyle(viewer, {
                    display: "block", position: "absolute", visibility: "hidden"
                });
            }
            this._viewerWidth = viewer.clientWidth;
            this._viewerHeight = viewer.clientHeight;
            if (styles) {
                $$D.setStyle(viewer, styles);
            }

            rangeWidth = Math.ceil(this._viewerWidth / scale);
            rangeHeight = Math.ceil(this._viewerHeight / scale);
        }
        this._rangeWidth = rangeWidth;
        this._rangeHeight = rangeHeight;
    },
    //开始
    _start: function () {
        clearTimeout(this._timer);
        var viewer = this._viewer, image = this._image, scale = this._scale;
        viewer.style.display = "block";
        $$CE.fireEvent(this, "start");
        this.onStart();
        $$E.removeEvent(image, "mouseover", this._START);
        $$E.removeEvent(image, "mousemove", this._START);
        $$E.addEvent(document, "mousemove", this._MOVE);
        $$E.addEvent(document, "mouseout", this._OUT);
        this.mouse && $$E.addEvent(document, $$B.firefox ? "DOMMouseScroll" : "mousewheel", this._MOUSE);
    },
    //移动
    _move: function (e) {
        clearTimeout(this._timer);
        var x = e.pageX, y = e.pageY, rect = this._rect;
        if (x < rect.left || x > rect.right || y < rect.top || y > rect.bottom) {
            this._END();//移出原图范围
        } else {
            var pos = {}, scale = this._scale, zoom = this._zoom,
                viewerWidth = this._viewerWidth,
                viewerHeight = this._viewerHeight;
            //修正坐标
            pos.left = viewerWidth / 2 - ( x - rect.left - this._repairLeft ) * scale;
            pos.top = viewerHeight / 2 - ( y - rect.top - this._repairTop ) * scale;

            $$CE.fireEvent(this, "repair", e, pos);
            //范围限制
            x = Math.ceil(Math.min(Math.max(pos.left, viewerWidth - zoom.width), 0));
            y = Math.ceil(Math.min(Math.max(pos.top, viewerHeight - zoom.height), 0));
            //设置定位
            zoom.style.left = x + "px";
            zoom.style.top = y + "px";

            $$CE.fireEvent(this, "move", e, x, y);
            this.onMove();
        }
    },
    //结束
    _end: function () {
        $$CE.fireEvent(this, "end");
        this.onEnd();
        this.autoHide && this._hide();
        this.stop();
        this.start();
    },
    //隐藏
    _hide: function () {
        this._viewer.style.display = "none";
    },
    //鼠标缩放
    _mouse: function (e) {
        this._scale += ( e.wheelDelta ? e.wheelDelta / (-120) : (e.detail || 0) / 3 ) * this.rate;

        var opt = this.options;
        this._rangeWidth = opt.rangeWidth;
        this._rangeHeight = opt.rangeHeight;

        this._initSize();
        this._initData();
        this._move(e);
        e.preventDefault();
    },
    //开始
    start: function () {
        if (this._viewerWidth && this._viewerHeight) {
            var image = this._image, START = this._START;
            $$E.addEvent(image, "mouseover", START);
            $$E.addEvent(image, "mousemove", START);
        }
    },
    //停止
    stop: function () {
        clearTimeout(this._timer);
        $$E.removeEvent(this._image, "mouseover", this._START);
        $$E.removeEvent(this._image, "mousemove", this._START);
        $$E.removeEvent(document, "mousemove", this._MOVE);
        $$E.removeEvent(document, "mouseout", this._OUT);
        $$E.removeEvent(document, $$B.firefox ? "DOMMouseScroll" : "mousewheel", this._MOUSE);
    },
    //修改设置
    reset: function (options) {
        this.stop();

        var viewer = this._viewer, zoom = this._zoom;
        if ($$D.contains(viewer, zoom)) {
            viewer.removeChild(zoom);
        }

        var opt = $$.extend(this.options, options || {});
        this._scale = opt.scale;
        this._max = opt.max;
        this._min = opt.min;
        this._originPic = opt.originPic;
        this._zoomPic = opt.zoomPic;
        this._rangeWidth = opt.rangeWidth;
        this._rangeHeight = opt.rangeHeight;

        //重置属性
        this._loaded = this._substitute = false;
        this._rect = null;
        this._repairLeft = this._repairTop =
            this._viewerWidth = this._viewerHeight = 0;

        this._initLoad();
    },
    //销毁程序
    dispose: function () {
        $$CE.fireEvent(this, "dispose");
        this.stop();
        if ($$D.contains(this._viewer, this._zoom)) {
            this._viewer.removeChild(this._zoom);
        }
        this._image.onload = this._preload.onload =
            this._image = this._preload = this._zoom = this._viewer =
                this.onLoad = this.onStart = this.onMove = this.onEnd =
                    this._START = this._MOVE = this._END = this._OUT = null
    }
}