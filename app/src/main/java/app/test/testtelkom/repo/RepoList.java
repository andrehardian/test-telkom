package app.test.testtelkom.repo;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;

import app.test.testtelkom.manager.DialogManager;
import app.test.testtelkom.manager.ServiceManager;
import lombok.Setter;

public class RepoList extends BaseRepository {
    @Setter
    private ListListener listener;

    @Inject
    public RepoList(Context context, ServiceManager serviceManager, DialogManager dialogManager) {
        super(serviceManager, context, dialogManager);
    }

    public void getList() {
        serviceManager.getList();
    }

    @Override
    public void onSuccessWithData(Object o) {
        if (o instanceof Long[]) {
            if (listener != null)
                listener.result(new ArrayList<>(Arrays.asList((Long[]) o)));
        }
    }

    public interface ListListener {
        void result(ArrayList<Long> data);
    }
}
