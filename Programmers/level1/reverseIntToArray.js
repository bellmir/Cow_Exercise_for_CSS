function solution(n) {
    return String(n).split('').reverse().map(i=>Number(i));
}

console.log(solution(12345))