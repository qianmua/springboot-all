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
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import pres.qianmuna.es.entity.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
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
    public void testSearch() throws IOException {
        SearchRequest request = new SearchRequest("qm_index");

        // 构建条件
        // 构造器 构造条件
        // 构造器模式
        SearchSourceBuilder builder = new SearchSourceBuilder();
        // highlight
        // xxx
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "a");
        MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();

        builder.query(termQueryBuilder);
        builder.from(0);
        builder.size(20);
        builder.timeout(new TimeValue(60 , TimeUnit.SECONDS));

        request.source(builder);

        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        System.out.println(response.toString());
        System.out.println(JSON.toJSONString(response.getHits()));
        response.getHits().forEach(v1 -> System.out.println(v1.getSourceAsMap()));

    }

    @Test
    public void testSearchHighlight() throws IOException {
        String keyWord = "";
        int current = 0;
        int limit = 10;

        SearchRequest request = new SearchRequest("qm_index");
        SearchSourceBuilder builder = new SearchSourceBuilder();

        builder.from(current);
        builder.size(limit);

        TermQueryBuilder query = QueryBuilders.termQuery("title", keyWord);
        builder.query(query);

        builder.timeout(new TimeValue(20 , TimeUnit.SECONDS));

        // 高亮
        HighlightBuilder builder1 = new HighlightBuilder();
        builder1.field("title");
        builder1.preTags("<span style:'color:red'>");
        builder1.postTags("</span>");
        // 多个字段高亮
//        builder1.requireFieldMatch(true);
        builder.highlighter(builder1);

        request.source(builder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        ArrayList<Map<String, Object>> list = new ArrayList<>();

        //解析高亮
        /*response.getHits().forEach(v1 -> {

        });*/
        for (SearchHit hit : response.getHits().getHits()) {
            // 高亮字段
            Map<String, HighlightField> map = hit.getHighlightFields();
            HighlightField title = map.get("title");
            // 结果字段
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();

            if (title != null){
                Text[] texts = title.fragments();
                StringBuilder titles = new StringBuilder();
                // 替换
                for (Text text : texts) {
                    titles.append(text);
                }
                sourceAsMap.put("title" , titles);
            }
            // result
            list.add(sourceAsMap);
        }

    }





}
