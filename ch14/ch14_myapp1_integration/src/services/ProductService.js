import axios from 'axios'
const API_URL = 'http://localhost:8084/api/products'  // 백엔드 제품 API URL
export default {
  fetchProducts() {
    return axios.get(API_URL).then(response => response.data)
  },
  getProductById(id) {
    return axios.get(`${API_URL}/${id}`).then(response => response.data)
  },
  createProduct(product) {
    const formData = new FormData();
  
    // JSON 데이터를 FormData에 추가
    formData.append('product', new Blob([JSON.stringify(product)], { type: 'application/json' }));
  
    // 이미지 파일 추가
    if (product.img) {
      formData.append('img', product.img);
    }
  
    return axios.post(`${API_URL}/add`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });
  },
}