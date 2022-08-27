import request from '@/utils/request'

export function upload(file) {
  return request({
    url: '/file/upload',
    method: 'post',
    data: file,
    headers: { 'Content-Type': 'multipart/form-data' }
  })
}

export function getPicture(fileName) {
  return request({
    url: '/file/fetch',
    method: 'get',
    params: {picname: fileName}
  })
}

export function getPictureURL(fileName) {
  return process.env.VUE_APP_BASE_API + '/file/fetch/' + fileName
}


export function getUploadUrl() {
  return process.env.VUE_APP_BASE_API + '/file/upload'
}
