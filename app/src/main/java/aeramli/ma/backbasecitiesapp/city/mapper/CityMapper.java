package aeramli.ma.backbasecitiesapp.city.mapper;

import java.util.ArrayList;
import java.util.List;

import aeramli.ma.backbasecitiesapp.city.disk.CityDiskModel;
import aeramli.ma.backbasecitiesapp.city.model.City;

public class CityMapper {

    public List<City> fromDisk(List<CityDiskModel> diskCities) {
        List<City> cities = new ArrayList<>(diskCities.size());
        for (CityDiskModel diskCity : diskCities) {
            cities.add(new City(diskCity.getId(),
                    diskCity.getName(),
                    diskCity.getCountry(),
                    diskCity.getCoordinate().getLat(),
                    diskCity.getCoordinate().getLon()));
        }
        return cities;
    }
}
