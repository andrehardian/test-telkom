package app.test.testtelkom.repo;

import android.content.Context;

import javax.inject.Inject;

import app.test.testtelkom.manager.DialogManager;
import app.test.testtelkom.manager.ServiceManager;
import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.HttpRequest;
import lombok.Getter;

public class BaseRepository implements ConnectionListener {
    @Getter
    protected ServiceManager serviceManager;
    protected Context context;
    protected DialogManager dialogManager;

    @Inject
    public BaseRepository(ServiceManager serviceManager,Context context,DialogManager dialogManager){
        this.serviceManager = serviceManager;
        this.context = context;
        this.dialogManager = dialogManager;
        this.serviceManager.setConnectionListener(this);
    }

    @Override
    public void onSuccessWithData(Object o) {

    }

    @Override
    public void onSuccessNull() {

    }

    @Override
    public void onMessageSuccess(String s) {
        dialogManager.setDialogListener(this::messageSuccessListener);
        dialogManager.showPopup(s);
    }

    protected void messageSuccessListener() {

    }

    @Override
    public void onError(Object o, HttpRequest httpRequest) {
    }

    @Override
    public void unAuthorized(HttpRequest httpRequest, String messageError) {

    }
}
