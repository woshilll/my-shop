<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 用户管理</title>
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
                用户管理
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
                        <div class="alert alert-success alert-dismissible" id="display">
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
                                            <label for="username" class="col-sm-4 control-label">姓名</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="username" class="form-control" placeholder="姓名">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="email" class="col-sm-4 control-label">邮箱</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="email" class="form-control" placeholder="邮箱">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-12 col-sm-3">
                                        <div class="form-group">
                                            <label for="phone" class="col-sm-4 control-label">电话</label>
                                            <div class="col-sm-8">
                                                <input type="text" id="phone" class="form-control" placeholder="电话">
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
                            <h3 class="box-title">用户列表</h3>
                            <div class="row" style="padding-left: 12px;padding-top: 10px;">
                                    <a href="/user/form" type="button" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 增加</a>&nbsp;&nbsp;&nbsp;
                                    <button  type="button" class="btn btn-default btn-sm" onclick="App.batchDelete('/user/batchDelete')"><i class="fa fa-trash"></i> 删除</button>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-download"></i> 导入</a>&nbsp;&nbsp;&nbsp;
                                    <a href="#" type="button" class="btn btn-default btn-sm"><i class="fa fa-upload"></i> 导出</a>&nbsp;&nbsp;&nbsp;
                                    <button  type="button" class="btn btn-default btn-sm" onclick="$('.box-info-search').css('display') == 'none' ? $('.box-info-search').show('fast') : $('.box-info-search').hide('fast')"><i class="fa fa-search"></i> 搜索</button>
                            </div>


<%--                                <div class="box-tools">--%>
<%--                                    <div class="input-group input-group-sm" style="width: 150px;">--%>
<%--                                        <input type="text" name="" class="form-control pull-right" placeholder="搜索" >--%>

<%--                                        <div class="input-group-btn">--%>
<%--                                            <button type="button" class="btn btn-default"><i class="fa fa-search"></i></button>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>


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
                                    <th>用户名</th>
                                    <th>手机号</th>
                                    <th>邮箱</th>
                                    <th>日期</th>
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
            {'data':'username'},
            {'data':'phone'},
            {'data':'email'},
            {'data':'updated'},
            {
                'data':function (row) {
                    let detailUrl='/user/detail?id='+row.id;
                    return '<button onclick="App.showDetail(\''+detailUrl+'\');"  type="button" class="btn btn-default btn-sm"><i class="fa fa-search"></i> 查看</button >&nbsp;&nbsp;'+
                        '<a href="/user/form?id='+row.id+'" type="button" class="btn btn-primary btn-sm"><i class="fa fa-edit"></i> 修改</a>&nbsp;&nbsp;'+
                        '<a href="#" type="button" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i> 删除</a>&nbsp;&nbsp;';
                }
            }
        ];
        _dataTable=App.initDataTables('/user/page' , columns);
    });

    
    function search() {
        let username=$('#username').val();
        let email=$('#email').val();
        let phone=$('#phone').val();
        let param={
            'username':username,
            'email':email,
            'phone':phone
        };
        _dataTable.settings()[0].ajax.data=param;
        _dataTable.ajax.reload();
    }
</script>


</body>
</html>