package aeramli.ma.backbasecitiesapp.ui.city.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import aeramli.ma.backbasecitiesapp.BackBaseApplication;
import aeramli.ma.backbasecitiesapp.R;
import aeramli.ma.backbasecitiesapp.city.CityRepository;
import aeramli.ma.backbasecitiesapp.databinding.FragmentCityListBinding;
import aeramli.ma.backbasecitiesapp.ui.viewmodel.CityListFragmentViewModel;

public class CityListFragment extends Fragment {
    private FragmentCityListBinding binding;
    private CityListFragmentViewModel viewModel;

    public CityListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_city_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        inject();

        binding = DataBindingUtil.bind(this.getView());
        binding.recycler.setHasFixedSize(true);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        ((SimpleItemAnimator) binding.recycler.getItemAnimator()).setSupportsChangeAnimations(false);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final String searchQuery = newText.trim();
                viewModel.search(searchQuery);
                return true;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.retrieveCities();
    }

    public void inject() {
        CityRepository repository = ((BackBaseApplication) getActivity().getApplication()).getCityRepository();
        viewModel = new CityListFragmentViewModel(repository);
    }
}
