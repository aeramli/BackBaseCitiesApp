package aeramli.ma.backbasecitiesapp.city.model;

import android.os.Parcel;
import android.os.Parcelable;

import aeramli.ma.backbasecitiesapp.data.TrieItem;

public class City implements TrieItem, Parcelable {

    private final int id;
    private final String name;
    private final String country;
    private final Double lat;
    private final Double lon;

    public City(int id, String name, String country, Double lat, Double lon) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lat = lat;
        this.lon = lon;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public Double getLat() {
        return lat;
    }

    public Double getLon() {
        return lon;
    }

    @Override
    public String getValue() {
        return getName().toLowerCase();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.country);
        dest.writeValue(this.lat);
        dest.writeValue(this.lon);
    }

    protected City(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.country = in.readString();
        this.lat = (Double) in.readValue(Double.class.getClassLoader());
        this.lon = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<City> CREATOR = new Parcelable.Creator<City>() {
        @Override
        public City createFromParcel(Parcel source) {
            return new City(source);
        }

        @Override
        public City[] newArray(int size) {
            return new City[size];
        }
    };
}
