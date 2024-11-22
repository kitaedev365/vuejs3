const add = (a, b) => {   //기본적인 화살표 함수
  return a + b;
};
console.log(add(2, 3));  // 출력: 5

const multiply = (a, b) => a * b;	//본문이 한 줄인 경우 중괄호와 return 생략 가능
console.log(multiply(2, 3));  // 출력: 6

const square = x => x * x;	//매개변수가 하나인 경우 괄호 생략 가능
console.log(square(4));  // 출력: 16

const sayHello = () => console.log('Hello!');	//매개변수가 없는 경우 괄호 필수
sayHello();  // 출력: Hello!