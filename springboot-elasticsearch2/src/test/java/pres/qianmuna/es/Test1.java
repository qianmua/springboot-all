package pres.qianmuna.es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import pres.qianmuna.es.entity.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/12  18:09
 * @description :
 */
@SpringBootTest
public class Test1 {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testCreateIndex(){
        // 创建 索引

        // 创建索引请求
        CreateIndexRequest qmIndex = new CreateIndexRequest("qm_index");
        // 执行
        try {
            // 请求后响应
            CreateIndexResponse response = restHighLevelClient.indices().create(qmIndex, RequestOptions.DEFAULT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIndex() throws IOException {
        GetIndexRequest request = new GetIndexRequest("qm_index");
        boolean b = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(b);
    }

    @Test
    public void testDeleteIndex(){
        DeleteRequest request = new DeleteRequest("qm_index");
    }

    @Test
    public void testCreateDocument() throws IOException {
        Member member = new Member();
        member.setName("a")
                .setAddress("a")
                .setId("1111");
        IndexRequest request = new IndexRequest("qm_index");
        request.id("1");
        request.timeout(TimeValue.timeValueMillis(100));
//        request.timeout("1s");

        // json
        String s = JSON.toJSONString(member );
        request.source(s , XContentType.JSON);

        // send
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(response.status());
    }

    @Test
    public void testGetDocument() throws IOException {
        GetRequest request = new GetRequest("qm_index", "1");

        // 不获取返回的 _source上下文
        // 提高传输效率
        request.fetchSourceContext(new FetchSourceContext(false));
        boolean exists = restHighLevelClient.exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);

    }

    @Test
    public void testGetDocumentInfo() throws IOException {
        GetRequest request = new GetRequest("qm_index", "1");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        response.getSourceAsMap();
        response.getSourceAsString();
    }

    @Test
    public void testUpdateDocumentInfo() throws IOException {
        UpdateRequest request = new UpdateRequest("qm_index", "1");
        request.timeout("1s");
        //..
        Member member = new Member();
        member.setName("a")
                .setAddress("a")
                .setId("1111");

        request.doc(JSON.toJSONString(member) , XContentType.JSON);

        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());

    }

    @Test
    public void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest("qm_index", "2");
        request.timeout("1s");

        DeleteResponse delete = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.toString());

    }


    @Test
    public void testBulkRequest() throws IOException {
        // 批量操作
        BulkRequest request = new BulkRequest();
        request.timeout("20s");

        ArrayList<Member> list = new ArrayList<>();

        list.add(new Member());

        list.forEach(v1 -> request.add(new IndexRequest("qm_index")
                // 不写 随机id
//                .id("index")
                .source(JSON.toJSONString(v1) , XContentType.JSON)));

        BulkResponse response = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        // 是否成功
        System.out.println(response.hasFailures());
    }

    @Test
    public void testSearch(){
        SearchRequest request = new SearchRequest("qm_index");

        // 构建条件
        // 构造器 构造条件
        // 构造器模式
        SearchSourceBuilder builder = new SearchSourceBuilder();
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "a");
        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        builder.query(termQueryBuilder);
        builder.from(0);
        builder.size(20);
        builder.timeout(new TimeValue(60 , TimeUnit.SECONDS));

    }





}
