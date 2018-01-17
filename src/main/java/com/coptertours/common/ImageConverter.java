package com.coptertours.common;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

public class ImageConverter {

	// public static String convertToBase64(String path) throws IOException {
	// BufferedImage image = null;
	// URL url = new URL(path);
	// image = ImageIO.read(url);
	//
	// ByteArrayOutputStream os = new ByteArrayOutputStream();
	// OutputStream b64 = new Base64OutputStream(os);
	// ImageIO.write(image, "png", b64);
	// String base64Image = os.toString("UTF-8");
	// return base64Image;
	// }

	public static String convertToBase64(byte[] imageBytes) throws IOException {
		String base64Image = Base64.encodeBase64String(imageBytes);
		return base64Image;
	}
}
