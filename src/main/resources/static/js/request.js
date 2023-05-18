// 登录请求
function loginRequest(username, password, callbackOnFail) {
    $.ajax({
        url: '/hause/bev1/user/login',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "username": username,
            "password": password
        },
        success: (data) => {
            if (data.code === 10000) {
                window.location.href="../"
            } else {
                callbackOnFail()
            }
        }
    });
}

// 注册请求
function registerRequest(username, password, phone, wechat, callbackOnFail) {
    $.ajax({
        url: '/hause/bev1/user/register',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "username": username,
            "password": password,
            "phone": phone,
            "wechat": wechat
        },
        success: (data) => {
            if (data.code === 10000) {
                window.location.href="../"
            } else {
                callbackOnFail()
            }
        }
    });
}

// 获取房子列表请求
function getHouseListRequest(favor, sortByPrice, asc, page, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/list',
        method: "GET",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "favor": favor,
            "sortByPrice": sortByPrice,
            "asc": asc,
            "pageNum": page
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求租房列表失败，状态码：" + data.code)
            }
        }
    });
}

// 搜索请求
function searchHouseRequest(keyword, page, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/search',
        method: "GET",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "keyword": keyword,
            "pageNum": page
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 是否收藏请求
function isFavorRequest(houseId, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/isFavor',
        method: "GET",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "houseId": houseId
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 收藏请求
function favorRequest(houseId, favor) {
    $.ajax({
        url: '/hause/bev1/house/favor',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "houseId": houseId,
            "favor": favor
        },
        success: (data) => {
            if (data.code !== 10000) {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 发布请求
function publishRequest(data, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/publish',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: data,
        success: (dat) => {
            if (dat.code === 10000) {
                callbackOnSuc(dat.data)
            } else {
                console.error("请求失败，状态码：" + dat.code)
            }
        }
    });
}

// 修改用户信息请求
function modifyUserInfoRequest(data, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/user/modify',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: data,
        success: (dat) => {
            if (dat.code === 10000) {
                callbackOnSuc(dat.data)
            } else {
                console.error("请求失败，状态码：" + dat.code)
            }
        }
    });
}

// 获取我的列表请求
function getMyHouseListRequest(page, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/my',
        method: "GET",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "pageNum": page,
            "pageSize": 30
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 获取审核列表请求
function getAdminHouseListRequest(page, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/review',
        method: "GET",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "pageNum": page,
            "pageSize": 30
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 删除房子请求
function deleteHouseRequest(houseId, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/delete',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "houseId": houseId
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 驳回房子请求
function rejectHouseRequest(houseId, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/reject',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "houseId": houseId
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}

// 通过房子请求
function passHouseRequest(houseId, callbackOnSuc) {
    $.ajax({
        url: '/hause/bev1/house/pass',
        method: "POST",
        xhrFields: {
            withCredentials: true
        },
        data: {
            "houseId": houseId
        },
        success: (data) => {
            if (data.code === 10000) {
                callbackOnSuc(data.data)
            } else {
                console.error("请求失败，状态码：" + data.code)
            }
        }
    });
}