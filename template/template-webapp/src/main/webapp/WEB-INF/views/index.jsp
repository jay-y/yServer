<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/template-inc/taglib.jsp" %>

<!DOCTYPE html>
<html lang="zh_CN" class="smart-style-5">
<head>
    <meta charset="utf-8">
    <title>Universal Manager System</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <!-- #CSS Links -->
    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/font-awesome.min.css">
    <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/smartadmin-production-plugins.min.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/smartadmin-production.min.css">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/smartadmin-skins.min.css">
    <!-- SmartAdmin RTL Support -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/smartadmin-rtl.min.css">

    <!-- We recommend you use "your_style.css" to override SmartAdmin
		     specific styles this will also ensure you retrain your customization with each SmartAdmin update.
		<link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/your_style.css"> -->

    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
    <link rel="stylesheet" type="text/css" media="screen"
          href="${smartSource}/css/demo.min.css">
    <!-- #FAVICONS -->
    <link rel="shortcut icon" href="${smartSource}/img/favicon/favicon.ico"
          type="image/x-icon">
    <link rel="icon" href="${smartSource}/img/favicon/favicon.ico"
          type="image/x-icon">

    <!-- #APP SCREEN / ICONS -->
    <!-- Specifying a Webpage Icon for Web Clip
            Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
    <link rel="apple-touch-icon" href="${smartSource}/img/splash/sptouch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="76x76"
          href="${smartSource}/img/splash/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120"
          href="${smartSource}/img/splash/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152"
          href="${smartSource}/img/splash/touch-icon-ipad-retina.png">
    <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <!-- Startup image for web apps -->
    <link rel="apple-touch-startup-image"
          href="${smartSource}/img/splash/ipad-landscape.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
    <link rel="apple-touch-startup-image"
          href="${smartSource}/img/splash/ipad-portrait.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="apple-touch-startup-image"
          href="${smartSource}/img/splash/iphone.png"
          media="screen and (max-device-width: 320px)">
    <style>
        .ui-jqgrid tr.jqgrow td {
            white-space: normal !important;
            height: auto;
        }
    </style>
