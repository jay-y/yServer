<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/template-inc/taglib.jsp" %>

<!DOCTYPE html>
<html lang="en-us" id="extr-page">
<head>
    <meta charset="utf-8">
    <title>Template Manager System</title>
    <meta name="description" content="">
    <meta name="author" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

    <!-- #CSS Links -->
    <!-- Basic Styles -->
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/font-awesome.min.css">

    <!-- SmartAdmin Styles : Caution! DO NOT change the order -->
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/smartadmin-production-plugins.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/smartadmin-production.min.css">
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/smartadmin-skins.min.css">

    <!-- SmartAdmin RTL Support -->
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/smartadmin-rtl.min.css">

    <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
    <link rel="stylesheet" type="text/css" media="screen" href="${smartSource}/css/demo.min.css">

    <!-- #FAVICONS -->
    <link rel="shortcut icon" href="${smartSource}/img/favicon/favicon.ico" type="image/x-icon">
    <link rel="icon" href="${smartSource}/img/favicon/favicon.ico" type="image/x-icon">

    <!-- #APP SCREEN / ICONS -->
    <!-- Specifying a Webpage Icon for Web Clip
         Ref: https://developer.apple.com/library/ios/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html -->
    <link rel="apple-touch-icon" href="${smartSource}/img/splash/sptouch-icon-iphone.png">
    <link rel="apple-touch-icon" sizes="76x76" href="${smartSource}/img/splash/touch-icon-ipad.png">
    <link rel="apple-touch-icon" sizes="120x120" href="${smartSource}/img/splash/touch-icon-iphone-retina.png">
    <link rel="apple-touch-icon" sizes="152x152" href="${smartSource}/img/splash/touch-icon-ipad-retina.png">

    <!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">

    <!-- Startup image for web apps -->
    <link rel="apple-touch-startup-image" href="${smartSource}/img/splash/ipad-landscape.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:landscape)">
    <link rel="apple-touch-startup-image" href="${smartSource}/img/splash/ipad-portrait.png"
          media="screen and (min-device-width: 481px) and (max-device-width: 1024px) and (orientation:portrait)">
    <link rel="apple-touch-startup-image" href="${smartSource}/img/splash/iphone.png"
          media="screen and (max-device-width: 320px)">

</head>

<body class="animated fadeInDown">

<header id="header">

    <div id="logo-group">
        <span id="logo"> <img src="${smartSource}/img/logo.png" alt="Admin"> </span>
    </div>

    <span id="extr-page-header-space"> <span class="hidden-mobile hidden-xs">需要一个账号？</span> <a
            href="register.jsp" class="btn btn-danger">点击创建</a> </span>

</header>

