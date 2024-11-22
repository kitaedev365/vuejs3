let age = 18;
if (age >= 18) {
    console.log('Adult');
} else if (age < 0 || typeof age !== 'number'){
    console.log('Error');
} else {
    console.log('Minor');
}