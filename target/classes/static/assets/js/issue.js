
const todos = document.querySelectorAll(".todo");
const all_status = document.querySelectorAll(".status");
let draggableTodo = null;

todos.forEach((todo) => {
  todo.addEventListener("dragstart", dragStart);
  todo.addEventListener("dragend", dragEnd);
});

function dragStart() {
  draggableTodo = this;
  setTimeout(() => {
    this.style.display = "none";
  }, 0);
 // console.log("dragStart");
}

function dragEnd() {
  draggableTodo = null;
  setTimeout(() => {
    this.style.display = "block";
  }, 0);
//  console.log("dragEnd");
}

all_status.forEach((status) => {
  status.addEventListener("dragover", dragOver);
  status.addEventListener("dragenter", dragEnter);
  status.addEventListener("dragleave", dragLeave);
  status.addEventListener("drop", dragDrop);
});

function dragOver(e) {
  e.preventDefault();
}

function dragEnter() {
  this.style.border = "1px dashed #ccc";
  //console.log("dragEnter");
}

function dragLeave() {
  this.style.border = "none";
 // console.log("dragLeave");
}

function dragDrop() {
  this.style.border = "none";

  // Check if tasks-wrapper div already exists
  let existingTasksWrapper = this.querySelector('.tasks-wrapper');
   let text=this.querySelector('.textlist');

  if (existingTasksWrapper) {
    existingTasksWrapper.appendChild(draggableTodo);
    
  } 
  const match = text.textContent.match(/(\w+)/);
if (match) {
  const dynamicText = match[1];
  console.log(dynamicText);
} else {
  console.log("No match found");
}

 // console.log("dropped");
}