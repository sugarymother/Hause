$(function () {
    // 头部显示发布按钮
    showHeaderPubBtn()

    let $houseList = $('.house_list_box ul')
    let $AllHouseBtn = $('#AllHouseBtn')
    let $FavorHouseBtn = $('#FavorHouseBtn')
    let $OrderByBtn = $('#OrderByBtn')
    let $OrderBtn = $('#OrderBtn')
    let $SearchIn = $('#SearchIn')
    let $SearchBtn = $('.search_box img')

    // 当前列表页
    let page = 1
    let isFavor = false
    let sortByPrice = false
    let asc = false
    let haveMorePage = true
    let flushIng = false

    // 添加列表项
    function addListItem(houseList) {
        for (let i = 0; i < houseList.length; i++) {
            let house = houseList[i]
            let newItem =
                $('<li>' +
                    '<img class="house_img" src="' + house.firstPicUrl + '" alt="house_img"/>' +
                    '<div class="house_info_area">' +
                        '<div class="list_title">' + house.title + '</div>' +
                        '<div class="house_type">' + house.type + '</div>' +
                        '<div class="house_info"> ' + house.specs + ' | ' + house.floor + ' | ' + house.towards +  ' | ' + house.area + '</div>' +
                        '<div class="house_addr">地址：' + house.addr + '</div>' +
                        '<div class="house_price">' + '￥' + house.price + '/月</div>' +
                    '</div>' +
                '</li>')
            newItem.click(function () {
                window.open('./house/detail/' + house.id)
            })
            $houseList.append(newItem)
        }
    }

    function addEnding() {
        $houseList.append($('<li class="list_ending">没有更多了~</li>'))
    }

    // 刷新房子列表
    function flushList(clear) {
        if (clear) {
            page = 1
            haveMorePage = true
            $houseList.empty()
        }
        getHouseListRequest(isFavor, sortByPrice, asc, page, function (data) {
            addListItem(data)
            page++
            flushIng = false
            if (data.length === 0) {
                haveMorePage = false
                addEnding()
            }
        })
    }
    flushList(true)

    // 所有租房点击事件
    $AllHouseBtn.click(function () {
        $AllHouseBtn.addClass('on')
        $FavorHouseBtn.removeClass('on')
        isFavor = false
        flushIng = true
        flushList(true)
    })

    // 我的收藏点击事件
    $FavorHouseBtn.click(function () {
        $AllHouseBtn.removeClass('on')
        $FavorHouseBtn.addClass('on')
        isFavor = true
        flushIng = true
        flushList(true)
    })

    // 排序方式点击事件
    $OrderByBtn.click(function () {
        if (sortByPrice) {
            sortByPrice = false
            $OrderByBtn.text('按时间')
        } else {
            sortByPrice = true
            $OrderByBtn.text('按价格')
        }
        flushIng = true
        flushList(true)
    })

    // 顺序点击事件
    $OrderBtn.click(function () {
        if (asc) {
            asc = false
            $OrderBtn.attr('src', '/hause/static/img/order_desc.png')
        } else {
            asc = true
            $OrderBtn.attr('src', '/hause/static/img/order_asc.png')
        }
        flushIng = true
        flushList(true)
    })

    function openSearchPage() {
        let key = $SearchIn.val()
        if (key !== '') {
            window.open('./house/search?key=' + key)
        }
    }

    // 搜索点击事件
    $SearchBtn.click(function () {
        openSearchPage()
    })

    // 搜索回车事件
    $SearchIn.keypress(function (event) {
        if (event.which === 13) {
            openSearchPage()
        }
    })

    // 滚动到底事件
    $(window).scroll(function () {
        if (!haveMorePage) {
            return
        }
        let scrollTop = document.documentElement.scrollTop || document.body.scrollTop
        let clientHeight = document.documentElement.clientHeight || document.body.clientHeight
        let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight
        let t = scrollHeight - scrollTop - clientHeight - 40
        if (t <= 0 && !flushIng) {
            flushIng = true
            flushList(false)
        }
    })
});