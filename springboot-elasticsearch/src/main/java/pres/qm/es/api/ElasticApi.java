package pres.qm.es.api;

import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author HJC
 * @version 1.0
 * 谦谦君子 卑以自牧也
 * @date 2020/9/9  9:46
 * @description :
 */
@Service
@RequiredArgsConstructor( onConstructor = @__(@Autowired))
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
}
