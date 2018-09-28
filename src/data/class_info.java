package data;

public class class_info {
	private int class_id;
	private String class_name;
	private String class_type;
	public class_info(int id, String name, String type)
	{
		class_id = id;
		class_name = name;
		class_type = type;
	}
	
	public int get_id()
	{
		return class_id;
	}
	
	public String get_name()
	{
		return class_name;
	}
	
	public String get_type()
	{
		return class_type;
	}
}
