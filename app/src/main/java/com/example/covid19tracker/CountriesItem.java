package com.example.covid19tracker;

import com.google.gson.annotations.SerializedName;

public class CountriesItem{

	@SerializedName("NewRecovered")
	private int newRecovered;

	@SerializedName("NewDeaths")
	private int newDeaths;

	@SerializedName("TotalRecovered")
	private int totalRecovered;

	@SerializedName("TotalConfirmed")
	private int totalConfirmed;

	@SerializedName("Country")
	private String country;

	@SerializedName("CountryCode")
	private String countryCode;

	@SerializedName("Slug")
	private String slug;

	@SerializedName("NewConfirmed")
	private int newConfirmed;

	@SerializedName("TotalDeaths")
	private int totalDeaths;

	@SerializedName("Date")
	private String date;

	public int getNewRecovered(){
		return newRecovered;
	}

	public int getNewDeaths(){
		return newDeaths;
	}

	public int getTotalRecovered(){
		return totalRecovered;
	}

	public int getTotalConfirmed(){
		return totalConfirmed;
	}

	public String getCountry(){
		return country;
	}

	public String getCountryCode(){
		return countryCode;
	}

	public String getSlug(){
		return slug;
	}

	public int getNewConfirmed(){
		return newConfirmed;
	}

	public int getTotalDeaths(){
		return totalDeaths;
	}

	public String getDate(){
		return date;
	}
}