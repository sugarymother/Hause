<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>发布房源</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/publish.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
    <script src="/hause/static/js/image-conversion.js"></script>
</head>

<body>
<#include "../common/header.ftl">
<div class="top_container">
    <div class="form_box">
        <form action="" onsubmit="return onSubmit()">
            <label>
                标题
                <input id="titleIn" type="text" name="title" required>
            </label>
            <label>
                规格
                <input id="specsIn1" class="num_input" type="number" value="1" min="0" required>
                室
                <input id="specsIn2" class="num_input" type="number" value="1" min="0" required>
                厅
                <input id="specsIn3" class="num_input" type="number" value="1" min="0" required>
                卫
            </label>
            <label>
                面积
                <input id="areaIn1" class="num_input" type="number" value="0" min="0" required>
                ㎡
            </label>
            <label>
                楼层
                <input id="floorIn1" class="num_input" type="number" value="1" min="1" required>
                /
                <input id="floorIn2" class="num_input" type="number" value="1" min="1" required>
                层
            </label>
            <label>
                类型
                <input id="typeIn1" class="radio_input" type="radio" name="type" value="整租" checked>
                <label class="radio_label" for="typeIn1">整租</label>
                <input id="typeIn2" class="radio_input" type="radio" name="type" value="合租">
                <label class="radio_label" for="typeIn2">合租</label>
            </label>
            <label>
                地址
                <input id="addrIn" type="text" name="addr" required>
            </label>
            <label>
                价格
                <input id="priceIn" class="num_input price_input" type="number" name="price" value="0" min="0" required>
                元/月
            </label>
            <label>
                朝向
                <input id="towardsIn1" class="radio_input" type="radio" name="towards" value="朝东" checked>
                <label class="radio_label" for="towardsIn1">东</label>
                <input id="towardsIn2" class="radio_input" type="radio" name="towards" value="朝南">
                <label class="radio_label" for="towardsIn2">南</label>
                <input id="towardsIn3" class="radio_input" type="radio" name="towards" value="朝西">
                <label class="radio_label" for="towardsIn3">西</label>
                <input id="towardsIn4" class="radio_input" type="radio" name="towards" value="朝北">
                <label class="radio_label" for="towardsIn4">北</label>
            </label>
            <label>
                小区
                <input id="commIn" type="text" name="community" required>
            </label>
            <label>
                描述
                <textarea id="descIn" name="description" required></textarea>
            </label>
            <label>房源图片</label>
            <ul class="pic_list">
            </ul>

            <div class="btn_box">
                <button id="SubmitBtn" type="submit">发布</button>
            </div>
        </form>
    </div>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/publish.js"></script>
</html>