package DG;

public class GetFourSpot {
	public String getLtLng(String longitude,String latitude)
	{
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLng = square.dlng(lng, lat, distance);
		
		double left_top_lng = lng - dLng;
		left_top_lng = (double)(Math.floor(left_top_lng*1000000)/1000000);
		String ltln = String.valueOf(left_top_lng);

		return ltln;
		}
	public String getLtLat(String longitude,String latitude)
	{
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLat = square.dlat(lat, distance);
		
		double left_top_lat = lat + dLat;
		left_top_lat = (double)(Math.floor(left_top_lat*1000000)/1000000);
		String ltla = String.valueOf(left_top_lat);

		return ltla;
	}
	
	public String getRtLng(String longitude,String latitude)
	{
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLng = square.dlng(lng, lat, distance);

		double right_top_lng = lng + dLng;
		right_top_lng = (double)(Math.floor(right_top_lng*1000000)/1000000);
		String rtln = String.valueOf(right_top_lng);
		
		
		return rtln;
	}
	public String getRtLat(String longitude,String latitude)
	{
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLat = square.dlat(lat, distance);
		
		double right_top_lat = lat + dLat;
		right_top_lat = (double)(Math.floor(right_top_lat*1000000)/1000000);
		String rtla = String.valueOf(right_top_lat);
		
		return rtla;
	}
	public String getLbLng(String longitude,String latitude)
	{
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLng = square.dlng(lng, lat, distance);
		
	    double left_bottom_lng = lng - dLng;
	    left_bottom_lng = (double)(Math.floor(left_bottom_lng*1000000)/1000000);
	    String lbln = String.valueOf(left_bottom_lng);
	    
		return lbln;
	}
	public String getLbLat(String longitude,String latitude)
	{
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLat = square.dlat(lat, distance);
		
		double left_bottom_lat = lat - dLat;
		left_bottom_lat = (double)(Math.floor(left_bottom_lat*1000000)/1000000);
		String lbla = String.valueOf(left_bottom_lat);

		return lbla;
	}
	public String getRbLng(String longitude,String latitude)
	{
		double lng = Double.parseDouble(longitude);
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLng = square.dlng(lng, lat, distance);
		
		double right_bottom_lng = lng + dLng;
		right_bottom_lng = (double)(Math.floor(right_bottom_lng*1000000)/1000000);
		String rbln = String.valueOf(right_bottom_lng);
		
		return rbln;
	}
	public String getRbLat(String longitude,String latitude)
	{
		double lat = Double.parseDouble(latitude);
		double distance = 0.05;
		Square square = new Square();
		double dLat = square.dlat(lat, distance);
		
		double right_bottom_lat = lat - dLat;
		right_bottom_lat = (double)(Math.floor(right_bottom_lat*1000000)/1000000);
		String rbla = String.valueOf(right_bottom_lat);
		
		return rbla;
	}

}
