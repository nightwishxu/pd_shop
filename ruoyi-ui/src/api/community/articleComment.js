import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listComment(query) {
  return request({
    url: '/articleComment/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getComment(id) {
  return request({
    url: '/articleComment/findById&id=' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addComment(data) {
  return request({
    url: '/articleComment/save',
    method: 'post',
    params: data
  })
}

// 修改【请填写功能名称】
export function updateComment(data) {
  return request({
    url: '/articleComment/save',
    method: 'put',
    params: data
  })
}

// 删除【请填写功能名称】
export function delComment(id) {
  return request({
    url: '/articleComment/del?id=' + id,
    method: 'delete'
  })
}

// 导出【请填写功能名称】
export function exportComment(query) {
  return request({
    url: '/articleComment/export',
    method: 'get',
    params: query
  })
}