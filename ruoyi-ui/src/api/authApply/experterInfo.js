import request from '@/utils/request'

// 查询专家意见日志记录列表
export function listInfo(query) {
  return request({
    url: '/experterInfo/list',
    method: 'get',
    params: query
  })
}

// 查询专家意见日志记录详细
export function getInfo(id) {
  return request({
    url: '/experterInfo/' + id,
    method: 'get'
  })
}

// 新增专家意见日志记录
export function addInfo(data) {
  return request({
    url: '/experterInfo',
    method: 'post',
    data: data
  })
}

// 修改专家意见日志记录
export function updateInfo(data) {
  return request({
    url: '/experterInfo/save',
    params: data
  })
}

export function saveInfo(data) {
  return request({
    url: '/experterInfo/saveInfo',
    params: data
  })
}


// 删除专家意见日志记录
export function delInfo(id) {
  return request({
    url: '/experterInfo/' + id,
    method: 'delete'
  })
}

// 导出专家意见日志记录
export function exportInfo(query) {
  return request({
    url: '/experterInfo/export',
    method: 'get',
    params: query
  })
}