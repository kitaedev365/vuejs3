const person = {
  name: "Kitai",
  age: 25
};
const handler = {
  set: (target, property, value) => {
    if (property === "age" && typeof value !== "number") {
      throw new TypeError("Age must be a number");
    }
    target[property] = value;
    return true;
  }
};
const proxyPerson = new Proxy(person, handler);
proxyPerson.age = 30;  // 정상 동작
console.log(proxyPerson.age);  // 출력: 30
// proxyPerson.age = "thirty";  // 오류: Age must be a number