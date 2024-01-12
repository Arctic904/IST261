package edu.psu.ist.model;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {
    private String Title;
    private User CreatedBy;
    private Date CreationDate;
    private String Content;

    public Note(String title, User createdBy, Date creationDate, String content) {
        Title = title;
        CreatedBy = createdBy;
        CreationDate = creationDate;
        Content = content;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public User getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(User createdBy) {
        CreatedBy = createdBy;
    }

    public Date getCreationDate() {
        return CreationDate;
    }

    public void setCreationDate(Date creationDate) {
        CreationDate = creationDate;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Title='" + Title + '\'' +
                ", CreatedBy=" + CreatedBy +
                ", CreationDate=" + CreationDate +
                ", Content='" + Content + '\'' +
                '}';
    }
}
