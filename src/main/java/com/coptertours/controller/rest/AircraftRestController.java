package com.coptertours.controller.rest;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.coptertours.common.AppConstants;
import com.coptertours.common.ImageConverter;
import com.coptertours.common.ImageResizer;
import com.coptertours.deviantsart.ImageUploadResponse;
import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AircraftRepository;
import com.google.gson.Gson;

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
		if (!StringUtils.isEmpty(aircraft.getImagePath())) {
			aircraft.setImagePath(aircraft.getImagePath());
		}
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

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/admin/aircrafts/uploadImage", method = RequestMethod.POST)
	public String uploadImage(@RequestParam("imageFile") MultipartFile imageFile, @RequestParam String aircraftNumber, HttpServletRequest request) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		final String filename = imageFile.getOriginalFilename();
		map.add("name", filename);
		map.add("filename", filename);
		ImageResizer imageResizer = new ImageResizer();
		byte[] resizedImage = imageResizer.resizeImageAsJPG(imageFile.getBytes(), 250);
		ByteArrayResource contentsAsResource = new ByteArrayResource(resizedImage) {
			@Override
			public String getFilename() {
				return filename;
			}
		};
		map.add("file", contentsAsResource);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> result = restTemplate.exchange(AppConstants.IMAGE_CDN, HttpMethod.POST, requestEntity, String.class);
		ImageUploadResponse imageUploadResponse = new Gson().fromJson(result.getBody(), ImageUploadResponse.class);

		Map<String, String> imageMap = new HashMap<String, String>();
		Object imageMapObj = request.getSession().getAttribute(AppConstants.IMAGE_MAP);
		if (imageMapObj != null) {
			imageMap = (Map<String, String>) imageMapObj;
		}
		imageMap.put(aircraftNumber, ImageConverter.convertToBase64(imageUploadResponse.getUrl()));

		request.getSession().setAttribute(AppConstants.IMAGE_MAP, imageMap);

		return imageUploadResponse.getUrl();
	}

	private File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException
	{
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}
}