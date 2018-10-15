package common;

public class common{

	public static int hash_method(int src, int size)
	{
		int dst = 0;
		dst = src % size + 1;
		return dst;
	}
}
