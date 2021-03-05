function solution(n, arr1, arr2) {
    let answer = new Array(n).fill('');
    answer = answer.map((e,i)=>(arr1[i]|arr2[i]).toString(2));
    return answer.map(e=>{
        for (let i = 0; i < e.length; i++) {
            e=e.replace('1', '#').replace('0', ' ')
        }
        return e.length===n ? e : ' '.repeat(n-e.length)+ e;
    });
}
console.log(solution(5,[9, 20, 28, 18, 11],[30, 1, 21, 17, 28]));