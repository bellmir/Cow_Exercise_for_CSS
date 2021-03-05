
function solution(s) {
    if(s.length%2===1)  //홀수
        return s.split('')[parseInt(s.length/2)];
    else        //짝수
        return s.slice(parseInt(s.length/2)-1,parseInt(s.length/2)+1);
}
/*
function solution(s) {
    return s.substr(Math.ceil(s.length / 2) - 1, s.length % 2 === 0 ? 2 : 1);
}
*/

console.log(solution("qwer"));