package aeramli.ma.backbasecitiesapp.ui.city.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import aeramli.ma.backbasecitiesapp.R;
import aeramli.ma.backbasecitiesapp.databinding.ActivityCityBinding;

public class CityActivity extends AppCompatActivity {

    private ActivityCityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_city);
        setSupportActionBar(binding.toolbar);
    }
}
