$(function () {
    // 设置头部跳转根路径
    setHeaderUrlSuffix('../')

    let $houseList = $('.house_list_box ul')
    let keyword = $('#Keyword').val()

    // 当前列表页
    let page = 1
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
                window.open('./detail/' + house.id)
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
        searchHouseRequest(keyword, page, function (data) {
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

    // 滚动到底事件
    $(window).scroll(function () {
        if (!haveMorePage) return
        let scrollTop = document.documentElement.scrollTop || document.body.scrollTop
        let clientHeight = document.documentElement.clientHeight || document.body.clientHeight
        let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight
        let t = scrollHeight - scrollTop - clientHeight - 40
        if (t <= 0 && !flushIng) {
            flushIng = true
            flushList(false)
        }
    })
})