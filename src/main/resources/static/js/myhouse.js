$(function () {
    // 设置头部跳转根路径
    setHeaderUrlSuffix('../')

    let $table = $('#MyTable')
    let page = 1
    let haveMorePage = true
    let flushIng = false

    function addRow(house) {
        let newRow = $(
            '<tr>' +
            '     <td>' + house.title + '</td>' +
            '     <td>' + house.specs + '</td>' +
            '     <td>' + house.area + '</td>' +
            '     <td>' + house.floor + '</td>' +
            '     <td>' + house.type + '</td>' +
            '     <td>' + house.addr + '</td>' +
            '     <td>' + house.price + '</td>' +
            '     <td>' + house.towards + '</td>' +
            '     <td>' +
            '     <button class="del_btn">删除</button>' +
            '     </td>' +
            '</tr>'
        )
        newRow.find('.del_btn').click(function () {
            let houseId = house.id
            if (confirm('确定删除标题为："' + house.title + '" 的房源吗？')) {
                // 删除
                deleteHouseRequest(houseId, function () {
                    flushIng = true
                    flushTable(true)
                    alert("删除成功")
                })
            }
        })
        $table.append(newRow)
    }

    function flushTable(clear) {
        if (clear) {
            page = 1
            haveMorePage = true
            $table.empty()
            $table.append($(
                '<tr>\n' +
                '      <th>标题</th>\n' +
                '      <th>规格</th>\n' +
                '      <th>面积</th>\n' +
                '      <th>楼层</th>\n' +
                '      <th>出租类型</th>\n' +
                '      <th>地址</th>\n' +
                '      <th>价格（元/月）</th>\n' +
                '      <th>朝向</th>\n' +
                '      <th>操作</th>\n' +
                '</tr>'
            ))
            $('.ending').hide()
        }
        getMyHouseListRequest(page, function (data) {
            if (data.length === 0) {
                haveMorePage = false
                $('.ending').show()
            } else {
                for (let i = 0; i < data.length; i++) {
                    addRow(data[i])
                }
            }
            page++
            flushIng = false
        })
    }
    flushTable(true)

    // 滚动到底事件
    $(window).scroll(function () {
        if (!haveMorePage) return
        let scrollTop = document.documentElement.scrollTop || document.body.scrollTop
        let clientHeight = document.documentElement.clientHeight || document.body.clientHeight
        let scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight
        let t = scrollHeight - scrollTop - clientHeight - 40
        if (t <= 0 && !flushIng) {
            flushIng = true
            flushTable(false)
        }
    })
})