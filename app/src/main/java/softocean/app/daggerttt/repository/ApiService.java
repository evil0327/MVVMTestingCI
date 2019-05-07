package softocean.app.daggerttt.repository;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import softocean.app.daggerttt.vo.City;

public interface ApiService {
    @GET("111svk")
    Single<Response<List<City>>> getCityListRx();
}
