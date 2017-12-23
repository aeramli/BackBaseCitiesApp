package aeramli.ma.backbasecitiesapp.ui.viewmodel;

import android.databinding.ObservableField;

import java.util.Collections;
import java.util.List;

import aeramli.ma.backbasecitiesapp.city.CityRepository;
import aeramli.ma.backbasecitiesapp.city.model.City;

public class CityListFragmentViewModel {
    private final CityRepository repository;
    private final ObservableField<List<City>> cities;

    public CityListFragmentViewModel(CityRepository repository) {
        this.repository = repository;
        this.cities = new ObservableField<>(Collections.emptyList());
    }

    public ObservableField<List<City>> getCities() {
        return cities;
    }

    public void retrieveCities() {
        retrieveCities(null);
    }

    public void retrieveCities(OnRetrieveFinishedListener listener) {
        repository.retrieve(citiesList -> {
            cities.set(citiesList);
            if (listener != null) {
                listener.onRetrieveFinished(citiesList);
            }
        });
    }

    public void search(String searchQuery) {
        repository.search(searchQuery, cities::set);
    }

    public interface OnRetrieveFinishedListener {
        void onRetrieveFinished(List<City> cities);
    }
}
