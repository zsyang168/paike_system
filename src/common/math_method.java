package common;

public class math_method {

	public static double min(double data[])
	{
		int length = data.length;
		double min =data[1];
		for(int i = 2; i < length; i ++)
		{
			if (data[i] < min)
				min = data[i];
		}
		return min;
	}

	public static double max(double data[])
	{
		int length = data.length;
		double max =data[1];
		for(int i = 2; i < length; i ++)
		{
			if (data[i] > max)
				max = data[i];
		}
		return max;
	}
}
