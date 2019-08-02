<%--
  Created by IntelliJ IDEA.
  User: yangge666
  Date: 2019-07-29
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>MyShop——个人注册</title>
    <link rel="stylesheet" type="text/css" href="/static/css/index.css">
    <link rel="stylesheet" type="text/css" href="/static/css/ziy.css">
    <link rel="stylesheet" type="text/css" href="/static/app/myStyle.css">
</head>
<body>
<!--dengl-->
<div class="yiny">
    <div class="beij_center">
        <div class="dengl_logo">
            <img src="/static/images/logo_1.png">
            <h1>欢迎注册</h1>
        </div>
    </div>
</div>
<form action="/register" method="post" id="myform" onsubmit="return formValidate();">
<div class="beij_center">
    <div class="ger_zhuc_beij">
        <div class="ger_zhuc_biaot">
            <ul>
                <li class="ger_border_color"><a href="/register">个人注册</a></li><c:if test="${message != null}">
                <i>|</i>
                <li><a href="#">${message}</a></li>
            </c:if>
                <p>我已经注册，现在就<a class="ftx-05 ml10" href="/login">登录</a></p>
            </ul>
        </div>
        <div class="zhuc_biaod">
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="username">用户名：</label>
              	</span>
                <input  id="username" name="username" class="i-text" type="text" placeholder="请输入用户名"><span id="userNameTitle">请输入4-12位用户名（首位为字母）</span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="password">设置密码：</label>
              	</span>
                <input id="password"  name="password" class="i-text" type="password" placeholder="请输入密码"><span id="passwordTitle">密码为6-16位</span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="rePassword">确认密码：</label>
              	</span>
                <input  id="rePassword" class="i-text" type="password" placeholder="请确认密码"><span id="repasswordTitle">请再次输入密码</span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="email">邮箱：</label>
              	</span>
                <input  id="email" name="email" class="i-text" type="email" placeholder="请输入邮箱"><span id="emailTitle">请输入保密邮箱</span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="phone">手机号码：</label>
              	</span>
                <input  id="phone" name="phone" class="i-text" type="text" placeholder="请输入手机号"><span id="telTitle">请输入关联手机号</span>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="checkbox"> </label>
              	</span>
                <div class="dag_biaod">
                    <input id="checkbox" type="checkbox"  >
                    阅读并同意
                    <a href="#" class="ftx-05 ml10">《MyShop用户注册协议》</a>
                    <a href="#" class="ftx-05 ml10">《隐私协议》</a>
                </div>
            </div>
            <div class="reg-items" >
              	<span class="reg-label">
                	<label for="btn"> </label>
              	</span>
                <input id="btn" class="reg-btn" value="立即注册" type="submit">
            </div>
        </div>
        <div class="xiaogg">
            <img src="/static/images/cdsgfd.jpg">
        </div>
    </div>
</div>
</form>

<div class="jianj_dib jianj_dib_1">
    <div class="beia_hao">
        <p>京ICP备：123456789号  </p>
        <p class="gonga_bei">京公网安备：123456789号</p>
    </div>
</div>

<script src="/static/js/jquery-1.11.1.min.js"></script>
<script src="/static/app/registerValidate.js"></script>
</body>
</html>
