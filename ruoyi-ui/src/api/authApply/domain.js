import request from '@/utils/request'

// 查询专业领域技能列表
export function listDomain(query) {
  return request({
    url: '/domain/domainList',
    method: 'get',
    params: query
  })
}

export function domainList(query) {
  return request({
    url: '/domain/list',
    method: 'get',
    params: query
  })
}

// 查询专业领域技能详细
export function getDomain(id) {
  return request({
    url: '/experterType/domain/' + id,
    method: 'get'
  })
}

// 新增专业领域技能
export function addDomain(data) {
  return request({
    url: '/domain/save',
    params: data
  })
}

// 修改专业领域技能
export function updateDomain(data) {
  return request({
    url: '/experterType/domain',
    method: 'put',
    data: data
  })
}

// 删除专业领域技能
export function delDomain(id) {
  return request({
    url: '/experterType/domain/' + id,
    method: 'delete'
  })
}

// 导出专业领域技能
export function exportDomain(query) {
  return request({
    url: '/experterType/domain/export',
    method: 'get',
    params: query
  })
}