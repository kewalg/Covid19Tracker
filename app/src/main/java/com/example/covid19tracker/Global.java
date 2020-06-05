package com.example.covid19tracker;

import com.google.gson.annotations.SerializedName;

public class Global{

	@SerializedName("NewRecovered")
	private int newRecovered;

	@SerializedName("NewDeaths")
	private int newDeaths;

	@SerializedName("TotalRecovered")
	private int totalRecovered;

	@SerializedName("TotalConfirmed")
	private int totalConfirmed;

	@SerializedName("NewConfirmed")
	private int newConfirmed;

	@SerializedName("TotalDeaths")
	private int totalDeaths;

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

	public int getNewConfirmed(){
		return newConfirmed;
	}

	public int getTotalDeaths(){
		return totalDeaths;
	}
}