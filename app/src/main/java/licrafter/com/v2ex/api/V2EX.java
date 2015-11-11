package licrafter.com.v2ex.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by lijinxiang on 11/5/15.
 */
public interface V2EX {
    public static String BASE_URL = "https://www.v2ex.com";

    /**
     * 获取tab主题列表
     *
     * @param tab
     * @param callback
     */
    @GET("/")
    void getTabTopics(@Query("tab") String tab, Callback<Response> callback);

    /**
     * 获取最近的主题列表(和tab列表差不多)
     *
     * @param recent
     * @param callback
     */
    @GET("/{recent}")
    void getRecentTopics(@Path("recent") String recent, @Query("p") int page, Callback<Response> callback);
}
