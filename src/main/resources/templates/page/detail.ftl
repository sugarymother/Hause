<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${(houseDetail.title)!"蛤租房"} - 房源详情</title>

    <link rel="stylesheet" href="/hause/static/css/base.css" type="text/css"/>
    <link rel="stylesheet" href="/hause/static/css/detail.css" type="text/css"/>
    <link rel="icon" type="image/x-icon" href="/hause/static/favicon.ico">
    <link rel="bookmark" type="image/x-icon" href="/hause/static/favicon.ico">
    <script src="/hause/static/js/jquery-3.4.1.min.js"></script>
</head>

<body>
<data id="HouseId" value="${houseDetail.id}"></data>
<#include "../common/header.ftl">
<div class="top_container">
    <div class="house_info_box">
        <div class="house_title">${(houseDetail.title)!"租房标题"}</div>
        <div class="under_title">
            <div class="pub_time">发布时间：${(houseDetail.updateTime)?string("yyyy-MM-dd HH:mm")!"datetime"}</div>
            <button id="FavorBtn" type="button">
                <img src="/hause/static/img/favor_d.png" alt="favor">
                收藏
            </button>
        </div>
        <div class="house_main_area">
            <div class="house_pic_area" style="background-image: url('${(houseDetail.picUrlList[0])!"null"}')">
                <#if houseDetail.picUrlList?size gt 1>
                    <div class="pic_selecter">
                        <#list houseDetail.picUrlList as url>
                            <div class="pic_btn" style="background-image: url('${url}')"></div>
                        </#list>
                    </div>
                </#if>
            </div>
            <div class="house_info_area">
                <div class="house_price">￥${(houseDetail.price)!"0.00"}<span>/月</span></div>
                <div class="house_main_detail">
                    <span>${(houseDetail.specs)!" 室 厅 卫"}</span>
                    <span>${(houseDetail.towards)!"朝向"}</span>
                    <span>${(houseDetail.area)!"面积"}</span>
                </div>
                <div class="house_other_detail">
                    <b>所属类型 </b><p>${(houseDetail.type)!"无"}</p><br>
                    <b>居住楼层 </b><p>${(houseDetail.floor)!"无"}</p><br>
                    <b>所属小区 </b><p>${(houseDetail.community)!"无"}</p><br>
                    <b>所在地址 </b><p>${(houseDetail.addr)!"无"}</p>
                </div>
                <div class="house_publisher_area">
                    <img src="/hause/static/img/user_large.png" alt="user_ico">
                    <div class="publisher_info">
                        <div class="pub_username">${(houseDetail.pubUserInfo.username)!"用户名"}</div>
                        <div>电话：${(houseDetail.pubUserInfo.phone)!"电话"}</div>
                        <div>微信：${(houseDetail.pubUserInfo.wechat)!"微信"}</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="desc_title">房源描述</div>
        <div class="house_desc">${(houseDetail.description)}</div>
        <#if houseDetail.picUrlList?size gt 0>
            <div class="desc_title">房源图片</div>
            <div class="house_pic_list">
                <#list houseDetail.picUrlList as url>
                    <img src="${url}" alt="house_pic">
                </#list>
            </div>
        </#if>
    </div>
</div>
</body>

<script src="/hause/static/js/request.js"></script>
<script src="/hause/static/js/detail.js"></script>
</html>