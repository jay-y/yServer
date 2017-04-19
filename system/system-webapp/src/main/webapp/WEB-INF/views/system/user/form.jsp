<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        &times;
    </button>
    <h4 class="modal-title">表单</h4>
</div>
<div class="modal-body no-padding">
    <form id="inputForm" class="smart-form">
        <input type="hidden" name="id" value="${user.code}"/>
        <input type="hidden" name="oldPassword" value="${user.password}">
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">姓名</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="name" value="${user.name}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">账号</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-user"></i>
                            <input type="text" name="username" value="${user.username}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">密码</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-lock"></i>
                            <input type="password" name="password" value="${user.password}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">角色:</label>
                    <div class="col col-10">
                        <select id="role" class="select2" name="role.code">
                            <c:set var="roleList" value="${sys:getRoleList()}"/>
                            <c:forEach items="${roleList}" var="role" varStatus="idxStatus">
                                <option value="${role.code}">${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">Email</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-envelope-o"></i>
                            <input type="email" name="email" value="${user.email}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <div class="label col col-2">联系方式</div>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-phone"></i>
                            <input type="number" name="phone" value="${user.phone}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">备注</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="remarks" rows="3" class="custom-scroll">${user.remarks}</textarea>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">IP</label>
                    <div class="col col-10">
                        <label class="input"> <i class="icon-append fa fa-map-marker"></i>
                            <input type="text" value="${user.ip}" disabled="disabled">
                        </label>
                    </div>
                </div>
            </section>
        </fieldset>
        <footer>
            <button type="submit" class="btn btn-primary">
                SUBMIT
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
                CANCEL
            </button>
        </footer>
    </form>
</div>

<script type="text/javascript">
    // common
    pageSetUp();

    // 保存请求
    var saveUrl = "${ctxRequest}/user/save";

    // pagefunction
    var pagefunction = function () {
        $("#role").val('${not empty user.role.code?user.role.code:0}');
        runAllForms();
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true,
                    minlength: 3,
                    maxlength: 100
                },
                name: {
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
                    required: '账户名不能为空.',
                },
                name: {
                    required: '姓名不能为空.',
                },
                password: {
                    required: '密码不能为空.',
                    minlength: '密码长度不得小于3.',
                    maxlength: '密码长度不得大于100.',
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
                $('#remoteModal').modal('hide');
                dialog.warn("确认要提交当前表单吗?", "提交表单", function () {
                    loadSubmit(saveUrl, $("#content"), $("#inputForm"));
                }, function () {
                    $('#remoteModal').modal('show');
                });
            },
            invalidHandler: function (form, validator) {  //不通过回调
                return false;
            }
        });
    };

    pagefunction();
</script>
