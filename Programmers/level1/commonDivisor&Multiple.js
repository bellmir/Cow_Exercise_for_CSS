function solution(n, m) {
    let min = Math.min(n,m);
    let max = Math.max(n,m);

    if(max%min===0) return [min, max];

    let divisorMin=[];
    let divisorMax=[];

    for(let i=1;i<=min;i++){
        if(min%i===0){
            divisorMin.push(i);
            min/=i;
        }
    }
    for(let i=1;i<=max;i++){
        if(max%i===0){
            divisorMax.push(i);
        }
    }

    let commonDivisor = divisorMin.reverse().find(e=>divisorMax.includes(e));  //최대공약수
    let commonMultiple = min*max/commonDivisor;     //최소공배수
    return [commonDivisor,commonMultiple];
}
console.log(solution(18,15))
//느림. 개선필요