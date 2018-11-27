import axios from 'axios'
import { MessageBox } from 'element-ui';

let base = 'http://127.0.0.1:8081/admin-server';
export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).catch(function (error) {
        MessageBox.alert(error.response.data.message, {
            confirmButtonText: '确定',
            callback: action => {
            }
        });
    });;
}
export const uploadFileRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    }).catch(function (error) {
        MessageBox.alert(error.response.data.message, {
            confirmButtonText: '确定',
            callback: action => {
            }
        });
    });
}
export const putRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    });
}
export const deleteRequest = (url) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`
    }).catch(function (error) {
        MessageBox.alert(error.response.data.message, {
            confirmButtonText: '确定',
            callback: action => {
            }
        });
    });
}
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        params: params,
        transformRequest: [function (data) {
            let ret = ''
            for (let it in data) {
                ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
            }
            return ret
        }],
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        url: `${base}${url}`
    }).catch(function (error) {
        MessageBox.alert(error.response.data.message, {
            confirmButtonText: '确定',
            callback: action => {
            }
        });
    });
}
