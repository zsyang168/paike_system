package common;

public class oa_time {
	private int day;
	private int s_time;
	private int e_time;

	public oa_time(int d, int s, int e)
	{
		day = d;
		s_time = s;
		e_time = e;
	}

	public int get_day()
	{
		return day;
	}

	public int get_start_time()
	{
		return s_time;
	}

	public int get_end_time()
	{
		return e_time;
	}

	public int[] get_time_list(int ppd)
	{
		int length = e_time - s_time +1;
		int result[] = new int[length];

		for(int i=0;i<length;i++)
			result[i] = day*ppd + e_time + i;

		return result;
	}
}

