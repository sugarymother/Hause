$(function () {
    // 关闭头部头像ui
    hideHeaderUI()

    // mode按钮事件
    $('#ModeBtn').click(function() {
        window.location.href = "./login"
    })

    let $msgTxt = $('.msg_txt')
    let $UsernameIn = $('#UsernameIn')
    let $PasswordIn = $('#PasswordIn')
    let $PhoneIn = $('#PhoneIn')
    let $WechatIn = $('#WechatIn')

    // submit事件
    $('#SubmitBtn').click(function () {
        let username = $UsernameIn.val()
        let password = $PasswordIn.val()
        let phone = $PhoneIn.val()
        let wechat = $WechatIn.val()

        if (username === "" || password === ""
            || phone === "" || wechat === "") {
            $msgTxt.text("请完整填写信息")
            $msgTxt.css('visibility', 'visible')
        } else {
            registerRequest(username, password, phone, wechat, function () {
                $msgTxt.text("注册失败，用户名已存在")
                $msgTxt.css('visibility', 'visible')
            })
        }
    })
});