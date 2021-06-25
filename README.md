# foodSystem
1.登录接口/api/login/loginUsr		(POST)
入参{
    "storeNo":"1",                          //店号
"usrId":"1",             		  //登录人    
"password":"1",                   //密码
}
出参
{
    "code": 0,
    "body": null,
    "msg": "登录成功"
}

2.订单查询接口/api/foodOrder/orderDetail		(GET)
入参{
    "storeNo":1,
    "beginDate":"2020-01-01",
    "endDate":"2021-05-30",
    "page":1,
    "limit":50
}
出参{
    "code": 0,
    "body": {
        "data": [
            {
                "orderNo": "202104281619581658023",
                "storeNo": "1",
                "tableNo": 1,
                "status": "未结账",
                "createTime": "2021-04-28 11:47:38"
            }
.....
        ],
        "count": 13
    },
    "msg": "Success"
}






3.订单详情查询接口/api/foodOrder/orderDetailList	(GET)
入参{
    "storeNo":1,
    "orderNo":"202104281619581658023"
}
出参{
    "code": 0,
    "body": {
        "data": [
            {
                "storeNo": "1",
                "name": "凉瓜肉片",
                "orderNo": 202104281619581658023,
                "sellPrice": "15.0",
                "itemNo": "2",
                "amount": 15,
                "qty": 1
            },
            {
                "storeNo": "1",
                "name": "京酱肉丝",
                "orderNo": 202104281619581658023,
                "sellPrice": "25.0",
                "itemNo": "3",
                "amount": 25,
                "qty": 1
            }
        ]
    },
    "msg": "Success"
}

4.webstocket通讯接口/webStocket/{usrId}

5.webstocket发送给指定人/api/push/toSomeOne
入参{
    "usrId":1,
    "message":"哈哈哈哈哈"
}

6.webstocket发送全部人/api/push/toEveryone
入参{
      "message":"哈哈哈哈哈"
}
7.上传菜品接口/api/foodDetail/upload          (Post)
入参{
    "file":选择上传的图片,
"picName":"哈哈哈哈哈",
"sellPrice":20.99
}
出参{
    "code": 0,
    "body": {
        "data": "4373050d-4d69-4143-9bf8-371aa7b019a0.jpg"
    },
    "msg": "Success"
}

8.获取菜品接口/api/foodDetail/getItemDetail		(GET)
入参{
   无
}
出参{
    "code": 0,
    "body": {
        "酒水": {
            "list": [
                {
                    "name": "鱼香肉丝",
                    "storeNo": "1001",
                    "pic": "/upload/imgs/1.jpg",
                    "sellPrice": "20",
                    "itemNo": 1,
                    "type": 1,
                    "status": 0
                }
             ]
        },
        "烧烤": {
            "list": [
                {
                    "name": "凉瓜鸡蛋",
                    "storeNo": "1001",
                    "pic": "/upload/imgs/1.jpg",
                    "sellPrice": "15",
                    "itemNo": 4,
                    "type": 2,
                    "status": 0
                }
            ]
        },
        "type": 2
    },
    "msg": "Success"
}
9.新增分类/api/foodDetail/insertCategory	(POST)
入参{
    "storeNo":1002,
    "typeName":"方法"
}
出参{
    "code": 0,
    "body": null,
    "msg": "Success"
}
10.新增店号/api/foodDetail/insertStore		(POST)
入参{
    "storeNo":1002,
    "storeName":"刚刚"
}
出参{
    "code": 0,
    "body": null,
    "msg": "Success"
}

11.新增店号/api/foodOrder/createOrder		(POST)
入参{
    "storeNo":1001,
    "tableNo":1,
    "detail":[
        {"storeNo":1001,"itemNo":1,"count":1,"sellPrice":10}
        ]
}
出参{
    "code": 0,
    "body": null,
    "msg": "Success"
}
