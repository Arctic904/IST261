package psu.edu.ist.model;

public class Incident {
    private String Title;
    private String Description;
    private User CreatedBy;
    private Severity Severity;
    private IncidentType Type;

    public Incident(String title, String description, User createdBy, Severity severity) {
        Title = title;
        Description = description;
        CreatedBy = createdBy;
        Severity = severity;
    }
    public Incident(String title, String description, User createdBy, IncidentType type) {
        Title = title;
        Description = description;
        CreatedBy = createdBy;
        Type = type;
        Severity = type.getSeverity();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public User getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(User createdBy) {
        CreatedBy = createdBy;
    }

    public Severity getSeverity() {
        return Severity;
    }

    public void setSeverity(Severity severity) {
        Severity = severity;
    }

    public IncidentType getType() {
        return Type;
    }

    public void setType(IncidentType type) {
        Type = type;
        Severity = type.getSeverity();
    }

    @Override
    public String toString() {
        return "Incident{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", CreatedBy=" + CreatedBy +
                ", Severity=" + Severity +
                ", Type=" + Type +
                '}';
    }
}

