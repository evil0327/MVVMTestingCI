package softocean.app.daggerttt.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import softocean.app.daggerttt.repository.ApiRepository;
import softocean.app.daggerttt.repository.DbRepository;
import softocean.app.daggerttt.repository.LocalRepository;
import softocean.app.daggerttt.vo.City;

public class MainViewModel extends ViewModel {
    private DbRepository dbRepository;
    private ApiRepository apiRepository;
    private CompositeDisposable disposables = new CompositeDisposable();

    private MutableLiveData<List<City>> mCityLiveData = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoadingLiveData = new MutableLiveData<>();
    private MutableLiveData<String> mErrorMsgLiveData = new MutableLiveData<>();


    @Inject
    public MainViewModel(DbRepository dbRepository, ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
        this.dbRepository = dbRepository;
    }

    public MutableLiveData<List<City>> getCityLiveData() {
        return mCityLiveData;
    }

    public MutableLiveData<Boolean> getLoadingLiveData() {
        return mLoadingLiveData;
    }

    public MutableLiveData<String> getErrorMsgLiveData() {
        return mErrorMsgLiveData;
    }


    public void getCityList() {
        mLoadingLiveData.postValue(true);
        disposables.add(apiRepository.getCityList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<City>>>() {
            @Override
            public void onSuccess(Response<List<City>> response) {
                if(response.isSuccessful()){
                    mCityLiveData.postValue(response.body());
                }else{
                    mErrorMsgLiveData.postValue("api error");
                }
                mLoadingLiveData.postValue(false);

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mErrorMsgLiveData.postValue(e.getMessage());
                mLoadingLiveData.postValue(false);
            }
        }));

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposables.dispose();
    }
}
