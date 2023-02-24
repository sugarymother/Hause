let picNum = 0
let $picList = $('.pic_list')

function onSubmit() {
    if (picNum === 0) {
        alert('请至少添加一张房源图片')
        return false
    }
    let specs = $('#specsIn1').val() + '室' + $('#specsIn2').val() + '厅' + $('#specsIn3').val() + '卫'
    let floor = $('#floorIn1').val() + '/' + $('#floorIn2').val() + '层'
    let area = $('#areaIn1').val() + '㎡'
    let picBase64List = new Array(0)
    let pics = $picList.children('li')
    for (let i = 0; i < pics.length; i++) {
        picBase64List.push(pics.children('data').eq(i).val())
    }
    let data = {
        "title": $('input[name="title"]').val(),
        "specs": specs,
        "area": area,
        "floor": floor,
        "type": $('input[name="type"]:checked').val(),
        "addr": $('input[name="addr"]').val(),
        "price": $('input[name="price"]').val(),
        "towards": $('input[name="towards"]:checked').val(),
        "community": $('input[name="community"]').val(),
        "description": $('textarea[name="description"]').val(),
        "base64PicList": picBase64List
    }
    publishRequest(data, function () {
        alert('发布成功')
        window.location.href='../'
    })
    return false
}

$(function () {
    // 设置头部跳转根路径
    setHeaderUrlSuffix('../')

    function addPic(e, selector, newItem) {
        let fd = new FormData();
        let files = e.target.files;
        if(files.length > 0){
            let fileSize = files[0].size / (1024 * 1024); //MB
            if (fileSize > 10) {
                alert("文件大小不可超过10MB，请重新选择")
                return
            }

            let fileName = files[0].name
            imageConversion.compressAccurately(files[0], 150).then(file=>{
                let reader = new FileReader()
                fd.append('pic', file)
                reader.readAsDataURL(file)  //转成base64
                reader.fileName = fileName

                reader.onload = function(){
                    let base64 = this.result.replace(/data:.*base64,/g, '')
                    newItem.children('data').val(base64)
                }
            })
        }
        selector.attr("disabled", "true")
        addItem()
    }

    function addItem() {
        let newItem = $(
            '<li>' +
                '- <input class="pic_input" type="file" accept="image/jpeg,image/jpg,image/png">' +
                '<button class="del_pic_btn" type="button">删除</button>' +
                '<data></data>' +
            '</li>'
        )
        $('li').last().children('button').css('visibility', 'visible')
        $picList.append(newItem)
        newItem.children('input').on('change', function (event) {
            addPic(event, $(this), newItem)
            picNum++
        })
        newItem.children('button').click(function () {
            $(this).parent().remove()
            picNum--
        })
    }
    addItem()
})