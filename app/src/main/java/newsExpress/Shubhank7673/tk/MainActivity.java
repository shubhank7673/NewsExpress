package newsExpress.Shubhank7673.tk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//http://newsapi.org/v2/top-headlines?country=us&apiKey=9208762db6a64b7891607b6f769ffbad
//https://www.googleapis.com/blogger/v3/blogs/394040623688436172/posts?key=AIzaSyAVgAw1TDa-mZzcr18-3FKXAKAXEIyubM0
public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    RecyclerView recyclerView;
    TextView testText;
    ProgressBar progressBarMain;
    ImageView reload;
    int selected=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBarMain = findViewById(R.id.progressBarMain);
        recyclerView = findViewById(R.id.newsList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setUpToolbar();
        getNewsData();
        //hamburger = findViewById(R.id.hamburger);
        drawerLayout = findViewById(R.id.drawer);
        /*hamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });*/
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reload.setVisibility(View.GONE);
                progressBarMain.setVisibility(View.VISIBLE);
                getNewsData();
            }
        });

        //testText = findViewById(R.id.testText);
        //setUpToolbar();
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.nav_home:
                        selected=0;
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        reload.setVisibility(View.GONE);
                        progressBarMain.setVisibility(View.VISIBLE);
                        getNewsData();
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_topHeadlines:
                        selected=1;
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        reload.setVisibility(View.GONE);
                        progressBarMain.setVisibility(View.VISIBLE);
                        getNewsData();
                        Toast.makeText(MainActivity.this, "Top Headlines", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_saved:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });
    }
    private void setUpToolbar()
    {
        reload = findViewById(R.id.reload);
        drawerLayout = findViewById(R.id.drawer);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    private void getNewsData()
    {
        Call<NewsList> newsListCall = selected==0?NewsAPI.getService().getNewsList():NewsAPI.getService().getTopHeadlinesList();
        newsListCall.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                NewsList list = response.body();
                //testText.setText(list.getArticles().get(0).getTitle());
                //Toast.makeText(MainActivity.this, list.getArticles().get(0).getAuthor(), Toast.LENGTH_SHORT).show();
                assert list != null;
                List<Article>li = list.getArticles();
                Collections.shuffle(li);
                recyclerView.setAdapter(new newsAdapter(MainActivity.this,li));
                progressBarMain.setVisibility(View.GONE);
                reload.setVisibility(View.VISIBLE);
                //Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
