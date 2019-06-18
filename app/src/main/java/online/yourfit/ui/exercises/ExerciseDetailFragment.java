package online.yourfit.ui.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import online.yourfit.R;
import online.yourfit.managers.ExercisesManager;
import online.yourfit.models.Exercise;

public class ExerciseDetailFragment extends Fragment {

    private View containerView;
    private TextView tvExerciseName;
    private ImageView imgPrimary;

    public static ExerciseDetailFragment newInstance(int i) {
        Bundle args = new Bundle();
        args.putInt(ExercisesManager.ARG_EXERCISE_ID, i);
        ExerciseDetailFragment fragment = new ExerciseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_exercise_detail, container, false);
        initViews(v);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        containerView = view;
        super.onViewCreated(view, savedInstanceState);

        int i = getArguments().getInt(ExercisesManager.ARG_EXERCISE_ID, -1);
        if (i < 0) {
            throw new RuntimeException("wrong card id");
        }

        Exercise item = ExercisesManager.getList().get(i);
        fillViews(item);
    }

    private void initViews(View v){
        tvExerciseName = v.findViewById(R.id.tv_exercise_detail_name);
        imgPrimary = v.findViewById(R.id.img_exercise_detail_img1);
    }

    private void fillViews(Exercise exercise){
        tvExerciseName.setText(exercise.getName());

        Glide.with(containerView)
                .load(exercise.getPrimaryImgUrl())
                .into(imgPrimary);
    }
}
