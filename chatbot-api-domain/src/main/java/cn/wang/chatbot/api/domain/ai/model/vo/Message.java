package cn.wang.chatbot.api.domain.ai.model.vo;

public class Message {
    private String content;
    private String role;

    public String getContent() { return content; }
    public void setContent(String value) { this.content = value; }

    public String getRole() { return role; }
    public void setRole(String value) { this.role = value; }
}
