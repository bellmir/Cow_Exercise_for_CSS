function solution(s) {
    return s.split(' ').map(word=>word.split('').map((char, i)=> i%2===0 ? char.toUpperCase() : char.toLowerCase()).join('')).join(' ');
}

/*
function solution(s) {
    let words = s.split(' ');
    
    for(let i in words){
        let word = words[i].split('');
        for(let j in word){
            if(j%2===0) word[j]=word[j].toUpperCase();
            else word[j] = word[j].toLowerCase();
        }
        words[i] = word.join('');
    }
    return words.join(' ');
}
}*/

console.log(solution("try hello world"));

