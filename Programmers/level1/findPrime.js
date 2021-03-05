function solution(n) {
    let arr = new Array(n+1).fill(true);
    for (let i = 2; i**2 <= n; i++) {
        for (let j = i; j <= n; j+=i) {
            if(j!==i && j%i===0){
                arr[j] = false;
            }
        }
    }
    arr = arr.filter(bool=>bool);
    return arr.length-2;
}


console.log(solution(10));