package cn.boy.solrj.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.junit.Before;
import org.junit.Test;

import cn.boy.solrj.pojo.Item;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class ItemService {

    private HttpSolrServer httpSolrServer;

    @Before
    public void init() {
        String baseURL = "http://solr.mytaotao.com/mytaotao";
        this.httpSolrServer = new HttpSolrServer(baseURL);
        httpSolrServer.setMaxRetries(1);// 最大重新连接次数
        httpSolrServer.setConnectionTimeout(5000);// 连接超时时间
        httpSolrServer.setParser(new XMLResponseParser());// 设置响应解析器
    }

    @Test
    public void del() throws Exception {
        this.httpSolrServer.deleteByQuery("*:*");
        this.httpSolrServer.commit();
    }

    @Test
    public void addData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        // 先通过后台获取数据
        String url = "http://manage.mytaotao.com/rest/item?page={page}&rows=200";
        Integer page = 1;
        Integer pageSize;
        do {
            String jsonData = doGet(StringUtils.replace(url, "{page}", String.valueOf(page)));

            JsonNode jsonNode = mapper.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            List<Item> items = mapper.readValue(rows.toString(), mapper.getTypeFactory()
                    .constructCollectionType(List.class, Item.class));
            // 写入索引库中
            this.httpSolrServer.addBeans(items);
            this.httpSolrServer.commit();
            pageSize = items.size();
            page++;
        } while (pageSize == 200);

    }

    // 使用HttpClient远程获取数据
    public String doGet(String url) throws Exception {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 创建http GET请求
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpclient.close();
        }
        return null;
    }
}
