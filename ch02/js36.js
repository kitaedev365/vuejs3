const person = {	//전통적인 함수의 this
  name: 'Kitai',
  sayName: function() {
    console.log(this.name);
  }
};
person.sayName();  // 출력: Kitai

const user = {	//외부 this 참조
  name: 'Kitai',
  sayName: function() {
    const printName = () => {
      console.log(this.name);  // 화살표 함수는 외부 스코프의 this를 사용
    };
    printName();
  }
};
user.sayName();  // 출력: Kitai

const member = {	//전통 함수와 화살표 함수 비교
  name: 'Kitai',
  sayName: function() {
    // 전통 함수: this는 동적으로 결정
    setTimeout(function() {
      console.log('Traditional function:', this.name);  // undefined
    }, 100);

    // 화살표 함수: this는 외부의 this를 참조
    setTimeout(() => {
      console.log('Arrow function:', this.name);  // Kitai
    }, 200);
  }
};
member.sayName();