import request from '@/utils/request'

// 查询消息列表
export function listNotify(query) {
  return request({
    url: '/notify/list',
    method: 'get',
    params: query
  })
}

// 查询消息详细
export function getNotify(id) {
  return request({
    url: '/notify/findById?id=' + id,
    method: 'get'
  })
}

// 新增消息
export function addNotify(data) {
  return request({
    url: '/notify/save',
    params: data
  })
}

// 修改消息
export function updateNotify(data) {
  return request({
    url: '/notify/save',
    params: data
  })
}

// 删除消息
export function delNotify(id) {
  return request({
    url: '/notify/del?id=' + id,
    method: 'delete'
  })
}

// 导出消息
export function exportNotify(query) {
  return request({
    url: '/notify/export',
    method: 'get',
    params: query
  })
}

export function send(id) {
  return request({
    url: '/notify/send?id=' + id,
    method: 'get'
  })
}

// 新