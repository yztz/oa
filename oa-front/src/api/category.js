import request from '@/utils/request'

export function query(params) {
  return request({
    url: '/category/query',
    method: 'get',
    params
  })
}

export function queryAll() {
  return request({
    url: '/category/queryall',
    method: 'get',
  })
}

export function update(data) {
  return request({
    url: '/category/update',
    method: 'post',
    data
  })
}

export function add(data) {
  return request({
    url: '/category/add',
    method: 'post',
    data
  })
}

export function deleteCategories(params) {
  return request({
    url: '/category/delete',
    method: 'get',
    params
  })
}

// export function add(data) {
//   return request({
//     url: '/category/add',
//     method: 'post',
//     data
//   })
// }
