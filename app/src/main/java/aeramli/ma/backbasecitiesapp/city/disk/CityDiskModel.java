package aeramli.ma.backbasecitiesapp.city.disk;

import com.google.gson.annotations.SerializedName;

public class CityDiskModel {
    @SerializedName("_id")
    private int id;
    private String name;
    private String country;
    @SerializedName("coord")
    private Coordinate coordinate;

    public CityDiskModel(int id, String name, String country, Coordinate coordinate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.coordinate = coordinate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public static class Coordinate {
        private Double lat;
        private Double lon;

        public Coordinate(Double lat, Double lon) {
            this.lat = lat;
            this.lon = lon;
        }

        public Double getLat() {
            return lat;
        }

        public void setLat(Double lat) {
            this.lat = lat;
        }

        public Double getLon() {
            return lon;
        }

        public void setLon(Double lon) {
            this.lon = lon;
        }
    }
}
