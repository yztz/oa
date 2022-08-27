import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get',
    // params: { token }
  })
}

// export function logout() {
//   return request({
//     url: '/vue-admin-template/user/logout',
//     method: 'post'
//   })
// }

export function query(params) {
  return request({
    url: '/user/query',
    method: 'get',
    params
  })

}

export function deleteUsers(params) {
  return request({
    url: '/user/delete',
    method: 'get',
    params
  })
}

export function update(data) {
  return request({
    url: '/user/update',
    method: 'post',
    data
  })
}

export function add(data) {
  return request({
    url: '/user/add',
    method: 'post',
    data
  })
}
