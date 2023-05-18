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
            '     <td>' + house.status + '</td>' +
            '     <td>' +
            '     <button class="del_btn" id="postDetail">详情</button>' +
            '     <button class="del_btn" id="postReject">驳回</button>' +
            '     <button class="del_btn" id="postPass">通过</button>' +
            '     </td>' +
            '</tr>'
        )
        newRow.find('#postReject').click(function () {
            let houseId = house.id
            if (confirm('确定驳回标题为："' + house.title + '" 的房源吗？')) {
                // 删除
                rejectHouseRequest(houseId, function () {
                    flushIng = true
                    flushTable(true)
                    alert("驳回成功")
                })
            }
        })
        newRow.find('#postPass').click(function () {
            let houseId = house.id
            if (confirm('确定通过标题为："' + house.title + '" 的房源吗？')) {
                // 删除
                passHouseRequest(houseId, function () {
                    flushIng = true
                    flushTable(true)
                    alert("通过成功")
                })
            }
        })
        newRow.find('#postDetail').click(function () {
            let houseId = house.id
            window.open('./detail/' + houseId)
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
                '      <th>状态</th>\n' +
                '      <th>操作</th>\n' +
                '</tr>'
            ))
            $('.ending').hide()
        }
        getAdminHouseListRequest(page, function (data) {
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