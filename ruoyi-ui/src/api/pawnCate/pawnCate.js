import request from '@/utils/request'

// 查询藏品类别列表
export function listCate(query) {
  return request({
    url: '/pawnCate/list',
    method: 'get',
    params: query
  })
}

// 查询藏品类别详细
export function getCate(id) {
  return request({
    url: '/pawnCate/findById?id=' + id,
    method: 'get'
  })
}

// 新增藏品类别
export function addCate(data) {
  return request({
    url: '/pawnCate/save',
    params: data
  })
}

// 修改藏品类别
export function updateCate(data) {
  return request({
    url: '/pawnCate/save',
    params: data
  })
}

// 删除藏品类别
export function delCate(id) {
  return request({
    url: '/pawnCate/del?id=' + id,
    method: 'delete'
  })
}

