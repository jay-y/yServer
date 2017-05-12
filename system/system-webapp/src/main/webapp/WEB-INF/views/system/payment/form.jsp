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
        <input type="hidden" name="id" value="${payment.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <c:set var="paymentCategorys" value="${sys:getDictList('category_payment')}"></c:set>
                    <label
                        class="label col col-2">${not empty paymentCategorys and not empty paymentCategorys[0]?paymentCategorys[0].description : "Category"}</label>
                    <div class="col col-10">
                        <select id="category" name="category" class="select2">
                            <c:forEach items="${paymentCategorys}" var="category" varStatus="idxStatus">
                                <option value="${category.value}">${category.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">APP ID</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="appId" value="${payment.appId}" placeholder="第三方指定ID或其他">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">BUSINESS</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="business" value="${payment.business}"
                                   placeholder="分配商户ID、接收付款账户或其他"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">API(URL)</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="apiUrl" value="${payment.apiUrl}" placeholder="非开发人员请勿修改"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">交易通知(URL)</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="notifyUrl" value="${payment.notifyUrl}" placeholder="非开发人员请勿修改"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">取消通知(URL)</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="cancelUrl" value="${payment.cancelUrl}" placeholder="非开发人员请勿修改"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">结果通知(URL)</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="returnUrl" value="${payment.returnUrl}" placeholder="非开发人员请勿修改"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">参数集(K:V)</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="params" rows="3" class="custom-scroll">${payment.params}</textarea>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">描述</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="remarks" rows="3" class="custom-scroll">${payment.remarks}</textarea>
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
    var saveUrl = "${ctxRequest}/payment/save";

    // pagefunction
    var pagefunction = function () {
        $("#category").val('${payment.category}');
        runAllForms();
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                appId: {
                    required: true
                },
                notifyUrl: {
                    required: true
                },
                cancelUrl: {
                    required: true
                },
                returnUrl: {
                    required: true
                }
            },

            // Messages for form validation
            messages: {},

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function () {  //通过之后回调
                if (yjs.checkNotEmpty($('#category').val())) {
                    $('#remoteModal').modal('hide');
                    dialog.warn("确认要提交当前表单吗?", "提交表单", function () {
                        loadSubmit(saveUrl, $("#content"), $("#inputForm"));
                    }, function () {
                        $('#remoteModal').modal('show');
                    });
                } else {
                    alert("类别不能为空.");
                }
            },
            invalidHandler: function () {  //不通过回调
                return false;
            }
        });
    };

    loadScript("${smartSource}/js/plugin/dropzone/dropzone.min.js", pagefunction);
</script>
