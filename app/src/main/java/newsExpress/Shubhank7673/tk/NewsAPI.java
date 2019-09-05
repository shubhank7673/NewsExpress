package newsExpress.Shubhank7673.tk;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NewsAPI {
    private static final String key = "9208762db6a64b7891607b6f769ffbad";
    private static final String country = "in";
    private static final String key2 = "AIzaSyAVgAw1TDa-mZzcr18-3FKXAKAXEIyubM0";
    private static String url = "http://newsapi.org/v2/top-headlines/";// + country + "&apiKey="+key;
    private static String url2 = "https://www.googleapis.com/blogger/v3/blogs/394040623688436172/posts/";

    public static newsDataFetch newsDataFetchObj = null;

    public static newsDataFetch getService() {
        if (newsDataFetchObj == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url2)
                    //.client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsDataFetchObj = retrofit.create(newsDataFetch.class);
        }
        return newsDataFetchObj;
    }



    public interface newsDataFetch {
        /*@GET("?country=in&apiKey="+key)
        Call<NewsList> getNewsList();*/
        @GET("?key="+key2)
        Call<NewsList> getNewsList();
    }
}
