package softocean.app.daggerttt.repository;

import android.content.SharedPreferences;
import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

public class LocalRepository {
    private SharedPreferences sharedPreferences;

    public LocalRepository(SharedPreferences sharedPreferences){
        this.sharedPreferences =  sharedPreferences;
    }

    public boolean putToken(String token){
        return sharedPreferences.edit().putString("token", token).commit();
    }

    public String getToken(){
        return sharedPreferences.getString("token", "no token");
    }

}
