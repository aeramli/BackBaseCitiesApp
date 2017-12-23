package aeramli.ma.backbasecitiesapp.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import aeramli.ma.backbasecitiesapp.city.model.City;

public class TrieTest {

    @Test
    public void add_sameCityNamesButDifferentCountry_success() {
        Trie<City> countryTrie = new Trie<>();
        List<City> countriesToAdd = buildCountryWithSameCityNames();
        for (City city : countriesToAdd) {
            countryTrie.add(city);
        }
        List<City> storedCountries = countryTrie.getItems();
        Assert.assertEquals(countriesToAdd.size(), storedCountries.size());
    }

    @Test
    public void add_duplicateCities_handled() {
        Trie<City> countryTrie = new Trie<>();
        List<City> countriesToAdd = buildCountryWithSameCityNames();
        countriesToAdd.addAll(countriesToAdd);
        for (City city : countriesToAdd) {
            countryTrie.add(city);
        }
        List<City> storedCountries = countryTrie.getItems();
        Assert.assertEquals(countriesToAdd.size() / 2, storedCountries.size());
    }

    @Test
    public void add_autocomplete_notEmptyResult() {
        Trie<City> countryTrie = new Trie<>();
        List<City> countriesToAdd = buildCountryWithSameCityNames();
        for (City city : countriesToAdd) {
            countryTrie.add(city);
        }
        Assert.assertEquals(1, countryTrie.autocomplete("Peru").size());
    }

    @Test
    public void add_autocomplete_emptyResult() {
        Trie<City> countryTrie = new Trie<>();
        List<City> countriesToAdd = buildCountryWithSameCityNames();
        for (City city : countriesToAdd) {
            countryTrie.add(city);
        }
        Assert.assertEquals(0, countryTrie.autocomplete("Paris").size());
    }

    private List<City> buildCountryWithSameCityNames() {
        List<City> countries = new ArrayList<>();
        countries.add(new City(1, "Perth", "AU", -31.953513, 115.857047));
        countries.add(new City(2, "Perth", "CA", 44.902448, -76.248012));
        countries.add(new City(3, "Perugia", "IT", 43.112222, 12.388889));
        countries.add(new City(3, "Perpignan", "FR", 42.6986, 2.8956));
        return countries;
    }
}
