import { AxiosApi } from '../api/AxiosApi';

export const productService = {
    getProducts,
    getPopularsProducts,
    getUserProducts,
    createProduct,
    editProduct,
    deleteProduct,
    getProduct,
    getPublicProduct,
    addProductView
};

const getReqOpt = {
    method: 'GET',
};

const postReqOpt = {
    method: 'POST',
};

const putReqOpt = {
    method: 'PUT',
};

const delReqOpt = {
    method: 'DELETE',
}

function authHeader() {
    // return authorization header with jwt token
    let token = localStorage.getItem('token');
    if (token) {
        return { 'Content-Type': 'application/json', 'Authorization': 'Bearer ' + token };
    } else {
        return { 'Content-Type': 'application/json' };
    }
}

async function getPopularsProducts() {
    const products = await new AxiosApi().fetch({ url: '/populars-products', options: getReqOpt })
    return products
}

async function getProducts() {
    getReqOpt.headers = authHeader()
    const products = await new AxiosApi().fetch({ url: '/products', options: getReqOpt })
    return products
}

async function getUserProducts() {
    getReqOpt.headers = authHeader()
    const products = await new AxiosApi().fetch({ url: '/user-products', options: getReqOpt })
    return products
}

async function createProduct(product){
    postReqOpt.headers = { 'Content-Type': 'multipart/form-data', 'Authorization': 'Bearer ' + localStorage.getItem('token') } 
    const response = await new AxiosApi().post({ url: '/products', body: product, options: postReqOpt })
    return response
}

async function editProduct(product){
    putReqOpt.headers = authHeader()
    const response = await new AxiosApi().put({ url: '/products', body: product, options: putReqOpt })
    return response
}

async function getProduct(id){
    getReqOpt.headers = authHeader()
    const response = await new AxiosApi().fetch({ url: `/products/${id}`, options: getReqOpt })
    return response
}

async function getPublicProduct(id){
    const response = await new AxiosApi().fetch({ url: `/products-public/${id}`, options: { method: 'GET', 'Content-Type': 'application/json' }})
    return response
}

async function addProductView(id){
    const response = await new AxiosApi().fetch({ url: `/products/${id}/view`, options: getReqOpt })
    return response
}

async function deleteProduct(id){
    delReqOpt.headers = authHeader()
    const response = await new AxiosApi().delete({ url: `/products/${id}`, options: delReqOpt })
    return response
}

