package aeramli.ma.backbasecitiesapp.city.disk;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import aeramli.ma.backbasecitiesapp.R;
import aeramli.ma.backbasecitiesapp.utils.FileHelper;

public class CityDiskDataSource {
    private final FileHelper fileHelper;
    private final Gson gson;

    public CityDiskDataSource(FileHelper fileHelper, Gson gson) {
        this.fileHelper = fileHelper;
        this.gson = gson;
    }

    public void parseCities(final OnCitiesParsedListener listener) {
        AsyncTask.execute(() -> {
            String citiesJson = fileHelper.read(R.raw.cities);
            List<CityDiskModel> cities = Collections.emptyList();
            if (!TextUtils.isEmpty(citiesJson)) {
                Type listType = new TypeToken<ArrayList<CityDiskModel>>() {}.getType();
                cities = gson.fromJson(citiesJson, listType);
            }
            listener.onCitiesParse(cities);
        });
    }

    public interface OnCitiesParsedListener {
        void onCitiesParse(List<CityDiskModel> cities);
    }
}
