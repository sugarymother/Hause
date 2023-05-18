<div class="header">
    <img class="header_title" src="/hause/static/img/title.png" alt="title">
    <div id="isAdmin" style="visibility: hidden">${(userInfo.admin?c)!"false"}</div>
    <button id="PubBtn" type="button">我要出租</button>
    <div>
        <img class="header_user_icon" src="/hause/static/img/user_icon.png" alt="user_icon">
        <div class="header_user_item_list">
            <button id="MyInfoBtn" type="button">我的信息</button>
            <button id="MyHouseBtn" type="button">我的出租</button>
        </div>
    </div>
    <div id="UsernameTxt">${(userInfo.username)!"请登录"}</div>
    <div class="header_spliter" style="margin: 0 10px 0 10px;"> | </div>
    <button class="exit_login_btn" type="button">退出</button>
</div>

<link rel="stylesheet" href="/hause/static/css/header.css" type="text/css"/>
<script src="/hause/static/js/header.js"></script>