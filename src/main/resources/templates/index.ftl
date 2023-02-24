<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>蛤租房</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/index.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
</head>

<body>
<#include "common/header.ftl">
<img class="bkg" src="/hause/static/img/bkg.png" alt="back"/>
<div class="top_container">
    <div class="search_box">
        <label for="SearchIn"></label><input id="SearchIn" placeholder="输入关键词进行搜索" type="text">
        <img src="/hause/static/img/search.png" alt="search"/>
    </div>
    <div class="house_list_header">
        <button class="on" id="AllHouseBtn" type="button">所有租房</button>
        <button id="FavorHouseBtn" type="button">我的收藏</button>
        <button id="OrderByBtn" type="button">按时间</button>
        <img id="OrderBtn" src="/hause/static/img/order_desc.png" alt="order"/>
    </div>
    <div class="house_list_box">
        <ul>
        </ul>
    </div>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/index.js"></script>
</html>