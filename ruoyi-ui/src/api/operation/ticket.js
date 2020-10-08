import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listTicket(query) {
  return request({
    url: '/userPawn/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getTicket(id) {
  return request({
    url: '/userPawn/findById?id=' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addTicket(data) {
  return request({
    url: '/userPawn/save',
    params: data
  })
}

// 修改【请填写功能名称】
export function updateTicket(data) {
  return request({
    url: '/userPawn/save',
    params: data
  })
}

// 删除【请填写功能名称】
export function delTicket(id) {
  return request({
    url: '/userPawn/del?id=' + id,
    method: 'delete'
  })
}


export function baoxiangGet(query) {
  return request({
    url: '/userPawn/baoxiangGet',
    method: 'get',
    params: query
  })
}
