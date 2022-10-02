package app.test.testtelkom.base;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

import app.test.testtelkom.manager.DialogManager;
import app.test.testtelkom.manager.ServiceManager;
import app.test.testtelkom.model.ResponseDetail;
import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.HttpRequest;

public class Common {

    @BindingAdapter(value = {"idData"}, requireAll = true)
    public static void setDetail(TextView view, long idData) {
        view.setText("loading name...");
        ServiceManager serviceManager = new ServiceManager(view.getContext(), new DialogManager(view.getContext()));
        serviceManager.showDialog(false);
        serviceManager.setConnectionListener(new ConnectionListener() {
            @Override
            public void onSuccessWithData(Object o) {
                if (o instanceof ResponseDetail) {
                    view.setText(((ResponseDetail) o).getBy());
                }
            }

            @Override
            public void onSuccessNull() {

            }

            @Override
            public void onMessageSuccess(String s) {

            }

            @Override
            public void onError(Object o, HttpRequest httpRequest) {
                view.setText("name not load");
            }

            @Override
            public void unAuthorized(HttpRequest httpRequest, String messageError) {

            }
        });
        serviceManager.getDetail(idData);
    }

}
