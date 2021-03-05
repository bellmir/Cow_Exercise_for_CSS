function solution(s, n) {
    let answer ='';
    for (let i = 0; i < s.length; i++) {
        if(s[i]===' '){     //공백일때
            answer +=' ';
            continue;
        }
        let charASCII = s.charCodeAt(i);
        if(charASCII>=65 && charASCII<=90){     //대문자일때
            charASCII = charASCII+n>90 ? 64+(charASCII+n-90) : charASCII+n;
        }else{      //소문자일때
            charASCII = charASCII+n>122 ? 96+(charASCII+n-122) : charASCII+n;
        }
        answer += String.fromCharCode(charASCII);
    }
    return answer;
}
console.log(solution(	"AB", 1));