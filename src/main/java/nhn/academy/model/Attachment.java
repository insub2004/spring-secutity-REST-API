package nhn.academy.model;

import java.awt.*;

public class Attachment {
    private String title;
    private String text;
    private String titleLink;
    private String botIconIMage;
    private Color color;

    public Attachment(String title, String text, String titleLink, String botIconIMage, Color color) {
        this.title = title;
        this.text = text;
        this.titleLink = titleLink;
        this.botIconIMage = botIconIMage;
        this.color = color;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    public void setBotIconIMage(String botIconIMage) {
        this.botIconIMage = botIconIMage;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTitleLink() {
        return titleLink;
    }

    public String getBotIconIMage() {
        return botIconIMage;
    }

    public Color getColor() {
        return color;
    }
}
