package com.coptertours.controller.rest;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.coptertours.domain.Aircraft;
import com.coptertours.repository.AircraftRepository;

@RestController
@RequestMapping(value = "/aircrafts")
public class AircraftRestController {
	@Autowired
	AircraftRepository aircraftRepository;

	@RequestMapping(method = RequestMethod.GET)
	Collection<Aircraft> aircrafts() {
		return this.aircraftRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	Aircraft addAircraft(@RequestBody Aircraft aircraft, final HttpServletResponse response) {
		if (!StringUtils.isEmpty(aircraft.getImagePath())) {
			aircraft.setImagePath(aircraft.getImagePath());
		}
		return this.aircraftRepository.save(aircraft);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	void deleteAircraft(@PathVariable Long id) {
		this.aircraftRepository.delete(id);
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	void uploadImage(@RequestParam("imageFile") MultipartFile imageFile, HttpServletRequest request) {
		// if (!imageFile.isEmpty()) {
		// String name = imageFile.getOriginalFilename();
		// try {
		// byte[] bytes = imageFile.getBytes();
		// String basePath = "src/main/resources/static/";
		// File imagesDirectory = new File(basePath + AppConstants.IMAGES_DIR);
		// imagesDirectory.mkdirs();
		// File file = new File(imagesDirectory + name);
		// file.createNewFile();
		// BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		// stream.write(bytes);
		// stream.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// System.out.println("You failed to upload " + name + " => " + e.getMessage());
		// }
		// } else {
		// System.out.println("You failed to upload file because the file was empty.");
		// }
	}
}