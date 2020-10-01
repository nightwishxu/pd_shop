import request from '@/utils/request'

// 查询专家列表
export function listExperter(query) {
  return request({
    url: '/experter/list',
    method: 'get',
    params: query
  })
}


export function experterList(query) {
  return request({
    url: '/experter/experterList',
    method: 'get',
    params: query
  })
}


// 查询专家详细
export function getExperter(id) {
  return request({
    url: '/experter/findById?id=' + id,
    method: 'get'
  })
}

// 新增专家
export function addExperter(data) {
  return request({
    url: '/experter/save',
    params: data
  })
}

// 修改专家
export function updateExperter(data) {
  return request({
    url: '/experter',
    method: 'put',
    data: data
  })
}

// 删除专家
export function delExperter(id) {
  return request({
    url: '/experter/' + id,
    method: 'delete'
  })
}

// 导出专家
export function exportExperter(query) {
  return request({
    url: '/experter/export',
    method: 'get',
    params: query
  })
}