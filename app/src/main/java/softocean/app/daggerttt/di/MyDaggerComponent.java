package softocean.app.daggerttt.di;


import javax.inject.Singleton;

import dagger.Component;
import softocean.app.daggerttt.ui.MainActivity;
import softocean.app.daggerttt.ui.MainFragment;

@Singleton
@Component(modules={AppModule.class, RepoModule.class})
public interface MyDaggerComponent {
    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
}