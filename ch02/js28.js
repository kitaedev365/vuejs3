function add(a, b) {  //기본 함수 선언
    return a + b;
}
console.log(add(2, 3));  // 출력: 5

const multiply = function(a, b) {  //익명 함수 선언
    return a * b;
};
console.log(multiply(2, 3));  // 출력: 6
   
const subtract = (a, b) => a - b;  //화살표 함수
console.log(subtract(5, 3));  // 출력: 2

function greet(name = 'Guest') {   //기본 매개변수
    console.log(`Hello, ${name}!`);
}
greet();  // 출력: Hello, Guest!
greet('Kitai');  // 출력: Hello, Kitai!

function sum(...numbers) {   //나머지 매개변수
    return numbers.reduce((total, num) => total + num, 0);
}
console.log(sum(1, 2, 3, 4));  // 출력: 10
    
function processUserInput(callback) {   //콜백 함수
    const name = 'Kitai';
    callback(name);
}
processUserInput(function(name) {
    console.log(`Hello, ${name}`);
});  // 출력: Hello, Kitai    