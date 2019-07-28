package online.yourfit.ui.blogs;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import online.yourfit.R;
import online.yourfit.data.blogs.BlogPost;

public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.BlogsViewHolder> {

    private List<BlogPost> items = new ArrayList<>();
    private BlogsAdapter.BlogsAdapterListener listener;

    public interface BlogsAdapterListener {
        void onBlogsPostSelected(int id);
    }

    public BlogsAdapter(BlogsAdapterListener listener) {
        this.listener = listener;
    }

    public void setItems(List<BlogPost> posts) {
        this.items = posts;
        Log.d("blogs", "count: " + posts.size());
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BlogsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_blog_item, parent, false);

        return new BlogsAdapter.BlogsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogsAdapter.BlogsViewHolder holder, int position) {
        holder.bind(this.items.get(position));

        final int id = this.items.get(position).getId();
        holder.itemView.setOnClickListener(v -> {
            if (listener == null) {
                throw new RuntimeException("Listener must be initialized");
            }
            listener.onBlogsPostSelected(id);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class BlogsViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAuthorAvatar;
        TextView tvTitle;
        TextView tvAbstract;
        TextView tvAuthorName;

        BlogsViewHolder(@NonNull View itemView) {
            super(itemView);

            this.tvTitle = itemView.findViewById(R.id.tv_title);
            this.tvAuthorName = itemView.findViewById(R.id.tv_authorName);
            this.tvAbstract = itemView.findViewById(R.id.tv_abstract);
            this.imgAuthorAvatar = itemView.findViewById(R.id.img_author_avatar);
        }

        void bind(BlogPost post) {
            tvTitle.setText(post.getTitle());
            tvAuthorName.setText(post.getAuthorName());
            tvAbstract.setText(post.getIntro());

            Glide.with(itemView)
                    .load(post.getAuthorAvatarUrl())
                    .into(imgAuthorAvatar);
        }
    }

}
