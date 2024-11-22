const arr1 = [1, 2, 3];
const arr2 = [...arr1, 4, 5, 6];
console.log(arr2);  // 출력: [1, 2, 3, 4, 5, 6]

const obj1 = { name: "Kitai", age: 25 };
const obj2 = { ...obj1, job: "developer" };
console.log(obj2);  // 출력: { name: 'Kitai', age: 25, job: 'developer' }

const numbers = [1, 2, 3];
const add = (a, b, c) => a + b + c;
console.log(add(...numbers));  // 출력: 6