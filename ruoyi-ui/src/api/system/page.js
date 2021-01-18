import request from '@/utils/request'

// 查询单页内容列表
export function listPage(query) {
  return request({
    url: '/sp/list',
    method: 'get',
    params: query
  })
}

// 查询单页内容详细
export function getPage(code) {
  return request({
    url: '/sp/findByCode?code=' + code,
    method: 'get'
  })
}

// 新增单页内容
export function addPage(data) {
  return request({
    url: '/sp/save',
    method: 'post',
    params: data
  })
}

// 修改单页内容
export function updatePage(data) {
  return request({
    url: '/sp/save',
    method: 'post',
    params: data
  })
}

// 删除单页内容
export function delPage(code) {
  return request({
    url: '/sp/' + code,
    method: 'delete'
  })
}

// 导出单页内容
export function exportPage(query) {
  return request({
    url: '/sp/export',
    method: 'get',
    params: query
  })
}