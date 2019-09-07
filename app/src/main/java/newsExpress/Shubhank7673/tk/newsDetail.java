package newsExpress.Shubhank7673.tk;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

public class newsDetail extends AppCompatActivity {
    ProgressBar progressBar;
    Toolbar toolbar;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        //Toast.makeText(this, getIntent().getStringExtra("url"), Toast.LENGTH_SHORT).show();
        progressBar = findViewById(R.id.progressBar);
        webView  = findViewById(R.id.newsDetailView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                //Toast.makeText(newsDetail.this, "Loaded !", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webView.loadUrl(getIntent().getStringExtra("url"));
    }
}
