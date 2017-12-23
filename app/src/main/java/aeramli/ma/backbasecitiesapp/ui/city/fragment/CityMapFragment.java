package aeramli.ma.backbasecitiesapp.ui.city.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import aeramli.ma.backbasecitiesapp.R;
import aeramli.ma.backbasecitiesapp.city.model.City;
import aeramli.ma.backbasecitiesapp.databinding.FragmentCityMapBinding;

public class CityMapFragment extends Fragment {
    private static final int DEFAULT_ZOOM_LEVEL = 7;
    private static final String EXTRA_CITY = "EXTRA_CITY";

    private FragmentCityMapBinding binding;
    private City city;

    public CityMapFragment() {
        // Required empty public constructor
    }

    public static CityMapFragment newInstance(City city) {
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_CITY, city);

        CityMapFragment fragment = new CityMapFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        city = getArguments().getParcelable(EXTRA_CITY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_city_map, container, false);
        binding.map.onCreate(savedInstanceState);
        binding.map.getMapAsync(googleMap -> {
            googleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(city.getLat(), city.getLon()))
                    .title(city.getName()));
            googleMap.animateCamera(CameraUpdateFactory
                    .newLatLngZoom(new LatLng(city.getLat(), city.getLon()), DEFAULT_ZOOM_LEVEL));
        });
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.map.onStart();
    }


    @Override
    public void onResume() {
        super.onResume();
        binding.map.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.map.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.map.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding.map.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.map.onLowMemory();
    }
}
