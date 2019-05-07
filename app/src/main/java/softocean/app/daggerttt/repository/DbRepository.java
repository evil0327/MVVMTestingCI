package softocean.app.daggerttt.repository;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

import io.reactivex.Maybe;
import softocean.app.daggerttt.db.MyDatabase;
import softocean.app.daggerttt.vo.City;

public class DbRepository {
    private MyDatabase database;

    public DbRepository(Application app){
        database = Room.databaseBuilder(app.getApplicationContext(), MyDatabase.class, "my_app.db")
                .build();
    }

    public Maybe<List<City>> getCities(){
        return database.cityDao().getAllCities();
    }

    public void insertCities(List<City> cities){
         database.cityDao().insertCities(cities);
    }

}
