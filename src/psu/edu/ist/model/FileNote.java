package psu.edu.ist.model;

import java.io.File;
import java.util.Date;

public class FileNote extends Note{
    private File fileContent;
    public FileNote(String title, User createdBy, Date creationDate, String content) {
        super(title, createdBy, creationDate, content);
    }

    public void setFile(File file){
        fileContent = file;
    }

    @Override
    public String getContent() {
        return super.getContent() + " & Filepath:" + fileContent.toString();
    }
}
