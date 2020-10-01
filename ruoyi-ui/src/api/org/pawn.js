import request from '@/utils/request'

// 查询典当列表
export function listPawn(query) {
  return request({
    url: '/system/pawn/list',
    method: 'get',
    params: query
  })
}

export function pawnRecord(query) {
  return request({
    url: '/userPawn/pawnRecord',
    method: 'get',
    params: query
  })
}

export function pawnLogSearch(query) {
  return request({
    url: '/pawnLog/search',
    method: 'get',
    params: query
  })
}

// 查询典当详细
export function getPawn(id) {
  return request({
    url: '/system/pawn/' + id,
    method: 'get'
  })
}

// 新增典当
export function addPawn(data) {
  return request({
    url: '/system/pawn',
    method: 'post',
    data: data
  })
}

// 修改典当
export function updatePawn(data) {
  return request({
    url: '/system/pawn',
    method: 'put',
    data: data
  })
}

// 删除典当
export function delPawn(id) {
  return request({
    url: '/system/pawn/' + id,
    method: 'delete'
  })
}

// 导出典当
export function exportPawn(query) {
  return request({
    url: '/system/pawn/export',
    method: 'get',
    params: query
  })
}