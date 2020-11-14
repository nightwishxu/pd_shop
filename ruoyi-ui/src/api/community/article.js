import request from '@/utils/request'

// 查询动态列表
export function listArticle(query) {
  return request({
    url: '/article/list',
    method: 'get',
    params: query
  })
}

// 查询动态详细
export function getArticle(id) {
  return request({
    url: '/article/findById?id=' + id,
    method: 'get'
  })
}

// 新增动态
export function addArticle(data) {
  return request({
    url: '/article/save',
    params: data
  })
}

// 修改动态
export function updateArticle(data) {
  return request({
    url: '/article/save',
    method: 'put',
    params: data
  })
}

// 删除动态
export function delArticle(id) {
  return request({
    url: '/article/del?id=' + id,
    method: 'delete'
  })
}

// 导出动态
export function exportArticle(query) {
  return request({
    url: '/article/export',
    method: 'get',
    params: query
  })
}
