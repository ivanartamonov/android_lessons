package online.yourfit.ui.exercises;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import online.yourfit.core.App;
import online.yourfit.data.exercises.ExerciseRepository;
import online.yourfit.data.exercises.ExercisesManager;
import online.yourfit.R;
import online.yourfit.data.exercises.Exercise;

public class ExercisesFragment extends Fragment implements ExercisesAdapter.ExercisesAdapterListener {

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
        this.showExercisesList();

        return root;
    }

    private void initViews() {
        recyclerView = root.findViewById(R.id.exercises_recycler);
    }

    private void showExercisesList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ExercisesAdapter(this);
        recyclerView.setAdapter(adapter);

        ExerciseRepository repository = new ExerciseRepository(App.instance);
        Flowable<List<Exercise>> observable = repository.getAll();

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
        super.onDestroyView();
    }

    @Override
    public void navigateToExerciseDetails(int id) {
        NavController controller = NavHostFragment.findNavController(this);

        Bundle args = new Bundle();
        args.putInt(ExercisesManager.ARG_EXERCISE_ID, id);

        controller.navigate(R.id.nav_exercise_detail, args);
    }
}
