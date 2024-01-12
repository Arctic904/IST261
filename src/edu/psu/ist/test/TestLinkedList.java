package edu.psu.ist.test;

import edu.psu.ist.controller.TodoListController;
import edu.psu.ist.model.Todo;
import edu.psu.ist.model.Note;
import edu.psu.ist.model.User;

import java.util.Date;
import java.util.List;

public class TestLinkedList {
    public TestLinkedList() {
        testShippingPriorities();
    }

    public void testShippingPriorities() {
        TodoListController todoList = new TodoListController(); //add
        User testUser = new User("Ron", "ron@example.com", "1234567890");
        Note linked = new Note("Added note content", testUser, new Date(), "Content");
        Todo created = new Todo("Added content", new Date(), Todo.Priority.High, linked);
        todoList.addTodo(created);
        List<Todo> todos = todoList.searchItem(Todo.Priority.Medium); //get-search
        System.out.println(todos);
        todos.get(0).setContent("Updated Content");
        todoList.updateTodo(1, todos.get(0));//update
        System.out.println(todoList);
        todoList.deleteItem(0);
        System.out.println(todoList);
        //remove
    }
}
