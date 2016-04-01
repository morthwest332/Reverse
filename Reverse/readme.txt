
	StringBuilder的reverse源码分析:
	
		//count为获取String的长度
		//注意长度从1开始计数，而不是0
		 public int length() {
          return count;
      }
	  
	   public AbstractStringBuilder reverse() {
         boolean hasSurrogate = false;
         int n = count - 1;
		 //将字符串转为数组后进行分组交换
		 //若为双数两两依次交换
		 //若为单数不交换最中间的数		 
         for (int j = (n-1) >> 1; j >= 0; --j) {
             char temp = value[j];
             char temp2 = value[n - j];
             if (!hasSurrogate) {
                 hasSurrogate = (temp >= Character.MIN_SURROGATE && temp <= Character.MAX_SURROGATE)
                    || (temp2 >= Character.MIN_SURROGATE && temp2 <= Character.MAX_SURROGATE);
             }
            value[j] = temp2;
            value[n - j] = temp;
         }
		 //返回采用四个字节(两个UTF-16编码)来表示一个字符的反转结果
         if (hasSurrogate) {
             // Reverse back all valid surrogate pairs
             for (int i = 0; i < count - 1; i++) {
                 char c2 = value[i];
                 if (Character.isLowSurrogate(c2)) {
                     char c1 = value[i + 1];
                     if (Character.isHighSurrogate(c1)) {
                         value[i++] = c1;
                        value[i] = c2;
                     }
                 }
             }
        }
        return this;
    }