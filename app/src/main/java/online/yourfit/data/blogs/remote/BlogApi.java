package online.yourfit.data.blogs.remote;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.blogs.BlogPost;
import retrofit2.http.GET;

public interface BlogApi {
    @GET("/api/blog")
    Flowable<List<BlogPost>> getBlogPosts();
}
