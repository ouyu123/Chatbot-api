package cn.wang.chatbot.api.domain.zsxq;

import cn.wang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;
import cn.wang.chatbot.api.domain.zsxq.model.req.ReqData;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.logging.Logger;

public interface ZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQurestionTopicod(String groupId, String cookie) throws IOException;
    boolean answer(String groupId, String cookie, ReqData reqData) throws  IOException;
}
