package softocean.app.daggerttt.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import softocean.app.daggerttt.repository.ApiRepository;
import softocean.app.daggerttt.repository.DbRepository;
import softocean.app.daggerttt.repository.LocalRepository;

@Module
public class RepoModule {
    @Provides
    @Singleton
    public LocalRepository provideLocalRepo(SharedPreferences sp) {
        return new LocalRepository(sp);
    }
    @Provides
    @Singleton
    public ApiRepository provideApiRepo() {
        return new ApiRepository();
    }

    @Provides
    @Singleton
    public DbRepository provideDatabaseRepo(Application application) {
        return new DbRepository(application);
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
}
