
export class BaseApi {

  constructor() {
    this.baseUrl = 'http://localhost:3000/api/v1'
  }

  async fetch(url) {
    const response = await fetch(`${this.baseUrl}${url}`)
    return await response.json()
  }
}