export default {
    roleArray: [
        {
            "roleId": 1,
            "roleName": "角色01",
            "roleDesc": "角色描述。。。。。。。",
            "roleStatus": "1"

        },
        {
            "roleId": 2,
            "roleName": "角色02",
            "roleDesc": "角色描述。。。。。。。",
            "roleStatus": "0"

        },
        {
            "roleId": 3,
            "roleName": "角色03",
            "roleDesc": "角色描述。。。。。。。",
            "roleStatus": "1"

        }
    ],
    authArray: [{
        "authName": "系统管理",
        "authcode": "100000000",
        "authType": "0",
        "authUrl": "",
        "authStatus": "1",
        "authIsShow": "1",
        "collapsed": "1",
        "authDesc": "",
        "parentCode": null,
        "level": 0,
        "children": [{
                "authName": "员工信息",
                "authcode": "100100000",
                "authType": "1",
                "authUrl": "/employeeManage",
                "authStatus": "1",
                "authIsShow": "1",
                "collapsed": "1",
                "authDesc": "",
                "parentCode": "100000000",
                "level": 1,
                "children": [{
                        "authName": "新建",
                        "authcode": "100100100",
                        "authType": "2",
                        "authUrl": "/employee/add",
                        "authStatus": "0",
                        "authIsShow": "0",
                        "collapsed": "0",
                        "authDesc": "",
                        "parentCode": "100100000",
                        "level": 2,
                    },
                    {
                        "authName": "修改",
                        "authcode": "100100200",
                        "authType": "2",
                        "authUrl": "/employee/add",
                        "authStatus": "1",
                        "authIsShow": "1",
                        "collapsed": "1",
                        "authDesc": "",
                        "parentCode": "100100000",
                        "level": 2,
                    }
                ]
            },
            {
                "authName": "角色信息",
                "authcode": "1200000",
                "authType": "1",
                "authUrl": "/employeeManage",
                "authStatus": "1",
                "authIsShow": "1",
                "collapsed": "1",
                "authDesc": "",
                "parentCode": "100000000",
                "level": 1,
            }
        ]
    }]
}
