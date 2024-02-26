package cn.wang.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * 测试
 */
public class ApiTest {

    @Test
    public void query_unanswered_question() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
         HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/28888888518881/topics?scope=unanswered_questions&count=20");
         get.addHeader("Cookie","tfstk=fxgyogZDfULrHOy2b8aU0Vthy6z8JPp6U2wQtXc3N82lPwi2xXcuNkv8P2kqBfnW9k4kTvkiUkME9QF3KvDUd0tX5bh8Jyv_LFTswJIEV1HztJVDt5POrbYT8xc8JyvjILw_VbH1m6mULyA4oWVFqy23ZIr01-butaVhnIPpPpc54BpU0dcgnqJzkxGFigwc-w0kJuyamM_F88Pu4Slu3_5ru7rzQo5Faz0ohX0SyoL5yyhjbvonphsYzXmur5GJzZziZmgUG2OCY8cjuj0z217ZQzkUj4qVtwz8-J44iVACX-g4Fv0zmC_aAr0_jzmXDLZQz5knyY5F-AjzI9FcOF3K49jUqSF4CIR4TiKgUVAkQnsdvunYgROJwMILqJV4CIJdvME-cSy6Zh5..; zsxq_access_token=03CF4018-54B0-78B6-72DF-B6F5E8F633D1_BCB63B612253A27B; zsxqsessionid=4d2a4a77c7582bcf2ea88f3d4ff332df; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415828141185888%22%2C%22first_id%22%3A%2218de44aa35d734-0ada00fbb73d088-4c657b58-1327104-18de44aa35e190%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkZTQ0YWEzNWQ3MzQtMGFkYTAwZmJiNzNkMDg4LTRjNjU3YjU4LTEzMjcxMDQtMThkZTQ0YWEzNWUxOTAiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU4MjgxNDExODU4ODgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415828141185888%22%7D%2C%22%24device_id%22%3A%2218de44aa35d734-0ada00fbb73d088-4c657b58-1327104-18de44aa35e190%22%7D");
         get.addHeader("Content-Type","application/json; charset=UTF-8");

        CloseableHttpResponse response = httpClient.execute(get);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }

    @Test
    public  void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/188828822154512/answer");

        post.addHeader("Cookie","tfstk=fxgyogZDfULrHOy2b8aU0Vthy6z8JPp6U2wQtXc3N82lPwi2xXcuNkv8P2kqBfnW9k4kTvkiUkME9QF3KvDUd0tX5bh8Jyv_LFTswJIEV1HztJVDt5POrbYT8xc8JyvjILw_VbH1m6mULyA4oWVFqy23ZIr01-butaVhnIPpPpc54BpU0dcgnqJzkxGFigwc-w0kJuyamM_F88Pu4Slu3_5ru7rzQo5Faz0ohX0SyoL5yyhjbvonphsYzXmur5GJzZziZmgUG2OCY8cjuj0z217ZQzkUj4qVtwz8-J44iVACX-g4Fv0zmC_aAr0_jzmXDLZQz5knyY5F-AjzI9FcOF3K49jUqSF4CIR4TiKgUVAkQnsdvunYgROJwMILqJV4CIJdvME-cSy6Zh5..; zsxq_access_token=03CF4018-54B0-78B6-72DF-B6F5E8F633D1_BCB63B612253A27B; zsxqsessionid=4d2a4a77c7582bcf2ea88f3d4ff332df; abtest_env=product; sajssdk_2015_cross_new_user=1; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22415828141185888%22%2C%22first_id%22%3A%2218de44aa35d734-0ada00fbb73d088-4c657b58-1327104-18de44aa35e190%22%2C%22props%22%3A%7B%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMThkZTQ0YWEzNWQ3MzQtMGFkYTAwZmJiNzNkMDg4LTRjNjU3YjU4LTEzMjcxMDQtMThkZTQ0YWEzNWUxOTAiLCIkaWRlbnRpdHlfbG9naW5faWQiOiI0MTU4MjgxNDExODU4ODgifQ%3D%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22415828141185888%22%7D%2C%22%24device_id%22%3A%2218de44aa35d734-0ada00fbb73d088-4c657b58-1327104-18de44aa35e190%22%7D");
        post.addHeader("Content-Type","application/json; charset=UTF-8");

        String paramJson = "{\"req_data\":{\"text\":\"不知道\",\"image_ids\":[]}}";
        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);
        CloseableHttpResponse response = httpClient.execute(post);

        if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
        {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }

    }
}
