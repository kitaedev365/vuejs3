const name = 'Kitai';		
const greeting1 = 'Hello, ' + name + '!';	//기존 방식에서는 문자열과 변수를 연결
console.log(greeting1);  // 출력: Hello, Kitai!

const greeting2 = `Hello, ${name}!`;		//템플릿 리터럴 사용
console.log(greeting2);  // 출력: Hello, Kitai!

const message = `
  This is a multiline
  string using template literals.
`;	//여러 줄 문자열
console.log(message);

const a = 5;		//표현식 삽입
const b = 10;
console.log(`The sum of ${a} and ${b} is ${a + b}.`);  // 출력: The sum of 5 and 10 is 15