import request from '@/utils/request'

// 查询焦点图列表
export function listFocus(query) {
  return request({
    url: '/focus/list',
    method: 'get',
    params: query
  })
}

// 查询焦点图详细
export function getFocus(id) {
  return request({
    url: '/focus/findById?id=' + id,
    method: 'get'
  })
}

// 新增焦点图
export function addFocus(data) {
  return request({
    url: '/focus/save',
    params: data
  })
}

// 修改焦点图
export function updateFocus(data) {
  return request({
    url: '/focus/save',
    params: data
  })
}

// 删除焦点图
export function delFocus(id) {
  return request({
    url: '/focus/del?id=' + id,
    method: 'delete'
  })
}

// 导出焦点图
export function exportFocus(query) {
  return request({
    url: '/focus/export',
    method: 'get',
    params: query
  })
}


export function listTo(query) {
  return request({
    url: '/focus/listTo',
    method: 'get',
    params: query
  })
}

export function listToStore(query) {
  return request({
    url: '/focus/listToStore',
    method: 'get',
    params: query
  })
}

export function findByLocation(query) {
  return request({
    url: '/focus/findByLocation',
    method: 'get',
    params: query
  })
}