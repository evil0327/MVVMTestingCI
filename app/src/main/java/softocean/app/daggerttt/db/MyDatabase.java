package softocean.app.daggerttt.db;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import softocean.app.daggerttt.vo.City;

@Database(entities = {City.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract CityDao cityDao();

}