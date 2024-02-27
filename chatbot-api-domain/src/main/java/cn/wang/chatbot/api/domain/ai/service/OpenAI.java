package cn.wang.chatbot.api.domain.ai.service;

import cn.wang.chatbot.api.domain.ai.IOPenAI;
import cn.wang.chatbot.api.domain.ai.model.aggregates.ApifoxModel;
import cn.wang.chatbot.api.domain.ai.model.vo.Choice;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class OpenAI implements IOPenAI {
    private Logger logger = LoggerFactory.getLogger(OpenAI.class);

    @Value("${chatbot_api.key}")
    private String key;
    @Override
    public String doChatGPT(String Question) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.chatanywhere.com.cn/v1/chat/completions");
        post.addHeader("authorization"," Bearer "+ key );
        post.addHeader("contentType","application/json");

        String paramJson = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \"" + Question +"\"}]\n" +
                "}";

//        System.out.println(paramJson);

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String Jsonstr = EntityUtils.toString(response.getEntity());
            ApifoxModel apifoxModel = JSON.parseObject(Jsonstr, ApifoxModel.class);
            StringBuilder stringBuffer = new StringBuilder();
            List<Choice> choices = apifoxModel.getChoices();
            for (Choice choice : choices) {
                stringBuffer.append(choice.getMessage().getContent());
            }
            return stringBuffer.toString();
        }
        else {
            throw new RuntimeException("api.openai.com Err Code is " + response.getStatusLine().getStatusCode());
        }
    }
}
