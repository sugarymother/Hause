$(function () {
    // 关闭头部头像ui
    hideHeaderUI()

    // mode按钮事件
    $('#ModeBtn').click(function() {
        window.location.href = "./register"
    })

    let $msgTxt = $('.msg_txt')
    let $UsernameIn = $('#UsernameIn')
    let $PasswordIn = $('#PasswordIn')

    // submit事件
    $('#SubmitBtn').click(function () {
        let username = $UsernameIn.val()
        let password = $PasswordIn.val()
        let md5 = $.md5(password)

        if (username === "" || password === "") {
            $msgTxt.text("请完整填写登录信息")
            $msgTxt.css('visibility', 'visible')
        } else {
            loginRequest(username, md5, function () {
                $msgTxt.text("登陆失败，用户名或密码错误")
                $msgTxt.css('visibility', 'visible')
            })
        }
    })
});