function solution(n) {
    let answer=[];
    for (let i = 1; i <= n; i++) {
        answer.push(i%2===0 ? '박' : '수');
    }
    return answer.join('');
}