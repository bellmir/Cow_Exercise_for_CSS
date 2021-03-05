function solution(n, lost, reserve) {
    let answer = 0;

    //console.log(lost, reserve);
    //reserve와 lost가 중복일 경우
    let dup = lost.filter((cloth)=>reserve.includes(cloth));
    //console.log('dup: ', dup);
    for(let i of dup){
        lost.splice(lost.indexOf(lost.find(e=>e===i)),1);
        reserve.splice(reserve.indexOf(reserve.find(e=>e===i)),1);
    }
    //console.log(lost, reserve);

    //reserve 학생보다 1작은 학생의 옷이 도난당한 경우
    dup = reserve.filter((cloth)=>lost.includes(cloth-1));
    //console.log('dup: ', dup);
    for(let i of dup){
        lost.splice(lost.indexOf(lost.find(e=>e===i-1)),1);
        reserve.splice(reserve.indexOf(reserve.find(e=>e===i)),1);
    }
    //console.log(lost, reserve);

    //reserve 학생보다 1큰 학생의 옷이 도난당한 경우
    dup = reserve.filter((cloth)=>lost.includes(cloth+1));
    //console.log('dup: ', dup);
    for(let i of dup){
        lost.splice(lost.indexOf(lost.find(e=>e===i+1)),1);
        reserve.splice(reserve.indexOf(reserve.find(e=>e===i)),1);
    }
    //console.log(lost, reserve);
    
    answer=n-lost.length;
    return answer;
}
console.log(solution(	5, [2, 4], [1, 3, 5]));
//reserve.splice(reserve.indexOf(reserve.find(e=>e===res)),1);