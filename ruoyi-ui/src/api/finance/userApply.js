import request from '@/utils/request'

// 查询机构提现申请列表
export function listApply(query) {
  return request({
    url: '/userWithdrawApply/list',
    method: 'get',
    params: query
  })
}

// 查询机构提现申请详细
export function getApply(id) {
  return request({
    url: '/userWithdrawApply/findById?id=' + id,
    method: 'get'
  })
}

// 新增机构提现申请
export function addApply(data) {
  return request({
    url: '/userWithdrawApply/save',
    method: 'post',
    params: data
  })
}

// 修改机构提现申请
export function updateApply(data) {
  return request({
    url: '/userWithdrawApply/save',
    method: 'put',
    params: data
  })
}

// 删除机构提现申请
export function delApply(id) {
  return request({
    url: '/userWithdrawApply/del?id=' + id,
    method: 'delete'
  })
}

// 导出机构提现申请
export function exportApply(query) {
  return request({
    url: '/userWithdrawApply/export',
    method: 'get',
    params: query
  })
}


export function changeState(params) {
  return request({
    url: '/userWithdrawApply/changeState',
    method: 'get',
    params: params
  })
}