const js_toDoListform = document.querySelector(".js_toDoListform"),
    js_todoListInput= js_toDoListform.querySelector("input"),
    js_toDoList =document.querySelector(".js_toDoList");

const TODOS_LS = 'toDos';
let toDos = [];
let toDoListID = 0;

function deleteToDo(event) {
    const liToRemove = event.target.parentNode        //눌린 버튼인 <button>"❌"</button>의 부모요소 <li id=...">...</id>을 선택
    js_toDoList.removeChild(liToRemove);
    toDos = toDos.filter(e=>e.id !== Number(liToRemove.id))     //html의 id는 String이기 때문에 number형으로 바꿔줘야한다.
    saveToDos();
}

function saveToDos() {
    localStorage.setItem(TODOS_LS, JSON.stringify(toDos));
}

function loadToDos() {
    const loadedToDos = localStorage.getItem(TODOS_LS);
    if(loadedToDos !== null){
        JSON.parse(loadedToDos).forEach((list)=>paintToDo(list.text));
    }
}

function handleSubmit(event) {
    event.preventDefault(); //submit일때 일어나는 기본 event 방지
    const currentValue = js_todoListInput.value;
    paintToDo(currentValue);
    js_todoListInput.value=""; //입력을 마치면 다시 input안을 공백으로
}

function paintToDo(text) {
    const li = document.createElement("li");  //html요소를 생성
    const delBtn = document.createElement("button");
    delBtn.innerText = "❌";
    delBtn.addEventListener("click", deleteToDo);
    const addText = document.createTextNode(text); //createTextNode는 선택한 요소에 텍스트 추가
    li.appendChild(addText)    //appendChild는 선택한 요소 안에 자식요소를 추가
    li.appendChild(delBtn);
    li.id = toDoListID;
    js_toDoList.appendChild(li);

    const toDoObj={
        text: text,
        id: toDoListID,
    }
    toDoListID++;
    toDos.push(toDoObj);
    saveToDos();    //이것 때문에 paintToDo가 loadToDos()로 호출됐을때 localStorage가 중복저장될것이라 생각될 수 있지만, saveToDo()는 새로고침하면 사라지는 toDos배열에서 데이터를 받아서 localStorage에 저장하는 로직이다. localStorage에서 받아오는 것이 아니다. 또 loadedToDos로 이미 localStorage가 불러와진 후 그것을 다시 저장하기에 localStorage는 덮혀씌워져도 결국 원래 데이터를 다 받아서 복구가 되는 것이다.
}

(function () {      //function init(){} init();
    localStorage.setItem("new", 3);
    loadToDos();
    js_toDoListform.addEventListener("submit", handleSubmit);
})();