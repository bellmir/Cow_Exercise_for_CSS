function solution(strings, n) {
    return strings.sort().sort((a,b)=>{
        if(a.charAt(n)<b.charAt(n))
            return -1;
        else if(a.charAt(n)>b.charAt(n))
            return 1;
        else
            return 0;
    });
}

console.log(solution(["sun", "bed", "car"], 1));