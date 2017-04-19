<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>
<%@ include file="/WEB-INF/common-inc/dialog.jsp" %>

<div class="row">
    <div class="col-xs-12 col-sm-7 col-md-7 col-lg-4">
        <h1 class="page-title txt-color-blueDark">
            <i class="fa fa-lg fa-fw fa-book"></i>菜单管理
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
                    <h2>菜单列表</h2>
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
                            <thead>
                            <tr>
                                <th class="hidden">ID</th>
                                <th style="width: 120px;"><i
                                        class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> 名称
                                </th>
                                <th style="width: 70px;"><i class="text-muted hidden-md hidden-sm hidden-xs"></i> 图标
                                </th>
                                <th style="width: 70px;"> 层级</th>
                                <th style="width: 120px;"><i
                                        class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"></i>
                                    路径
                                </th>
                                <th style="width: 70px;"><i
                                        class="fa fa-fw fa-calendar txt-color-blue hidden-md hidden-sm hidden-xs"></i>
                                    排序
                                </th>
                                <th class="text-align-center" style="width: 120px;"><i
                                        class="fa fa-fw fa-briefcase txt-color-blue hidden-md hidden-sm hidden-xs"></i>
                                    操作
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:set var="menulList" value="${sys:getMenuList()}"/>
                            <c:forEach items="${menulList}" var="menu" varStatus="idxStatus">
                                <c:if test="${menu.type eq '0'}">
                                    <c:choose>
                                        <c:when test="${menu.type eq '0' and menu.url eq '#'}">
                                            <tr>
                                                <td class="hidden">${menu.code}</td>
                                                <td style="width: 100px;" class="tr-details-close"
                                                    onclick="onDetails(this,'${menu.code}')">
                                                    <i id="i_${menu.code}" class="fa fa-fw fa-folder-o"></i>${menu.name}
                                                </td>
                                                <td style="width: 50px;"><i class="fa fa-fw ${menu.icon}"></i></td>
                                                <td style="width: 50px;">主目录</td>
                                                <td style="width: 100px;">${menu.url}</td>
                                                <td style="width: 50px;">${menu.sort}</td>
                                                <td class="text-align-center" style="width: 100px;">
                                                    <ul class="demo-btns">
                                                        <li><a href="javascript:void(0);"
                                                               onclick="onForm(this,'${menu.code}');"
                                                               class="btn btn-sm bg-color-blue txt-color-white"
                                                               data-toggle="modal" data-target="#remoteModal"><i
                                                                class="fa fa-edit fa-lg"></i></a></li>
                                                        <li>&nbsp;<a href="javascript:void(0);"
                                                                     onclick="onDelete('${menu.code}');"
                                                                     class="btn btn-sm bg-color-red txt-color-white"><i
                                                                class="fa fa-trash-o fa-lg"></i></a></li>
                                                    </ul>
                                                </td>
                                            </tr>
                                            <c:forEach items="${menulList}" var="menu2">
                                                <c:if test="${menu2.type ne '0' and menu2.parent.code eq menu.code}">
                                                    <tr class="childs_${menu.code}" style="display: none;">
                                                        <td class="hidden">${menu2.code}</td>
                                                        <td style="width: 100px;"><i
                                                                class="fa fa-fw fa-angle-double-right"></i>${menu2.name}
                                                        </td>
                                                        <td style="width: 50px;"><i class="fa fa-fw ${menu2.icon}"></i>
                                                        </td>
                                                        <td style="width: 50px;">子目录</td>
                                                        <td style="width: 100px;">${menu2.url}</td>
                                                        <td style="width: 50px;">${menu2.sort}</td>
                                                        <td class="text-align-center" style="width: 100px;">
                                                            <ul class="demo-btns">
                                                                <li><a href="javascript:void(0);"
                                                                       onclick="onForm(this,'${menu2.code}');"
                                                                       class="btn btn-sm bg-color-blue txt-color-white"
                                                                       data-toggle="modal" data-target="#remoteModal"><i
                                                                        class="fa fa-edit fa-lg"></i></a></li>
                                                                <li>&nbsp;<a href="javascript:void(0);"
                                                                             onclick="onDelete('${menu2.code}');"
                                                                             class="btn btn-sm bg-color-red txt-color-white"><i
                                                                        class="fa fa-trash-o fa-lg"></i></a></li>
                                                            </ul>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td class="hidden">${menu.code}</td>
                                                <td style="width: 100px;"><i
                                                        class="fa fa-fw fa-folder-open"></i>${menu.name}</td>
                                                <td style="width: 50px;"><i class="fa fa-fw ${menu.icon}"></i></td>
                                                <td style="width: 50px;">主目录</td>
                                                <td style="width: 100px;">${menu.url}</td>
                                                <td style="width: 50px;">${menu.sort}</td>
                                                <td class="text-align-center" style="width: 100px;">
                                                    <ul class="demo-btns">
                                                        <li><a href="javascript:void(0);"
                                                               onclick="onForm(this,'${menu.code}');"
                                                               class="btn btn-sm bg-color-blue txt-color-white"
                                                               data-toggle="modal" data-target="#remoteModal"><i
                                                                class="fa fa-edit fa-lg"></i></a></li>
                                                        <li>&nbsp;<a href="javascript:void(0);"
                                                                     onclick="onDelete('${menu.code}');"
                                                                     class="btn btn-sm bg-color-red txt-color-white"><i
                                                                class="fa fa-trash-o fa-lg"></i></a></li>
                                                    </ul>
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </c:forEach>
                            </tbody>
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
    var formUrl = "${ctxRequest}/menu/form";
    // 删除请求
    var delUrl = "${ctxRequest}/menu/delete";

    function onDetails(obj, id) {
        var isOpen = obj.className == 'tr-details-close';
        if (isOpen) {
            $(obj).addClass("tr-details-open").removeClass("tr-details-close");
            $("#i_" + id).addClass("fa-folder-open").removeClass("fa-folder-o");
            $(".childs_" + id).show();
        } else {
            $(obj).addClass("tr-details-close").removeClass("tr-details-open");
            $("#i_" + id).addClass("fa-folder-o").removeClass("fa-folder-open");
            $(".childs_" + id).hide();
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

    // pagefunction
    var pagefunction = function () {
    };

    pagefunction();
</script>
