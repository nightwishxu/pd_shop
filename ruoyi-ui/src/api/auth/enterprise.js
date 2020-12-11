import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listEnterprise(query) {
  return request({
    url: '/authEnterprise/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getEnterprise(id) {
  return request({
    url: '/authEnterprise/findById?id=' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addEnterprise(data) {
  return request({
    url: '/authEnterprise/save',
    method: 'post',
    params: data
  })
}

// 修改【请填写功能名称】
export function updateEnterprise(data) {
  return request({
    url: '/authEnterprise/save',
    method: 'put',
    params: data
  })
}

// 删除【请填写功能名称】
export function delEnterprise(id) {
  return request({
    url: '/authEnterprise/del?id=' + id,
  })
}

// 导出【请填写功能名称】
export function exportEnterprise(query) {
  return request({
    url: '/authEnterprise/export',
    method: 'get',
    params: query
  })
}


export function chargeState(data) {
  return request({
    url: '/authEnterprise/changeState',
    params: data
  })
}
