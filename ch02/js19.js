for (let i = 0; i < 5; i++) {
     console.log(i); // 0, 1, 2, 3, 4
}
let array = [10, 20, 30]; 
for (let value of array) { 
    console.log(value); 
}
let person = { name: 'Kitai', age: 25 };
for (let key in person) {
     console.log(`${key}: ${person[key]}`);
}    