</head>
<body class="smart-style-5">
<!-- #HEADER -->
<header id="header">
    <div id="logo-group">
        <!-- PLACE YOUR LOGO HERE -->
        <span id="logo"> <img src="${smartSource}/img/logo-pale.png" alt="Admin"></span>
        <!-- END LOGO PLACEHOLDER -->
    </div>

    <!-- #TOGGLE LAYOUT BUTTONS -->
    <!-- pulled right: nav area -->
    <div class="pull-right">

        <!-- collapse menu button -->
        <div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu"
                          title="收起菜单"><i class="fa fa-reorder"></i></a>
				</span>
        </div>
        <!-- end collapse menu -->

        <!-- #MOBILE -->
        <!-- Top menu profile link : this shows only when top menu is active -->
        <ul id="mobile-profile-img"
            class="header-dropdown-list hidden-xs padding-5">
            <li class=""><a href="#"
                            class="dropdown-toggle no-margin userdropdown"
                            data-toggle="dropdown"> <img
                    src="${smartSource}/img/avatars/sunny.png" alt="John Doe"
                    class="online"/>
            </a>
                <ul class="dropdown-menu pull-right">
                    <li><a href="javascript:void(0);"
                           class="padding-10 padding-top-0 padding-bottom-0"><i
                            class="fa fa-cog"></i>设置</a></li>
                    <li class="divider"></li>
                    <li><a href="${smartSource}/ajax/profile.html"
                           class="padding-10 padding-top-0 padding-bottom-0"> <i
                            class="fa fa-user"></i>个人资料<u>P</u></a></li>
                    <li class="divider"></li>
                    <li><a href="javascript:void(0);"
                           class="padding-10 padding-top-0 padding-bottom-0"
                           data-action="toggleShortcut"><i class="fa fa-arrow-down"></i>快速访问<u>S</u></a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="javascript:void(0);"
                           class="padding-10 padding-top-0 padding-bottom-0"
                           data-action="launchFullscreen"><i class="fa fa-arrows-alt"></i>全屏<u>S</u></a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="${ctx}/logout"
                           class="padding-10 padding-top-5 padding-bottom-5"
                           data-action="userLogout"><i class="fa fa-sign-out fa-lg"></i>
                        <strong>退出<u>L</u></strong></a></li>
                </ul>
            </li>
        </ul>

        <!-- logout button -->
        <div id="logout" class="btn-header transparent pull-right">
				<span> <a href="${ctx}/logout" title="退出"
                          data-action="userLogout" data-logout-msg="确认离开系统？"><i
                        class="fa fa-sign-out"></i></a>
				</span>
        </div>
        <!-- end logout button -->

        <!-- search mobile button (this is hidden till mobile view port) -->
        <div id="search-mobile" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0)" title="查找"><i
                        class="fa fa-search"></i></a>
				</span>
        </div>
        <!-- end search mobile button -->

        <!-- #SEARCH -->
        <!-- input: search field -->
        <form action="https://www.baidu.com/s"
              class="header-search pull-right">
            <input id="search-fld" type="text" name="wd" placeholder="查找更多">
            <button type="button"
                    onclick="window.open('https://www.baidu.com/s?wd='+document.getElementById('search-fld').value)">
                <i class="fa fa-search"></i>
            </button>
            <a href="javascript:void(0);" id="cancel-search-js"
               title="Cancel Search"><i class="fa fa-times"></i></a>
        </form>
        <!-- end input: search field -->

        <!-- fullscreen button -->
        <div id="fullscreen" class="btn-header transparent pull-right">
				<span> <a href="javascript:void(0);"
                          data-action="launchFullscreen" title="全屏"><i
                        class="fa fa-arrows-alt"></i></a>
				</span>
        </div>
        <!-- end fullscreen button -->

        <!-- multiple lang dropdown : find all flags in the flags page -->
        <ul class="header-dropdown-list hidden-xs">
            <li><a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <img src="${smartSource}/img/blank.gif" class="flag flag-cn"
                     alt="China(mainland)"> <span> 中文（简体）</span> <i
                    class="fa fa-angle-down"></i>
            </a>
                <ul class="dropdown-menu pull-right">
                    <li class="active"><a href="javascript:void(0);"><img
                            src="${smartSource}/img/blank.gif" class="flag flag-cn"
                            alt="China"> 中文（简体）</a></li>
                    <!--<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-us" alt="United States"> English (US)</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-fr" alt="France"> Français</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-es" alt="Spanish"> Español</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-de" alt="German"> Deutsch</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-jp" alt="Japan"> 日本語</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-it" alt="Italy"> Italiano</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-pt" alt="Portugal"> Portugal</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-ru" alt="Russia"> Русский язык</a>
							</li>
							<li>
								<a href="javascript:void(0);"><img src="${smartSource}/img/blank.gif" class="flag flag-kr" alt="Korea"> 한국어</a>
							</li>-->

                </ul>
            </li>
        </ul>
        <!-- end multiple lang -->

    </div>
    <!-- end pulled right: nav area -->

</header>
<!-- END HEADER -->

