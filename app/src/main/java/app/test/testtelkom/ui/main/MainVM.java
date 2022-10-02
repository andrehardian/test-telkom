package app.test.testtelkom.ui.main;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import javax.inject.Inject;

import app.test.testtelkom.base.BaseViewModel;
import app.test.testtelkom.repo.RepoList;
import lombok.Getter;

public class MainVM extends BaseViewModel<RepoList> implements RepoList.ListListener {
    @Getter
    private MutableLiveData<ArrayList<Long>> data = new MutableLiveData<>();

    @Inject
    public MainVM(RepoList repoList) {
        repo = repoList;
        repo.setListener(this);
    }

    void getList() {
        repo.getList();
    }

    @Override
    public void result(ArrayList<Long> data) {
        this.data.postValue(data);
    }
}
