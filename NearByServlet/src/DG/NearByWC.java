package DG;

public class NearByWC 
{
	private String country;
	private String city;
	private String admin;
	private String name;
	private String add;
	private String type;
	private String lng;
	private String grade;
	private String lat;
	public NearByWC(String country,String city,String admin,String name,String add,String type,String lng,String grade,String lat)
	{
		this.country = country.replace("\"", "'");
		this.city = city.replace("\"", "'");
		this.admin = admin.replace("\"", "'");
		this.name = name.replace("\"", "'");
		this.add = add.replace("\"", "'");
		this.type = type.replace("\"", "'");
		this.lng = lng.replace("\"", "'");
		this.grade = grade.replace("\"", "'");
		this.lat = lat.replace("\"", "'");
	}
	
	public String toString()
	{
		return "INSERT INTO `wc`(`COUNTRY`, `CITY`, `ADMINISTRATION`, `NAME`, `ADDRESS`, `TYPE`, `PX`, `GRADE`, `PY`)VALUES(\"" + this.country + "\",\"" + this.city + "\",\"" + this.admin + "\",\"" + this.name + "\",\"" + this.add + "\",\"" + this.type + "\",\"" + this.lng + "\",\"" + this.grade + "\",\"" + this.lat + "\")" ;
	}

	
}