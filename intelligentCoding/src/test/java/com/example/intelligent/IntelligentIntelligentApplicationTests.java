package com.example.intelligent;

import com.alibaba.fastjson.JSON;
import com.intelligent.config.ElasticSearchConfig;
import com.intelligent.model.Users;
import com.intelligent.util.ESconst;
import com.sun.corba.se.spi.activation.ServerAlreadyRegistered;
import kafka.utils.Json;
import org.apache.catalina.User;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
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
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootTest
//@RunWith(ElasticSearchConfig.class)
class IntelligentIntelligentApplicationTests {

	@Autowired
	@Resource
	private RestHighLevelClient client;
	@Test
	void contextLoads() {
	}

	// 测试索引的创建 Request
	@Test
	void testCreateIndex() throws IOException {
		// 1、创建索引请求
		CreateIndexRequest request = new CreateIndexRequest("wx_index");
		// 2、执行请求, IndicesClient,请求后获得响应
		CreateIndexResponse response = client.indices().create(request, RequestOptions.DEFAULT);
		System.out.println(response);
	}

	// 测试获取索引
	@Test
	void testExistIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("wx_index");
		boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);

	}

	//测试删除索引
	@Test
	void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("wx_index");
		AcknowledgedResponse isDelete = client.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(isDelete.isAcknowledged());
	}

	// 测试添加文档
	@Test
	void testAddDocument() throws IOException {
		// 创建对象
		Users users =  new Users();
		users.setName("wanwan");
		users.setIntro("2020.11.04哦");
		// 创建请求,创建请求之前请先确认索引已经创建好了，所以还是要执行上面的创建索引的请求
		IndexRequest request = new IndexRequest("wx_index");

		// 创建规则 PUT /wx_index/_doc/1
		request.id("1");
		request.timeout(TimeValue.timeValueSeconds(1));

		// 将数据放入请求 json数据格式
		request.source(users);

		// 客户端发送请求,获取响应的结果
		IndexResponse index = client.index(request, RequestOptions.DEFAULT);

		System.out.println(index.status());
		System.out.println(index.toString());
	}

	// 获取文档,首先判断文档是否存在 GET /index/doc/1
	@Test
	void testIsExist() throws IOException {
		GetRequest request = new GetRequest("wx_indx", "1");
		// 不获取返回的_source的上下文了
		request.fetchSourceContext(new FetchSourceContext(false));
		request.storedFields("_none_");

		boolean exist = client.exists(request, RequestOptions.DEFAULT);
		System.out.println(exist);

	}

	// 获取文档信息， GET /index/doc/1/_search
	@Test
	void testGetDocument() throws IOException {
		GetRequest request =  new GetRequest("wx_indx", "1");

		GetResponse response = client.get(request, RequestOptions.DEFAULT);
		String source = response.getSourceAsString();
		System.out.println(source);
		System.out.println(response);
	}

	// 更新文档信息，POST /index/doc/1/_update
	@Test
	void testUpdateDocument() throws IOException {
		UpdateRequest request = new UpdateRequest("wx_indx", "1");
		request.timeout(TimeValue.timeValueSeconds(1));
		Users  users = new Users();
		users.setName("shirley");
		users.setIntro("新增加的一个用户");
		request.doc(JSON.toJSON(users));
		UpdateResponse response = client.update(request, RequestOptions.DEFAULT);

		System.out.println(response.status());
	}

	// 删除文档记录
	@Test
	void testDeleteDocument() throws IOException {
		DeleteRequest request = new DeleteRequest("wx_index", "1");
		request.timeout("1s");
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		System.out.println(response.status());
	}

	// 特殊的，真实的项目一般都是批量操作数据！
	@Test
	void  testBuklRequest() throws IOException {
		BulkRequest request = new BulkRequest();
//		request.timeout("10s");
		ArrayList<Users> userList = new ArrayList<>();
		Users user1 = new Users();
		user1.setIntro("我是一号用户");
		user1.setName("No.1");
		Users user2 = new Users();
		user2.setName("No.2");
		user2.setIntro("我是二号用户");
		Users user3 = new Users();
		user3.setName("No.3");
		user3.setIntro("我是三号用户");
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);

		// 数据的插入操作, 批处理请求
		for (int i = 0; i < userList.size(); i++) {
			// 批量更新和批量删除就在这里面修改对应的请求就行了
			request.add(
					new IndexRequest("wx_index")
//					.id(""+(i+1)) // 这里不设置id的话就会生成随机的id，可以保证没有重复的id
					.source(userList.get(i))
			);
		}
		BulkResponse responses = client.bulk(request, RequestOptions.DEFAULT);
		System.out.println(responses.status());
	}

	// 查询
	// 重点内容：SearchRequest 搜索请求
	// SearchSourceBuilder 搜索条件构造
	// HighlightBuilder 构建高亮
	// TermQueryBuilder 构建精确查询
	// MatchAllQueryBuilder 匹配全部
	// xxxQueryBuilder 对应所有的查询命令
	@Test
	void testSearch() throws IOException {
		// 1、构建查询请求 SearchRequest
		SearchRequest request = new SearchRequest(ESconst.USER_INDEX);

		// 2、构建搜索条件 SearchSourceBuilder:分页搜索，高亮字段,查询条件
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		sourceBuilder.highlighter();// 高亮
		// 查询条件，可以使用QueryBuilders 工具类 快速使用
		// QueryBuilders.termQuery 精确查询
		TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "No.1");
		sourceBuilder.query(termQueryBuilder);
		// QueryBuilders.matchAllQuery 匹配所有查询
		MatchAllQueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
		sourceBuilder.query(matchAllQuery);
		// 查询条件构造好之后设置查询规则，比如查询时间等等的条件
		sourceBuilder.timeout(TimeValue.timeValueSeconds(2));

		// 设置分页查询
		sourceBuilder.from();
		sourceBuilder.size();

		// 3、搜索条件放入请求当中
		request.source(sourceBuilder);
		// 4、执行请求 client上线
		SearchResponse response = client.search(request, RequestOptions.DEFAULT);

		// 5、查看结果
		System.out.println(response.getHits().toString());
		for (SearchHit hit : response.getHits()) {
			System.out.println(hit.getSourceAsMap());
		}
	}
}
