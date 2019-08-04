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

            <div class="row">
                <div class="col-xs-12">
                    <c:if test="${baseResult != null}">
                        <div class="alert alert-success alert-dismissible" id="message">
                            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                                ${baseResult.message}
                        </div>
                    </c:if>
                    <div class="box box-info box-info-search" style="display: none;">
                        <div class="box-header">
                            <h3 class="box-title">高级搜索</h3>
                        </div>
                        <div class="form-horizontal">
                            <div class="box-body">
                                <div class="row">
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="title" class="col-sm-4 control-label">标题</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="title" class="form-control" placeholder="标题">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="subTitle" class="col-sm-4 control-label">子标题</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="subTitle" class="form-control" placeholder="子标题">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="titleDesc" class="col-sm-4 control-label">标题描述</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="titleDesc" class="form-control" placeholder="标题描述">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button type="button" onclick="search()" class="btn btn-info pull-right">搜索</button>
                            </div>
                        </div>

                    </div>
                    <div class="box">
                        <div class="box-header">
                            <h3 class="box-title">内容列表</h3>
                            <div class="row" style="padding-left: 12px;padding-top: 10px;">
                                    <a href="/content/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-plus"></i> 增加</a>&nbsp;&nbsp;&nbsp;
                                    <button  type="button" class="btn btn-default btn-sm" onclick="App.batchDelete('/content/batchDelete')"><i class="fa fa-trash"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                    <button onclick="$('#export').click();" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</button>&nbsp;&nbsp;&nbsp;
                                    <button  type="button" class="btn btn-default btn-sm" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                            </div>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive">
                            <table id="dataTables" class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>
                                        <label>
                                         <input type="checkbox" class="minimal check_master">
                                        </label>
                                    </th>
                                    <th>ID</th>
                                    <th>所属分类</th>
                                    <th>标题</th>
                                    <th>子标题</th>
                                    <th>标题描述</th>
                                    <th>链接</th>
                                    <th>图片1</th>
                                    <th>图片2</th>
                                    <th>更新时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
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
<sys:modal/>

<script>
    let _dataTable;
    $(function () {
        let columns=[
            {
                'data':function (row,type,val,meta) {
                    return '<input id="'+row.id+'" type="checkbox" class="minimal">'
                }
            },
            {'data':'id'},
            {'data':'tbContentCategory.name'},
            {'data':'title'},
            {'data':'subTitle'},
            {'data':'titleDesc'},
            {
                'data':function (row) {
                    if (row.url == null || row.url.length === 0){
                        return '';
                    }
                    return '<a href="'+row.url+'" >查看</a>'
                }
            },
            {
                'data':function (row) {
                    if (row.pic == null || row.pic.length === 0){
                        return '';
                    }
                    return '<a href="'+row.pic+'" >查看</a>'
                }
            },
            {
                'data':function (row) {
                    if (row.pic2 == null || row.pic2.length === 0){
                        return '';
                    }
                    return '<a href="'+row.pic2+'" >查看</a>'
                }
            },
            {'data':function (row) {
                    return DateTime.format(row.updated , "yyyy-MM-dd HH:mm:ss")
                }},
            {
                'data':function (row) {
                    let detailUrl='/content/detail?id='+row.id;
                    return '<button onclick="App.showDetail(\''+detailUrl+'\');"  type="button" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 查看</button >&nbsp;&nbsp;'+
                        '<a href="/content/form?id='+row.id+'" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> 修改</a>&nbsp;&nbsp;'+
                        '<button onclick="App.initSingleDelete(\'/content/batchDelete\',\''+row.id+'\')" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> 删除</button>&nbsp;&nbsp;';
                }
            }
        ];
        _dataTable=App.initDataTables('/content/page' , columns);

        setTimeout(function () {
            $('#message').css("display" , "none")
        } , 5000);
    });

    
    function search() {
        let title=$('#title').val();
        let subTitle=$('#subTitle').val();
        let titleDesc=$('#titleDesc').val();
        let param={
            'title':title,
            'subTitle':subTitle,
            'titleDesc':titleDesc
        };
        _dataTable.settings()[0].ajax.data=param;
        _dataTable.ajax.reload();
    }
</script>


</body>
</html>