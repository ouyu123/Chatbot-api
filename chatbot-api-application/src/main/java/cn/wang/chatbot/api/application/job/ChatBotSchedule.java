package cn.wang.chatbot.api.application.job;

import cn.wang.chatbot.api.domain.zsxq.ZsxqApi;
import cn.wang.chatbot.api.domain.ai.IOPenAI;
import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.res.AnswerRes;
import cn.wang.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Component
public class ChatBotSchedule {
    private Logger logger = LoggerFactory.getLogger(ChatBotSchedule.class);
    @Value("${chatbot_api.groupId}")
    private String groupId;

    @Value("${chatbot_api.cookie}")
    private String cookie;
    @Resource
    private ZsxqApi zsxqApi;
    @Resource
    private IOPenAI openAI;


    @Scheduled(cron = "0/30 * * * * ?")
    public void run()
    {
        try {
            if(new Random().nextBoolean())
            {
                logger.info("随机打样");
                return;
            }

            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            int hour = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
            if(hour > 22 || hour <7)
            {
                logger.info("休息中....");
                return ;
            }
            UnAnsweredQuestionsAggregates unAnsweredQuestionsAggregates = zsxqApi.queryUnAnsweredQurestionTopicod(groupId, cookie);
            logger.info("爬取的问题:{}", JSON.toJSONString(unAnsweredQuestionsAggregates));

            List<Topics> topics = unAnsweredQuestionsAggregates.getResp_data().getTopics();
            if(null == topics || topics.isEmpty())
            {
                logger.info("没有未问题");
            }else{
                Topics topics1 = topics.get(0);
                String res = openAI.doChatGPT(topics1.getQuestion().getText());
                logger.info("AI回答的问题：{}",res);
                AnswerRes answer = zsxqApi.answer(groupId, cookie, topics1.getTopic_id(), res, false);
                logger.info("回答状态：{}",answer.isSucceeded());
            }
            }catch (Exception e) {
            logger.error("自动回答问题异常",e);
        }
    }
}
