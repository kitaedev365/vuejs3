try {
    let result = 10 / 0;  // 의도적으로 오류를 발생시키는 경우도 포함
    console.log(result);
} catch (error) {
    console.log("오류 발생:", error.message);
}
// 출력: 오류 발생: Infinity (0으로 나누기 에러 발생)    