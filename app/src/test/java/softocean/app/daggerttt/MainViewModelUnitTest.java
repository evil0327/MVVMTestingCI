package softocean.app.daggerttt;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import softocean.app.daggerttt.repository.ApiRepository;
import softocean.app.daggerttt.repository.DbRepository;
import softocean.app.daggerttt.vm.MainViewModel;
import softocean.app.daggerttt.vo.City;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MainViewModelUnitTest {
    @Mock
    private DbRepository dbRepository;
    @Mock
    private ApiRepository apiRepository;

    private MainViewModel mainViewModel;

    private static List<City> FAKE_CITY_LIST = new ArrayList<>();

    @ClassRule
    public static RxSchedulersOverrideRule sSchedulersOverrideRule = new RxSchedulersOverrideRule();

    @Rule
    public InstantTaskExecutorRule instantExecutor = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainViewModel  = new MainViewModel(dbRepository, apiRepository);
    }

    @Test
    public void test_getCityList(){
        City city = new City();
        city.setName("aaa");
        FAKE_CITY_LIST.add(city);

        Response<List<City>> res = Response.success(FAKE_CITY_LIST);
        when(apiRepository.getCityList()).thenReturn(Single.just(res));

        mainViewModel.getCityList();

        Assert.assertEquals(mainViewModel.getCityLiveData().getValue().size(), 1);
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
}