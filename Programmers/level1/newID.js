function solution(new_id) {
    new_id= new_id.toLowerCase()
    .replace(/[^\w-.]/g, '')
    .replace(/\.+/g,'.')
    .replace(/^\.|\.$/g, '');
    if (new_id==="") new_id="a";
    else if (new_id.length>=16) {new_id=new_id.slice(0,15).replace(/\.$/g, '')}
    return new_id.length<3 ? new_id.padEnd(3, new_id[new_id.length-1]) : new_id;
    // return new_id.length<=2 ? new_id+new_id[new_id.length-1].repeat(3-new_id.length) : new_id;
} 
solution("..!)adsAAfa..dfd.");