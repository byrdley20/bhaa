package com.coptertours.controller.rest;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coptertours.common.ImageConverter;
import com.coptertours.common.ImageResizer;
import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AircraftRepository;

@RestController
public class AircraftRestController {
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping(value = "/admin/aircrafts", method = RequestMethod.GET)
	Collection<Aircraft> aircrafts() {
		return this.aircraftRepository.findAll();
	}

	@RequestMapping(value = "/admin/aircrafts", method = RequestMethod.POST)
	@ResponseBody
	Aircraft addAircraft(@RequestBody Aircraft aircraft, final HttpServletResponse response) {
		return this.aircraftRepository.save(aircraft);
	}

	@RequestMapping(value = "/admin/aircrafts/{id}", method = RequestMethod.DELETE)
	void deleteAircraft(@PathVariable Long id) {
		this.aircraftRepository.delete(id);
	}

	/**
	 * scale image
	 * 
	 * @param sbi image to scale
	 * @param imageType type of image
	 * @param dWidth width of destination image
	 * @param dHeight height of destination image
	 * @param fWidth x-factor for transformation / scaling
	 * @param fHeight y-factor for transformation / scaling
	 * @return scaled image
	 */
	public static BufferedImage scaleImage(BufferedImage sbi, int imageType, int dWidth, int dHeight, double wScale, double hScale) {
		BufferedImage dbi = null;
		if (sbi != null) {
			dbi = new BufferedImage(dWidth, dHeight, imageType);
			Graphics2D g = dbi.createGraphics();
			AffineTransform at = AffineTransform.getScaleInstance(wScale, hScale);
			g.drawRenderedImage(sbi, at);
		}
		return dbi;
	}

	@RequestMapping(value = "/admin/aircrafts/uploadImage", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam String aircraftNumber, HttpServletRequest request) throws IOException {
		ImageResizer imageResizer = new ImageResizer();
		byte[] resizedImage = imageResizer.resizeImageAsJPG(imageFile.getBytes(), 250);
		return ImageConverter.convertToBase64(resizedImage);
	}
}