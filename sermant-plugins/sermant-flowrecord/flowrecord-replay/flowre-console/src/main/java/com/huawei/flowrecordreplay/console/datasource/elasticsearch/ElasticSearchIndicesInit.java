/*
 * Copyright (C) 2021-2021 Huawei Technologies Co., Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.huawei.flowrecordreplay.console.datasource.elasticsearch;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 用于初始化 ES 表格
 *
 * @author luanwenfei
 * @version 0.0.1
 * @since 2021-04-12
 */
@Component
public class ElasticSearchIndicesInit {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchIndicesInit.class);

    @Autowired
    RestHighLevelClient restHighLevelClient;

    public void replayResult(String index) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        request.settings(Settings.builder().put("index.number_of_shards", 1)
                .put("index.number_of_replicas", 1));
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .startObject("properties")
                .startObject("traceId").field("type", "keyword").endObject()
                .startObject("method").field("type", "keyword").endObject()
                .startObject("recordTime").field("type", "text").endObject()
                .startObject("replayTime").field("type", "text").endObject()
                .startObject("compareResult").field("type", "boolean").endObject()

                .startObject("fieldCompare")
                .startObject("properties")
                .startObject("name").field("type", "keyword").endObject()
                .startObject("record").field("type", "text").endObject()
                .startObject("replay").field("type", "text").endObject()
                .startObject("compare").field("type", "boolean").endObject()
                .startObject("ignore").field("type", "boolean").endObject()
                .endObject()
                .endObject()
                .endObject()
                .endObject();
        request.mapping(builder);
        restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
    }
}
