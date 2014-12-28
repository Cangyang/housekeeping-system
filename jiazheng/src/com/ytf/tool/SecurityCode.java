package com.ytf.tool;
import java.util.Arrays;
/**
 * �����࣬���������֤���ַ���
 * @author Administrator
 *
 */

public class SecurityCode {
	/**
	 * ��֤���Ѷȼ���Simpleֻ�������֣�Medium�������ֺ�СдӢ�ģ�Hard�������ֺʹ�СдӢ��
	 */
	public enum SecurityCodeLevel {Simple,Medium,Hard};
	
	/**
	 * ����Ĭ����֤�룬4λ�е��Ѷ�
	 * @return String ��֤��
	 */
    public static String getSecurityCode() {
    	return getSecurityCode(4,SecurityCodeLevel.Medium,false);
    }
    
    /**
     * �������Ⱥ��Ѷ��������֤��
     * @param length ����
     * @param �Ѷȼ���
     * @param isCanRepeate �Ƿ��ܹ������ظ����ַ������Ϊtrue������ܳ��֣�������
     * @return String ��֤��
     */
    public static String getSecurityCode(int length,SecurityCodeLevel level,boolean isCanRepeate) {
    	//�����ȡlen���ַ�
    	int len = length;
    	
    	//�ַ�����(��ȥ�׻���������0������1����ĸl����ĸo����ĸO)
    	char[] codes = {'1','2','3','4','5','6','7','8','9',
    			        'a','b','c','d','e','f','g','h','i',
    			        'j','k','m','n','p','q','r','s','t',
    			        'u','v','w','x','y','z','A','B','C',
    			        'D','E','F','G','H','I','J','K','L',
    			        'M','N','P','Q','R','S','T','U','V',
    			        'W','X','Y','Z'}; 
    	//���߲�ͬ�ѶȽ�ȡ�ַ�����
    	if (level==SecurityCodeLevel.Simple) {
    		codes = Arrays.copyOfRange(codes, 0, 9);
    	}else if(level==SecurityCodeLevel.Medium) {
    		codes = Arrays.copyOfRange(codes, 0, 33);
    	}
    	
    	//�ַ����ͳ���
    	int n = codes.length;
    	
    	//�׳�����ʱ�쳣
    	if(len > n&&isCanRepeate==false) {
    		throw new RuntimeException(
    				String.format("����SecurityCode.getSecurityCode(%1$s%2$s%3$s)�����쳣," + 
    		                      "��isCanRepeateΪ%3$sʱ,�������%1$s���ܴ���%4$s", 
    		                      len,level,isCanRepeate,n));
    	}
    	
    	//��ų�ȡ�������ַ�
    	char[] result = new char[len];
    	
    	//�ж��ܷ�����ظ����ַ�
    	if(isCanRepeate) {
    		for(int i = 0;i < result.length;i++) {
    			//����0��n-1�������
    			int r = (int)(Math.random()*n);
    			
    			//��result�еĵ�i��Ԫ������Ϊcodes[r]��ŵ���ֵ
    			result[i]=codes[r];
    		}
    	}else{
    		for(int i = 0;i < result.length;i++) {
    			//����0��n-1�������
    			int r = (int)(Math.random()*n);
    			
    			//��result�еĵ�i��Ԫ������Ϊcodes[r]��ŵ���ֵ
    			result[i] = codes[r];
    			
    			//����ȷ�������ٴγ�ȡ��ͬ�����ַ�����Ϊ���е��ַ����벻ͬ
    			//��ˣ������������е����һ���ַ���дcodes[r],����n��1
    			codes[r] = codes[n-1];
    			n--;
    		}
    	}
    	
    	return String.valueOf(result);
    }
}
