<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容管理</title>
    <jsp:include page="../includes/head.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/treeTable/themes/vsStyle/treeTable.min.css"/>
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
                <li class="active">用户管理</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">

            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-success alert-dismissible" id="message">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info box-info-search" style="display: none;">



                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                            <div class="row" style="padding-left: 12px;padding-top: 10px;">
                                    <a href="/content/category/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 增加</a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="treeTable" class="table table-hover">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>名称</th>
                                        <th>排序</th>
                                        <th>操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${contentCategories}" var="contentCategory">
                                        <tr id="${contentCategory.id}" pId="${contentCategory.parent.id}">
                                            <td>${contentCategory.id}</td>
                                            <td>${contentCategory.name}</td>
                                            <td>${contentCategory.sortOrder}</td>
                                            <td>
                                                <a href="/content/category/form?id=${contentCategory.id}" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> 修改</a>&nbsp;&nbsp;
                                                <button onclick="App.initSingleDelete('/content/category/delete' , ${contentCategory.id} , '删除当前分类会同时删除下级分类，以及被删分类的内容，你确定吗？')" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> 删除</button>&nbsp;&nbsp;
                                                <a href="/content/category/form?parent.id=${contentCategory.id}&parent.name=${contentCategory.name}" type="button" class="btn btn-default btn-sm"><i class="fa fa-trash"></i> 新增下级菜单</a>&nbsp;&nbsp;
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>
        </section>
    </div>

    <jsp:include page="../includes/footer.jsp"/>
</div>
<jsp:include page="../includes/body.jsp"/>
<sys:modal tittle="删除分类"/>
<script src="/static/assets/plugins/treeTable/jquery.treeTable.min.js">
</script>
<script>
    $(function () {
        $('#treeTable').treeTable({
            column:0,
            expandLevel:2
        });

        setTimeout(function () {
            $('#message').css("display" , "none")
        } , 5000);

    });
</script>
</body>
</html>