package softocean.app.daggerttt;

import android.app.Application;

import softocean.app.daggerttt.di.AppModule;
import softocean.app.daggerttt.di.DaggerComponentHolder;
import softocean.app.daggerttt.di.DaggerMyDaggerComponent;
import softocean.app.daggerttt.di.MyDaggerComponent;
import softocean.app.daggerttt.di.RepoModule;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();


        MyDaggerComponent daggerComponent = DaggerMyDaggerComponent.builder()
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .repoModule(new RepoModule())
                .build();
        DaggerComponentHolder.setAppComponent(daggerComponent);
    }

}
