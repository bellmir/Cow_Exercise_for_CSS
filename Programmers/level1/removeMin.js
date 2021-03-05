function solution(arr) {
    //const min = Math.min(...arr);
    const min = arr.reduce((prev,cur)=>Math.min(prev,cur));
    return arr.length===1 ? [-1] : arr.filter(e=>e>min);
}
console.log(solution([4,3,2,1]));
//좀 느림