<!-- #NAVIGATION -->
<!-- Left panel : Navigation area -->
<!-- Note: This width of the aside area can be adjusted through LESS/SASS variables -->
<aside id="left-panel">

    <!-- User info -->
    <div class="login-info">
			<span> <!-- User image size is adjusted inside CSS, it should stay as is -->

				<a href="javascript:void(0);" id="show-shortcut"
                   data-action="toggleShortcut"> <img
                        src="${smartSource}/img/avatars/sunny.png" alt="我" class="online"/>
					<span> Admin </span> <i
                            class="fa fa-angle-down"></i>
			</a>

			</span>
    </div>
    <!-- end user info -->

    <!-- NAVIGATION : This navigation is also responsive

        To make this navigation dynamic please make sure to link the node
        (the reference to the nav > ul) after page load. Or the navigation
        will not initialize.
        -->
    <nav>
        <!--
            NOTE: Notice the gaps after each icon usage <i></i>..
            Please note that these links work a bit different than
            traditional href="" links. See documentation for details.
            -->
        <ul>
            <li class=""><a href="${ctx}/a/home" title="首页"><i
                    class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">首页</span></a>
            </li>

            <%--<c:set var="channelList" value="${fns:getChannelList()}"/>--%>
            <%--<c:set var="firstMenu" value="true"/>--%>
            <%--<c:forEach items="${channelList}" var="menu" varStatus="idxStatus">--%>
            <%--<c:if test="${menu.path eq '0'}">--%>
            <%--<li><c:choose>--%>
            <%--<c:when test="${empty menu.module}">--%>
            <%--<a data-id="${menu.id}" href="#"> <i--%>
            <%--class="fa fa-lg fa-fw fa-th-large"></i> <span--%>
            <%--class="menu-item-parent">${menu.title}</span>--%>
            <%--</a>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<a data-id="${menu.id}"--%>
            <%--href="${(fn:indexOf(menu.module, '://') eq -1 ? ctx : '').concat('/').concat(menu.module)}">--%>
            <%--<i class="fa fa-lg fa-fw fa-th-large"></i> <span--%>
            <%--class="menu-item-parent">${menu.title}</span>--%>
            <%--</a>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--<ul>--%>
            <%--<c:forEach items="${channelList}" var="menu2">--%>
            <%--<c:if test="${menu2.path eq fn:replace('0-X','X',menu.id)}">--%>
            <%--<li><c:choose>--%>
            <%--<c:when test="${empty menu2.module}">--%>
            <%--<a data-id="${menu2.id}" href="#"> <i--%>
            <%--class="fa fa-lg fa-fw fa-th-large"></i> <span--%>
            <%--class="menu-item-parent">${menu2.title}</span>--%>
            <%--</a>--%>
            <%--</c:when>--%>
            <%--<c:otherwise>--%>
            <%--<a data-id="${menu2.id}"--%>
            <%--href="${(fn:indexOf(menu2.module, '://') eq -1 ? ctx : '').concat('/').concat(menu2.module)}">--%>
            <%--<i class="fa fa-fw fa-th-large"></i> <span>${menu2.title}</span>--%>
            <%--</a>--%>
            <%--</c:otherwise>--%>
            <%--</c:choose></li>--%>
            <%--<c:set var="firstMenu" value="false"/>--%>
            <%--</c:if>--%>
            <%--</c:forEach>--%>
            <%--</ul>--%>
            <%--</li>--%>
            <%--</c:if>--%>
            <%--</c:forEach>--%>
            <%-- <li class="top-menu-invisible">
                    <a href="#"><i class="fa fa-lg fa-fw fa-cube txt-color-blue"></i> <span class="menu-item-parent">SmartAdmin Intel</span></a>
                    <ul>
                        <li>
                            <a href="${smartSource}/ajax/difver.html"><i class="fa fa-stack-overflow"></i> Different Versions</a>
                        </li>
                        <li>
                            <a href="${smartSource}/ajax/applayout.html"><i class="fa fa-cube"></i> App Settings</a>
                        </li>
                        <li>
                            <a href="http://192.241.236.31/smartadmin/BUGTRACK/track_/documentation/index.html" target="_blank"><i class="fa fa-book"></i> Documentation</a>
                        </li>
                        <li>
                            <a href="http://192.241.236.31/smartadmin/BUGTRACK/track_/" target="_blank"><i class="fa fa-bug"></i> Bug Tracker</a>
                        </li>
                        <li>
                            <a href="http://myorange.ca/supportforum/" target="_blank"><i class="fa fa-wechat"></i> SmartAdmin Support</a>
                        </li>
                    </ul>
                </li> --%>
        </ul>
    </nav>

    <%-- <a href="${smartSource}/ajax/difver.html" class="btn btn-primary nav-demo-btn">AngularJS, PHP and .Net Versions</a> --%>

    <span class="minifyme" data-action="minifyMenu"> <i
            class="fa fa-arrow-circle-left hit"></i>
		</span>

</aside>
<!-- END NAVIGATION -->

<!-- #MAIN PANEL -->
<div id="main" role="main">

    <!-- RIBBON -->
    <div id="ribbon">
			<span class="ribbon-button-alignment"> <a id="reset"
                                                      class="btn btn-ribbon" data-action="resetWidgets" data-html="true"
                                                      data-reset-msg="你想重置所有保存的部件以及清除本地存储??"> <i class="fa fa-refresh"
                                                                                                 title="清除本地存储"></i></a>
			</span>

        <!-- breadcrumb -->
        <ol class="breadcrumb">
            <!-- This is auto generated -->
        </ol>
        <!-- end breadcrumb -->

        <!-- You can also add more buttons to the
            ribbon for further usability

            Example below:

            <span class="ribbon-button-alignment pull-right" style="margin-right:25px">
                <a href="#" id="search" class="btn btn-ribbon hidden-xs" data-title="search"><i class="fa fa-grid"></i> Change Grid</a>
                <span id="add" class="btn btn-ribbon hidden-xs" data-title="add"><i class="fa fa-plus"></i> Add</span>
                <button id="search" class="btn btn-ribbon" data-title="search"><i class="fa fa-search"></i> <span class="hidden-mobile">Search</span></button>
            </span> -->

    </div>
    <!-- END RIBBON -->

    <!-- #MAIN CONTENT -->
    <div id="content"></div>

    <!-- END #MAIN CONTENT -->

