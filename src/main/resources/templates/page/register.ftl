<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>蛤租房 - 注册</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/login.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
    <script src="/hause/static/js/jquery.md5.js"></script>
</head>

<body>
<#include "../common/header.ftl">
<div class="login_box">
    <img src="/hause/static/img/title.png" alt="title">
    <label for="UsernameIn"></label><input id="UsernameIn" placeholder="用户名" type="text">
    <label for="PasswordIn"></label><input id="PasswordIn" placeholder="密码" type="password">
    <label for="PhoneIn"></label><input id="PhoneIn" placeholder="手机号" type="text">
    <label for="WechatIn"></label><input id="WechatIn" placeholder="微信号" type="text">
    <div class="msg_txt">msg</div>
    <button id="SubmitBtn" type="submit">注册</button>
    <button id="ModeBtn" type="button">已有账号，直接登录</button>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/register.js"></script>
</html>