package com.example.laurent.velostation;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface VelostationDao {
    @Query("SELECT * FROM velostation")
    List<Velostation> getAll();

    @Query("SELECT * FROM velostation WHERE uid IN (:userIds)")
    List<Velostation> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM velostation WHERE name LIKE :first AND "
            + "lat LIKE :lat AND + lon LIKE :lon LIMIT 1")
    Velostation findByName(String first, String lat, String lon);

    @Insert
    void insertAll(Velostation... velostations);

    @Delete
    void delete(Velostation velostation);
}
