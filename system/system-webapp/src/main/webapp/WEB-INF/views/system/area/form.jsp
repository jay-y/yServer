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
        <input type="hidden" name="id" value="${area.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">名称</label>
                    <div class="col col-10">
                        <label class="input"> <i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="name" value="${area.name}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">编号</label>
                    <div class="col col-10">
                        <label class="input"> <i class="icon-append fa fa-inbox"></i>
                            <input type="number" name="number" value="${area.number}">
                        </label>
                    </div>
                </div>
            </section>
            <c:if test="${not empty area.parent and area.type ne '0'}">
                <section>
                    <div class="row">
                        <label class="label col col-2">从属</label>
                        <div class="col col-10">
                            <select id="parentSelect" class="select2" name="parent.code">

                                <c:set var="areaList" value="${sys:getAreaList()}"/>
                                <c:forEach items="${areaList}" var="area" varStatus="idxStatus">
                                    <option value="${area.code}">${area.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </section>
            </c:if>
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
    var saveUrl = "${ctxRequest}/area/save";

    var getParentArea = "${ctxRequest}/area/getParentArea";
    var getChildsArea = "${ctxRequest}/area/getChildsArea";

    function initParentArea() {
        $.post(getParentArea, function (result) {
            var dataList = result.data;
            var htmlStr = '';
            for (var index in dataList) {
                var data = dataList[index];
                htmlStr += '<option value="' + data.code + '">' + data.name + '</option>';
            }
            $("#parentSelect").html(htmlStr);
        });
    }

    function getChildsArea(id, type) {
        $.post(getChildsArea, {type: type, code: id}, function (result) {

        });
    }

    // pagefunction
    var pagefunction = function () {
        $("#parentSelect").val('${not empty data.parent.code?data.parent.code:0}');
        runAllForms();
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                name: {
                    required: true
                },
                number: {
                    required: true
                }
            },

            // Messages for form validation
            messages: {
                name: {
                    required: '名称不能为空.',
                },
                number: {
                    required: '编号不能为空.',
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

    var pagedestroy = function () {
    }

    pagefunction();
</script>
