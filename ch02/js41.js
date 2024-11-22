const fetchData = () => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve("Data received!");
    }, 2000);
  });
};
fetchData().then(result => {
  console.log(result);  // 2초 후 출력: Data received!
});