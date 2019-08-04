<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>My Shop | 404 找不到页面</title>
    <jsp:include page="../includes/head.jsp"/>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <jsp:include page="../includes/header.jsp"/>


    <jsp:include page="../includes/aside.jsp"/>
    <header class="main-header">

        <div class="content-wrapper">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    404 找不到页面
                </h1>
                <ol class="breadcrumb">
                    <li><a href="/main"><i class="fa fa-dashboard"></i> 首页</a></li>
                    <li class="active">404 找不到</li>
                </ol>
            </section>

            <!-- Main content -->
            <section class="content">
                <div class="error-page">
                    <h2 class="headline text-yellow"> 404</h2>

                    <div class="error-content">
                        <h3><i class="fa fa-warning text-yellow"></i> 糟糕! 找不到页面.</h3>

                        <p>
                            我们找不到你要寻找的页面.
                            或许, 你可以 <a href="/main">返回首页</a>
                        </p>


                    </div>
                    <!-- /.error-content -->
                </div>
                <!-- /.error-page -->
            </section>
            <!-- /.content -->
        </div>
        <jsp:include page="../includes/footer.jsp"/>

        <jsp:include page="../includes/body.jsp"/>
</body>
</html>
