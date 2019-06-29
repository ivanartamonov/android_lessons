package online.yourfit.ui;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<T extends BaseViewModel> extends AppCompatActivity implements IView {

    protected T viewModel;

    /**
     * ViewModel must be initialized before bindView() is called
     * @param layout layout for the activity
     */
    protected final void bindView(int layout) {
        if (viewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
        }
        setContentView(layout);
    }

    @Override protected void onStop() {
        super.onStop();
        viewModel.clearSubscriptions();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        viewModel.detach();
    }

    @Override
    public void error(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void error() {
        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
    }
}
