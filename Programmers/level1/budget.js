function solution(d, budget) {
    let count = 0;
    d.sort((a,b)=>a-b).forEach(cost => {
        if(budget-cost>=0){
            budget-=cost;
            count++;
        }
    });
    return count;
}