package cn.wang.chatbot.api.domain.zsxq.service;

import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.req.AnswerReq;
import cn.wang.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.wang.chatbot.api.domain.zsxq.model.res.AnswerRes;
import com.alibaba.fastjson.JSON;
import net.sf.json.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class ZsxqApi implements cn.wang.chatbot.api.domain.zsxq.ZsxqApi {
    private Logger logger =  LoggerFactory.getLogger(ZsxqApi.class);
    public UnAnsweredQuestionsAggregates queryUnAnsweredQurestionTopicod(String groupId, String cookie) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/"+groupId+"/topics?scope=unanswered_questions&count=20");
        get.addHeader("Cookie", cookie);
        get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String jsonStr = EntityUtils.toString(response.getEntity());
            logger.info("提问数据。groupId：{}  jsonStr：{}", groupId, jsonStr);
            return JSON.parseObject(jsonStr,UnAnsweredQuestionsAggregates.class);
        }else {
           throw  new RuntimeException("err code " + response.getStatusLine().getStatusCode());
        }
    }

    public AnswerRes answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws IOException {

        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/"+topicId+"/answer");

        post.addHeader("Cookie",cookie);
        post.addHeader("Content-Type","application/json; charset=UTF-8");
        post.addHeader("User-Agent","\n" +
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36 Edg/122.0.0.0");

//        String paramJson = "{\"req_data\":{\"text\":"+text+",\"image_ids\":[]}}";
        AnswerReq answerReq = new AnswerReq(new ReqData(text, silenced));

        String paramJson = JSONObject.fromObject(answerReq).toString();
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));

        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String res = EntityUtils.toString(response.getEntity());
            logger.info("回答问题结果。groupId：{} topicId：{} jsonStr：{}", groupId, topicId, res);
            return JSON.parseObject(res, AnswerRes.class);
        }else {
            throw  new RuntimeException("err code " + response.getStatusLine().getStatusCode());
        }
    }
}
