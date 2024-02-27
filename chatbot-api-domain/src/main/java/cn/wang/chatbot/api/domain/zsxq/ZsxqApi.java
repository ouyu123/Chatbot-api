package cn.wang.chatbot.api.domain.zsxq;

import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.req.ReqData;
import cn.wang.chatbot.api.domain.zsxq.model.res.AnswerRes;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Logger;

public interface ZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQurestionTopicod(String groupId, String cookie) throws IOException;
    AnswerRes answer(String groupId, String cookie, String topicId, String text, boolean silenced) throws  IOException;
}
