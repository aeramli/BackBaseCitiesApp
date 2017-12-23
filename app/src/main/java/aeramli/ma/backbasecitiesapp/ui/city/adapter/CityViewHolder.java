package aeramli.ma.backbasecitiesapp.ui.city.adapter;

import android.support.v7.widget.RecyclerView;

import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.databinding.CityListItemBinding;

public class CityViewHolder extends RecyclerView.ViewHolder {
    private CityListItemBinding binding;

    public CityViewHolder(CityListItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(City city) {
        binding.setCity(city);
        binding.executePendingBindings();
    }
}
