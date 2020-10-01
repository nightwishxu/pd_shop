import request from '@/utils/request'

// 查询优惠券列表
export function listCoupon(query) {
  return request({
    url: '/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券详细
export function getCoupon(id) {
  return request({
    url: '/coupon/findById?id=' + id,
    method: 'get'
  })
}

// 新增优惠券
export function addCoupon(data) {
  return request({
    url: '/coupon/save',
    params: data
  })
}

// 修改优惠券
export function updateCoupon(data) {
  return request({
    url: '/coupon/save',
    params: data
  })
}

// 删除优惠券
export function delCoupon(id) {
  return request({
    url: '/coupon/del?id=' + id,
    method: 'delete'
  })
}

// 导出优惠券
export function exportCoupon(query) {
  return request({
    url: '/coupon/export',
    method: 'get',
    params: query
  })
}