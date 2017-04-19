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
        <input id="file" type="hidden" name="id" value="${file.code}"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">文件</label>
                    <div class="col col-10">
                        <div id="dropzone" class="dropzone"></div>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">描述</label>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="remarks" rows="3" class="custom-scroll">${file.remarks}</textarea>
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
    var saveUrl = "${ctxRequest}/file/save";

    var photoIds = [];

    // pagefunction
    var pagefunction = function () {
        onInitDropzone();

        $("#inputForm").validate({
            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function () {  //通过之后回调
                if (yjs.checkNotEmpty(photoIds)) {
                    $("#file").val(JSON.stringify(photoIds).replace(/\[|\]|"/g, ""));
                    $('#remoteModal').modal('hide');
                    dialog.warn("确认要提交当前表单吗?", "提交表单", function () {
                        loadSubmit(saveUrl, $("#content"), $("#inputForm"));
                    }, function () {
                        $('#remoteModal').modal('show');
                    });
                } else {
                    alert("文件不能为空.");
                }
            },
            invalidHandler: function () {  //不通过回调
                return false;
            }
        });
    };

    function onInitDropzone() {
        var mockFiles = [];
        if (yjs.checkNotEmpty('${file.code}')) {
            var photo = {
                code: '${file.code}',
                name: '${file.name}',
                size: '${file.size}',
                uri: '${file.uri}',
                accepted: true
            }
            photoIds.push(photo.code);
            mockFiles.push(photo);
        }
        $("#dropzone").dropzone({
            url: "${ctxRequest}/upload",
            maxFiles: 1,
            maxFilesize: 512,
//            acceptedFiles: ".png,.jpg,.jpeg",
            addRemoveLinks: true,//添加移除文件
            dictMaxFilesExceeded: "您一次最多只能上传{{maxFiles}}个文件.",
            dictFileTooBig: "文件过大({{filesize}}MB),上传文件最大支持:{{maxFilesize}}MB.",

            dictDefaultMessage: '<span class="text-center"><span class="font-lg visible-xs-block visible-sm-block visible-lg-block"><span class="font-lg"><i class="fa fa-caret-right text-danger"></i> Drop files <span class="font-xs">to upload</span></span><span>&nbsp&nbsp<h4 class="display-inline"> (Or Click)</h4></span>',
            dictResponseError: '文件上传失败!',
//            dictInvalidFileType: "你不能上传该类型文件,文件类型只能是.png,.jpg,.jpeg.",
            dictRemoveFile: "移除文件",
            dictFallbackMessage: "当前浏览器版本过低,不支持上传.",
            uploadMultiple: false,
            init: function () {
                if (yjs.checkNotEmpty(mockFiles)) {
                    this.emit("initImage", mockFiles); //初始化图片
                }
                this.on("error", function (file, message) {
                    alert(message);
                    this.removeFile(file);
                });
                this.on("success", function (file, result) {
                    var fileList = result.data;
                    if (yjs.checkNotEmpty(fileList)) {
                        for (var index in fileList) {
                            photoIds.push(fileList[index].code);
                            file.code = fileList[index].code;
                        }
                    }
                });
                this.on("removedfile", function (file) {
                    if (yjs.checkNotEmpty(photoIds)) {
                        for (var i = 0; i < photoIds.length; i++) {
                            if (photoIds[i] == file.code) {
                                photoIds.splice(i, 1);
                                break;
                            }
                        }
                    }
                });
            }
        });
    }

    loadScript("${smartSource}/js/plugin/dropzone/dropzone.min.js", pagefunction);
</script>
