package aeramli.ma.backbasecitiesapp.city;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import aeramli.ma.backbasecitiesapp.city.disk.CityDiskDataSource;
import aeramli.ma.backbasecitiesapp.city.mapper.CityMapper;
import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.data.Trie;

public class CityRepository {
    private final CityDiskDataSource diskDataSource;
    private final CityMapper mapper;
    private final AtomicReference<Trie<City>> trieCache;
    private final AtomicReference<List<City>> allCitiesCache;

    public CityRepository(CityDiskDataSource diskDataSource, CityMapper mapper) {
        this.diskDataSource = diskDataSource;
        this.mapper = mapper;
        this.trieCache = new AtomicReference<>();
        this.allCitiesCache = new AtomicReference<>();
    }

    public void retrieve(@NonNull final OnCitiesRetrievedListener listener) {
        if (trieCache.get() != null) {
            AsyncTask.execute(() -> listener.onCitiesRetrieved(trieCache.get().getItems()));
        } else {
            diskDataSource.parseCities(citiesFromDisk -> {
                final List<City> cities = mapper.fromDisk(citiesFromDisk);
                sortCities(cities);
                Trie<City> trie = new Trie<>();
                trie.add(cities);
                trieCache.set(trie);
                allCitiesCache.set(cities);
                listener.onCitiesRetrieved(cities);
            });
        }
    }

    private void sortCities(List<City> cities) {
        Collections.sort(cities, (firstCity, secondCity) -> {
            int result = firstCity.getName().compareTo(secondCity.getName());
            if (result != 0) {
                return result;
            }
            return firstCity.getCountry().compareTo(secondCity.getCountry());
        });
    }

    public void search(String name, @NonNull OnSearchFinishedListener listener) {
        if (name.isEmpty()) {
            listener.onSearchFinished(allCitiesCache.get());
            return;
        }
        AsyncTask.execute(() -> {
            List<City> cities = trieCache.get().autocomplete(name.toLowerCase());
            sortCities(cities);
            listener.onSearchFinished(cities);
        });
    }

    public interface OnCitiesRetrievedListener {
        void onCitiesRetrieved(List<City> cities);
    }

    public interface OnSearchFinishedListener {
        void onSearchFinished(List<City> cities);
    }
}
