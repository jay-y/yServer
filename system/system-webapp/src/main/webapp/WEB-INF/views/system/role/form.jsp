<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/system-inc/taglib.jsp" %>

<link rel="stylesheet" type="text/css" media="screen"
      href="${smartSource}/js/plugin/jquery-ztree/3.5.25/css/zTreeStyle/zTreeStyle.css"/>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        &times;
    </button>
    <h4 class="modal-title">角色表单</h4>
</div>
<div class="modal-body no-padding">
    <form id="inputForm" class="smart-form">
        <input id="id" type="hidden" name="id" value="${role.code}"/>
        <input id="menus" type="hidden" name="menus"/>
        <fieldset>
            <section>
                <div class="row">
                    <label class="label col col-2">角色名称</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-inbox"></i>
                            <input id="name" type="text" name="name" value="${role.name}">
                        </label>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <label class="label col col-2">英文名称</label>
                    <div class="col col-10">
                        <label class="input"><i class="icon-append fa fa-user"></i>
                            <input id="enname" type="text" name="enname" value="${role.enname}">
                        </label>
                    </div>
                </div>
            </section>
            <%--<section>--%>
            <%--<div class="row">--%>
            <%--<label class="label col col-2">是否系统数据</label>--%>
            <%--<div class="col col-10">--%>
            <%--<select id="isSys" name="isSys" class="select2">--%>
            <%--<option value="">请选择</option>--%>
            <%--<option value="0">否</option>--%>
            <%--<option value="1">是</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</section>--%>
            <%--<section>--%>
            <%--<div class="row">--%>
            <%--<label class="label col col-2">数据范围</label>--%>
            <%--<div class="col col-10">--%>
            <%--<select id="dataScope" name="dataScope" class="select2">--%>
            <%--<option value="">请选择</option>--%>
            <%--<option value="1">所有数据</option>--%>
            <%--<option value="2">所在公司及以下数据</option>--%>
            <%--<option value="3">所在公司数据</option>--%>
            <%--<option value="4">所在部门及以下数据</option>--%>
            <%--<option value="5">所在部门数据</option>--%>
            <%--<option value="8">仅本人数据</option>--%>
            <%--<option value="9">按明细设置</option>--%>
            <%--</select>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</section>--%>
            <section>
                <div class="row">
                    <label class="label col col-2">是否可用</label>
                    <div class="col col-10">
                        <select id="isAvailable" name="isAvailable" class="select2">
                            <option value="y">是</option>
                            <option value="n">否</option>
                        </select>
                    </div>
                </div>
            </section>
            <section>
                <div class="row">
                    <div class="label col col-2">备注</div>
                    <div class="col col-10">
                        <label class="textarea textarea-expandable">
                            <textarea name="remarks" rows="3" class="custom-scroll">${role.remarks}</textarea>
                        </label>
                    </div>
                </div>
            </section>
        </fieldset>
    </form>
    <div class="row" style=" margin: 0; outline: 0;  padding-top: 10px;">
        <div class="col-xs-4 col-sm-5 col-md-2" style="color: #fff;font-weight: normal;padding-top: 7px;">角色授权</div>
        <div id="menuTree" class="col-xs-8 col-sm-7 col-md-10 ztree"></div>
    </div>
    <div class="smart-form">
        <footer>
            <button type="button" onclick="onSubmit();" class="btn btn-primary">
                SUBMIT
            </button>
            <button type="button" class="btn btn-default" data-dismiss="modal">
                CANCEL
            </button>
        </footer>
    </div>
</div>
<script src="${smartSource}/js/plugin/jquery-ztree/3.5.25/js/jquery.ztree.all.js"></script>
<script type="text/javascript">
    // common
    pageSetUp();

    // 保存请求
    var saveUrl = "${ctxRequest}/role/save";

    // 保存请求
    var initUrl = "${ctxRequest}/role/formInit";

    var tree;

    var pagefunction = function () {
        onInit();

        //验证提交的表单是否达到要求
        $("#inputForm").validate({
            // Rules for form validation
            rules: {
                name: {
                    required: true
                },
            },

            // Messages for form validation
            messages: {
                name: {
                    required: '角色名称不能为空.',
                }
            },

            // Do not change code below
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function () {  //通过之后回调
                //获取表单数据并将数据传入声明的对象中
                var checkNodes = tree.getCheckedNodes(true) //角色权限
                var menus = "";
                for (var i = 0; i < checkNodes.length; i++) {
                    menus += "," + checkNodes[i].id;
                }
                menus = menus.substring(1);
                $("#menus").val(menus);
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

    function onInit() {
        //操作位修改时，回显下拉框数据
        $("#isAvailable").val('${role.isAvailable}');
        runAllForms();
        var id = $("#id").val();
        // 默认展开全部节点
        $.post(initUrl, {id: id}, function (result) {
            var data = result.data;
            var ids = data.ids;
            var menuList = data.menuList;
            onInitTree(ids, menuList);
        });
    }


    // 角色——授权
    function onInitTree(ids, menuList) {
        var setting = {
            check: {enable: true, chkStyle: "checkbox", chkboxType: {"Y": "p", "N": "s"}, nocheckInherit: true},
            view: {selectedMulti: true, showLine: true, fontCss: {color: "white"}},
            data: {simpleData: {enable: true}},
            callback: {
                beforeClick: function (id, node) {
                    tree.checkNode(node, !node.checked, true, true);
                    return false;
                }
            }
        };
        //树图节点
        var zNodes = new Array();
        console.log(menuList);
        console.log(ids);
        for (var index in menuList) {
            var menu = menuList[index];
            var zNode = {};
            zNode.id = menu.code;
            if (yjs.checkNotEmpty(menu.parent)) {
                zNode.pId = menu.parent.code;
            }
            zNode.name = menu.name;
            zNodes.push(zNode);
        }
        tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
        var idsArr = ids.split(",");
        for (var i = 0; i < idsArr.length; i++) {
            var node = tree.getNodeByParam("id", idsArr[i]);
            try {
                tree.checkNode(node, true);
            } catch (e) {
            }
        }
        // 默认展开全部节点
        tree.expandAll(true);
    }

    function onSubmit() {
        $("#inputForm").submit();
    }

    pagefunction();
</script>