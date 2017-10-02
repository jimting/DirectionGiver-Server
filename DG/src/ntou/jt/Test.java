package ntou.jt;

public class Test 
{
	public static void main(String args[])
	{
		try {
			NearBy[] temp = GetNearBy.getRest(0, 0, 0);
			for(NearBy i : temp)
			{
				System.out.println(i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
