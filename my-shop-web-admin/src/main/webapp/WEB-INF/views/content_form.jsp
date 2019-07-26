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
    <link rel="stylesheet" href="/static/assets/plugins/wangEditor/wangEditor.min.css" />
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
                <div class="alert alert-danger alert-dismissible" >
                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                        ${baseResult.message}
                </div>
            </c:if>

            <div class="box box-info">
                <div class="box-header with-border">
                    <h3 class="box-title">${tbContent.id == null ? "新增":"修改"}内容</h3>
                </div>
                <!-- /.box-header -->
                <!-- form start -->
                <form:form id="inputForm" cssClass="form-horizontal" action="/content/save" method="post" modelAttribute="tbContent">
                    <form:hidden path="id"/>
                    <div class="box-body">
                        <div class="form-group">
                            <label for="categoryName" class="col-sm-2 control-label">分类</label>

                            <div class="col-sm-10">
                                <form:hidden id="categoryId" path="tbContentCategory.id"/>
                                <input id="categoryName" readonly="true" class="form-control required" placeholder="请选择" value="${tbContent.tbContentCategory.name}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="title" class="col-sm-2 control-label">标题</label>

                            <div class="col-sm-10">
                                <form:input path="title" cssClass="form-control required" placeholder="标题"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="subTitle" class="col-sm-2 control-label">子标题</label>

                            <div class="col-sm-10">
                                <form:input path="subTitle" cssClass="form-control required" placeholder="子标题"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="titleDesc" class="col-sm-2 control-label">标题描述</label>

                            <div class="col-sm-10">
                                <form:input path="titleDesc" cssClass="form-control required" placeholder="标题描述"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="url" class="col-sm-2 control-label">地址</label>

                            <div class="col-sm-10">
                                <form:input path="url" cssClass="form-control required" placeholder="地址"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic" class="col-sm-2 control-label">图片1</label>

                            <div class="col-sm-10">
                                <form:hidden path="pic"/>
                                <div id="dropPic" class="dropzone"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="pic2" class="col-sm-2 control-label">图片2</label>

                            <div class="col-sm-10">
                                <form:hidden path="pic2"/>
                                <div id="dropPic2" class="dropzone"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="content" class="col-sm-2 control-label">内容</label>

                            <div class="col-sm-10">
                                <form:hidden path="content"/>
                                <div id="editor"></div>
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
<script src="/static/assets/plugins/wangEditor/wangEditor.min.js"></script>
<sys:modal tittle="选择节点" message="<ul id='myTree' class='ztree'></ul>"/>
<script>
    //分类信息
    $(function () {
        $('#categoryName').click(function () {
            $('#modal-default').modal('show')
        });
        App.initMyTree("/content/category/tree/data" , ["id"] , function (nodes) {
            let node = nodes[0];
            $('#categoryId').val(node.id);
            $('#categoryName').val(node.name);
            $('#modal-default').modal('hide')
        });
        initWangEditor();
    });
    function initWangEditor (){
        //富文本编辑器
        let E = window.wangEditor;
        let editor = new E('#editor');
        // 配置服务器端地址
        editor.customConfig.uploadImgServer = '/upload';
        //自定义 fileName
        editor.customConfig.uploadFileName = 'editorFile';
        editor.create();
        editor.txt.html('${tbContent.content}');
        $('#btnSubmit').bind('click', function () {
            let contentHtml = editor.txt.html();
            $('#content').val(contentHtml)
        })

    };
    //图片一
    App.initDropZoneUpload({
        id: '#dropPic',
        url: '/upload',
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $('#pic').val(data.fileName);
            });
        }
    });
    //图片二
    App.initDropZoneUpload({
        id: '#dropPic2',
        url: '/upload',
        init: function () {
            this.on("success", function (file, data) {
                // 上传成功触发的事件
                $('#pic2').val(data.fileName);
            });
        }
    });

</script>



</body>
</html>