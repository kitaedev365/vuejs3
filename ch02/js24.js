function checkAge(age) {
    if (age < 18) {
       throw new Error("나이가 너무 적습니다.");
    }
    return "입장이 허용되었습니다.";
}
try {
    console.log(checkAge(15));
} catch (error) {
    console.log("오류:", error.message);
}
// 출력: 오류: 나이가 너무 적습니다.    