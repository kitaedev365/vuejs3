const myPromise = new Promise((resolve, reject) => {
const success = true;
    if (success) {
        resolve("The operation was successful!");
    } else {
        reject("The operation failed.");
    }
});
myPromise
    .then(result => {
    console.log(result);  // 출력: The operation was successful!
    })
    .catch(error => {
    console.error(error);  // 만약 실패했다면 출력
    });
    