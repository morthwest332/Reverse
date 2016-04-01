package liu.reverse;

public class Reverse {

	public static void main(String []args)
	{
		String str="hello!";
	
		//��ӡԭʼ�ַ���
		PrintString.print(str);
		System.out.println();
		//��һ��Ϊʹ��StringBuilder��reverse()����
		PrintString.print(new StringBuilder(str).reverse().toString());
		System.out.println();
		//�ڶ���ʹ�õݹ�ʵ��
		PrintString.print(recursionReverseString(str));
		System.out.println();
		
		//�����ֲ��÷ǵݹ齻��ʵ��
		char []array=reverseString(str);
		System.out.print(array);
		
	}

	private static char[] reverseString(String str) {
		// TODO �Զ����ɵķ������
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
		// TODO �Զ����ɵķ������
		if(str==null||str.length()<2)
			return str;
		return recursionReverseString(str.substring(1, str.length()))+str.charAt(0);
	}
}
