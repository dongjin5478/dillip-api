package com.student.api.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.api.response.ApiEntity;
import com.student.api.response.ApiResponseObject;
import com.student.api.response.UniversityDetailsDTO;
import com.student.api.service.ReportService;
import com.student.api.util.ProjectConstant;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
public class UniversityController {

	private final static Logger LOGGER = Logger.getLogger("Dillip Logger");

	@Autowired
	private ReportService reportService;

	@Operation(summary = "getAllUniversityDetailsByCountryName")
	@GetMapping(path = "/get-university-details")
	public ResponseEntity<ApiResponseObject> getUniversityDetailsByCountryName(
			@Parameter(description = "String", required = true) @RequestParam(name = "country_name",required = true) String countryName) {
		HttpStatus status = null;
		HttpHeaders httpHeaders = new HttpHeaders();
		String message = null;
		List<UniversityDetailsDTO> response = null;
		LOGGER.log(Level.INFO, "############# getUniversityDetails() :: countryName :: " + countryName);
		try {
			response = reportService.getUniversityDetailsByCountryName(countryName);
			if (!response.isEmpty()) {
				status = HttpStatus.OK;
				message = ProjectConstant.DATA_FOUND;
			} else {
				status = HttpStatus.NOT_FOUND;
				message = ProjectConstant.DATA_NOT_FOUND;
			}
		} catch (Exception e) {
			LOGGER.log(Level.INFO, "########## Exception Occured ##########", e);
			message = e.getMessage();
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<ApiResponseObject>(new ApiEntity<List<UniversityDetailsDTO>>(message, response),
				httpHeaders, status);

	}
}
