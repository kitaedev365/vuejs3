const target = {
  message: "Hello, world!"
};
const handler = {
  get: (target, property) => {
    if (property in target) {
      return target[property];
    } else {
      return "Property does not exist";
    }
  }
};
const proxy = new Proxy(target, handler);
console.log(proxy.message);  // 출력: Hello, world!
console.log(proxy.nonExistentProperty);  // 출력: Property does not exist