function solution(s) {
    if((s.length !==4 && s.length !==6) || isNaN(s))
        return false;
    else if(parseInt(s)!==Number(s))
        return false;
    return true;
}