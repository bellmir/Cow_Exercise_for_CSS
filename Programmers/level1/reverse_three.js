/*
function solution(n) {
    let base3 = n.toString(3).split('');
    let answer=0;
    for(let i=0;i<base3.length;i++){
        answer+=(base3[i]*(3**i));
    }
    return answer;
}
*/

function solution(n) {
    return parseInt(n.toString(3).split('').reverse().join(''),3);
}

console.log(solution(125));