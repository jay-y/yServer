<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>
<%@ include file="/WEB-INF/common-inc/dialog.jsp" %>

<div class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark">
            <i class="fa fa-lg fa-fw fa-user"></i>用户管理
        </h1>
    </div>
</div>
<!-- widget grid -->
<section id="widget-grid" class="">
    <!-- row -->
    <div class="row">
        <!-- NEW WIDGET START -->
        <article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
            <!-- Widget ID (each widget will need unique ID)-->
            <div class="jarviswidget jarviswidget-color-darken" id="wid-id-0" data-widget-editbutton="false">
                <header>
                    <span class="widget-icon"> <i class="fa fa-table"></i> </span>
                    <h2>用户列表</h2>
                </header>
                <!-- widget div-->
                <div>
                    <!-- widget edit box -->
                    <div class="jarviswidget-editbox">
                        <!-- This area used as dropdown edit box -->
                    </div>
                    <!-- end widget edit box -->
                    <!-- widget content -->
                    <div class="widget-body no-padding">
                        <ul class="demo-btns">
                            <li>
                                <a href="javascript:void(0);" onclick="onForm(this);"
                                   data-toggle="modal" data-target="#remoteModal"
                                   class="btn btn-labeled btn-info">
                                    <span class="btn-label"><i
                                        class="fa fa-plus"></i> </span>新增
                                </a>
                            </li>
                        </ul>
                        <tag:message content="${msg}" type="${status}"/>
                        <table id="table" class="table table-striped table-bordered table-hover" width="100%">
                        </table>
                    </div>
                    <!-- end widget content -->
                </div>
                <!-- end widget div -->

            </div>
            <!-- end widget -->
        </article>
        <!-- WIDGET END -->
    </div>
    <!-- end row -->
</section>
<!-- end widget grid -->

<script type="text/javascript">
    // common
    pageSetUp();

    // 数据请求
    var dataUrl = "${ctxRequest}/user/data";
    // 新增请求
    var formUrl = "${ctxRequest}/user/form";
    // 删除请求
    var delUrl = "${ctxRequest}/user/delete";

    // pagefunction
    var pagefunction = function () {
        var table = $('#table');
        var columns = [
            {
                "title": "ID",
                "class": "hidden",
                "sName": "code",
                "mData": "code"
            },
            {
                "sTitle": "姓名",
                "sName": "name",
                "mData": "name",
                "width": 100
            },
            {
                "title": "Email",
                "sTitle": "Email",
                "sName": "email",
                "mData": "email",
                "sType": "email",
                "width": 100
            },
            {
                "sTitle": "联系方式",
                "sName": "phone",
                "mData": "phone",
                "sType": "number",
                "width": 100
            },
            {
                "sTitle": "角色",
                "sName": "role",
                "mData": "role",
                "mRender": function (cell, b, row, d) {
                    return yjs.checkNotEmpty(cell) ? cell.name : "暂无";
                },
                "width": 100
            },
            {
                "sTitle": "IP",
                "sName": "ip",
                "mData": "ip",
                "width": 100
            },
            {
                "sTitle": "更新时间",
                "sName": "updatedTime",
                "mData": "updatedTime",
                "sType": "date",
                "width": 120
            },
            {
                "sTitle": "<i class='fa fa-fw fa-briefcase text-muted hidden-md hidden-sm hidden-xs'></i>操作",
                "class": "text-align-center",
                "orderable": false,
                "mRender": function (cell, b, row, d) {
                    var operation = '<ul class="demo-btns">';
                    operation += '<li><a href="javascript:void(0);" onclick="onForm(this,\'' + row.code + '\');" class="btn btn-sm bg-color-blue txt-color-white" data-toggle="modal" data-target="#remoteModal"><i class="fa fa-edit fa-lg" title="修改"></i></a></li>';
                    operation += '<li>&nbsp;<a href="javascript:void(0);" onclick="onDelete(\'' + row.code + '\');" class="btn btn-sm bg-color-red txt-color-white"><i class="fa fa-trash-o fa-lg" title="删除"></i></a></li>';
                    operation += '</ul>';
                    return operation;
                },
                "width": 100
            }
        ];
        var dataTable = yjs.dataTables.onInit(dataUrl, table, columns);
        // Apply the filter
        $("#table .custom-condition")
            .on('change', function () {
                dataTable.column($(this).parent().index() + ':visible')
                    .search(this.value)
                    .draw();
            });
    };

    function onDelete(id) {
        dialog.warn("确认要删除当前数据吗?", "删除数据", function () {
            loadSubmit(delUrl, $("#content"), {id: id}, false);
        });
    }

    function onForm(obj, id) {
        obj.href = formUrl + (yjs.checkNotEmpty(id) ? "?id=" + id : "");
    }

    // load related plugins
    loadScript("${smartSource}/js/plugin/datatables/jquery.dataTables.min.js", function () {
        loadScript("${smartSource}/js/plugin/datatables/dataTables.colVis.min.js", function () {
            loadScript("${smartSource}/js/plugin/datatables/dataTables.tableTools.min.js", function () {
                loadScript("${smartSource}/js/plugin/datatables/dataTables.bootstrap.min.js", function () {
                    loadScript("${smartSource}/js/plugin/datatable-responsive/datatables.responsive.min.js", function () {
                        loadScript("${commonSource}/js/yjs.utils.dataTables.js", pagefunction);
                    });
                });
            });
        });
    });
</script>
