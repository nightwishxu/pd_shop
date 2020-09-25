import request from '@/utils/request'

//寄拍商品列表
export function listGoods(query) {
  return request({
    url: '/userGoods/sellList',
    method: 'get',
    params: query
  })
}

// 查询商品详细
export function getGoods(id) {
  return request({
    url: '/userGoods/findById?id=' + id,
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
    url: '/userGoods/save',
    method: 'post',
    params: data
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

export function changeCheck(data) {
  return request({
    url: '/userGoods/changeCheck',
    params: data
  })
}

export function settle(data) {
  return request({
    url: 'userGoods/settle',
    params: data
  })
}


//鉴定列表
export function goodsList(query) {
  return request({
    url: '/userGoods/list',
    method: 'get',
    params: query
  })
}
