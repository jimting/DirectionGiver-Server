package DG;

public class NearBy 
{
	private String add;
	private String kind;
	private String des;
	private String name;
	private String openTime;
	private String px;
	private String py;
	private String tel;
	private String web;
	public NearBy(String add,String kind,String des,String name,String openTime,String px,String py,String tel,String web)
	{
		this.add = add.replace("\"", "'");
		this.kind = kind.replace("\"", "'");
		if(kind.equals("01")||kind.equals("1"))
		{	
			if(kind.equals("01"))
				{
				this.kind = kind.replace("01","異國料理");
				}else
				{
				this.kind = kind.replace("1","異國料理");
				}
		}
		else if(kind.equals("02")||kind.equals("2"))
		{
			if(kind.equals("02"))
			{
			this.kind = kind.replace("02","火烤料理");
			}else
			{
			this.kind = kind.replace("2","火烤料理");
			}
		}
		else if(kind.equals("03")||kind.equals("3"))
		{
			if(kind.equals("03"))
			{
			this.kind = kind.replace("03","中式美食");
			}else
			{
			this.kind = kind.replace("3","中式美食");
			}
		}
		else if(kind.equals("04")||kind.equals("4"))
		{
			if(kind.equals("04"))
			{
			this.kind = kind.replace("04","夜市小吃");
			}else
			{
			this.kind = kind.replace("4","夜市小吃");
			}
		}
		else if(kind.equals("05")||kind.equals("5"))
		{
			if(kind.equals("05"))
			{
			this.kind = kind.replace("05","甜點冰品");
			}else
			{
			this.kind = kind.replace("5","甜點冰品");
			}
		}
		else if(kind.equals("06")||kind.equals("6"))
		{
			if(kind.equals("06"))
			{
			this.kind = kind.replace("06","伴手禮");
			}else
			{
			this.kind = kind.replace("6","伴手禮");
			}
		}
		else if(kind.equals("07")||kind.equals("7"))
		{
			if(kind.equals("07"))
			{
			this.kind = kind.replace("07","地方特產");
			}else
			{
			this.kind = kind.replace("7","地方特產");
			}
		}
		else if(kind.equals("08")||kind.equals("8"))
		{
			if(kind.equals("08"))
			{
			this.kind = kind.replace("08","素食");
			}else
			{
			this.kind = kind.replace("8","素食");
			}
		}
		else if(kind.equals("09")||kind.equals("9"))
		{
			if(kind.equals("09"))
			{
			this.kind = kind.replace("09","其他");
			}else
			{
			this.kind = kind.replace("9","其他");
			}
		}
		this.des = des.replace("\"", "'");
		this.name = name.replace("\"", "'");
		this.openTime = openTime.replace("\"", "'");
		this.px = px;
		this.py = py;
		this.tel = tel.replace("\"", "'");
		this.web = web;
	}
	
	public String toString()
	{
		return "INSERT INTO `restaurant`(`NAME`, `ADDRESS`, `DESCRIPTION`, `CLASS`, `OPENTIME`, `PX`, `PY`, `TEL`, `WEBSITE`, `flag`)VALUES(\"" + this.name + "\",\"" + this.add + "\",\"" + this.des + "\",\"" + this.kind + "\",\"" + this.openTime + "\",\"" + this.px + "\",\"" + this.py + "\",\"" + this.tel + "\",\"" + this.web + "\",0)" ;
	}

	
}