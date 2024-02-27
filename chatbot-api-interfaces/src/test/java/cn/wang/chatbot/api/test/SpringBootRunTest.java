package cn.wang.chatbot.api.test;

import cn.wang.chatbot.api.domain.zsxq.ZsxqApi;
import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
}
