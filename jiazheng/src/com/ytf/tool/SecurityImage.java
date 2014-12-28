package com.ytf.tool;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;
import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * �����࣬������֤��ͼƬ
 * @version 1.0 2014/03/24
 * @author Administrator
 *
 */

public class SecurityImage {
	
	/**
	 * ������֤��ͼƬ
	 * @param securityCode ��֤���ַ�
	 * @return BufferedImage ͼƬ
	 */
	
	public static BufferedImage createImage(String securityCode) {
		//��֤�볤��
		int codeLength = securityCode.length();
		//�����С
		int fSize = 15;
		int fWidth = fSize + 1;
		//ͼƬ���
		int width = codeLength * fWidth + 6;
		//ͼƬ�߶�
		int height = fSize * 2 + 1;
		
		//ͼƬ
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		
		//���ñ���ɫ
		g.setColor(Color.WHITE);
		//��䱳��
		g.fillRect(0, 0, width, height);
		
		//���ñ߿���ɫ
		g.setColor(Color.LIGHT_GRAY);
		//�߿�������ʽ
		g.setFont(new Font("Arial",Font.BOLD,height-2));
		//���Ʊ߿�
		g.drawRect(0, 0, width - 1, height - 1);
		
		//�������
		Random rand = new Random();
		
		//���������ɫ
		g.setColor(Color.LIGHT_GRAY);
		for(int i = 0;i < codeLength * 6;i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			//����1*1��С�ľ���
			g.drawRect(x, y, 1, 1);
		}
		
		//������֤��
		int codeY = height - 10;
		//����������ɫ����ʽ
		g.setColor(new Color(19,148,246));
		g.setFont(new Font("Georgia", Font.BOLD, fSize));
		for(int i = 0;i < codeLength;i++) {
			g.drawString(String.valueOf(securityCode.charAt(i)),i * 16 + 5, codeY);
		}
		//�ر���Դ
		g.dispose();
		
		return image;
	}
	
	/**
	 * ������֤��ͼƬ������ʽ
	 * @paramsecurityCode ��֤��
	 * @return ByteArrayInputStream ͼƬ��
	 */
	public static ByteArrayInputStream getImageAsInputStream(String securityCode) {
		BufferedImage image = createImage(securityCode);
		return convertImageToStream(image);
	}
	
	/**
	 * ��BufferedImageת����ByteArrayInputStream
	 * @param image ͼƬ
	 * @return ByteArrayInputStream ��
	 */
	
	private static ByteArrayInputStream convertImageToStream(BufferedImage image) {
		ByteArrayInputStream inputStream = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(bos);
		try {
			jpeg.encode(image);
			byte[] bts = bos.toByteArray();
			inputStream = new ByteArrayInputStream(bts);
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return inputStream;
	}

}
