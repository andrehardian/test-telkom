package app.test.testtelkom.repo;

import android.content.Context;

import androidx.databinding.ObservableField;

import javax.inject.Inject;

import app.test.testtelkom.manager.DialogManager;
import app.test.testtelkom.manager.ServiceManager;
import app.test.testtelkom.model.ResponseDetail;

public class RepoSpecificDetailList extends BaseRepository {

    private ObservableField<String> name;

    @Inject
    public RepoSpecificDetailList(Context context, DialogManager dialogManager) {
        super(new ServiceManager(context, dialogManager), context, dialogManager);
    }

    public void getDetail(long id, ObservableField<String> name) {
        this.name = name;
        serviceManager.getDetail(id);
    }

    @Override
    public void onSuccessWithData(Object o) {
        if (o instanceof ResponseDetail) {
            if (name != null)
                name.set(((ResponseDetail) o).getBy());
        }
    }


}
