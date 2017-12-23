package aeramli.ma.backbasecitiesapp.ui.bindingadapter;


import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.ui.city.adapter.CityListRecyclerAdapter;

public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"cities", "listener"}, requireAll = false)
    public static void setStations(RecyclerView recyclerView, List<City> cityList, CityListRecyclerAdapter.OnCitySelectedListener listener) {
        if (cityList != null) {
            recyclerView.setAdapter(new CityListRecyclerAdapter(cityList, listener));
        }
    }
}
