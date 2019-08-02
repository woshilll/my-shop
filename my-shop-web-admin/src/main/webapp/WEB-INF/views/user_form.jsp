<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 首页</title>
    <jsp:include page="../includes/head.jsp"/>
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
                控制面板
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="#"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">控制面板</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <c:if test="${baseResult != null}">
                <div class="alert alert-danger alert-dismissible" id="message" >
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                </div>
            </c:if>

            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbUser.id == null ? "新增":"修改"}用户</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form id="inputForm" cssClass="form-horizontal" action="/user/save" method="post" modelAttribute="tbUser">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">邮箱</label>

                            <div class="col-sm-10">
                                <form:input path="email" cssClass="form-control required email" placeholder="请输入邮箱"/>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">密码</label>

                            <div class="col-sm-10">
                                <form:password path="password" cssClass="form-control required" placeholder="请输入密码"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="username" class="col-sm-2 control-label">姓名</label>

                            <div class="col-sm-10">
                                <form:input path="username" cssClass="form-control required" placeholder="请输入姓名"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="phone" class="col-sm-2 control-label">手机号</label>

                            <div class="col-sm-10">
                                <form:input path="phone" cssClass="form-control required mobile" placeholder="请输入手机号"/>
                            </div>
                        </div>
                    </div>


                    <!-- /.box-body -->
                    <div class="box-footer">
                        <button type="button" class="btn btn-default" onclick="history.go(-1)">返回</button>
                        <button type="submit" class="btn btn-info pull-right">提交</button>
                    </div>
                    <!-- /.box-footer -->
                </form:form>

            </div>
        </section>
    </div>

    <jsp:include page="../includes/footer.jsp"/>
</div>

<jsp:include page="../includes/body.jsp"/>
<script>
    $(function () {
        setTimeout(function () {
            $('#message').css("display" , "none")
        } , 5000)
    })
</script>
</body>
</html>