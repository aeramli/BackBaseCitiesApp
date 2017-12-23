package aeramli.ma.backbasecitiesapp.ui.city.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import aeramli.ma.backbasecitiesapp.R;
import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.databinding.ActivityCityBinding;
import aeramli.ma.backbasecitiesapp.ui.city.adapter.CityListRecyclerAdapter;
import aeramli.ma.backbasecitiesapp.ui.city.fragment.CityListFragment;
import aeramli.ma.backbasecitiesapp.ui.city.fragment.CityMapFragment;

public class CityActivity extends AppCompatActivity implements CityListRecyclerAdapter.OnCitySelectedListener {

    private ActivityCityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_city);
        setSupportActionBar(binding.toolbar);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment, CityListFragment.newInstance()).commit();
    }

    @Override
    public void onCitySelected(City city) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, CityMapFragment.newInstance(city));
        transaction.addToBackStack(CityMapFragment.class.getCanonicalName());
        transaction.commit();
    }
}
