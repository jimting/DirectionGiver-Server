package DG;

public class NearByHotel
{
	private String add;
	private String des;
	private String name;
	private String grade;
	private String parking;
	private String service;
	private String px;
	private String py;
	private String tel;
	private String web;
	public NearByHotel(String add,String des,String name,String grade,String parking,String service,String px,String py,String tel,String web)
	{
		des = des.replace("\"", "'");
		des = des.replace("\r", "");
		des = des.replace("\n", "");
		this.add = add.replace("\"", "'");
		this.des = des;
		this.name = name.replace("\"", "'");
		this.parking = parking.replace("\"", "'");
		this.service = service.replace("\"", "'");
		this.grade = grade.replace("\"", "'");
		this.px = px;
		this.py = py;
		this.tel = tel.replace("\"", "'");
		this.web = web;
	}
	
	public String toString()
	{
		return "INSERT INTO `hotel`(`name`, `address`, `grade`, `description`, `parkingInfo`, `service`, `px`, `py`, `tel`, `website`, `flag`)VALUES(\"" + this.name + "\",\"" + this.add + "\",\"" + this.grade + "\",\"" + this.des + "\",\"" + this.parking + "\",\"" + this.service + "\",\"" + this.px + "\",\"" + this.py + "\",\"" + this.tel + "\",\"" + this.web + "\",0)" ;
	}

	
}