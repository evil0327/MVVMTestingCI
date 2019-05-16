package softocean.app.daggerttt;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import android.content.Context;
import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import androidx.test.rule.ActivityTestRule;

import java.util.List;

import softocean.app.daggerttt.testing.TestActivity;
import softocean.app.daggerttt.ui.MainFragment;
import softocean.app.daggerttt.vm.MainViewModel;
import softocean.app.daggerttt.vo.City;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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
    public void test_loading_show_and_hide() {
        mainViewModel.getLoadingLiveData().postValue(true);
        onView(withId(R.id.loading)).check(matches(isDisplayed()));
        mockLoadingLiveData.postValue(false);
        onView(withId(R.id.loading)).check(matches(not(isDisplayed())));
    }

    @Test
    public void test_recyclerview_size() {

    }


}
