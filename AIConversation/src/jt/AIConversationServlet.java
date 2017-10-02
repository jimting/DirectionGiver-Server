package jt;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class AIConversationServlet
 */
@WebServlet("/AIConversationServlet")
public class AIConversationServlet extends HttpServlet {
	private String tokenId = "Bearer 2b0ed04782064644b8066768bbda2fa5";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AIConversationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String userInput = request.getParameter("userInput");
        String userInputtmp = new String(request.getParameter("userInput").getBytes("UTF-8"));
        String url = "https://api.api.ai/v1/query?v=20150910";
        url = this.stringParser(url);
        String urlParameters = "{\"query\": [\"" + userInput + "\"],\"lang\": \"zh-cn\",\"sessionId\": \"1234567890\"}";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json;charset=utf-8");
        post.setHeader("Authorization", tokenId);
        post.setEntity(new StringEntity(urlParameters, "UTF-8"));
        HttpResponse theResponse = httpClient.execute(post);
        HttpEntity entity = theResponse.getEntity();
        String responseString = EntityUtils.toString(entity, "UTF-8");
        //System.out.println(responseString);
        String result = ConversationCheck.checkJson(responseString);
        out.write(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	 public String stringParser(String url) throws IOException
	 {
	        String url2 = new String();
	        for (int j = 0; j < url.length(); j++)
	        {
	            if (url.substring(j, j + 1).matches("[\\u4e00-\\u9fa5]+"))
	            {
	                try
	                {
	                    url2 = url2 + URLEncoder.encode(url.substring(j, j + 1),"UTF-8");
	                }
	                catch (UnsupportedEncodingException e)
	                {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            else
	            {
	                url2 = url2 + url.substring(j, j + 1).toString();
	            }
	        }

	        return url2;
	}
}
