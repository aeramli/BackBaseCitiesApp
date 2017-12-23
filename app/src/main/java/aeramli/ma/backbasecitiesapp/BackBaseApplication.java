package aeramli.ma.backbasecitiesapp;

import android.app.Application;
import android.os.StrictMode;

import com.google.gson.Gson;

import java.util.concurrent.atomic.AtomicReference;

import aeramli.ma.backbasecitiesapp.city.CityRepository;
import aeramli.ma.backbasecitiesapp.city.disk.CityDiskDataSource;
import aeramli.ma.backbasecitiesapp.city.mapper.CityMapper;
import aeramli.ma.backbasecitiesapp.utils.FileHelper;

public class BackBaseApplication extends Application {

    private AtomicReference<CityRepository> cityRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        cityRepository = new AtomicReference<>();
    }

    public CityRepository getCityRepository() {
        if (cityRepository.get() != null) {
            return cityRepository.get();
        }
        FileHelper fileHelper = provideFileHelper();
        CityDiskDataSource diskDataSource = provideCityDiskDataSource(fileHelper);
        CityRepository repository = new CityRepository(diskDataSource, new CityMapper());
        cityRepository.set(repository);
        return repository;
    }

    private CityDiskDataSource provideCityDiskDataSource(FileHelper fileHelper) {
        return new CityDiskDataSource(fileHelper, new Gson());
    }

    private FileHelper provideFileHelper() {
        return new FileHelper(this);
    }
}
