import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listPersonal(query) {
  return request({
    url: '/authPersonal/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getPersonal(id) {
  return request({
    url: '/authPersonal/findById?id=' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addPersonal(data) {
  return request({
    url: '/authPersonal/save',
    method: 'post',
    params: data
  })
}

// 修改【请填写功能名称】
export function updatePersonal(data) {
  return request({
    url: '/authPersonal/save',
    method: 'put',
    params: data
  })
}

// 删除【请填写功能名称】
export function delPersonal(id) {
  return request({
    url: '/authPersonal/del?id=' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportPersonal(query) {
  return request({
    url: '/authPersonal/export',
    method: 'get',
    params: query
  })
}


export function changeState(data) {
  return request({
    url: '/authPersonal/changeState',
    params: data
  })
}
