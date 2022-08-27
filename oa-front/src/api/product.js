import request from '@/utils/request'

export function query(params) {
  return request({
    url: '/news/query',
    method: 'get',
    params
  })
}

export function add(data) {
  return request({
    url: '/news/add',
    method: 'post',
    data
  })
}


export function update(data) {
  return request({
    url: '/news/update',
    method: 'post',
    data
  })
}

export function deleteProducts(params) {
  return request({
    url: '/news/delete',
    method: 'get',
    params
  })
}
