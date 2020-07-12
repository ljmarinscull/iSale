import axios from 'axios'
import { BaseApi } from '@/api/BaseApi'

export class AxiosApi extends BaseApi {

  constructor() {
    super()
  }

  async fetch({ url, options }) {
    const { data } = await axios.get(`${this.baseUrl}${url}`, options)
    return data
  }

  async post({ url, body, options }) {
    const { data } = await axios.post(`${this.baseUrl}${url}`, body, options)
    return data
  }

  async put({ url, body, options }) {
    const { data } = await axios.put(`${this.baseUrl}${url}`, body, options)
    return data
  }

  async patch({ url, body, options }) {
    const { data } = await axios.patch(`${this.baseUrl}${url}`, body, options)
    return data
  }

  async delete({ url, options }) {
    const { data } = await axios.delete(`${this.baseUrl}${url}`, options)
    return data
  }

}