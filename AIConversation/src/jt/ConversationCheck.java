package jt;

import org.json.JSONException;
import org.json.JSONObject;

public class ConversationCheck 
{
	static String checkJson(String jsonString)
    {
        //System.out.println("StartGettingJson");
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //System.out.println(jsonObj.toString());

        //確認被分類到哪個intent
        String whatIntent = null;
        try {
            whatIntent = jsonObj.getJSONObject("result").getJSONObject("metadata").getString("intentName");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        System.out.println(whatIntent);
        
        //如果是問天氣，且什麼資訊都沒有
        if(whatIntent.equals("weatherNothing"))
        {
        	return "weatherNothing;";
        }
        else if(whatIntent.equals("weatherNotDate"))
        {
        	String city = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("geo-city");
        	return "weatherNotDate;" + city;
        }
        else if(whatIntent.equals("whereToGo"))
        {
        	String destination = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("shop");
        	return "whereToGo;" + destination;
        }
        else if(whatIntent.equals("weatherNotLocation"))
        {
        	String date = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("date");
        	return "weatherNotLocation;" + date;
        }
        else if(whatIntent.equals("whatIsShopData"))
        {
        	String shop = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("shop");
        	return "whatIsShopData;" + shop;
        }
        else if(whatIntent.equals("defaultWelcomeIntent"))
        {
        	String welcome = jsonObj.getJSONObject("result").getJSONObject("fulfillment").getString("speech");
        	return "defaultWelcomeIntent;" + welcome;
        }
        else if(whatIntent.equals("howManyTime"))
        {
        	return "howManyTime;";
        }
        else if(whatIntent.equals("weatherComposite"))
        {
        	String city = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("geo-city");
        	String date = jsonObj.getJSONObject("result").getJSONObject("parameters").getString("date");
        	return "weatherNotLocation;" + city + ";" + date;
        }
        else if(whatIntent.equals("filterDirtyWords"))
        {
        	return "filterDirtyWords;";
        }
        else if(whatIntent.equals("goToilet"))
        {
        	return "goToilet;";
        }
        else//聽不懂
        {
        	String IDontKnow = jsonObj.getJSONObject("result").getJSONObject("fulfillment").getString("speech");
        	return "defaultFallbackIntent;" + IDontKnow;
        }
    }
	public static void main(String arg[])
	{
		checkJson("我想去廁所");
        
	}
}
