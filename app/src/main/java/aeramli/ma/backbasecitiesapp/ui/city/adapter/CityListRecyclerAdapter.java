package aeramli.ma.backbasecitiesapp.ui.city.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.databinding.CityListItemBinding;

public class CityListRecyclerAdapter extends RecyclerView.Adapter<CityViewHolder> {
    public interface OnCitySelectedListener {
        void onCitySelected(City city);
    }

    private final List<City> cities;
    private final OnCitySelectedListener listener;

    public CityListRecyclerAdapter(@NonNull List<City> cities, @Nullable OnCitySelectedListener listener) {
        this.cities = cities;
        this.listener = listener;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CityListItemBinding binding = CityListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        final City city = cities.get(position);
        holder.bind(city);
        if (listener != null) {
            holder.itemView.setOnClickListener(view -> listener.onCitySelected(city));
        }
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}