package app.test.testtelkom.manager;


import android.content.Context;

import app.test.testtelkom.con.ConDetail;
import app.test.testtelkom.con.ConList;
import app.test.testtelkom.utils.UtilsCon;

public class ServiceManager extends HttpCoreManager {
    public ServiceManager(Context context,
                          DialogManager dialogManager) {
        this.context = context;
        this.dialogManager = dialogManager;
        utilsCon = new UtilsCon(context);
        setContext(context);
        init();
    }


    public void getDetail(long id) {
        initSubscribe(new ConDetail(context, id));
    }

    public void getList() {
        initSubscribe(new ConList(context));
    }
}
