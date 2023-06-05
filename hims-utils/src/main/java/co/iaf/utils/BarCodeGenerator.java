package co.iaf.utils;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarCodeGenerator {

	public static byte[] getBarCodeImage(String barCodeText, int width, int height) {
		try {
			Hashtable<EncodeHintType, ErrorCorrectionLevel> hintmap = new Hashtable<>();
			hintmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
			Writer writer = new Code128Writer();
			BitMatrix bitMatrix = writer.encode(barCodeText, BarcodeFormat.CODE_128, width, height);
			ByteArrayOutputStream byteArrayOutpuStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutpuStream);
			
			return byteArrayOutpuStream.toByteArray();
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
}
