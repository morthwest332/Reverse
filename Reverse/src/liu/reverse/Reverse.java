package liu.reverse;

public class Reverse {

	public static void main(String []args)
	{
		String str="hello!";
	
		//打印原始字符串
		PrintString.print(str);
		System.out.println();
		//第一种为使用StringBuilder的reverse()方法
		PrintString.print(new StringBuilder(str).reverse().toString());
		System.out.println();
		//第二种使用递归实现
		PrintString.print(recursionReverseString(str));
		System.out.println();
		
		//第三种采用非递归交换实现
		char []array=reverseString(str);
		System.out.print(array);
		
	}

	private static char[] reverseString(String str) {
		// TODO 自动生成的方法存根
		char []strArray=str.toCharArray();
		int j=strArray.length-1;
		for(int i=0;i<j;i++,j--)
		{
		    char temp=strArray[i];
		    strArray[i]=strArray[j];
		    strArray[j]=temp;
		}
		return strArray;
	}

	private static String recursionReverseString(String str) {
		// TODO 自动生成的方法存根
		if(str==null||str.length()<2)
			return str;
		return recursionReverseString(str.substring(1, str.length()))+str.charAt(0);
	}
}
