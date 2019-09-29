package com.ats.test.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ats.test.BaseResponse;
import com.ats.test.model.AtmPoint;
import com.ats.test.model.SearchCriteria;


/**
 * @author Adondoni
 *
 */
@RestController
@RequestMapping("/atm")
public class ServiceController 
{
	
	public ServiceController() {
		super();
	}
	
	public ServiceController(AtmService service) {
		super();
		this.atmService = service;
	}

	@Autowired
    private AtmService atmService;
	
	
	/**
	* GET service that list all ATMs points retrieved from external service.
	* @see {@link com.ats.test.BaseResponse}
	* @return a BaseResponse 
	*/
	@GetMapping(value = "/list")
	public ResponseEntity<?> list() 
	{
		return processRequest(Optional.empty());
	}
	
	/**
	 * POST service that search an ATMs points contains a specific text.
	 * @see {@link com.ats.test.BaseResponse}
	 * @see {@link com.ats.model.SearchCriteria}
	 * @param search search criteria as request body
	 * @return a BaseResponse
	 */
	@PostMapping(value = "/search")
	public ResponseEntity<?> search(@RequestBody SearchCriteria search) 
	{
		return processRequest(Optional.of(search));
	}
	
	/**
	 * Internal process request.
	 * @param search optional value contains search criteria.
	 * @return a BaseResponse
	 */
	private ResponseEntity<?> processRequest(Optional<SearchCriteria> search)
	{
		BaseResponse response = new BaseResponse();
		List<AtmPoint> dto = new ArrayList<>();
		
		try 
		{
			dto = atmService.retrieveAtm(search);
			
			if (dto.isEmpty()) {
				response.setMessage("no ATMs Point found!");
	        } else {
	        	response.setMessage(String.format("found %d ATMs point", dto.size()));
	        }
		} 
		catch (Exception ex) 
		{
			//log exception
			response.setMessage("an error occurred processing data");
			//return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(dto);
		}

		response.setResult(dto);

        return ResponseEntity.ok(response);
	}

}
