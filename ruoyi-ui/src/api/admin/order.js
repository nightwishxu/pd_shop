import request from '@/utils/request'

// 查询订单列表
export function listOrder(query) {
  return request({
    url: '/order/orderAdminList',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getOrder(id) {
  return request({
    url: '/order/findById?id=' + id,
    method: 'get'
  })
}

// 新增订单
export function addOrder(data) {
  return request({
    url: '/admin/order',
    method: 'post',
    data: data
  })
}
// 发货
export function updateState(data) {
  return request({
    url: '/order/updateState',
    params: data
  })
}
//新品商场订单同意退款 --发送退款信息  or 拒绝退款  --发送拒绝信息
export function sendRefData(data) {
  return request({
    url: '/order/sendRefData',
    params: data
  })
}

// 修改订单
export function updateOrder(data) {
  return request({
    url: '/order/save',
    params: data
  })
}


// 删除订单
export function delOrder(id) {
  return request({
    url: '/order/del?id=' + id,
    method: 'delete'
  })
}

// 导出订单
export function exportOrder(query) {
  return request({
    url: '/admin/order/export',
    method: 'get',
    params: query
  })
}
