package softocean.app.daggerttt.di;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import softocean.app.daggerttt.vm.MainViewModel;
import softocean.app.daggerttt.vm.MyViewModelFactory;

@Module
public abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindRepoViewModel(MainViewModel repoViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(MyViewModelFactory factory);
}