<div id="main" role="main">

    <!-- MAIN CONTENT -->
    <div id="content" class="container">

        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-7 col-lg-8 hidden-xs hidden-sm">
                <h1 class="txt-color-red login-header-big">WeChat Manager System</h1>
                <div class="hero">

                    <div class="pull-left login-desc-box-l">
                        <h4 class="paragraph-header">美好的一天!</h4>
                        <div class="login-app-icons">
                            <%--<a href="javascript:void(0);" class="btn btn-danger btn-sm">Frontend Template</a>--%>
                            <%--<a href="javascript:void(0);" class="btn btn-danger btn-sm">Find out more</a>--%>
                        </div>
                    </div>

                    <%--<img src="${smartSource}/img/demo/iphoneview.png" class="pull-right display-image" alt="" style="width:210px">--%>

                </div>

                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <h5 class="about-heading">关于我们</h5>
                        <p>
                            为客户提供优质产品及服务!
                        </p>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6">
                        <h5 class="about-heading">加入我们</h5>
                        <p>
                            为您提供广阔的发展空间!
                        </p>
                    </div>
                </div>

            </div>
            <div class="col-xs-12 col-sm-12 col-md-5 col-lg-4">
                <div class="well no-padding">
                    <form action="${ctx}/login" id="login-form" method="post" class="smart-form client-form">
                        <header>
                            登录
                        </header>

                        <fieldset>
                            <section>
                                <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
                                    <button data-dismiss="alert" class="close">×</button>
                                    <label id="loginError" class="error">${message}</label>
                                </div>
                            </section>

                            <section>
                                <label class="label">账号</label>
                                <label class="input"> <i class="icon-append fa fa-user"></i>
                                    <input type="text" name="username">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-user txt-color-teal"></i>
                                        请输入您的用户名</b></label>
                            </section>

                            <section>
                                <label class="label">密码</label>
                                <label class="input"> <i class="icon-append fa fa-lock"></i>
                                    <input type="password" name="password">
                                    <b class="tooltip tooltip-top-right"><i class="fa fa-lock txt-color-teal"></i>
                                        请输入您的密码</b> </label>
                                <div class="note">
                                    <a href="forgotpassword.jsp">忘记密码?</a>
                                </div>
                            </section>

                            <c:if test="${isValidateCodeLogin}">
                                <section>
                                    <label class="label" for="validateCode">验证码</label>
                                    <tag:validateCode name="validateCode" inputCssStyle="margin-bottom:0;width:100px;"/>
                                </section>
                            </c:if>

                            <section>
                                <label class="checkbox">
                                    <input type="checkbox" id="rememberMe"
                                           name="rememberMe" ${rememberMe ? 'checked' : ''}>
                                    <i></i>记住我（公共场所慎用）</label>
                            </section>
                        </fieldset>
                        <footer>
                            <button type="submit" class="btn btn-primary">
                                登录
                            </button>
                        </footer>
                    </form>

                </div>

                <%--<h5 class="text-center"> - Or sign in using -</h5>--%>

                <%--<ul class="list-inline text-center">--%>
                <%--<li>--%>
                <%--<a href="javascript:void(0);" class="btn btn-primary btn-circle"><i class="fa fa-facebook"></i></a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="javascript:void(0);" class="btn btn-info btn-circle"><i class="fa fa-twitter"></i></a>--%>
                <%--</li>--%>
                <%--<li>--%>
                <%--<a href="javascript:void(0);" class="btn btn-warning btn-circle"><i class="fa fa-linkedin"></i></a>--%>
                <%--</li>--%>
                <%--</ul>--%>

            </div>
        </div>
    </div>

</div>

<!--================================================== -->

<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
<script src="${smartSource}/js/plugin/pace/pace.min.js"></script>

<!-- jQuery + jQueryUI; fall back to local -->
<script src="${smartSource}/js/libs/jquery-2.1.1.min.js"></script>
<script src="${smartSource}/js/libs/jquery-ui-1.10.3.min.js"></script>

<!-- IMPORTANT: APP CONFIG -->
<script src="${smartSource}/js/app.config.js"></script>

<!-- JS TOUCH : include this plugin for mobile drag / drop touch events
<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script> -->

<!-- BOOTSTRAP JS -->
<script src="${smartSource}/js/bootstrap/bootstrap.min.js"></script>

<!-- JQUERY VALIDATE -->
<script src="${smartSource}/js/plugin/jquery-validate/jquery.validate.min.js"></script>

<!-- JQUERY MASKED INPUT -->
<script src="${smartSource}/js/plugin/masked-input/jquery.maskedinput.min.js"></script>

<!--[if IE 8]>

<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

<![endif]-->

<!-- MAIN APP JS FILE -->
<script src="${smartSource}/js/app.min.js"></script>

<script type="text/javascript">
    runAllForms();

    $(function () {
        // Validation
        $("#login-form").validate({
            // Rules for form validation
            rules: {
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 3,
                    maxlength: 20
                }
            },

            // Messages for form validation
            messages: {
                email: {
                    required: 'Please enter your email address',
                    email: 'Please enter a VALID email address'
                },
                password: {
                    required: 'Please enter your password'
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            }
        });
    });
</script>

<!-- Your GOOGLE ANALYTICS CODE Below -->
<script type="text/javascript">

    var _gaq = _gaq || [];
    _gaq.push(['_setAccount', 'UA-43548732-3']);
    _gaq.push(['_trackPageview']);

    (function () {
        var ga = document.createElement('script');
        ga.type = 'text/javascript';
        ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0];
        s.parentNode.insertBefore(ga, s);
    })();

</script>

</body>
</html>