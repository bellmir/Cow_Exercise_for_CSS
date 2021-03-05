function solution(x) {
    let sumOfDigit = 0;
    x.toString().split('').forEach(digit => sumOfDigit+=Number(digit));
    return x%sumOfDigit===0 ? true : false;
}