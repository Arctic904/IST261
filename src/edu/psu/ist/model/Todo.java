package edu.psu.ist.model;

import java.util.Date;

public class Todo {
    private String content;
    private Date dueDate;
    private Priority priority = Priority.Low;
    private Note attachedNote;

    public Todo(String content, Date dueDate, Priority priority, Note attachedNote) {
        this.content = content;
        this.dueDate = dueDate;
        this.priority = priority;
        this.attachedNote = attachedNote;
    }

    public Todo(String content, Date dueDate, Note attachedNote) {
        this.content = content;
        this.dueDate = dueDate;
        this.attachedNote = attachedNote;
    }

    public enum Priority {
        Low, Medium, High
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Note getAttachedNote() {
        return attachedNote;
    }

    public void setAttachedNote(Note attachedNote) {
        this.attachedNote = attachedNote;
    }

    public boolean decideToInsert(Todo todo){
        boolean decision = false;
        if (this.priority.compareTo(todo.priority) < 0) {
            decision = true;
        } else if (this.priority.equals(todo.priority)) {
            if (this.dueDate.before(todo.dueDate)) {
                decision = true;
            }
        }
        return decision;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "content='" + content + '\'' +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", attachedNote=" + attachedNote +
                '}';
    }
}
