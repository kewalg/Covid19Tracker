package com.example.covid19tracker;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CountriesItem implements Parcelable {

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

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getSlug() {
        return slug;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public String getDate() {
        return date;
    }

    protected CountriesItem(Parcel in) {
        newRecovered = in.readInt();
        newDeaths = in.readInt();
        totalRecovered = in.readInt();
        totalConfirmed = in.readInt();
        country = in.readString();
        countryCode = in.readString();
        slug = in.readString();
        newConfirmed = in.readInt();
        totalDeaths = in.readInt();
        date = in.readString();
    }

    public static final Creator<CountriesItem> CREATOR = new Creator<CountriesItem>() {
        @Override
        public CountriesItem createFromParcel(Parcel in) {
            return new CountriesItem(in);
        }

        @Override
        public CountriesItem[] newArray(int size) {
            return new CountriesItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(newRecovered);
        dest.writeInt(newDeaths);
        dest.writeInt(totalRecovered);
        dest.writeInt(totalConfirmed);
        dest.writeString(country);
        dest.writeString(countryCode);
        dest.writeString(slug);
        dest.writeInt(newConfirmed);
        dest.writeInt(totalDeaths);
        dest.writeString(date);
    }
}