package com.course.practicaljava.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class MyElasticsearchConfig extends ElasticsearchConfiguration {
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder().connectedTo("localhost:9200")
       //.usingSsl("cafingerprint")
                .withBasicAuth("elastic", "zHbRzazN98l1_2J7s*zh").build();
    }
}
