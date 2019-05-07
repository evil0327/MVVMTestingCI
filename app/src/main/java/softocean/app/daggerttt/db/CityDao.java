package softocean.app.daggerttt.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Maybe;
import softocean.app.daggerttt.vo.City;

@Dao
public interface CityDao {
    @Query("select * from city")
    Maybe<List<City>> getAllCities();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCities(List<City> cities);

}