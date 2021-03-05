function solution(arr, divisor) {
    var answer = arr.filter(e=>e%divisor===0);
    return answer.length===0 ? [-1] : answer.sort((a,b)=>a-b)
}
/*
function solution(arr, divisor) {
    var answer = [];
    for(let e of arr){
        if(e%divisor===0){
            answer.push(e);
        }
    }
    if(answer.length===0)
        answer.push(-1);
    answer.sort((a,b)=>a-b);
    return answer;
}
*/
console.log(solution([5, 9, 7, 10] ,5 ));