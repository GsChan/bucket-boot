import {getRequest, postRequest, putRequest, deleteRequest, uploadFileRequest} from './requests'

export const employeeApi = {
    getEmployees: (query) => getRequest("/employee/get/list", query),
    saveEmployee: (body) => putRequest("/employee/add", body)
}

export const roleApi = {
    getEmployees: (query) => getRequest("/employee/get/list", query),
    saveEmployee: (body) => putRequest("/employee/add", body)
}

export const authApi = {
    getEmployees: (query) => getRequest("/employee/get/list", query),
    saveEmployee: (body) => putRequest("/employee/add", body)
}
