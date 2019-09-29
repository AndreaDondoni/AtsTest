/**
 * 
 */
package com.ats.test.model;

import com.google.gson.Gson;

/**
 * DTO object that map ATM Point information.
 * @author Adondoni
 */
public class AtmPoint 
{
	private Address address;
	private Integer distance;
	private String  type;
	
	public AtmPoint(Address address, Integer distance, String type) {
		super();
		this.address = address;
		this.distance = distance;
		this.type = type;
	}
	public Address getAddress() {return address;}
	public void setAddress(Address address) {this.address = address;}
	
	public Integer getDistance() {return distance;}
	public void setDistance(Integer distance) {this.distance = distance;}
	
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	
	@Override
	public String toString()
	{
		return new Gson().toJson(this);
	}
}
