package com.nbank.dm.rule.server.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * @author WY
 */
public class HttpUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    public static String post(Map<String,String> map,String url) throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntity entity = new MultipartEntity();
        if(map!=null) {
            for(Entry<String, String> entry:map.entrySet()) {
            	entity.addPart(entry.getKey(),new StringBody(entry.getValue()));
            }	
        }
        post.setEntity(entity);
        HttpResponse response = httpclient.execute(post);
        String result = "";
        if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
            HttpEntity entitys = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entitys);
            }
        } else {
            result = response.toString();
        }
        httpclient.getConnectionManager().shutdown();
        return result;
    }
    public static String sendPost(Map<String,String> requestMap,String url) throws Exception {
    	HttpClient httpclient = new DefaultHttpClient();  
		HttpPost post = new HttpPost(url);  
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();  
		  
        if(requestMap!=null) {
            for(Entry<String, String> entry:requestMap.entrySet()) {
            	NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            	pairs.add(pair);
            }	
        }
		post.setEntity(new UrlEncodedFormEntity(pairs,HTTP.UTF_8));  
		HttpResponse response = httpclient.execute(post);
		String result = "";
		if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){    
			
			HttpEntity entitys = response.getEntity();  
			if (entitys != null) {  
				result=EntityUtils.toString(entitys);
			}  

		}else{
			result = response.toString();
		}
		httpclient.getConnectionManager().shutdown();  
		return result;
    }
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
//            for (String key : map.keySet()) {
//                System.out.println(key + "--->" + map.get(key));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}
