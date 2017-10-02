package DG;


public class Square {
	public double dlng(double lng,double lat,double distance){
		double dlng1 = 2 * Math.asin(Math.sin(distance/(2 * 6378.137)));
		double dlng2 = Math.cos(Math.toRadians(lat));
		double dlng = dlng1 / dlng2;
		dlng = Math.toDegrees(dlng);
		return dlng;
	}
	public double dlat(double lat,double distance){
		double dlat = distance/6378.137;
		dlat = Math.toDegrees(dlat);
		return dlat;
	}

}