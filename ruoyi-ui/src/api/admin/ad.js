import request from '@/utils/request'

// 查询认证商城广告列表
export function listAd(query) {
  return request({
    url: '/ad/list',
    method: 'get',
    params: query
  })
}

// 查询认证商城广告详细
export function getAd(id) {
  return request({
    url: '/ad/findById?id=' + id,
    method: 'get'
  })
}

// 新增认证商城广告
export function addAd(data) {
  return request({
    url: '/ad/save',
    params: data
  })
}

// 修改认证商城广告
export function updateAd(data) {
  return request({
    url: '/ad/save',
    params: data
  })
}

// 删除认证商城广告
export function delAd(id) {
  return request({
    url: '/ad/del?id=' + id,
  })
}

// 导出认证商城广告
export function exportAd(query) {
  return request({
    url: '/admin/ad/export',
    method: 'get',
    params: query
  })
}


export function goodsList(q, type) {
  return request({
    url: '/ad/goodsList?type=' + type,
    method: 'get'
  })
}

export function videoList(q) {
  return request({
    url: '/ad/videoList',
    method: 'get'
  })
}

export function dpList(id) {
  return request({
    url: '/ad/dpList',
    method: 'get'
  })
}