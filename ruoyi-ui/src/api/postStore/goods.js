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

//鉴定者鉴定
export function authAdminVerify(data) {
  return request({
    url: '/userGoods/authAdminVerify',
    params: data
  })
}

//鉴定
export function beginToOper(data) {
  return request({
    url: '/userGoods/beginToOper',
    params: data
  })
}


export function checkDetail(data) {
  return request({
    url: '/userGoods/checkDetail',
    params: data
  })
}


export function goodsByOrgList(query) {
  return request({
    url: '/userGoods/goodsByOrgList',
    method: 'get',
    params: query
  })
}

export function paltBackToOrg(data) {
  return request({
    url: '/userGoods/paltBackToOrg',
    params: data
  })
}


export function saveByBackToUser(data) {
  return request({
    url: '/userGoods/saveByBackToUser',
    params: data
  })
}