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
        <input type="hidden" name="id" value="${logistics.code}"/>
        <input id="photo" type="hidden" name="photo.code" value="${logistics.photo.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">展示图</label>
                    <div class="col col-10">
                        <div id="dropzone" class="dropzone"></div>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">联系人</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-envelope"></i>
                            <input type="text" name="clientName"
                                   value="${logistics.clientName}"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">联系方式</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-envelope"></i>
                            <input type="text" name="contactWay"
                                   value="${logistics.contactWay}"/>
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">物流公司</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input type="text" name="company" value="${logistics.company}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <c:set var="logisticsCategorys" value="${sys:getDictList('category_logistics')}"></c:set>
                    <label
                        class="label col col-2">${not empty logisticsCategorys and not empty logisticsCategorys[0]?logisticsCategorys[0].description : "Category"}</label>
                    <div class="col col-10">
                        <select id="category" name="category" class="select2">
                            <c:forEach items="${logisticsCategorys}" var="category" varStatus="idxStatus">
                                <option value="${category.value}">${category.label}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">价格</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-dollar"></i>
                            <input type="number" name="price" value="${not empty logistics.price?logistics.price:0}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">描述</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="remarks" rows="3" class="custom-scroll">${logistics.remarks}</textarea>
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
    var saveUrl = "${ctxRequest}/logistics/save";

    // pagefunction
    var pagefunction = function () {
        $("#category").val('${logistics.category}');
        runAllForms();
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                clientName: {
                    required: true
                },
                contactWay: {
                    required: true
                },
                company: {
                    required: true
                },
                price: {
                    required: true
                }
            },

            // Messages for form validation
            messages: {
                clientName: {
                    required: '联系人不能为空.'
                },
                contactWay: {
                    required: '联系方式不能为空.'
                },
                company: {
                    required: '物流公司不能为空.'
                },
                price: {
                    required: '价格不能为空.'
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function () {  //通过之后回调
                if (yjs.checkNotEmpty($('#photo').val()) && yjs.checkNotEmpty($('#category').val())) {
                    $('#remoteModal').modal('hide');
                    dialog.warn("确认要提交当前表单吗?", "提交表单", function () {
                        loadSubmit(saveUrl, $("#content"), $("#inputForm"));
                    }, function () {
                        $('#remoteModal').modal('show');
                    });
                } else {
                    alert("图片且类别不能为空.");
                }
            },
            invalidHandler: function () {  //不通过回调
                return false;
            }
        });

        $("#dropzone").dropzone({
            url: "${ctxRequest}/upload",
            maxFiles: 1,
            maxFilesize: 512,
            acceptedFiles: ".png,.jpg,.jpeg",
            addRemoveLinks: true,//添加移除文件
            dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件.",
            dictFileTooBig: "文件过大({{filesize}}MB),上传文件最大支持:{{maxFilesize}}MB.",

            dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i> Drop files <span class="font-xs">to upload</span></span><span>&nbsp&nbsp<h4 class="display-inline"> (Or Click)</h4></span>',
            dictResponseError: '文件上传失败!',
            dictInvalidFileType: "你不能上传该类型文件,文件类型只能是.png,.jpg,.jpeg.",
            dictRemoveFile: "移除文件",
            dictFallbackMessage: "当前浏览器版本过低,不支持上传.",
            uploadMultiple: false,
            init: function () {
                if (yjs.checkNotEmpty('${logistics.photo.code}')) {
                    var photo = {
                        name: "${logistics.photo.name}",
                        size: ${not empty logistics.photo.size?logistics.photo.size:0},
                        uri: "${logistics.photo.uri}",
                        accepted: true
                    };
                    var mockFiles = [photo];
                    if (yjs.checkNotEmpty(mockFiles)) {
                        this.emit("initImage", mockFiles); //初始化图片
                    }
                }
                this.on("error", function (file, message) {
                    alert(message);
                    this.removeFile(file);
                });
                this.on("success", function (file, result) {
                    var fileList = result.data;
                    if (yjs.checkNotEmpty(fileList)) {
                        $("#photo").val(fileList[0].code);
                    }
                });
                this.on("removedfile", function (file) {
                    $("#photo").val('');
                });
            }
        });
    };

    loadScript("${smartSource}/js/plugin/dropzone/dropzone.min.js", pagefunction);
</script>
