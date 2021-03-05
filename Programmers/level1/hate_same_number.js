function solution(arr)
{
    let answer=[];
    let dup = -1;
    for(let i=0;i<arr.length;i++){
        if(dup!==arr[i]){
            answer.push(arr[i]);
            dup=arr[i];
        }
    }
    return answer;
}
/*
function solution(arr)
{
    return arr.filter((val,index) => val != arr[index+1]);
}
*/

console.log(solution([4,4,4,3,3]));