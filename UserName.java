package RestaurantManagement;

public class UserName {
	private static String userName; //�����¼�û���
	private static String tableNum;  //����������
	
	static void setUserName(String str)
	{
		userName=str;
	}
	
	static String getUserName()
	{
		return userName;
	}
	
	static void settableNum(String i)
	{
		tableNum=i;
	}
	
	static String gettableNum()
	{
		return tableNum;
	}
}
