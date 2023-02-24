<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${userInfo.username} - 我的出租</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/myhouse.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
</head>

<body>
<#include "../common/header.ftl">
<div class="top_container">
    <div class="my_list_box">
        <table id="MyTable">
        </table>
        <div class="ending" hidden>没有更多了~</div>
    </div>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/myhouse.js"></script>
</html>