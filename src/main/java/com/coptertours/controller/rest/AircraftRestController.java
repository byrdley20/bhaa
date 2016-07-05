package com.coptertours.controller.rest;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
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
import com.coptertours.domain.AdCompliance;
import com.coptertours.domain.Aircraft;
import com.coptertours.domain.ExcludedAdCompliance;
import com.coptertours.domain.ExcludedMaintenanceType;
import com.coptertours.domain.MaintenanceType;
import com.coptertours.repository.AdComplianceRepository;
import com.coptertours.repository.AircraftRepository;
import com.coptertours.repository.ExcludedAdComplianceRepository;
import com.coptertours.repository.ExcludedMaintenanceTypeRepository;
import com.coptertours.repository.MaintenanceTypeRepository;

@RestController
public class AircraftRestController {
	@Autowired
	private AircraftRepository aircraftRepository;
	@Autowired
	private AdComplianceRepository adComplianceRepository;
	@Autowired
	private MaintenanceTypeRepository maintenanceTypeRepository;
	@Autowired
	private ExcludedAdComplianceRepository excludedAdComplianceRepository;
	@Autowired
	private ExcludedMaintenanceTypeRepository excludedMaintenanceTypeRepository;

	@RequestMapping(value = "/admin/aircrafts", method = RequestMethod.GET)
	Collection<Aircraft> aircrafts() {
		return this.aircraftRepository.findAll();
	}

	@RequestMapping(value = "/admin/aircrafts", method = RequestMethod.POST)
	@ResponseBody
	Aircraft addAircraft(@RequestBody Aircraft aircraft, final HttpServletResponse response) {
		this.excludedAdComplianceRepository.deleteByAircraftId(aircraft.getId());
		this.excludedMaintenanceTypeRepository.deleteByAircraftId(aircraft.getId());

		Aircraft savedAircraft = this.aircraftRepository.save(aircraft);

		updateAdCompliances(aircraft, savedAircraft);
		updateMaintenanceTypes(aircraft, savedAircraft);
		return savedAircraft;
	}

	private void updateAdCompliances(Aircraft aircraft, Aircraft savedAircraft) {
		if (!StringUtils.isEmpty(aircraft.getExcludedAdComplianceIds())) {
			List<Long> longIds = new ArrayList<Long>();
			for (String id : Arrays.asList(aircraft.getExcludedAdComplianceIds().split(","))) {
				longIds.add(Long.parseLong(id));
			}
			List<ExcludedAdCompliance> excludedAdCompliances = new ArrayList<ExcludedAdCompliance>();
			for (AdCompliance adCompliance : this.adComplianceRepository.findAll(longIds)) {
				ExcludedAdCompliance excludedAdCompliance = new ExcludedAdCompliance();
				excludedAdCompliance.setAircraftId(aircraft.getId());
				excludedAdCompliance.setAdCompliance(adCompliance);
				excludedAdComplianceRepository.save(excludedAdCompliance);
				excludedAdCompliances.add(excludedAdCompliance);
			}
			savedAircraft.setExcludedAdCompliances(excludedAdCompliances);
		}
		List<AdCompliance> adCompliances = new ArrayList<AdCompliance>();
		for (AdCompliance adCompliance : this.adComplianceRepository.findByModelAndActiveTrue(aircraft.getModel(), new Sort(Sort.Direction.ASC, "name"))) {
			boolean includedAdCompliance = true;
			if (savedAircraft.getExcludedAdCompliances() != null) {
				for (ExcludedAdCompliance excludedAdCompliance : savedAircraft.getExcludedAdCompliances()) {
					if (excludedAdCompliance.getAdCompliance().getId() == adCompliance.getId()) {
						includedAdCompliance = false;
						break;
					}
				}
			}
			if (includedAdCompliance) {
				adCompliances.add(adCompliance);
			}
		}
		savedAircraft.setAdCompliances(adCompliances);
	}

