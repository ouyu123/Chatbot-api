package cn.wang.chatbot.api.test;

import cn.wang.chatbot.api.application.job.ChatBotSchedule;
import cn.wang.chatbot.api.domain.ai.IOPenAI;
import cn.wang.chatbot.api.domain.zsxq.ZsxqApi;
import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRunTest {

    private Logger logger = LoggerFactory.getLogger(SpringBootRunTest.class);
    @Value("${chatbot_api.groupId}")
    private String groupId;

    @Value("${chatbot_api.cookie}")
    private String cookie;
    @Resource
    private ZsxqApi zsxqApi;

    @Resource
    private IOPenAI openAI;

    @Resource
    private ChatBotSchedule chatBotSchedule;
    @Test
    public void test_zsxqApi() throws IOException {
        UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQurestionTopicod(groupId, cookie);
        logger.info("测试结果", JSON.toJSONString(unAnsweredQuestionsAggregates));

        List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
        for (Topics topic: topics ) {
            String topicId=topic.getTopic_id();
            String text = topic.getQuestion().getText();
            logger.info("问题id: {} 问题内容： {}",topicId,text);

            //回答问题
            zsxqApi.answer(groupId,cookie,topicId,text,false);
        }


    }
    @Test
    public void chatGtp_test() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost("https://api.chatanywhere.com.cn/v1/chat/completions");
        post.addHeader("authorization"," Bearer sk-QRRmRR3WPIMLDjGKPNILXTGz5cQQUycEMyGGLjWaQk9tWD6J" );
        post.addHeader("contentType","application/json");
        String paramJson = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \"写一个冒泡排血\"}]\n" +
                "}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("application/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String res = EntityUtils.toString(response.getEntity());
            logger.info("返回的内容 {}", res);

        }

    }

    @Test
    public void Test_OpenAi() throws IOException {
        String res = openAI.doChatGPT("写一个英语你好");
        logger.info("返回结果{}",res);
    }

    @Test
    public  void  Test_chatBotSchedule(){
        chatBotSchedule.run();
    }
}
