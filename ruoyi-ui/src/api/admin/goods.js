import request from '@/utils/request'

// 查询商品列表
export function listGoods(query) {
  return request({
    url: '/goods/list',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getGoods(id) {
  return request({
    url: '/goods/' + id,
    method: 'get'
  })
}

// 新增商品
export function addGoods(data) {
  return request({
    url: '/goods/save',
    method: 'post',
    data: data
  })
}

// 修改商品
export function updateGoods(data) {
  return request({
    url: '/goods/save',
    method: 'post',
    data: data
  })
}

// 删除商品
export function delGoods(id) {
  return request({
    url: '/goods/del?id=' + id,
  })
}

// 导出商品
export function exportGoods(query) {
  return request({
    url: '/goods/export',
    method: 'get',
    params: query
  })
}

export function changeState(data) {
  return request({
    url: '/goods/changeState',
    params: data
  })
}


export function getAttribute(cateCode){
  return request({
    url: '/goodsAttribute/list?cateCode='+cateCode,
  })
}


