package online.yourfit.data.blogs.remote;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import online.yourfit.core.App;
import online.yourfit.data.blogs.BlogPost;

public class BlogRemoteRepository {

    @Inject
    BlogApi exercisesApi;

    public BlogRemoteRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public Flowable<List<BlogPost>> getAll() {
        return exercisesApi.getBlogPosts();
    }
}
