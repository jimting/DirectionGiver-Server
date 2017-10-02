package ntou.jt;

public class RoadInfo
{
    private String distance;
    private String duration;
    private location end_location;
    private location start_location;
    private String html_instructions;
    private String polyline;
    private String travel_mode;
    //private NearBy landMark[];
    //private NearBy nearShops[];
    //private NearBy nearBy[];
    //private double userPosition;
    public RoadInfo(String distance,String duration,location end_location,location start_location,String html_instructions,String polyline,String travel_mode)
    {
        this.distance = distance;
        this.duration = duration;
        this.end_location = end_location;
        this.start_location = start_location;
        this.html_instructions = html_instructions;
        this.polyline = polyline;
        this.travel_mode = travel_mode;
        /*this.userPosition = GetJiaoDu(start_location.Y,start_location.X, end_location.Y,end_location.X);
        try 
        {
			//this.landMark = GetNearBy.getLandMark(start_location.X,start_location.Y);
			//this.nearShops = GetNearBy.getShops(start_location.X,start_location.Y);
			this.nearBy = GetNearBy.getNearBy(end_location.Y,end_location.X,userPosition);
        } 
        catch (Exception e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
    public RoadInfo(RoadInfo temp) 
	{
    	this.distance = temp.distance;
        this.duration = temp.duration;
        this.end_location = temp.end_location;
        this.start_location = temp.start_location;
        this.html_instructions = temp.html_instructions;
        this.polyline = temp.polyline;
        this.travel_mode = temp.travel_mode;
        /*this.userPosition = GetJiaoDu(start_location.Y,start_location.X, end_location.Y,end_location.X);
        try 
        {
			//this.landMark = GetNearBy.getLandMark(start_location.X,start_location.Y);
			//this.nearShops = GetNearBy.getShops(start_location.X,start_location.Y);
			this.nearBy = GetNearBy.getNearBy(end_location.Y,end_location.X,userPosition);
        } 
        catch (Exception e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
    public RoadInfo() 
    {
		// TODO Auto-generated constructor stub
	}
	
	public void setDistance(String distance)
    {
        this.distance = distance;
    }
    public String getDistance()
    {
        return distance;
    }
    public void setDuration(String duration)
    {
        this.duration = duration;
    }
    public String getDuration()
    {
        return duration;
    }
    public void setHtml_instructions(String html_instructions)
    {
        this.html_instructions = html_instructions;
    }
    public String getHtml_instructions()
    {
        return html_instructions;
    }
    public void setPolyline(String polyline)
    {
        this.polyline = polyline;
    }
    public String getPolyline()
    {
        return polyline;
    }
    public void setTravel_mode(String travel_mode)
    {
        this.travel_mode = travel_mode;
    }
    public String getTravel_mode()
    {
        return travel_mode;
    }
    public void setStart_location(location start_location)
    {
        this.start_location = start_location;
    }
    public location getStart_location()
    {
        return start_location;
    }
    public void setEnd_location(location end_location)
    {
        this.end_location = end_location;
    }
    public location getEnd_location()
    {
        return end_location;
    }
    
    /*public NearBy[] getLandMark() {
		return landMark;
	}
	public void setLandMark(NearBy[] landMark) {
		this.landMark = landMark;
	}
	public NearBy[] getNearShops() {
		return nearShops;
	}
	public void setNearShops(NearBy[] nearShops) {
		this.nearShops = nearShops;
	}*/
	/*public NearBy[] getNearBy() {
		return nearBy;
	}
	public void setNearBy(NearBy[] nearBy) {
		this.nearBy = nearBy;
	}
	public String NearBytoString()
    {
    	int totalLenth = nearBy.length;
    	NearBy[] x = new NearBy[totalLenth];
    	//將地標與商家合併於陣列x
    	for(int i = 0; i < totalLenth;i++)
    	{
    		x[i] = nearBy[i];
    	}
    	//依照與查詢點的距離，由小到大做排序
    	for(int i=x.length-1;i>=0;i=i-1)
    	{
	    	for(int j=0;j<i;j=j+1)
	    	{
		    	if (x[j].getDistance()>x[i].getDistance())
		    	{
			    	NearBy tmp = x[j];
			    	x[j]=x[i];
			    	x[i]=tmp;
		    	}
	    	}
    	}
    	
    	String result = "";
    	//印出全部的商家景點(排序後)
    	for(NearBy temp:x)
		{
    		if(temp.getDistance() <= 10) //小於等於20公尺再顯示
    		{
    			result+=temp;
    		}
		}
    	if(result == "")//如果附近沒有景點，就顯示沒有景點
    	{
    		return "附近10公尺內沒有商店或地標\n";
    	}
		return result;
    }*/
    @Override
    public String toString()
    {
    	String result = "起始：("+start_location+")\n路線："+html_instructions+"\n結束：("+end_location+")\n\n\n"/*下個路口資訊：\n"+ NearBytoString()*/;
        return result;
    }
    public static double GetJiaoDu(double lat1, double lng1, double lat2, double lng2)
    {
        double x1 = lng1;
        double y1 = lat1;
        double x2 = lng2;
        double y2 = lat2;
        double pi = Math.PI;
        double w1 = y1 / 180 * pi;
        double j1 = x1 / 180 * pi;
        double w2 = y2 / 180 * pi;
        double j2 = x2 / 180 * pi;
        double ret;
        if (j1 == j2)
        {
            if (w1 > w2) return 270;
            else if (w1 < w2) return 90;
            else return -1;
        }
        ret = 4 * Math.pow(Math.sin((w1 - w2) / 2), 2) - Math.pow(Math.sin((j1 - j2) / 2) * (Math.cos(w1) - Math.cos(w2)), 2);
        ret = Math.sqrt(ret);
        double temp = (Math.sin(Math.abs(j1 - j2) / 2) * (Math.cos(w1) + Math.cos(w2)));
        ret = ret / temp;
        ret = Math.atan(ret) / pi * 180;
        if (j1 > j2)
        {
            if (w1 > w2) ret += 180;
            else ret = 180 - ret;
        }
        else if (w1 > w2) ret = 360 - ret;
        return ret;
    }
}