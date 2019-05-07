package softocean.app.daggerttt.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import softocean.app.daggerttt.vo.City;

@Database(entities = {City.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CityDao cityDao();

}