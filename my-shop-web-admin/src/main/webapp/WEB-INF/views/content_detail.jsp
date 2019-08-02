<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的商城 | 内容详情</title>
</head>
<body class="hold-transition skin-blue sidebar-mini" style="overflow-y:scroll">
<div class="box-body">

    <p align="center">
    <p align="center"><h3>所属分类：${tbContent.tbContentCategory.name}</h3></p>
    <p align="center"><h3>标题：${tbContent.title}</h3></p>
    <p align="center"><h3>子标题：${tbContent.subTitle}</h3></p>
    <p align="center"><h3>标题描述：${tbContent.titleDesc}</h3></p>
    <p align="center"><h3>链接：<a href="${tbContent.url}">${tbContent.url}</a></h3></p>
    <p align="center">
    <h3>图片1</h3>
        <img  src="${tbContent.pic}" style="max-width:100%;">
    </p>
    <p align="center">
    <h3>图片2</h3>
    <img src="${tbContent.pic2}" style="max-width:100%;">
    </p>
    <p align="center">
    <h3>内容</h3>
    ${tbContent.content}
    </p>
    </p>

</div>
<jsp:include page="../includes/body.jsp"/>
</body>
</html>