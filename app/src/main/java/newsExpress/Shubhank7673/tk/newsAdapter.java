package newsExpress.Shubhank7673.tk;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.newsViewHolder> {
    private Context context;
    private List<Article> articles;

    public newsAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }



    @NonNull
    @Override
    public newsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_item,parent,false);
        return new newsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newsViewHolder holder, int position) {
        final Article article = articles.get(position);
        holder.newsTitle.setText(article.getTitle());
        holder.newsDescription.setText(article.getDescription());
        Glide.with(context).load(article.getUrlToImage()).into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,newsDetail.class);
                intent.putExtra("url",article.getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class newsViewHolder extends RecyclerView.ViewHolder
    {
        ImageView newsImage;
        TextView newsTitle;
        TextView newsDescription;
        public newsViewHolder(View newsItemView)
        {
            super(newsItemView);
            newsImage = (ImageView) newsItemView.findViewById(R.id.newsImage);
            newsTitle = (TextView) newsItemView.findViewById(R.id.newsTitle);
            newsDescription = (TextView) newsItemView.findViewById(R.id.newsDescription);
        }
    }
}
