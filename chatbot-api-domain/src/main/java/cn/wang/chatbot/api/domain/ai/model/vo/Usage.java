package cn.wang.chatbot.api.domain.ai.model.vo;

public class Usage {
    private long completionTokens;
    private long promptTokens;
    private long totalTokens;

    public long getCompletionTokens() { return completionTokens; }
    public void setCompletionTokens(long value) { this.completionTokens = value; }

    public long getPromptTokens() { return promptTokens; }
    public void setPromptTokens(long value) { this.promptTokens = value; }

    public long getTotalTokens() { return totalTokens; }
    public void setTotalTokens(long value) { this.totalTokens = value; }
}
