package by.it_academy.team1.usermessages.core.entity;

import java.time.LocalDateTime;

public class Message {
    private LocalDateTime sentDate;
    private String usernameFrom;
    private String usernameTo;
    private String text;

    public Message() {
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public Message(LocalDateTime sentDate, String usernameFrom, String usernameTo, String text) {
        this.sentDate = sentDate;
        this.usernameFrom = usernameFrom;
        this.usernameTo = usernameTo;
        this.text = text;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "sentDate=" + sentDate +
                ", usernameFrom='" + usernameFrom + '\'' +
                ", usernameTo='" + usernameTo + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
