function solution(s){
    let countY=0;
    let countP=0;
    s= s.toLowerCase();
    for(let i=0;i<s.length;i++){
        if(s.charAt(i)==='y')
            countY++;
        else if(s.charAt(i)==='p')
            countP++;
    }
    return countP===countY ? true : false;
}