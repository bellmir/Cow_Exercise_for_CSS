function solution(nums) {
    let combination = [];
    let count=0;
    for (let i = 0; i < nums.length-2; i++) {
        for (let j = i+1; j < nums.length-1; j++){
            for(let k= j+1; k< nums.length;k++){
                combination.push(nums[i]+nums[j]+nums[k]);
            }
        }
    }
    combination.forEach(e=>{if(isPrime(e)) count++});
    return count;
}

function isPrime(num) {
    for(let i=2;i*2<=num;i++){
        if(num%i===0)
            return false;
    }
    return true;
}

console.log(solution([1,2,7,6,4]))