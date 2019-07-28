package online.yourfit.ui.blogs;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import online.yourfit.R;
import online.yourfit.data.workout.Workout;
import online.yourfit.ui.BaseFragment;

public class BlogDetailsFragment extends BaseFragment {

    public static final String ARG_BLOG_ID = "blogId";
    private BlogViewModel viewModel;
    private int blogId;

    private TextView tvTitle;
    private TextView tvAuthorName;
    private TextView tvIntro;
    private WebView blogText;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid ID");
        }

        blogId = bundle.getInt(BlogDetailsFragment.ARG_BLOG_ID, -1);
        if (blogId < 0) {
            throw new RuntimeException("Invalid ID");
        }

        viewModel = BlogViewModel.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blog_details, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.startObserving();
    }

    private void initViews(View v) {
        tvTitle = v.findViewById(R.id.tv_blogTitle);
        tvAuthorName = v.findViewById(R.id.tv_authorName);
        //tvIntro = v.findViewById(R.id.tv_intro);
        blogText = v.findViewById(R.id.blogText);
    }

    private void startObserving() {
        viewModel.getPost(blogId).observe(this, blogPost -> {
            tvTitle.setText(blogPost.getTitle());
            tvAuthorName.setText(blogPost.getAuthorName());
            //tvIntro.setText(blogPost.getIntro());
            setActionBarTitle(blogPost.getTitle());
            blogText.loadData(blogPost.getText(), "text/html; charset=utf-8", "UTF-8");
        });
    }
}
