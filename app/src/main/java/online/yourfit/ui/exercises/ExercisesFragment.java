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

import online.yourfit.MainActivity;
import online.yourfit.R;
import online.yourfit.models.Exercise;
import online.yourfit.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExercisesFragment extends Fragment {

    private static final String LOG_TAG = "ExercisesFragment";

    // Views
    private View root;
    private RecyclerView recyclerView;

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

        final ExercisesAdapter adapter = new ExercisesAdapter(listener);
        recyclerView.setAdapter(adapter);

        Call<List<Exercise>> exercisesCall = NetworkService.getInstance()
                .getJSONApi()
                .getExercises();

        exercisesCall.enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(@NonNull Call<List<Exercise>> call, @NonNull Response<List<Exercise>> response) {
                List<Exercise> list = response.body();
                if (list != null) {
                    adapter.setItems(list);
                } else {
                    Log.d(LOG_TAG, "Response exercises is empty");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Exercise>> call, @NonNull Throwable t) {
                Log.d(LOG_TAG, "onFailure");
            }
        });
    }
}
