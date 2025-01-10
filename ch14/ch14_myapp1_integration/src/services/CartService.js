import axios from 'axios'

const CartService = {
  async addToCart(product) {
    // 현재 로그인된 사용자 정보 (여기서는 localStorage에 저장된 username을 사용한다고 가정)
    const username = localStorage.getItem('username');
    // 서버로 보내는 데이터 수정: UserDto와 ProductDto를 올바르게 포함시킴
    try {
      console.log("로그인한 사용자 : "+username)
      console.log("선택된 상품"+product.id)
      const token = localStorage.getItem('user'); // 토큰 가져오기
      const response = await axios.post('http://localhost:8084/api/carts/add', {
        username: username, 
        id: product.id,
      }, {
        headers: {
            Authorization: `Bearer ${token}`, // 토큰 포함
            'Content-Type': 'application/json',
       }
    });

      return response.data;
    } catch (error) {
      console.error('Error adding product to cart:', error);
      throw new Error('장바구니에 상품을 추가하는데 실패했습니다.');
    }
  }
};

export default CartService;