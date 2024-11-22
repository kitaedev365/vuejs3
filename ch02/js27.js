const today = new Date();
console.log(today);  // 현재 날짜와 시간 출력
const specificDate = new Date('2023-12-25');
console.log(specificDate);  // 특정 날짜 출력

console.log(Math.PI);  // 출력: 3.141592653589793
console.log(Math.max(1, 5, 10));  // 출력: 10
console.log(Math.random());  // 0과 1 사이의 임의의 수 출력

const fruits = ['apple', 'banana', 'cherry'];
console.log(fruits.length);  // 출력: 3
fruits.push('grape');  // 배열에 요소 추가
console.log(fruits);  // 출력: ['apple', 'banana', 'cherry', 'grape']

const text = 'Hello, World!';
console.log(text.length);  // 출력: 13
console.log(text.toUpperCase());  // 출력: HELLO, WORLD!
console.log(text.includes('World'));  // 출력: true

let promise = new Promise((resolve, reject) => {
    let success = true;
    if (success) {
        resolve('작업 성공');
    } else {
        reject('작업 실패');
    }
});
promise.then((message) => {
    console.log(message);  // 성공 시 실행
}).catch((error) => {
    console.log(error);  // 실패 시 실행
});