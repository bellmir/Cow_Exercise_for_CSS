function solution(N, stages) {
    let reachedUser = new Array(N).fill(0);
    let failRate = reachedUser.map((e,i)=>{
        let challenged=0 , notCleared=0;
        stages.forEach(stage=> {
            if(stage>=i+1) challenged++;    //stage의 시도된 횟수
            if(stage===i+1) notCleared++;   //stage의 실패 횟수
        });
        return {
            index: i,
            rate:notCleared/challenged,  // 실패율 = 실패 / 시도 ;
        };
    });
    return failRate.sort((a,b)=> b.rate - a.rate).map(obj=>obj.index+1);
}

//object를 사용해서 너무 느림. 개선 필요
console.log(solution(5,	[2, 1, 2, 6, 2, 4, 3, 3]));