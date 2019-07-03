package online.yourfit.data.programs.local;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import online.yourfit.core.db.AppDatabase;
import online.yourfit.data.programs.Program;

public class ProgramLocalRepository {

    private ProgramDao dao;

    public ProgramLocalRepository(Application application) {
        dao = AppDatabase.getInstance(application.getApplicationContext()).programDao();
    }

    public LiveData<List<Program>> fetchAll() {
        return dao.getAll();
    }

    public void insert(Program user) {
        new ProgramLocalRepository.InsertProgramAsyncTask(dao).execute(user);
    }

    public void update(Program user) {
        new ProgramLocalRepository.UpdateProgramAsyncTask(dao).execute(user);
    }

    public void delete(Program user) {
        new ProgramLocalRepository.DeleteProgramAsyncTask(dao).execute(user);
    }

    public void deleteAll() {
        new ProgramLocalRepository.DeleteAllProgramsAsyncTask(dao).execute();
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
