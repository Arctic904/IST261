package edu.psu.ist.controller;

import edu.psu.ist.model.Note;
import edu.psu.ist.model.Todo;
import edu.psu.ist.model.TodoTableModel;
import edu.psu.ist.model.User;
import edu.psu.ist.view.TodoListView;

import java.util.*;

public class TodoListController {
    private Date creationDate;
    private LinkedList<Todo> todos = new LinkedList<Todo>();
    private TodoTableModel todoTableModel;
    private TodoListView todoListView;

    public TodoListController() {
        this.setCreationDate();
        this.createTodoList();
        this.printTodoList();
        this.todoTableModel = new TodoTableModel(todos);
        this.todoListView = new TodoListView(this);
    }

    private void printTodoList() {
        for (Todo todo : todos) {
            System.out.println(todo.toString());
        }
    }

    private void createTodoList() {
        Note testNote = new Note("Test", new User("John", "john@example.com", "1234567890"), new Date(), "Content");
        Todo todo1 = new Todo("Add notes", new Date(), testNote);
        Todo todo2 = new Todo("Add New Users", new Date(2020, Calendar.NOVEMBER, 20), Todo.Priority.Medium, testNote);
        addTodo(todo1);
        addTodo(todo2);
    }

    public void addTodo(Todo todo) {
        boolean todoAdded = false;
        boolean insertDecision = false;
        ListIterator<Todo> todoListIterator = todos.listIterator();
        while(todoListIterator.hasNext()){
            insertDecision = todoListIterator.next().decideToInsert(todo);
            if (insertDecision) {
                todos.add(todoListIterator.previousIndex(), todo);
                todoAdded = true;
                break;
            }
        }
        if(!todoAdded){
            todos.add(todo);
        }
    }

    public List<Todo> searchItem(Todo.Priority todoPriority) {
        List<Todo> results = new ArrayList<>();
        for (Todo currentElement : todos) {
            if (currentElement.getPriority().equals(todoPriority)) {
                results.add(currentElement);
            }
        }
        System.out.println("search results: " + results);
        return results;
    }

    public void updateTodo(int index, Todo todo){
        this.todos.set(index, todo);
    }

    public void deleteItem(int index){
        this.todos.remove(index);
    }

    private void setCreationDate() {
        this.creationDate = new Date();
    }

    @Override
    public String toString() {
        return "TodoList{" +
                "creationDate=" + creationDate +
                ", todos=" + todos +
                '}';
    }

    public TodoTableModel getTodoTableModel() {
        return this.todoTableModel;
    }

    public void setVisibile() {
        this.todoListView.setVisible(true);
    }
}
