import request from '@/utils/request'

// 查询证书列表
export function listCertificate(query) {
  return request({
    url: '/certificate/list',
    method: 'get',
    params: query
  })
}

// 查询证书详细
export function getCertificate(id) {
  return request({
    url: '/certificate/findById?id=' + id,
    method: 'get'
  })
}


export function getByUserGoodsId(userGoodsId) {
  return request({
    url: '/certificate/getByUserGoodsId?userGoodsId=' + userGoodsId,
    method: 'get'
  })
}
export function checkDetail(id) {
  return request({
    url: '/certificate/checkDetail?id=' + id,
    method: 'get'
  })
}

// 新增证书
export function addCertificate(data) {
  return request({
    url: '/certificate/save',
    params: data
  })
}

// 修改证书
export function updateCertificate(data) {
  return request({
    url: '/certificate/save',
    params: data
  })
}

// 删除证书
export function delCertificate(id) {
  return request({
    url: '/certificate/del?=' + id,
  })
}

// 导出证书
export function exportCertificate(query) {
  return request({
    url: '/certificate/export',
    method: 'get',
    params: query
  })
}




export function saveCertificateLog(data) {
  return request({
    url: '/certificateLog/save',
    params: data
  })
}
