package aeramli.ma.backbasecitiesapp.ui.viewmodel;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import java.util.Collections;
import java.util.List;

import aeramli.ma.backbasecitiesapp.city.CityRepository;
import aeramli.ma.backbasecitiesapp.city.model.City;

public class CityListFragmentViewModel {
    private final CityRepository repository;
    private final ObservableField<List<City>> cities;
    private final ObservableBoolean progressVisible;

    public CityListFragmentViewModel(CityRepository repository) {
        this.repository = repository;
        this.cities = new ObservableField<>(Collections.emptyList());
        this.progressVisible = new ObservableBoolean(true);
    }

    public ObservableField<List<City>> getCities() {
        return cities;
    }

    public ObservableBoolean getProgressVisible() {
        return progressVisible;
    }

    public void retrieveCities() {
        repository.retrieve(citiesList -> {
            cities.set(citiesList);
            progressVisible.set(false);
        });
    }

    public void search(String searchQuery) {
        repository.search(searchQuery, cities::set);
    }
}
