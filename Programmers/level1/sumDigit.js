function solution(n)
{
    n=String(n);
    let sum =0;
    for(let digit of n){
        sum+=Number(digit);
    }
    return sum;
}
console.log(solution(987));