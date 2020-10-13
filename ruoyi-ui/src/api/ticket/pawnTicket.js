import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listTicket(query) {
  return request({
    url: '/pawnTicket/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getTicket(id) {
  return request({
    url: '/pawnTicket/findById?id=' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addTicket(data) {
  return request({
    url: '/pawnTicket/save',
    params: data
  })
}

// 修改【请填写功能名称】
export function updateTicket(data) {
  return request({
    url: '/pawnTicket/save',
    method: 'put',
    params: data
  })
}

// 删除【请填写功能名称】
export function delTicket(id) {
  return request({
    url: '/pawnTicket/del?id=' + id,
  })
}


// 查看合同
export function showContract(pawnTicketId) {
  return request({
    url: '/pawnTicket/showContract?pawnTicketId=' + pawnTicketId,
    method: 'get'
  })
}

export function findOrg() {
  return request({
    url: '/pawnTicket/findOrg',
  })
}