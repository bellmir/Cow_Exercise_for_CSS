function solution(numbers, hand) {
    var answer = '';
    let keyPad=[[3,1],[0,0],[0,1],[0,2],[1,0],[1,1],[1,2],[2,0],[2,1],[2,2]];
    let leftPosition = [3,0];
    let rightPosition = [3,2];
    for(let num of numbers){
        if(num === 1 || num ===4 || num===7){
            leftPosition = keyPad[num];
            answer +='L';
        }
        else if(num === 3 || num ===6 || num===9){
            rightPosition = keyPad[num];
            answer+='R';
        }
        else{
            if(position(leftPosition, keyPad[num])<position(rightPosition, keyPad[num])){
                leftPosition = keyPad[num];
                answer +='L';
            }
            else if(position(leftPosition, keyPad[num])>position(rightPosition, keyPad[num])){
                rightPosition = keyPad[num];
                answer+='R';
            }
            else{
                if(hand==='right'){
                    rightPosition = keyPad[num];
                    answer+='R';
                }
                else{
                    leftPosition = keyPad[num];
                    answer +='L';
                }
            }
        }
    }
    return answer;
}
    
function position(current, target){
    let x =Math.abs(current[0]-target[0]);
    let y =Math.abs(current[1]-target[1]);
    return x+y;
}

console.log(solution([1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5], "right"));