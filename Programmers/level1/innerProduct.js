function solution(a, b) {
    let answer = 0;
    for(let i=0;i<a.length;i++){
        answer+=a[i]*b[i];
    }
    return answer;
}

//a와 b의 내적은 a[0]*b[0] + a[1]*b[1] + ... + a[n-1]*b[n-1]