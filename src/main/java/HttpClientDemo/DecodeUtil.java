package HttpClientDemo;

public class DecodeUtil {
	
	public static String decode(String para){
		
		char[] strarry = para.toCharArray();
		for(int i=0;i<strarry.length;i++){
			strarry[i]=(char) (strarry[i]-2);
		}
				
		return new String(strarry);
	}

}
