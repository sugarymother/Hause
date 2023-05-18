let headerShowUI = true
let headerUrlSuffix = "./"
let isAdmin = false

function hideHeaderUI() {
    headerShowUI = false
    $('.exit_login_btn').css('visibility', 'hidden')
    $('.header_spliter').text('')
}

function showHeaderPubBtn() {
    if (!isAdmin) $('#PubBtn').css('visibility', 'visible')
}

function setHeaderUrlSuffix(suffix) {
    headerUrlSuffix = suffix
}

$(function() {
    if ($('#isAdmin').text() === 'true') {
        isAdmin = true
    }

    let userItemListDOM = $('.header_user_item_list')

    // 头像悬停事件
    $('.header_user_icon').hover(function() {
        if (headerShowUI) {
            userItemListDOM.css('visibility', 'visible')
        }
    })
    userItemListDOM.hover(function() {
        if (headerShowUI) {
            userItemListDOM.css('visibility', 'visible')
        }
    }, function() {
        userItemListDOM.css('visibility', 'hidden')
    })

    // 退出按钮事件
    let exitLoginBtn = $('.exit_login_btn')
    exitLoginBtn.click(function () {
        let day = new Date()
        day.setTime(day.getTime() - 1000)
        document.cookie = 'HAUSE_TOKEN=;path=/;expires=' + day.toUTCString()
        window.location.href= headerUrlSuffix + 'user/login'
    })

    // 标题点击事件
    $('.header_title').click(function () {
        window.location.href= headerUrlSuffix
    })

    // 发布点击事件
    $('#PubBtn').click(function () {
        window.open(headerUrlSuffix + 'house/publish')
    })

    // 我的出租点击事件
    let $MyHouseBtn = $('#MyHouseBtn');
    if (isAdmin) {
        $MyHouseBtn.text("房源审核")
        $MyHouseBtn.click(function () {
            window.open(headerUrlSuffix + 'house/review')
        })
    } else {
        $MyHouseBtn.click(function () {
            window.open(headerUrlSuffix + 'house/my')
        })
    }

    // 我的信息点击事件
    $('#MyInfoBtn').click(function () {
        window.open(headerUrlSuffix + 'user/info')
    })
})