</div>
<!-- END #MAIN PANEL -->

<!-- #PAGE FOOTER -->
<div class="page-footer">
    <div class="row">
        <div class="col-xs-12 col-sm-6">
				<span class="txt-color-white">${fns:getConfig('platform')}
					${fns:getPropertyByProj('version')} <span class="hidden-xs"> -
						&nbsp; Powered By <a href="${fns:getProperty('website')}"
                                             target="_blank">${fns:getPropertyByProj('company')}</a>
				</span> Copyright &copy; ${fns:getPropertyByProj('copyrightYear')}
				</span>
        </div>

    </div>
    <!-- end row -->
</div>
<!-- END FOOTER -->

<!--================================================== -->
<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)
    <script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>-->

<!-- #PLUGINS -->
<!-- jQuery + jQueryUI; fall back to local -->
<script src="${commonSource}/js/jquery-2.1.1.min.js"></script>
<script src="${commonSource}/js/jquery-ui-1.10.3.min.js"></script>
<!-- IMPORTANT: APP CONFIG -->
<script src="${smartSource}/js/app.config.js"></script>
<script type="text/javascript">
    $.sound_path = "${smartSource}/sound/";
</script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
<script
        src="${smartSource}/js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>
<!-- BOOTSTRAP JS -->
<script src="${smartSource}/js/bootstrap/bootstrap.min.js"></script>
<!-- CUSTOM NOTIFICATION -->
<script src="${smartSource}/js/notification/SmartNotification.min.js"></script>
<!-- JARVIS WIDGETS -->
<script src="${smartSource}/js/smartwidgets/jarvis.widget.min.js"></script>
<!-- EASY PIE CHARTS -->
<script
        src="${smartSource}/js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>
<!-- SPARKLINES -->
<script src="${smartSource}/js/plugin/sparkline/jquery.sparkline.min.js"></script>
<!-- JQUERY VALIDATE -->
<script
        src="${smartSource}/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<script
        src="${smartSource}/js/plugin/bootstrapvalidator/bootstrapValidator.min.js"></script>
<script
        src="${smartSource}/js/plugin/bootstrapvalidator/language/zh_CN.min.js"></script>
<!-- JQUERY MASKED INPUT -->
<script
        src="${smartSource}/js/plugin/masked-input/jquery.maskedinput.min.js"></script>
<!-- JQUERY SELECT2 INPUT -->
<script src="${smartSource}/js/plugin/select2/select2.min.js"></script>
<!-- JQUERY UI + Bootstrap Slider -->
<script
        src="${smartSource}/js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>
<!-- browser msie issue fix -->
<script src="${smartSource}/js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

<!-- FastClick: For mobile devices: you can disable this in app.js -->
<script src="${smartSource}/js/plugin/fastclick/fastclick.min.js"></script>

<!--[if IE 8]>
<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>
<![endif]-->

<!-- Demo purpose only -->
<script src="${smartSource}/js/demo.min.js"></script>

<!-- MAIN APP JS FILE -->
<%-- 		<script src="${smartSource}/js/app.min.js"></script> --%>
<script src="${smartSource}/js/app.custom.js"></script>

<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
<!-- Voice command : plugin -->
<script src="${smartSource}/js/speech/voicecommand.min.js"></script>

<!-- SmartChat UI : plugin -->
<script src="${smartSource}/js/smart-chat-ui/smart.chat.ui.min.js"></script>
<script src="${smartSource}/js/smart-chat-ui/smart.chat.manager.min.js"></script>

