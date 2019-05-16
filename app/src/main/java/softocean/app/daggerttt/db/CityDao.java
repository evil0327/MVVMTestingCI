package softocean.app.daggerttt.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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