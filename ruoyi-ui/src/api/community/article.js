import request from '@/utils/request'

// 查询动态列表
export function listArticle(query) {
  return request({
    url: '/community/article/list',
    method: 'get',
    params: query
  })
}

// 查询动态详细
export function getArticle(id) {
  return request({
    url: '/community/article/' + id,
    method: 'get'
  })
}

// 新增动态
export function addArticle(data) {
  return request({
    url: '/community/article',
    method: 'post',
    data: data
  })
}

// 修改动态
export function updateArticle(data) {
  return request({
    url: '/community/article',
    method: 'put',
    data: data
  })
}

// 删除动态
export function delArticle(id) {
  return request({
    url: '/community/article/' + id,
    method: 'delete'
  })
}

// 导出动态
export function exportArticle(query) {
  return request({
    url: '/community/article/export',
    method: 'get',
    params: query
  })
}