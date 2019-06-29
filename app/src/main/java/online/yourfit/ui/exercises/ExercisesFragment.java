package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.activities.MainActivity;
import online.yourfit.R;
import online.yourfit.data.exercises.Exercise;
import online.yourfit.core.NetworkService;

public class ExercisesFragment extends Fragment {

    private static final String LOG_TAG = "ExercisesFragment";

    // Views
    private View root;
    private RecyclerView recyclerView;

    @NonNull
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private ExercisesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_exercises, container, false);

        this.initViews();
        this.showWorkoutHistoryList();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.exercises_recycler);
    }

    private void showWorkoutHistoryList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ExercisesAdapter.IDetailExerciseListener listener = (MainActivity) getActivity();

        adapter = new ExercisesAdapter(listener);
        recyclerView.setAdapter(adapter);

        Observable<List<Exercise>> observable = NetworkService.getInstance()
                .getExercisesApi()
                .getExercises();

        compositeDisposable.add(observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Exercise>>() {
                    @Override
                    public void accept(List<Exercise> exercises) {
                        adapter.setItems(exercises);
                    }
                })
        );
    }

    @Override
    public void onDestroyView() {
        compositeDisposable.clear();
        Log.d(LOG_TAG, "compositeDisposable.clear()");
        super.onDestroyView();
    }
}
