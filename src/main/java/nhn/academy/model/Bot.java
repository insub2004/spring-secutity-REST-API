package nhn.academy.model;


import java.util.ArrayList;
import java.util.List;

public class Bot {
    private String botName;
    private String text;
    private List<Attachment> attachments;

    public Bot(String botName, String text, List<Attachment> attachments) {
        this.botName = botName;
        this.text = text;
        this.attachments = attachments;
    }

    public String getBotName() {
        return botName;
    }

    public String getText() {
        return text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }
}
