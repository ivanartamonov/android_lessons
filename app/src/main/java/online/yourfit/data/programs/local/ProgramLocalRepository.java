package online.yourfit.data.programs.local;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import online.yourfit.core.App;
import online.yourfit.data.programs.Program;

public class ProgramLocalRepository {

    @Inject
    ProgramDao dao;

    public ProgramLocalRepository() {
        App.instance.getAppComponent().inject(this);
    }

    public LiveData<List<Program>> fetchAll() {
        return dao.getAll();
    }

    public void insert(Program program) {
        new ProgramLocalRepository.InsertProgramAsyncTask(dao).execute(program);
    }

    public void update(Program program) {
        new ProgramLocalRepository.UpdateProgramAsyncTask(dao).execute(program);
    }

    public void delete(Program program) {
        new ProgramLocalRepository.DeleteProgramAsyncTask(dao).execute(program);
    }

    public void deleteAll() {
        new ProgramLocalRepository.DeleteAllProgramsAsyncTask(dao).execute();
    }

    public Completable deleteById(int id) {
        return Completable.fromAction(() -> dao.deleteById(id));
    }

    public LiveData<Program> findById(int id) {
        return dao.getById(id);
    }

    private static class InsertProgramAsyncTask extends AsyncTask<Program, Void, Void> {
        private ProgramDao dao;

        InsertProgramAsyncTask(ProgramDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Program... programs) {
            dao.insert(programs[0]);
            return null;
        }
    }

    private static class UpdateProgramAsyncTask extends AsyncTask<Program, Void, Void> {
        private ProgramDao dao;

        UpdateProgramAsyncTask(ProgramDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Program... programs) {
            dao.update(programs[0]);
            return null;
        }
    }

    private static class DeleteProgramAsyncTask extends AsyncTask<Program, Void, Void> {
        private ProgramDao dao;

        DeleteProgramAsyncTask(ProgramDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Program... programs) {
            dao.delete(programs[0]);
            return null;
        }
    }

    private static class DeleteAllProgramsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ProgramDao dao;

        DeleteAllProgramsAsyncTask(ProgramDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll();
            return null;
        }
    }
}
