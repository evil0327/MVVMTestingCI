package softocean.app.daggerttt;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import java.util.List;

import io.reactivex.annotations.NonNull;
import softocean.app.daggerttt.repository.ApiRepository;
import softocean.app.daggerttt.repository.DbRepository;
import softocean.app.daggerttt.testing.TestActivity;
import softocean.app.daggerttt.ui.MainFragment;
import softocean.app.daggerttt.vm.MainViewModel;
import softocean.app.daggerttt.vo.City;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {
    private MainViewModel mainViewModel;

    MutableLiveData<Boolean> mockLoadingLiveData = new MutableLiveData<>();
    MutableLiveData<List<City>> mockCityLiveData = new MutableLiveData<>();
    MutableLiveData<String> mockErrorMsgLiveData = new MutableLiveData<>();


    MainFragment mainFragment;
    @Rule
    public ActivityTestRule<TestActivity> activityRule =
            new ActivityTestRule<>(TestActivity.class, true, true);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mainViewModel = mock(MainViewModel.class);
        when(mainViewModel.getLoadingLiveData()).thenReturn(mockLoadingLiveData);
        when(mainViewModel.getCityLiveData()).thenReturn(mockCityLiveData);
        when(mainViewModel.getErrorMsgLiveData()).thenReturn(mockErrorMsgLiveData);

        mainFragment = new MainFragment();
        mainFragment.viewModel = mainViewModel;
        activityRule.getActivity().attachFragment(mainFragment);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("softocean.app.daggerttt", appContext.getPackageName());
    }

    @Test
    public void testMain() {
        mainViewModel.getLoadingLiveData().postValue(true);

        onView(withId(R.id.loading)).check(matches(isDisplayed()));
        mockLoadingLiveData.postValue(false);
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }


}
