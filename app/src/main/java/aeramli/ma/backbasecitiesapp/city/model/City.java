package aeramli.ma.backbasecitiesapp.city.model;

import aeramli.ma.backbasecitiesapp.data.TrieItem;

public class City implements TrieItem {

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
        return getName();
    }
}
