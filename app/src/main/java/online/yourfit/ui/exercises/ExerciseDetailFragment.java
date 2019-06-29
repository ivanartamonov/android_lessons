package online.yourfit.ui.exercises;

import android.app.Activity;
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

import java.util.Timer;
import java.util.TimerTask;

import online.yourfit.R;
import online.yourfit.data.exercises.ExercisesManager;
import online.yourfit.data.exercises.Exercise;

public class ExerciseDetailFragment extends Fragment {

    private View containerView;
    private TextView tvExerciseName;
    private TextView tvExerciseType;
    private ImageView imgPrimary;

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

        Bundle bundle = getArguments();
        if (bundle == null) {
            throw new RuntimeException("Invalid exercise ID");
        }

        int id = bundle.getInt(ExercisesManager.ARG_EXERCISE_ID, -1);
        if (id < 0) {
            throw new RuntimeException("Invalid exercise ID");
        }

        Exercise item = ExercisesManager.getList().get(id);
        fillViews(item);
    }

    private void initViews(View v){
        tvExerciseName = v.findViewById(R.id.tv_exercise_detail_name);
        tvExerciseType = v.findViewById(R.id.tv_exercise_detail_type);
        imgPrimary = v.findViewById(R.id.img_exercise_detail_img1);
    }

    private boolean isMainImg = true;

    private void fillViews(Exercise exercise){
        tvExerciseName.setText(exercise.getName());

        if (!exercise.getTypeName().isEmpty()) {
            this.tvExerciseType.setText(exercise.getTypeName());
        } else {
            this.tvExerciseType.setVisibility(View.GONE);
        }

        final Exercise e = exercise;

        Timer mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Activity activity = getActivity();

                if (activity != null) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (isMainImg) {
                                isMainImg = false;
                                Glide.with(containerView)
                                        .load(e.getPrimaryImgUrl())
                                        .into(imgPrimary);
                            } else {
                                isMainImg = true;
                                Glide.with(containerView)
                                        .load(e.getSecondaryImgUrl())
                                        .into(imgPrimary);
                            }
                        }
                    });
                }

            }
        }, 0, 1000);
    }

}
