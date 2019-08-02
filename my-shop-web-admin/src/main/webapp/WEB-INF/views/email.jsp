<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
    <jsp:include page="../includes/head.jsp"/>
    <link rel="stylesheet" href="/static/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
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
                工具
                <small></small>
            </h1>
            <ol class="breadcrumb">
                <li><a href="/main"><i class="fa fa-dashboard"></i>首页</a></li>
                <li class="active">发送邮件</li>
            </ol>
        </section>
        <!-- Main content -->
        <section class="content">
            <form action="/email/send" method="post">
                <div class="row">
                    <div class="col-md-12">
                        <div class="box box-info">
                            <div class="box-header">
                                <h3 class="box-title">发送邮件
                                </h3>
                                <!-- tools box -->
                                <div class="pull-right box-tools">
                                    <button type="submit" class="btn btn-info btn-sm" data-toggle="tooltip"
                                            title="发送">
                                        <i class="fa fa-send"></i></button>
                                </div>
                                <!-- /. tools -->
                            </div>
                            <div class="form-horizontal">
                                <div class="box-body">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-1 control-label">收件人</label>
                                        <div class="col-sm-6">
                                            <input type="email" class="form-control" id="inputEmail3" name="to"
                                                   placeholder="请输入收件人邮箱">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword3" class="col-sm-1 control-label">主&nbsp;&nbsp;&nbsp;&nbsp;题</label>
                                        <div class="col-sm-6">
                                            <input type="text" class="form-control" id="inputPassword3" name="subTitle"
                                                   placeholder="请输入主题">
                                        </div>
                                    </div>
                                </div>
                                <!-- /.box-body -->
                            </div>

                            <!-- /.box-header -->
                            <div class="box-body pad">
                                <form>
                    <textarea id="editor1" name="content" rows="10" cols="80">
                                            编辑邮件内容
                    </textarea>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    </div>

    <jsp:include page="../includes/footer.jsp"/>
</div>
<jsp:include page="../includes/body.jsp"/>
<!-- CK Editor -->
<script src="/static/assets/bower_components/ckeditor/ckeditor.js"></script>
<script src="/static/assets/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
<script>
    $(function () {
        CKEDITOR.replace('editor1')
        $('.textarea').wysihtml5()
    })
</script>
</body>
</html>