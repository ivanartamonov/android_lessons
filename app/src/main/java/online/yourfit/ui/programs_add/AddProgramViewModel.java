package online.yourfit.ui.programs_add;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import online.yourfit.data.programs.Program;
import online.yourfit.data.programs.local.ProgramLocalRepository;

class AddProgramViewModel extends AndroidViewModel {

    private ProgramLocalRepository localRepository;

    AddProgramViewModel(Application application) {
        super(application);
        localRepository = new ProgramLocalRepository(application);
    }

    void createNew(String title) {
        Program program = new Program(title, 1, 1, 1);

        localRepository.insert(program);
    }

}
