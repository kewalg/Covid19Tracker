package com.example.covid19tracker;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{

	@SerializedName("Countries")
	private List<CountriesItem> countries;

	@SerializedName("Global")
	private Global global;

	@SerializedName("Date")
	private String date;

	public List<CountriesItem> getCountries(){
		return countries;
	}

	public Global getGlobal(){
		return global;
	}

	public String getDate(){
		return date;
	}
}