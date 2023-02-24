<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${userInfo.username} - 个人信息</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/user.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
</head>

<body>
<#include "../common/header.ftl">
<div class="top_container">
    <div class="user_info_box">
        <img src="/hause/static/img/user_large.png" alt="user_ico">
        <div class="username">${userInfo.username}</div>
        <div class="info_line">
            <label for="Phone">电话 </label><input class="off" id="Phone" type="number" value="${userInfo.phone}" disabled>
            <button id="PhoneBtn" type="button">修改</button>
        </div>
        <div class="info_line">
            <label for="Wechat">微信 </label><input class="off" id="Wechat" type="text" value="${userInfo.wechat}" disabled>
            <button id="WechatBtn" type="button">修改</button>
        </div>
    </div>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/user.js"></script>
</html>