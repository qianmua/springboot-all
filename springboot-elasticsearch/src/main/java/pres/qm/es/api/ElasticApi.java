package pres.qm.es.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.settings.put.UpdateSettingsRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.PutMappingRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  9:46
 * @description :
 */
@Service
@RequiredArgsConstructor( onConstructor = @__(@Autowired))
@Slf4j
public class ElasticApi {

    private final RestHighLevelClient esClient;


    /**
     * 查看索引存在
     * @param indexName 索引
     */
    public boolean existIndex(String indexName) throws IOException {
        GetIndexRequest request = new GetIndexRequest(indexName);
        return esClient
                .indices().exists(request , RequestOptions.DEFAULT);
    }


    public void createIndex(String indexName , int numberOfShards , int numberOfReplicas) throws IOException {

        if (!existIndex(indexName)){
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            request
                    .settings(Settings.builder()
                    // 索引分配的主分片数量
                    .put("index.number_of_shards" , numberOfShards)
                    // 主分片分配的 副本分片数量
                    .put("index.number_of_replicas" , numberOfReplicas)
                    );
            request.mapping("{\n" +
                    "  \"properties\": {\n" +
                    "    \"message\": {\n" +
                    "      \"type\": \"text\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}", XContentType.JSON);

            //同步创建索引
            //CreateIndexResponse response = esClient.indices().create(request, RequestOptions.DEFAULT);


            // 异步
            esClient.indices()
                    .createAsync(request, RequestOptions.DEFAULT, new ActionListener<CreateIndexResponse>() {
                        @Override
                        public void onResponse(CreateIndexResponse createIndexResponse) {
                            log.info("执行 -> {}" , createIndexResponse );
                        }

                        @Override
                        public void onFailure(Exception e) {
                            log.error("失败 -> {}" , e.getMessage());
                        }
                    });
        }

    }



    public void updpateIndexSetting(String indexName){
        UpdateSettingsRequest request = new UpdateSettingsRequest(indexName);
        String key = "index.number_of_replicas";
        int settingVals = 2;

        Settings.Builder builder = Settings.builder().put(key, settingVals);
        request.settings(builder);
        // 更新存在的setting 配置
        // default false
        request.setPreserveExisting(true);

        // 更新settings配置(同步)
        //esClient.indices().putSettings(request, RequestOptions.DEFAULT);

        esClient.indices()
                .putSettingsAsync(request, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
                    @Override
                    public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                        log.info("running... {}" , acknowledgedResponse);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("fail -> {}" , e.getMessage());
                    }
                });
    }


    public void putIndexMapping(String indexName) throws IOException {
        PutMappingRequest request = new PutMappingRequest(indexName);
        XContentBuilder builder = XContentFactory.jsonBuilder();

        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("new_parameter");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();

        request.source(builder);

        // mapping 配置 同步
        //AcknowledgedResponse response = esClient.indices().putMapping(request, RequestOptions.DEFAULT);

        // 异步方式
        esClient.indices()
                .putMappingAsync(request, RequestOptions.DEFAULT, new ActionListener<AcknowledgedResponse>() {
                    @Override
                    public void onResponse(AcknowledgedResponse acknowledgedResponse) {
                        log.info("running... {}" , acknowledgedResponse);
                    }

                    @Override
                    public void onFailure(Exception e) {
                        log.error("error -> {}" ,e.getMessage());

                    }
                });
    }


    public void addDocument1( String jsonIndexName) throws IOException {
        IndexRequest request = new IndexRequest(jsonIndexName);
        request.id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2020-03-28\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);

        request.routing("routing");

        esClient.index(request, RequestOptions.DEFAULT);
    }


    public void addDocument2(String mapIndexName){
        HashMap<String, Object> map = new HashMap<>();

        map.put("user", "kimchy");
        map.put("postDate", new Date());
        map.put("message", "trying out Elasticsearch");

        IndexRequest request = new IndexRequest(mapIndexName).id("1").source(map);

        request.routing("routing");

        esClient.indexAsync(request, RequestOptions.DEFAULT, new ActionListener<IndexResponse>() {
            @Override
            public void onResponse(IndexResponse indexResponse) {
                log.info("running... {}" , indexResponse);
            }

            @Override
            public void onFailure(Exception e) {
                log.error("error {} " , e.getMessage());
            }
        });
    }


    public void updateDocument(String indexName) throws IOException {
        // documentId
        UpdateRequest request = new UpdateRequest(indexName, "1");

        // 组装更新内容的数据结构有四种: json字符串、Map、XContentBuilder、Key-Value
        // json字符串
//        String jsonString = "{" +
//                "\"updated\":\"2020-03-29\"," +
//                "\"reason\":\"daily update\"" +
//                "}";
//        request.doc(jsonString);

        // Map
//        Map<String, Object> jsonMap = new HashMap<>();
//        jsonMap.put("updated", new Date());
//        jsonMap.put("reason", "daily update");
//        request.doc(jsonMap);

        // XContentBuilder
//        XContentBuilder builder = XContentFactory.jsonBuilder();
//        builder.startObject();
//        builder.timeField("updated", new Date());
//        builder.timeField("reason", "daily update");
//        builder.endObject();
//        request.doc(builder);

        // Key-Value
        request.doc("updated", new Date(),"reason", "daily update");

        // 同步的方式发送更新请求
        esClient.update(request, RequestOptions.DEFAULT);
    }

    public void deleteDocument(String indexName) throws IOException {
        DeleteByQueryRequest queryRequest = new DeleteByQueryRequest();
        queryRequest.setQuery(new TermQueryBuilder("user" , "kimchy"));
        // 忽略版本冲突
        queryRequest.setConflicts("proceed");

        esClient.deleteByQuery(queryRequest , RequestOptions.DEFAULT);
    }

    public void bulkDocument(String indexName) throws IOException{
        BulkRequest request = new BulkRequest();
        // 删除操作
        request.add(new DeleteRequest(indexName, "3"));
        // 更新操作
        request.add(new UpdateRequest(indexName, "2")
                .doc(XContentType.JSON,"other", "test"));
        // 普通的PUT操作，相当于全量替换或新增
        request.add(new IndexRequest(indexName).id("4")
                .source(XContentType.JSON,"field", "baz"));
        esClient.bulk(request, RequestOptions.DEFAULT);
    }


    public void searchDocument(){

    }




}