	private void updateMaintenanceTypes(Aircraft aircraft, Aircraft savedAircraft) {
		if (!StringUtils.isEmpty(aircraft.getExcludedMaintenanceTypesIds())) {
			List<Long> longIds = new ArrayList<Long>();
			for (String id : Arrays.asList(aircraft.getExcludedMaintenanceTypesIds().split(","))) {
				longIds.add(Long.parseLong(id));
			}
			List<ExcludedMaintenanceType> excludedMaintenanceTypes = new ArrayList<ExcludedMaintenanceType>();
			for (MaintenanceType maintenanceType : this.maintenanceTypeRepository.findAll(longIds)) {
				ExcludedMaintenanceType excludedMaintenanceType = new ExcludedMaintenanceType();
				excludedMaintenanceType.setAircraftId(aircraft.getId());
				excludedMaintenanceType.setMaintenanceType(maintenanceType);
				excludedMaintenanceTypeRepository.save(excludedMaintenanceType);
				excludedMaintenanceTypes.add(excludedMaintenanceType);
			}
			savedAircraft.setExcludedMaintenanceTypes(excludedMaintenanceTypes);
		}
		List<MaintenanceType> maintenanceTypes = new ArrayList<MaintenanceType>();
		for (MaintenanceType maintenanceType : this.maintenanceTypeRepository.findByModelAndActiveTrue(aircraft.getModel(), new Sort(Sort.Direction.ASC, "name"))) {
			boolean includedMaintenanceType = true;
			if (savedAircraft.getExcludedMaintenanceTypes() != null) {
				for (ExcludedMaintenanceType excludedMaintenanceType : savedAircraft.getExcludedMaintenanceTypes()) {
					if (excludedMaintenanceType.getMaintenanceType().getId() == maintenanceType.getId()) {
						includedMaintenanceType = false;
						break;
					}
				}
			}
			if (includedMaintenanceType) {
				maintenanceTypes.add(maintenanceType);
			}
		}
		savedAircraft.setMaintenanceTypes(maintenanceTypes);
	}

	@RequestMapping(value = "/admin/aircrafts/{id}", method = RequestMethod.DELETE)
	void deleteAircraft(@PathVariable Long id) throws Exception {
		Aircraft aircraft = this.aircraftRepository.findOne(id);
		List<ExcludedAdCompliance> deletedExcludedAdCompliances = deleteExcludedAdCompliances(id, aircraft);
		List<ExcludedMaintenanceType> deletedExcludedMaintenanceTypes = deleteExcludedMaintenanceTypes(id, aircraft);
		try {
			this.aircraftRepository.delete(id);
		} catch (Exception e) {
			excludedAdComplianceRepository.save(deletedExcludedAdCompliances);
			excludedMaintenanceTypeRepository.save(deletedExcludedMaintenanceTypes);
			throw e;
		}
	}

	private List<ExcludedAdCompliance> deleteExcludedAdCompliances(Long id, Aircraft aircraft) {
		List<ExcludedAdCompliance> deletedExcludedAdCompliances = new ArrayList<ExcludedAdCompliance>();
		if (aircraft.getExcludedAdCompliances() != null) {
			for (ExcludedAdCompliance excludedAdCompliance : aircraft.getExcludedAdCompliances()) {
				deletedExcludedAdCompliances.add(excludedAdCompliance);
				this.excludedAdComplianceRepository.delete(excludedAdCompliance);
			}
		}
		return deletedExcludedAdCompliances;
	}

	private List<ExcludedMaintenanceType> deleteExcludedMaintenanceTypes(Long id, Aircraft aircraft) {
		List<ExcludedMaintenanceType> deletedExcludedMaintenanceTypes = new ArrayList<ExcludedMaintenanceType>();
		if (aircraft.getExcludedAdCompliances() != null) {
			for (ExcludedMaintenanceType excludedMaintenanceType : aircraft.getExcludedMaintenanceTypes()) {
				deletedExcludedMaintenanceTypes.add(excludedMaintenanceType);
				this.excludedMaintenanceTypeRepository.delete(excludedMaintenanceType);
			}
		}
		return deletedExcludedMaintenanceTypes;
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