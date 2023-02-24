$(function () {
    // 设置头部跳转根路径
    setHeaderUrlSuffix('../../')

    let houseId = $('#HouseId').val()
    let $FavorBtn = $('#FavorBtn')

    let isFavor = false
    isFavorRequest(houseId, function (favor) {
        isFavor = favor
        if (isFavor) {
            $FavorBtn.html('<img src="/hause/static/img/favor_l.png" alt="favor">取消收藏')
        } else {
            $FavorBtn.html('<img src="/hause/static/img/favor_d.png" alt="favor">收藏')
        }
    })

    // 图片选择器
    $('.pic_btn').click(function () {
        $('.house_pic_area').css(
            'background-image',
                $(this).css('background-image')
            )
    })

    // 收藏
    $FavorBtn.click(function () {
        if (isFavor) {
            isFavor = false
            $FavorBtn.html('<img src="/hause/static/img/favor_d.png" alt="favor">收藏')
        } else {
            isFavor = true
            $FavorBtn.html('<img src="/hause/static/img/favor_l.png" alt="favor">取消收藏')
        }
        favorRequest(houseId, isFavor)
    })
})