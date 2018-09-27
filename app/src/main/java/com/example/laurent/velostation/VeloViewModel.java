package com.example.laurent.velostation;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.List;

public class VeloViewModel extends AndroidViewModel {

    private VeloRepository mRepository;

    private LiveData<List<Velostation>> mAllStations;

    public VeloViewModel (Application application) {
        super(application);
        mRepository = new VeloRepository(application);
        mAllStations = mRepository.getmAllVelo();
    }

    LiveData<List<Velostation>> getmAllStations() { return mAllStations; }

    public void insert(Velostation velostation) { mRepository.insert(velostation); }
}