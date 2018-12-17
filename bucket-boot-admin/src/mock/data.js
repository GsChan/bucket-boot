export default {
    employeeArray: [
        {
            empId: 1,
            loginName: 'liwq01',
            empNo: '10001',
            empName: '李四',
            sex: '0',
            tel: '13806061407',
            email: '123@qq.com',
            empStatus: '1',
            isFrozen: '1',
            createTime: '2018-07-01 10:00:00',
            updateTime: '2018-07-01 10:00:00'
        },
        {
            empId: 2,
            loginName: 'liwq02',
            empNo: '10002',
            empName: '李四2',
            sex: '0',
            tel: '13806061402',
            email: '122@qq.com',
            empStatus: '1',
            isFrozen: '1',
            createTime: '2018-07-01 10:00:00',
            updateTime: '2018-07-01 10:00:00'
        },
        {
            empId: 3,
            loginName: 'liwq03',
            empNo: '10003',
            empName: '李四3',
            sex: '1',
            tel: '13806061437',
            email: '133@qq.com',
            empStatus: '1',
            isFrozen: '1',
            createTime: '2018-07-01 10:00:00',
            updateTime: '2018-07-01 10:00:00'
        }
    ],
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
