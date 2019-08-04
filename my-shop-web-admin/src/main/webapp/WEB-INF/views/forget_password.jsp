<%--
  Created by IntelliJ IDEA.
  User: yangge666
  Date: 2019-08-03
  Time: 19:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>My Shop | 重置密码</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/assets/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/assets/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/assets/dist/css/AdminLTE.min.css">
    <!-- iCheck -->
    <link rel="stylesheet" href="/static/assets/plugins/iCheck/square/blue.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body class="hold-transition register-page">
<div class="register-box">
    <div class="register-box-body">
        <p class="login-box-msg">重制密码</p>

        <form action="/forget/changePwd" method="post">
            <div class="form-group has-feedback">
                <input id="email" type="email" name="email" class="form-control" placeholder="请输入邮箱">
                <span class="form-control-feedback"></span>
            </div>
            <div class="form-group has-feedback">
                <input type="password" name="password" class="form-control" placeholder="新密码">
                <span class="form-control-feedback"></span>
            </div>
            <div class="row">
                <div class="col-xs-8">
                    <div class="form-group has-feedback">
                        <input type="text" name="yzm" class="form-control" placeholder="验证码">
                        <span class="form-control-feedback"></span>
                    </div>
                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button id="yzm" type="button" class="btn btn-primary btn-block btn-flat">获取验证码</button>
                </div>
                <!-- /.col -->
            </div>

            <div class="row">
                <div class="col-xs-8">

                </div>
                <!-- /.col -->
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">提交</button>
                </div>
                <!-- /.col -->
            </div>
        </form>
        <a href="/login" class="text-center">我记住密码了</a>
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<!-- jQuery 3 -->
<script src="/static/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/static/assets/plugins/iCheck/icheck.min.js"></script>
<script>
    let countdown=60;
    let yzm = $('#yzm');
    yzm.click(function () {
        let email = $('#email').val();
        if (email == null || email === ''){
            alert("请输入正确的邮箱");
            return;
        }
        $.ajax({
            url:'/forget/sendEmail?email='+email,
            method:'get',
            success:function (data) {
                console.log(data);
            }
        });
        setTime(yzm);
    });
    function setTime(yzm) {
         if (countdown > 0) {
            yzm.attr("disabled" , true);
            yzm.text(countdown + '秒后重试');
            countdown--;
        }
        setTimeout(function() {
            if (countdown === 0) {
                yzm.attr("disabled" , false);
                yzm.text("获取验证码");
                countdown = 60;
            }else {
                setTime(yzm)
            }
        },1000)
    }
</script>
</body>
</html>
