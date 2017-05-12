<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>
<%@ include file="/WEB-INF/common-inc/dialog.jsp" %>

<div class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark">
            <i class="fa fa-lg fa-fw fa-book"></i>区域管理
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
                    <h2>区域列表</h2>
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

    // 新增请求
    var formUrl = "${ctxRequest}/area/form";
    // 删除请求
    var delUrl = "${ctxRequest}/area/delete";

    var getParentArea = "${ctxRequest}/area/getParentArea";
    var getChildsArea = "${ctxRequest}/area/getChildsArea";

    // pagefunction
    var pagefunction = function () {
        var table = $('#table');
        var columns = [
            {
                "sTitle": "ID",
                "class": "hidden",
                "sName": "code",
                "mData": "code"
            },
            {
                "sTitle": "区域名",
                "sName": "name",
                "mData": "name",
                "mRender": function (cell, b, row, d) {
                    return '<i id="i_' + row.code + '_' + row.type + '" class="fa fa-fw fa-folder-o"></i>' + cell;
                },
                "width": 100
            },
            {
                "sTitle": "区域编号",
                "sName": "number",
                "mData": "number",
                "sType": "number",
                "width": 100
            },
            {
                "sTitle": "创建时间",
                "sName": "createdTime",
                "mData": "createdTime",
                "sType": "date",
                "width": 120
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
        var config = yjs.dataTables.getDataTableConfig(getParentArea, table, columns);
        config.fnDrawCallback = function () {
            $("#table tbody tr").each(function (index, elem) {
                $(elem).find("td:eq(1)").each(function (index, elem) {
                    $(elem).click(function () {
                        var params = $(this).find("i:first")[0].id.split("_");
                        var id = params[1];
                        var type = params[2];
                        $(this).attr("name", "area_parent_" + id);
                        onDetails(this, id, type);
                    });
                });
            });
        };
        var dataTable = yjs.dataTables.getDataTable(table, config);
        // Apply the filter
        $("#table .custom-condition")
            .on('change', function () {
                dataTable.column($(this).parent().index() + ':visible')
                    .search(this.value)
                    .draw();
            });
    };

    function onDetails(obj, id, type) {
        var suffix = id + "_" + type;
        $("#" + suffix).click();
        var isOpen = (yjs.checkEmpty(obj.className) || obj.className == 'tr-details-close');
        if (isOpen && type < 3) {
            $(obj).addClass("tr-details-open").removeClass("tr-details-close");
            $("#i_" + suffix).addClass("fa-folder-open").removeClass("fa-folder-o");
            $.post(getChildsArea, {type: type, code: id}, function (result) {
                for (var index in result.data) {
                    var data = result.data[index];
                    var deep = '';
                    for (var i = 0; i < type; i++) {
                        deep += '<i class="fa fa-fw fa-angle-double-right"></i>';
                    }
                    var child = document.createElement("tr");
                    child.id = "childs_" + suffix + " " + type;
                    child.className = "childs_" + suffix + " childs_" + type;
                    child.innerHTML += '<td class="hidden">' + data.code + '</td>';
                    child.innerHTML += '<td id="' + suffix + '" onclick="onDetails(this,\'' + data.code + '\', \'' + data.type + '\');">'
                        + deep + '<i id="i_' + data.code + '_' + data.type + '" class="fa fa-fw fa-folder-o"></i>' + data.name + '</td>';
                    child.innerHTML += '<td>' + data.number + '</td>';
                    child.innerHTML += '<td>' + data.createdTime + '</td>';
                    child.innerHTML += '<td>' + data.updatedTime + '</td>';
                    var operation = '<ul class="demo-btns">';
                    operation += '<li><a href="javascript:void(0);" onclick="onForm(this,\'' + data.code + '\');" class="btn btn-sm bg-color-blue txt-color-white" data-toggle="modal" data-target="#remoteModal"><i class="fa fa-edit fa-lg"></i></a></li>';
                    operation += '<li>&nbsp;<a href="javascript:void(0);" onclick="onDelete(\'' + data.code + '\');" class="btn btn-sm bg-color-red txt-color-white"><i class="fa fa-trash-o fa-lg"></i></a></li>';
                    operation += '</ul>';
                    child.innerHTML += '<td class="text-align-center" style="width: 100px;">' + operation + '</td>';
                    $(obj).parent().after(child);
                }
            });
        } else {
            $(obj).addClass("tr-details-close").removeClass("tr-details-open");
            $("#i_" + suffix).addClass("fa-folder-o").removeClass("fa-folder-open");
            if (type == "0") {
                for (var i = 0; i < 4; i++) {
                    $(".childs_" + i).remove();
                }
                $(".tr-details-open").each(function (i, d) {
                    $(d).addClass("tr-details-close").removeClass("tr-details-open");
                    $(d).find("i:first").addClass("fa-folder-o").removeClass("fa-folder-open");
                });
            } else {
                $(".childs_" + suffix).remove();
            }
        }
    }

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
