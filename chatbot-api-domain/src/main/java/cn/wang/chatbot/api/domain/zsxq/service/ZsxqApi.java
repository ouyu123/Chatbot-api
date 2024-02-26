package cn.wang.chatbot.api.domain.zsxq.service;

import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.req.ReqData;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Logger;

public class ZsxqApi {
    private Logger logger = (Logger) LoggerFactory.getLogger(cn.wang.chatbot.api.domain.zsxq.ZsxqApi.class);
    UnAnsweredQuestionsAggregates queryUnAnsweredQurestionTopicod(String groupId, String cookie) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");
        get.addHeader("Cookie", cookie);
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String jsonStr = EntityUtils.toString(response.getEntity());
            return JSON.parseObject(jsonStr,UnAnsweredQuestionsAggregates.class);
        }else {
           throw  new RuntimeException("err code " + response.getStatusLine().getStatusCode());
        }
    }

    boolean answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        return false;
    }
}
