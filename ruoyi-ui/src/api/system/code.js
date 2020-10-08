import request from '@/utils/request'

// 查询基础参数列表
export function listCode(query) {
  return request({
    url: '/code/list',
    method: 'get',
    params: query
  })
}

// 查询基础参数详细
export function getCode(code) {
  return request({
    url: '/code/findById?code=' + code,
    method: 'get'
  })
}

// 新增基础参数
export function addCode(data) {
  return request({
    url: '/code/save',
    params: data
  })
}

// 修改基础参数
export function updateCode(data) {
  return request({
    url: '/code/save',
    params: data
  })
}

// 删除基础参数
export function delCode(code) {
  return request({
    url: '/code/del?code=' + code,
  })
}

