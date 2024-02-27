package cn.wang.chatbot.api.domain.ai;

import java.io.IOException;

public interface IOPenAI {

    public  String doChatGPT(String Question) throws IOException;
}
