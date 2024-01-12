package by.it_academy.team1.usermessages.core.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private String usernameTo;
    private String usernameFrom;
    private String text;

    private LocalDateTime sentDate;


    public MessageDto() {
    }

    public MessageDto(String usernameTo, String usernameFrom, String text, LocalDateTime sentDate) {
        this.usernameTo = usernameTo;
        this.usernameFrom = usernameFrom;
        this.text = text;
        this.sentDate = sentDate;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public String getUsernameFrom() {
        return usernameFrom;
    }

    public void setUsernameFrom(String usernameFrom) {
        this.usernameFrom = usernameFrom;
    }

    public String getUsernameTo() {
        return usernameTo;
    }

    public void setUsernameTo(String usernameTo) {
        this.usernameTo = usernameTo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "usernameTo='" + usernameTo + '\'' +
                ", usernameFrom='" + usernameFrom + '\'' +
                ", text='" + text + '\'' +
                '}';
    }


}
