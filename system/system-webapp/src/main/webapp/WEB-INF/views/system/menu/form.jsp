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
        <input type="hidden" name="id" value="${menu.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">名称</label>
                    <div class="col col-10">
                        <label class="input"> <i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="name" value="${menu.name}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">从属</label>
                    <div class="col col-10">
                        <select id="parentSelect" class="select2" name="parent.code">
                            <option value="">主目录</option>
                            <c:set var="menulList" value="${sys:getMenuList()}"/>
                            <c:forEach items="${menulList}" var="menuItem" varStatus="idxStatus">
                                <c:if test="${empty menuItem.parent and menuItem.type eq '0'}">
                                    <option value="${menuItem.code}">${menuItem.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <div class="label col col-2">URL</div>
                    <div class="col col-10">
                        <label class="input">
                            <input type="text" name="url" value="${not empty menu.url?menu.url:'#'}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <div class="label col col-2">图标</div>
                    <div class="col col-10">
                        <label class="input">
                            <input type="text" name="icon" value="${not empty menu.icon?menu.icon:'fa-th-large'}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <div class="label col col-2">排序</div>
                    <div class="col col-10">
                        <label class="input">
                            <input type="text" name="sort" value="${not empty menu.sort?menu.sort:0}">
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
    var saveUrl = "${ctxRequest}/menu/save";

    // pagefunction
    var pagefunction = function () {
        $("#parentSelect").val('${menu.parent.code}');
        runAllForms();
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                name: {
                    required: true
                },
                url: {
                    required: true
                },
                sort: {
                    required: true
                }
            },

            // Messages for form validation
            messages: {
                name: {
                    required: 'Please enter name.',
                },
                url: {
                    required: 'Please enter url.',
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function () {  //通过之后回调
                $('#remoteModal').modal('hide');
                dialog.warn("确认要提交当前表单吗?", "提交表单", function () {
                    loadSubmit(saveUrl, $("#content"), $("#inputForm"));
                }, function () {
                    $('#remoteModal').modal('show');
                });
            },
            invalidHandler: function () {  //不通过回调
                return false;
            }
        });
    };

    pagefunction();
</script>
