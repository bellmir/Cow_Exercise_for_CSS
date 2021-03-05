function solution(num) {
    let i=0;
    while(num!==1 && i<500){
        num = num%2===0 ? num/2 : num*3+1;
        i++;
    }
    return i<500 ? i : -1;
}