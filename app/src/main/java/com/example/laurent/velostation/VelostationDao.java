package com.example.laurent.velostation;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VelostationDao {
    @Query("SELECT * FROM velostation")
    LiveData<List<Velostation>> getAll();

    @Query("SELECT * FROM velostation WHERE uid IN (:userIds)")
    LiveData<List<Velostation>> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM velostation WHERE naam LIKE :first AND "
            + "lat LIKE :lat AND + lon LIKE :lon LIMIT 1")
    Velostation findByName(String first, String lat, String lon);

    @Insert
    void insertAll(Velostation... velostations);

    @Delete
    void delete(Velostation velostation);

    @Insert
    void insert(Velostation param);
}
