package online.yourfit.ui.blogs;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.data.blogs.BlogPost;
import online.yourfit.data.blogs.BlogRepository;

public class BlogViewModel extends ViewModel {

    private static BlogViewModel instance;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private BlogRepository blogRepository;

    MutableLiveData<BlogPost> blogPost = new MutableLiveData<>();

    public BlogViewModel() {
        blogRepository = new BlogRepository();
    }

    public static BlogViewModel getInstance() {
        if (instance == null) {
            instance = new BlogViewModel();
        }
        return instance;
    }

    public LiveData<BlogPost> getPost(int id) {
        Disposable disposable = blogRepository.getById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(post -> blogPost.setValue(post), throwable -> {
                    Log.d("Blogs", throwable.getMessage());
                });

        compositeDisposable.add(disposable);

        return blogPost;
    }

    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}
