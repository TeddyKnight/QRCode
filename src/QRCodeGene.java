
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
 
import javax.imageio.ImageIO;
 
import com.swetake.util.Qrcode;
 

public class QRCodeGene {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String imgPath = "D://worksp/QRCode/img/1.png";
		String content = "http://www.baidu.com";
		QRCodeGene handler = new QRCodeGene();
		handler.encoderQRCode(content, imgPath);
		System.out.println("encoder QRcode success");
	}
 
	public void encoderQRCode(String content, String imgPath) {
		try {
			Qrcode qrcodeHandler = new Qrcode();
			qrcodeHandler.setQrcodeErrorCorrect('M');
			qrcodeHandler.setQrcodeEncodeMode('B');
			qrcodeHandler.setQrcodeVersion(7);
			System.out.println(content);
			byte[] contentBytes = content.getBytes("utf-8");
			BufferedImage bufImg = new BufferedImage(140, 140,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D gs = bufImg.createGraphics();
			gs.setBackground(Color.WHITE);
			gs.clearRect(0, 0, 140, 140);
			// �趨ͼ����ɫ> BLACK
			gs.setColor(Color.BLACK);
			// ����ƫ���� �����ÿ��ܵ��½�������
			int pixoff = 2;
			// �������> ��ά��
 
			if (contentBytes.length > 0 && contentBytes.length < 120) {
				boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);
				for (int i = 0; i < codeOut.length; i++) {
					for (int j = 0; j < codeOut.length; j++) {
						if (codeOut[j][i]) {
							gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);
						}
					}
				}
			} else {
				System.err.println("QRCode content bytes length = "
						+ contentBytes.length + " not in [ 0,120 ]. ");
 
			}
			gs.dispose();
			bufImg.flush();
			File imgFile = new File(imgPath);
			// ���ɶ�ά��QRCodeͼƬ
			ImageIO.write(bufImg, "png", imgFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
 
	}
}


