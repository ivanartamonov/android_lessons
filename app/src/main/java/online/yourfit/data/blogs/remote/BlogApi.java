package online.yourfit.data.blogs.remote;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.blogs.BlogPost;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BlogApi {
    @GET("/api/blog")
    Flowable<List<BlogPost>> getBlogPosts();

    @GET("/api/blog/get")
    Flowable<BlogPost> getById(@Query("id") int id);
}
