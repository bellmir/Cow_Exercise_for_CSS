function solution(nums) {
    let dup = -1;
    let differnt = 0;
    nums.sort().forEach(e => {
        if(dup!==e){
            differnt++;
            dup = e;
        }
    });
    return nums.length/2>differnt ? differnt : nums.length/2;
}

/*set을 이용한 방법
function solution(nums){
    const setOfPoketmon = new Set(nums);
    const kind = setOfPoketmon.size;
    return nums.length/2>kind ? kind : nums.length/2;
}
*/