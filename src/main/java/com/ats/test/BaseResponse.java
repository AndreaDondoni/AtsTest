/**
 * 
 */
package com.ats.test;

import java.util.List;

import com.ats.test.model.AtmPoint;

/**
 * Object to manage service response.
 * @author Adondoni
 */
public class BaseResponse 
{
	private String message;
	private List<AtmPoint> result;
	
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	public List<AtmPoint> getResult() {return result;}
	public void setResult(List<AtmPoint> result) {this.result = result;}
}
