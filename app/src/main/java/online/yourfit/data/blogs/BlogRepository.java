package online.yourfit.data.blogs;

import java.util.List;

import io.reactivex.Flowable;
import online.yourfit.data.blogs.remote.BlogRemoteRepository;

public class BlogRepository {

    private BlogRemoteRepository remoteRepository;

    public BlogRepository() {
        remoteRepository = new BlogRemoteRepository();
    }

    public Flowable<List<BlogPost>> getAll() {
        return remoteRepository.getAll();
    }

}
