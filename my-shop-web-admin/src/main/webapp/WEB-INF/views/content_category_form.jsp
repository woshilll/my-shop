<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 首页</title>
    <jsp:include page="../includes/head.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/jquery-ztree/css/zTreeStyle/zTreeStyle.min.css">
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/dropzone.min.css" />
    <link rel="stylesheet" href="/static/assets/plugins/dropzone/min/basic.min.css" />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/header.jsp"/>



    <jsp:include page="../includes/aside.jsp"/>



    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                内容管理
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">内容管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <c:if test="${baseResult != null}">
                <div id="message" class="alert alert-danger alert-dismissible" >
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                </div>
            </c:if>

            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbContentCategory.id == null ? "新增":"修改"}分类</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form id="inputForm" cssClass="form-horizontal" action="/content/category/save" method="post" modelAttribute="tbContentCategory">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label  class="col-sm-2 control-label">父分类</label>

                            <div class="col-sm-10">
                                <form:hidden id="categoryParentId" path="parent.id"/>
                                <input id="categoryParentName" readonly="true" class="form-control required" placeholder="请选择" value="${tbContentCategory.parent.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">当前分类</label>

                            <div class="col-sm-10">
                                <form:input path="name" cssClass="form-control required" placeholder="当前分类"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sortOrder" class="col-sm-2 control-label">排序</label>

                            <div class="col-sm-10">
                                <form:input path="sortOrder" cssClass="form-control required" placeholder="排序"/>
                            </div>
                        </div>
                    </div>


                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button id="btnSubmit" type="submit" class="btn btn-info pull-right">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </form:form>

            </div>
        </section>
    </div>

    <jsp:include page="../includes/footer.jsp"/>
</div>
<jsp:include page="../includes/body.jsp"/>
<script src="/static/assets/plugins/jquery-ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script src="/static/assets/plugins/dropzone/dropzone.js"></script>
<sys:modal tittle="选择节点" message="<ul id='myTree' class='ztree'></ul>"/>
<script>
    //分类信息
    $(function () {
        $('#categoryParentName').click(function () {
            $('#modal-default').modal('show')
        });
        App.initMyTree("/content/category/tree/data" , ["id"] , function (nodes) {
            let node = nodes[0];
            $('#categoryParentId').val(node.id);
            $('#categoryParentName').val(node.name);
            $('#modal-default').modal('hide')
        });
    });

</script>



</body>
</html>