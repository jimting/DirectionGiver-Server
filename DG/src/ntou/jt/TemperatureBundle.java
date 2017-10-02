package ntou.jt;

import java.util.Arrays;

public class TemperatureBundle
{
	String location; // 地理區域名稱
	String weekDates[]; // 本周日期
	String dayTemps[]; // 本周白天氣溫
	String nightTemps[]; // 本周晚上氣溫
	String dayImages[]; //本週白天氣候圖示
	String nightImages[]; //本週晚上氣候圖示

	public TemperatureBundle (String location, String[] weekDates,
			String[] dayTemps, String[] nightTemps, String dayImages[], String nightImages[])
	{
		this.location = location;
		this.weekDates = weekDates;
		this.dayTemps = dayTemps;
		this.nightTemps = nightTemps;
		this.dayImages = dayImages;
		this.nightImages = nightImages;
	}

	public String getLocation ()
	{
		return location;
	}

	public String[] getWeekDates ()
	{
		return weekDates;
	}

	public String[] getDayTemps ()
	{
		return dayTemps;
	}

	public String[] getNightTemps ()
	{
		return nightTemps;
	}
	
	public String[] getDayImages ()
	{
		return dayImages;
	}
	
	public String[] getNightImages ()
	{
		return nightImages;
	}

	@Override
	public String toString ()
	{
		return "TemperatureBundle [location=" + location + ", weekDates="
				+ Arrays.toString(weekDates) + ", dayTemps="
				+ Arrays.toString(dayTemps) + ", nightTemps="
				+ Arrays.toString(nightTemps) + ", dayImages="
				+ Arrays.toString(dayImages) + ", nightImages="
				+ Arrays.toString(nightImages) + "]";
	}
}
