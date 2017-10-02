package ntou.jt;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetShopRating 
{
	public static String getShopRating(String name) throws IOException
	{
		String url = "https://www.google.com.tw/search?q="+name;
		Connection con = Jsoup.connect(url).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10000);
	    Connection.Response resp = con.execute();
	    Document doc = null;
	    if (resp.statusCode() == 200)
	    {
	        doc = con.get();
	    }
	    Elements GoogleRating = doc.select("div._A8k");
	    Elements FBRating = doc.select("div._f4n");
	    Element searchResult = doc.getElementById("resultStats");
	    
	    String content = GoogleRating.html();
	    content += FBRating.html();
	    content += searchResult.html();
		return content;	
	}
}
