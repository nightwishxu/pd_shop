import request from '@/utils/request'

// 查询在线视频列表
export function listOnline(query) {
  return request({
    url: '/videoOnline/list',
    method: 'get',
    params: query
  })
}

// 查询在线视频详细
export function getOnline(id) {
  return request({
    url: '/videoOnline/findById?id=' + id,
    method: 'get'
  })
}

// 新增在线视频
export function addOnline(data) {
  return request({
    url: '/videoOnline/save',
    params: data
  })
}

// 修改在线视频
export function updateOnline(data) {
  return request({
    url: '/videoOnline/save',
    method: 'put',
    params: data
  })
}

// 删除在线视频
export function delOnline(id) {
  return request({
    url: '/videoOnline/del?id=' + id,
  })
}

// 导出在线视频
export function exportOnline(query) {
  return request({
    url: '/videoOnline/export',
    method: 'get',
    params: query
  })
}