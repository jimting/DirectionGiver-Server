package ntou.jt;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Demo {
public static void main(String[] args) {
String addr = GetAddr("25.0360641", "121.452156");
System.out.println(addr);
//getCoordinate("中國");
}
/**
* 根據經緯度反向解析位址，有時需要多嘗試幾次
* 注意:(摘自：HTTP://code.google.com/intl/zh-CN/apis/maps/faq.html
* 提交的位址解析請求次數是否有限制？) 如果在 24 小時時段內收到來自一個 IP 位址超過 2500 個位址解析請求， 或從一個 IP
* 位址提交的位址解析請求速率過快，Google 地圖 API 編碼器將用 620 狀態碼開始回應。 如果位址解析器的使用仍然過多，則從該 IP
* 位址對 Google 地圖 API 位址解析器的訪問可能被永久阻止。
*
* @param latitude
* 緯度
* @param longitude
* 經度
* @return
*/
public static String GetAddr(String latitude, String longitude) {
String addr = "";
//也可以是HTTP://maps.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s，不過解析出來的是英文位址
//金鑰可以隨便寫一個key=abc
//output=csv,也可以是xml或json，不過使用csv返回的資料最簡潔方便解析
String url = String.format(
"HTTP://ditu.google.cn/maps/geo?output=csv&key=abcdef&q=%s,%s",
latitude, longitude);
URL myURL = null;
URLConnection HTTPsConn = null;
try {
myURL = new URL(url);
} catch (MalformedURLException e) {
e.printStackTrace();
return null;
}
try {
HTTPsConn = (URLConnection) myURL.openConnection();
if (HTTPsConn != null) {
InputStreamReader insr = new InputStreamReader(
HTTPsConn.getInputStream(), "UTF-8");
BufferedReader br = new BufferedReader(insr);
String data = null;
if ((data = br.readLine()) != null) {
System.out.println(data);
String[] retList = data.split(",");
if (retList.length > 2 && ("200".equals(retList[0]))) {
addr = retList[2];
addr = addr.replace("\"", "");
} else {
addr = "";
}
}
insr.close();
}
} catch (IOException e) {
e.printStackTrace();
return null;
}
return addr;
}

public static void getCoordinate(String addr)
{
String addrs = "";
String address = null;
try {
address = java.net.URLEncoder.encode(addr,"UTF-8");
} catch (UnsupportedEncodingException e1) {
e1.printStackTrace();
};
String output = "csv";
String key = "abc";
String url = String.format("HTTP://maps.google.com/maps/geo?q=%s&output=%s&key=%s", address, output, key);
URL myURL = null;
URLConnection HTTPsConn = null;
//進行轉碼
try {
myURL = new URL(url);
} catch (MalformedURLException e) {
e.printStackTrace();
}

try {
HTTPsConn = (URLConnection) myURL.openConnection();
if (HTTPsConn != null) {
InputStreamReader insr = new InputStreamReader(
HTTPsConn.getInputStream(), "UTF-8");
BufferedReader br = new BufferedReader(insr);
String data = null;
if ((data = br.readLine()) != null) {
System.out.println(data);
String[] retList = data.split(",");
/*
String latitude = retList[2];
String longitude = retList[3];

System.out.println("緯度"+ latitude);
System.out.println("經度"+ longitude);
*/
if (retList.length > 2 && ("200".equals(retList[0]))) {
addrs = retList[2];
addrs = addr.replace("\"", "");
} else {
addrs = "";
}
}
insr.close();
}
} catch (IOException e) {
e.printStackTrace();
}
System.out.println(addrs);
}
}
