function greet(name = 'Guest') {   //기본 파라미터
    console.log(`Hello, ${name}!`);
}
greet();          // 출력: Hello, Guest!
greet('Kitai');   // 출력: Hello, Kitai!

function sum(...numbers) {   //가변 파라미터
    return numbers.reduce((total, num) => total + num, 0);
}
console.log(sum(1, 2, 3));  // 출력: 6
console.log(sum(4, 5, 6, 7, 8));  // 출력: 30    