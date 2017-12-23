package aeramli.ma.backbasecitiesapp.ui.city.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.databinding.CityListItemBinding;

public class CityListRecyclerAdapter extends RecyclerView.Adapter<CityViewHolder> {
    private final List<City> cities;

    public CityListRecyclerAdapter(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityListItemBinding binding = CityListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        holder.bind(cities.get(position));
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
