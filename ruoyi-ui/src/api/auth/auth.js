import request from '@/utils/request'


export function listUser(query) {
    return request({
        url: '/auth/user/list',
        method: 'post',
        params: query
    })
}