import React from 'react'
import { useEffect } from 'react';
import { useState } from 'react'

function TodoList() {
    const [todos, setTodos] = useState([]);
    const [todoSize, setTodoSize] = useState(true);
    const URL = "http://localhost:8080/api/todos";
    useEffect(() =>{
        const fetchData = async () =>{
            const data = await fetch(`${URL}`)
            const dataJson = await data.json();
            if (dataJson.length === 0){
                setTodoSize === false;
            }
            setTodos(dataJson)
        }
        fetchData();
    })

    const editTodo = (index) =>{

    }

    const deleteTodo = async (index) =>{
        await fetch(`${URL}/${index}`, {method : 'DELETE'});
        const newData = todos.filter(e => e.id != index);
        if (newData.length === 0){
            setTodoSize === false;
        }
        setTodos(newData);
    }

  return (
    <>
        <h1>TodoList</h1>
        <input type="text" placeholder='Add Công việc mới' />
        <button>Add</button>
        {todos.length > 0 ? 
            <ul>
                {todos.map((e, i) =>(
                    <li key={i}>
                        <input type="checkbox" name="" id="" />
                        {e.title}
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