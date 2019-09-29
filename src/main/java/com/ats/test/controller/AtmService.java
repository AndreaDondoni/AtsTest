/**
 * 
 */
package com.ats.test.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ats.test.model.AtmPoint;
import com.ats.test.model.SearchCriteria;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author Adondoni
 *
 */
@Service("AtmService")
public class AtmService 
{
	private static final String ATM_URL = "https://www.dropbox.com/s/6fg0k2wxwrheyqk/ATMs?dl=1";

	private URL url =null;
	
	/**
	 * Default costructor.
	 * Initialize URL object
	 */
	public AtmService() 
	{
		super();
		try {
			url = new URL(ATM_URL);
		} catch (MalformedURLException e) 
		{
			//log exception
		}
	}

	/**
	 * Service to retrieve ATMs Point
	 * @param search
	 * @return
	 * @throws Exception
	 */
	public List<AtmPoint> retrieveAtm(Optional<SearchCriteria> search) throws Exception 
	{
		return search.isPresent() ? searchAtm(search.get()) : loadAtm();
	}
	
	/**
	 * Returns a filtered AtmPoint list
	 * @param search
	 * @return
	 * @throws Exception
	 */
	private List<AtmPoint> searchAtm(SearchCriteria search) throws Exception 
	{
		List<AtmPoint> dto = loadAtm();
		
		return dto.stream()
				 .filter(item -> item.toString().contains(search.getText()))
			     .collect(Collectors.toList());
	}
	
	/**
	 * Load ATMs Point information from remote service URL
	 * @return
	 * @throws IOException 
	 */
	private List<AtmPoint> loadAtm() throws IOException
	{
		InputStreamReader reader = null;
		
		List<AtmPoint> dto = new ArrayList<AtmPoint>();
		
		try 
		{
			if(url!=null)
			{
				reader 	= new InputStreamReader(url.openStream());
				dto = new Gson().fromJson(reader, new TypeToken<List<AtmPoint>>(){}.getType());
				
			}
			else
			{
				throw new MalformedURLException("external service not available");
			}
		} 
		finally 
		{
			if(reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					//log exception
				}
		}
		
		return dto;
	}

}
