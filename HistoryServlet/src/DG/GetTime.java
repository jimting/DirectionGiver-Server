package DG;

import java.util.*;
import java.text.SimpleDateFormat;

public class GetTime {
		public String getNowTime(){
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String now = sdFormat.format(date);
		System.out.println(now);
		return now;
	}
}
