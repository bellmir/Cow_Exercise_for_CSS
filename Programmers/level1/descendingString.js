function solution(s) {
    return s.split('').sort((a,b)=>a>b? -1:1).join('');
}
/*
function solution(s) {
  return s
    .split("")
    .sort()
    .reverse()
    .join("");
}
*/
console.log(solution("Zbcdefg"));