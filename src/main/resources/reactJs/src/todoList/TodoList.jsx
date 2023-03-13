import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'

function TodoList() {
    const [todos, setTodos] = useState([]);
    const [inputValue, SetInputValue] = useState("");

    const URL = "http://localhost:8080/api/todos";

    // get dữ liệu ban đầu
    useEffect(() =>{
        const fetchData = async () =>{
            const data = await fetch(`${URL}`)
            const dataJson = await data.json();  
            setTodos(dataJson)
        }
        fetchData();
    },[])

    // thêm todo
    const addTodo =  async () =>{
        if(inputValue.trim() === ""){
            alert("Tiêu đề không để trống");
            return;
        }
        let todo = await fetch(`${URL}`, 
            {method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({title: `${inputValue}`})
        })
        const dataJson = await todo.json();
        setTodos([...todos,dataJson]);

        // set dữ liệu ô input về trống
        SetInputValue("");
    }

    // edit todo
    const editTodo = async (index) =>{
        const titleName = prompt("Nhập tiêu đề cần sửa");
        if (titleName.trim() === ""){
            alert("Tiêu đề trống");
            return;
        }

        // nhập true trả về true, nhập khác trả về false
        const status = prompt("Hoàn thành nhập true ");
        const getStatus = status.trim().toLowerCase() == "true" ? "true" : "false";

        const todo = await fetch(`${URL}/${index}`,
            {method : 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({title: titleName, status : getStatus})
        })

        const newTodo = await todo.json();
        console.log(newTodo)
        const newTodos = todos.map(e=>{
            if(e.id == index){
                return newTodo
            }
            return e;
        })
        setTodos(newTodos);
    }


    // xóa todo
    const deleteTodo = async (index) =>{
        await fetch(`${URL}/${index}`, {method : 'DELETE'});
        const newData = todos.filter(e => e.id != index);
        setTodos(newData);
    }

    // toggle status
    const changeChecked = async (index) => {
        await fetch(`${URL}/toggle/${index}`)
    
        const newTodos = todos.map(e => {
            if (e.id === index){
                e.status = !e.status;
            } 
            return e;
        })
        setTodos(newTodos);
    }
    
    // sử dụng useEffect để render lại mọi hành động khi thay đổi list todo
    useEffect(() =>{
        console.log("render")
    },[todos])

  return (
    <>
        <h1>TodoList</h1>
        <input type="text" placeholder='Add Công việc mới' value={inputValue} onChange={e => SetInputValue(e.target.value)}/>
        <button onClick={addTodo}>Add</button>
        {todos.length > 0 ? 
            <ul>
                {todos.map((e, i) =>(
                    <li key={i}>
                        <input type="checkbox" name="" id="" onChange = {() => changeChecked(e?.id)}  checked = {e?.status}/>
                        {e?.title}
                        <button onClick={() =>editTodo(e.id)}>Edit</button>
                        <button onClick={() => deleteTodo(e.id)}>Delete</button>
                    </li>
                ))}
            </ul>
        : (<h2>Danh sách công việc trống</h2>)
        }
    </>
  )
}

export default TodoList