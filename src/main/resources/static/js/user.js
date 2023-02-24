$(function () {
    // 设置头部跳转根路径
    setHeaderUrlSuffix('../')

    let $phoneIn = $('#Phone')
    let $phoneBtn = $('#PhoneBtn')
    let $wechatIn = $('#Wechat')
    let $wechatBtn = $('#WechatBtn')

    let phoneOn = false
    let wechatOn = false

    function modifyPhone() {
        modifyUserInfoRequest({
            "phone": $phoneIn.val()
        }, function () {
            alert('修改成功')
        })
    }

    function modifyWechat() {
        modifyUserInfoRequest({
            "wechat": $wechatIn.val()
        }, function () {
            alert('修改成功')
        })
    }

    $phoneBtn.click(function () {
        if (phoneOn) {
            phoneOn = false
            $phoneBtn.text('修改')
            $phoneIn.addClass('off')
            $phoneIn.attr('disabled', true)
            modifyPhone()
        } else {
            phoneOn = true
            $phoneBtn.text('确定')
            $phoneIn.removeClass('off')
            $phoneIn.attr('disabled', false)
            $phoneIn.select()
        }
    })

    $wechatBtn.click(function () {
        if (wechatOn) {
            wechatOn = false
            $wechatBtn.text('修改')
            $wechatIn.addClass('off')
            $wechatIn.attr('disabled', true)
            modifyWechat()
        } else {
            wechatOn = true
            $wechatBtn.text('确定')
            $wechatIn.removeClass('off')
            $wechatIn.attr('disabled', false)
            $wechatIn.select()
        }
    })
})