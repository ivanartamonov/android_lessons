package online.yourfit.ui.programs;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import online.yourfit.data.programs.Program;
import online.yourfit.data.programs.local.ProgramLocalRepository;

class ProgramsViewModel extends AndroidViewModel {

    private ProgramLocalRepository localRepository;

    ProgramsViewModel(@NonNull Application application) {
        super(application);
        localRepository = new ProgramLocalRepository();
    }

    LiveData<List<Program>> getProgramsList() {
        return localRepository.fetchAll();
    }

    LiveData<Program> getProgramById(int id) {
        return localRepository.findById(id);
    }
}
