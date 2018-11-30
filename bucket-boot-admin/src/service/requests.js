import axios from 'axios'

let base = 'http://127.0.0.1:8081/admin-server';

let handleError = function(error) {
    if (error.response) {
        // 请求已发出，但服务器响应的状态码不在 2xx 范围内
        switch(error.response.status) {
            case 401:
                top.window.postMessage({
                    type: 'NO_LOGIN'
                });
                break;
            case 403:
                top.window.postMessage({
                    type: 'NO_PERMISSION'
                });
                break;
            default:
        }
      } else {
        // Something happened in setting up the request that triggered an Error
        console.log('Error', error.message);
      }
}

export const postRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${base}${url}`,
        data: params,
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        }
    }).catch(function (error) {
        handleError(error)
    });
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
        handleError(error)
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
    }).catch(function (error) {
        handleError(error)
    });
}
export const deleteRequest = (url) => {
    return axios({
        method: 'delete',
        url: `${base}${url}`
    }).catch(function (error) {
        handleError(error)
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
        handleError(error)
    });
}
