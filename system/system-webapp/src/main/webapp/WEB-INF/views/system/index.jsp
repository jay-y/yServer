<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>

<div class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark">
            <i class="fa fa-lg fa-fw fa-home"></i>首页
        </h1>
    </div>
    <div class="col-xs-12 col-sm-5 col-md-5 col-lg-8">
        <ul id="sparks" class="">
            <li class="sparks-info">
                <h5>&nbsp;<a href="javascript:void(0);" onclick="window.open('${ctx}/druid/index.html');">
                    <span class="txt-color-purple">
                        <i class="fa fa-database" data-rel="bootstrap-tooltip" title="Increased"></i>
                        &nbsp;Druid Monitor</span>
                </a></h5>
            </li>
        </ul>
    </div>
</div>
<!-- widget grid -->
<section id="widget-grid" class="">
    <!-- row -->
    <div class="widget-body">
        <tag:message content="${msg}" type="${status}"/>
        <form id="inputForm" class="smart-form">
            <input type="hidden" name="id" value="${data.code}"/>
            <fieldset>
                <section>
                    <div class="row">
                        <label class="label col col-1">用户名:</label>
                        <div class="col col-6">
                            <label class="input"><i class="icon-append fa fa-inbox"></i>
                                <input type="text" name="username" value="${data.username}">
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">角色:</label>
                        <div class="col col-6">
                            <label class="input">
                                <input type="text" value="${not empty data.role?data.role.name:'暂无'}" disabled="true">
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">Email:</label>
                        <div class="col col-6">
                            <label class="input"><i class="icon-append fa fa-inbox"></i>
                                <input type="text" name="email" value="${data.email}">
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">联系方式:</label>
                        <div class="col col-6">
                            <label class="input"><i class="icon-append fa fa-inbox"></i>
                                <input type="text" name="phone" value="${data.phone}">
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">备注</label>
                        <div class="col col-6">
                            <label class="textarea textarea-expandable">
                                <textarea name="remarks" rows="3" class="custom-scroll">${data.remarks}</textarea>
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">IP:</label>
                        <div class="col col-6">
                            <label class="input">
                                <input type="text" name="ip" value="${data.ip}" disabled="true">
                            </label>
                        </div>
                    </div>
                </section>
                <section>
                    <div class="row">
                        <label class="label col col-1">上次登录:</label>
                        <div class="col col-6">
                            <label class="input">
                                <input type="text" name="updatedTime" value="${data.updatedTime}" disabled="true">
                            </label>
                        </div>
                    </div>
                </section>
            </fieldset>
            <footer>
                <label class="label col col-1">&nbsp;</label>
                <div class="col col-6">
                    <button type="submit" class="btn btn-primary">
                        保存
                    </button>
                </div>
            </footer>
        </form>
    </div>
    <!-- end row -->
</section>
<!-- end widget grid -->

<script type="text/javascript">
    // common
    pageSetUp();

    // 数据请求
    var dataUrl = "${ctxRequest}/home/data";
    //保存
    var saveUrl = "${ctxRequest}/user/update";

    // pagefunction
    var pagefunction = function () {
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                username: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                }
            },

            // Messages for form validation
            messages: {
                username: {
                    required: '账号不能为空.',
                },
                email: {
                    required: '邮箱不能为空.',
                    email: '邮箱格式错误.'
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {  //通过之后回调
                loadSubmit(saveUrl, $("#content"), $("#inputForm"));
            },
            invalidHandler: function (form, validator) {  //不通过回调
                return false;
            }
        });
    };

    pagefunction();
</script>
