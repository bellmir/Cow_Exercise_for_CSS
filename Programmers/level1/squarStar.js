function solution(n,m){
    let answer = '';
    for(let i=0;i<m;i++){
        for(let j=0;j<n;j++){
            answer +="*";       //repeat() 써도 됨
        }
        answer +="\n";
    }
    return answer;
}
solution(5, 3);