package cn.wang.chatbot.api.domain.ai.model.vo;

public class Choice {
    private String finishReason;
    private Long index;
    private Message message;

    public String getFinishReason() { return finishReason; }
    public void setFinishReason(String value) { this.finishReason = value; }

    public Long getIndex() { return index; }
    public void setIndex(Long value) { this.index = value; }

    public Message getMessage() { return message; }
    public void setMessage(Message value) { this.message = value; }
}