<script src="${commonSource}/js/yjs.utils.core.js"></script>
<script type="text/javascript">
    /**
     * POST跳转
     *
     * @param a
     * @param b
     * @param data
     * @param hasSerialize
     */
    function loadSubmit(a, b, data, hasSerialize) {
        hasSerialize = (hasSerialize == null || hasSerialize == undefined) ? true : hasSerialize;
        if (hasSerialize) {
            data = data.serializeArray();
        }
        debugState &&
        root.root.console.log("Loading URL: %c" + a, debugStyle),
                $.ajax({
                    "type": "POST",
                    "url": a,
                    "data": data,
                    "dataType": "html",
                    "cache": !0,
                    "beforeSend": function () {
                        if ($.navAsAjax
                                && $(".google_maps")[0]
                                && b[0] == $("#content")[0]) {
                            var a = $(".google_maps"),
                                    c = 0;
                            a.each(function () {
                                c++;
                                var b = document.getElementById(this.id);
                                c == a.length + 1 || (b && b.parentNode.removeChild(b), debugState && root.console.log("Destroying maps.........%c" + this.id, debugStyle_warning))
                            }),
                            debugState && root.console.log("\u2714 Google map instances nuked!!!")
                        }
                        if ($.navAsAjax
                                && $(".dataTables_wrapper")[0]
                                && b[0] == $("#content")[0]) {
                            var d = $.fn.dataTable.fnTables(!0);
                            $(d).each(function () {
                                0 != $(this).find(".details-control").length ? ($(this).find("*").addBack().off().remove(), $(this).dataTable().fnDestroy()) : $(this).dataTable().fnDestroy()
                            }),
                            debugState && root.console.log("\u2714 Datatable instances nuked!!!")
                        }
                        if ($.navAsAjax
                                && $.intervalArr.length > 0
                                && b[0] == $("#content")[0]
                                && enableJarvisWidgets) {
                            for (; $.intervalArr.length > 0;) clearInterval($.intervalArr.pop());
                            debugState && root.console.log("\u2714 All JarvisWidget intervals cleared")
                        }
                        if ($.navAsAjax
                                && b[0] == $("#content")[0]
                                && enableJarvisWidgets
                                && $("#widget-grid")[0]
                                && ($("#widget-grid").jarvisWidgets("destroy"), debugState && root.console.log("\u2714 JarvisWidgets destroyed")), $.navAsAjax && b[0] == $("#content")[0]) {
                            if ("function" == typeof pagedestroy) try {
                                pagedestroy(),
                                debugState && root.console.log("\u2714 Pagedestroy()")
                            }
                            catch (e) {
                                pagedestroy = void 0,
                                debugState && root.console.log("! Pagedestroy() Catch Error")
                            }
                            $.fn.sparkline && $("#content .sparkline")[0] && ($("#content .sparkline").sparkline("destroy"), debugState && root.console.log("\u2714 Sparkline Charts destroyed!")),
                            $.fn.easyPieChart && $("#content .easy-pie-chart")[0] && ($("#content .easy-pie-chart").easyPieChart("destroy"), debugState && root.console.log("\u2714 EasyPieChart Charts destroyed!")),
                            $.fn.select2 && $("#content select.select2")[0] && ($("#content select.select2").select2("destroy"), debugState && root.console.log("\u2714 Select2 destroyed!")),
                            $.fn.mask && $("#content [data-mask]")[0] && ($("#content [data-mask]").unmask(), debugState && root.console.log("\u2714 Input Mask destroyed!")),
                            $.fn.datepicker && $("#content .datepicker")[0] && ($("#content .datepicker").off(), $("#content .datepicker").remove(), debugState && root.console.log("\u2714 Datepicker destroyed!")),
                            $.fn.slider && $("#content .slider")[0] && ($("#content .slider").off(), $("#content .slider").remove(), debugState && root.console.log("\u2714 Bootstrap Slider destroyed!"))
                        }
                        pagefunction = null,
                                b.removeData().html(""),
                                b.html('<h1 class="ajax-loading-animation"><i class="fa fa-cog fa-spin"></i> Loading...</h1>'),
                        b[0] == $("#content")[0] && ($("body").find("> *").filter(":not(" + ignore_key_elms + ")").empty().remove(), drawBreadCrumb(), $("html").animate({
                            "scrollTop": 0
                        }, "fast"))
                    },
                    "success": function (a) {
                        b.css({
                            "opacity": "0.0"
                        }).html(a).delay(50).animate({
                                    "opacity": "1.0"
                                },
                                300),
                                a = null,
                                b = null
                    },
                    "error": function (c, d, e) {
                        b.html('<h4 class="ajax-loading-error"><i class="fa fa-warning txt-color-orangeDark"></i> Error requesting <span class="txt-color-red">' + a + "</span>: " + c.status + ' <span style="text-transform: capitalize;">' + e + "</span></h4>")
                    },
                    "async": !0
                });
    }
</script>
</body>
</html>