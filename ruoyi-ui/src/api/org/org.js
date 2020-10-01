import request from '@/utils/request'

// 查询机构列表
export function listOrg(query) {
  return request({
    url: '/pawnOrg/list',
    method: 'get',
    params: query
  })
}

export function pawnMsg(query) {
  return request({
    url: '/pawnOrg/pawnMsg',
    method: 'get',
    params: query
  })
}


// 查询机构详细
export function getOrg(id) {
  return request({
    url: '/pawnOrg/findById?id=' + id,
    method: 'get'
  })
}

// 新增机构
export function addOrg(data) {
  return request({
    url: '/pawnOrg/save',
    params: data
  })
}

// 修改机构
export function updateOrg(data) {
  return request({
    url: '/pawnOrg/save',
    params: data
  })
}

export function changePwds(data) {
  return request({
    url: '/pawnOrg/changePwds',
    params: data
  })
}

export function changeState(data) {
  return request({
    url: '/pawnOrg/changeState',
    params: data
  })
}

export function saveService(data) {
  return request({
    url: '/pawnOrg/saveService',
    params: data
  })
}


// 删除机构
export function delOrg(id) {
  return request({
    url: '/pawnOrg/del?id=' + id,
    method: 'delete'
  })
}

// 导出机构
export function exportOrg(query) {
  return request({
    url: '/org/org/export',
    method: 'get',
    params: query
  })
}