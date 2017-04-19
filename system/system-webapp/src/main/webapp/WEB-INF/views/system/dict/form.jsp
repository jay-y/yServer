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
        <input type="hidden" name="id" value="${dict.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">标签名</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="label" value="${dict.label}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">值</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-user"></i>
                            <input type="text" name="value" value="${dict.value}" placeholder="非开发人员请勿修改">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">类型值</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-lock"></i>
                            <input type="text" name="type" value="${dict.type}" placeholder="非开发人员请勿修改">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">类型描述</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="description" rows="3" class="custom-scroll">${dict.description}</textarea>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">排序</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="number" name="sort" value="${not empty dict.sort?dict.sort:0}">
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
    var saveUrl = "${ctxRequest}/dict/save";

    // pagefunction
    var pagefunction = function () {
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                label: {
                    required: true
                },
                value: {
                    required: true,
                },
                type: {
                    required: true
                },
                description: {
                    required: true,
                }
                ,
                sort: {
                    required: true,
                }
            },

            // Messages for form validation
            messages: {
                label: {
                    required: '不能为空.'
                },
                value: {
                    required: '不能为空.'
                },
                type: {
                    required: '不能为空.'
                },
                description: {
                    required: '不能为空.'
                },
                sort: {
                    required: '不能为空.'
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
