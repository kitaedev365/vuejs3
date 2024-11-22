const numbers = [1, 2, 3];   //배열 구조분해 할당
const [a, b, c] = numbers;
console.log(a);  // 출력: 1
console.log(b);  // 출력: 2
console.log(c);  // 출력: 3

const person = {   //객체 구조분해 할당
    name: 'Kitai',
    age: 25,
    city: 'Seoul'
};
const { name, age, city } = person;
console.log(name);  // 출력: Kitai
console.log(age);   // 출력: 25
console.log(city);  // 출력: Seoul

const data = {   //기본값을 사용한 구조분해 할당
    id: 'Kitai',
    past: 25
};
const { id, past, addr = 'Unknown' } = data;
console.log(id);  // 출력: Kitai
console.log(past);   // 출력: 25
console.log(addr);  // 출력: Unknown

const user = {    //중첩 객체 구조분해 할당
    nick: 'Kitai',
    address: {
        place: 'Seoul',
        country: 'Korea'
    }
};
const { nick, address: { place, country } } = user;
console.log(nick);    // 출력: Kitai
console.log(city);    // 출력: Seoul
console.log(country); // 출력: Korea