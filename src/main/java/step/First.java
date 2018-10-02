/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package step;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Home
 */
public class First {
    public static void main(String[] arg) throws Exception{
CloseableHttpClient httpclient = HttpClients.createDefault();
//HttpGet httpGet = new HttpGet("https://www.i.ua/");
//CloseableHttpResponse response1 = httpclient.execute(httpGet);
//// The underlying HTTP connection is still held by the response object
//// to allow the response content to be streamed directly from the network socket.
//// In order to ensure correct deallocation of system resources
//// the user MUST call CloseableHttpResponse#close() from a finally clause.
//// Please note that if response content is not fully consumed the underlying
//// connection cannot be safely re-used and will be shut down and discarded
//// by the connection manager. 
//try {
//    System.out.println(response1.getStatusLine());
//    HttpEntity entity1 = response1.getEntity();
//    // do something useful with the response body
//    // and ensure it is fully consumed
//    EntityUtils.consume(entity1);
//} finally {
//    response1.close();
//}

HttpPost httpPost = new HttpPost("https://itstep.org/ru/");
//List <NameValuePair> nvps = new ArrayList <>();
//nvps.add(new BasicNameValuePair("login", "dimatarik@i.ua"));
//nvps.add(new BasicNameValuePair("pass", "19tarik79"));
//nvps.add(new BasicNameValuePair("_subm:", "lform"));
//nvps.add(new BasicNameValuePair("_url", "http://mbox.i.ua/?_rand=1537794166&phcode=aa19bbef5b6bac1ff59e92de9ec4a1f2"));
//nvps.add(new BasicNameValuePair("scode", "aeee29b2f4deda1d457a8ae19876ab96"));
//nvps.add(new BasicNameValuePair("_rand", "0.020110894221587072"));
////nvps.add(new BasicNameValuePair("", ""));
//
//httpPost.setEntity(new UrlEncodedFormEntity(nvps));
CloseableHttpResponse response2 = httpclient.execute(httpPost);

try {
    System.out.println(response2.getStatusLine());
    HttpEntity entity2 = response2.getEntity();  
    File file = new File("F:\\TEST.html");
    FileOutputStream fos = new FileOutputStream(file);
     entity2.writeTo(fos);
     fos.close();
    // do something useful with the response body
    // and ensure it is fully consumed
    EntityUtils.consume(entity2);
} finally {
    response2.close();
}

  java.net.URI uri;
 
        try {
            uri = new URI("file:F:/TEST.html");
            java.awt.Desktop.getDesktop().browse(uri);         
        } catch (IOException ex) {
            ex.printStackTrace();
        }  catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
    
}
