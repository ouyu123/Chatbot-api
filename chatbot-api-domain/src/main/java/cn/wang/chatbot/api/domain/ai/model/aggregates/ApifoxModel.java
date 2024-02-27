package cn.wang.chatbot.api.domain.ai.model.aggregates;

import cn.hutool.core.annotation.Link;
import cn.wang.chatbot.api.domain.ai.model.vo.Choice;
import cn.wang.chatbot.api.domain.ai.model.vo.Usage;

import java.util.List;

public class ApifoxModel {
    private List<Choice> choices;

    private long created;
    private String id;
    private String object;
    private Usage usage;

    public void setChoices(List<Choice> choices){
        this.choices = choices;
    }
    public List<Choice> getChoices(){
        return this.choices;
    }

    public long getCreated() { return created; }
    public void setCreated(long value) { this.created = value; }

    public String getid() { return id; }
    public void setid(String value) { this.id = value; }

    public String getObject() { return object; }
    public void setObject(String value) { this.object = value; }

    public Usage getUsage() { return usage; }
    public void setUsage(Usage value) { this.usage = value; }
}
