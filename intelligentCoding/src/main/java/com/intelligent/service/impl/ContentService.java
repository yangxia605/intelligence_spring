package com.intelligent.service.impl;

import com.intelligent.model.Users;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

}
