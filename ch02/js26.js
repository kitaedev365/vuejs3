let person = {
    name: 'Kitai',
    age: 25,
    greet: function() {
    console.log(`Hello, my name is ${this.name}`);
    }
};
person.greet(); // 'Hello, my name is Kitai'    