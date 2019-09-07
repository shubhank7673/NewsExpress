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
    private static String topHeadlines = "http://newsapi.org/v2/";// + country + "&apiKey="+key;
    private static String url2 = "https://www.googleapis.com/blogger/v3/blogs/394040623688436172/posts/";
    private static String url = "https://newsapi.org/v2/";;
    private static String sources = "https://newsapi.org/v2/sources?country=in&apiKey=9208762db6a64b7891607b6f769ffbad";
    private static String everything = "https://newsapi.org/v2/";

    public static newsDataFetch newsDataFetchObj = null;

    public static newsDataFetch getService() {
        if (newsDataFetchObj == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    //.client(getUnsafeOkHttpClient().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsDataFetchObj = retrofit.create(newsDataFetch.class);
        }
        return newsDataFetchObj;
    }



    public interface newsDataFetch {
        @GET("top-headlines?country=in&apiKey="+key)
        Call<NewsList> getTopHeadlinesList();
        @GET("everything?q=india&pagesize=100&page=1&apiKey="+key)
        Call<NewsList> getNewsList();

    }
}
