package edu.psu.ist.model;

import java.awt.image.BufferedImage;
import java.util.Date;

public class ImageNote extends Note{
    private BufferedImage imageContent;
    public ImageNote(String title, User createdBy, Date creationDate, String content) {
        super(title, createdBy, creationDate, content);
    }

    public void setImage(BufferedImage image){
        imageContent = image;
    }

    @Override
    public String getContent() {
        return super.getContent() +
                " & Image: { Width:" + imageContent.getWidth() +
                ", Height: " + imageContent.getHeight() + " }";
    }
}
