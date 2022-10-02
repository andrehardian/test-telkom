package app.test.testtelkom.manager;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.test.testtelkom.R;
import app.test.testtelkom.utils.UtilsCon;
import connection.rxconnection.connection.CallBackForLog;
import connection.rxconnection.connection.ConnectionListener;
import connection.rxconnection.connection.ConnectionManager;
import connection.rxconnection.connection.HttpRequest;
import connection.rxconnection.model.ModelLog;

public class HttpCoreManager extends ConnectionManager implements ConnectionListener, CallBackForLog {
    private final List<HttpRequest> listRequestHold = new ArrayList<>();
    protected String waitingDownload;
    protected String waitingLogin;
    protected String internalServerError;
    protected String noInternet;
    protected UtilsCon utilsCon;
    protected Context context;
    protected ConnectionListener listener;
    protected DialogManager dialogManager;
    private boolean showError = true;

    protected void init() {
        waitingDownload = context.getResources().getString(R.string.waiting_download);
        waitingLogin = context.getResources().getString(R.string.waiting_login);
        internalServerError = context.getResources().getString(R.string.internal_server_error);
        noInternet = context.getResources().getString(R.string.no_internet);
        super.setConnectionListener(this);
    }

    @Override
    public ConnectionManager setContext(Context context) {
        return super.setContext(context);
    }

    public HttpCoreManager setShowError(boolean showError) {
        this.showError = showError;
        return this;
    }

    @Override
    public ConnectionManager setConnectionListener(ConnectionListener connectionListener) {
        listener = connectionListener;
        return super.setConnectionListener(this);
    }


    protected void initSubscribe(HttpRequest httpRequest, String message, boolean checkConnection) {
        initCon(httpRequest);
        checkConnectionSubscribe(httpRequest, message, checkConnection);
    }

    private void checkConnectionSubscribe(HttpRequest httpRequest, String message,
                                          boolean checkConnection) {
        try {
            if (checkConnection)
                if (utilsCon.isInternetAvailable(getContext())) {
                    subscribe(httpRequest, message);
                } else {
                    showDialog(noInternet);
                    if (listener != null)
                        listener.onError(null, httpRequest);
                }
            else
                subscribe(httpRequest, message);
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null)
                listener.onError(e.getMessage(), httpRequest);
        }
    }

    private void checkConnectionSubscribe(HttpRequest httpRequest, boolean checkConnection) {
        try {
            if (checkConnection)
                if (utilsCon.isInternetAvailable(getContext())) {
                    subscribe(httpRequest);
                } else {
                    showDialog(noInternet);
                    if (listener != null)
                        listener.onError(null, httpRequest);
                }
            else
                subscribe(httpRequest);
        } catch (Exception e) {
            e.printStackTrace();
            if (listener != null)
                listener.onError(e.getMessage(), httpRequest);
        }
    }

    protected void initCon(HttpRequest httpRequest) {
        httpRequest.setCustomHeader(header());
        httpRequest.setLogInfoRequestResponse(true);
        httpRequest.setCallBackForLog(this);
    }

    protected void initSubscribe(HttpRequest httpRequest) {
        initCon(httpRequest);
        checkConnectionSubscribe(httpRequest, true);
    }

    @Override
    public void onSuccessWithData(Object o) {
        if (o instanceof String) {
            if (listener != null)
                try {
                    listener.onMessageSuccess((String) o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            if (listener != null)
                listener.onSuccessWithData(o);
        }
    }

    protected void recallHoldService() {
        for (HttpRequest httpRequest : listRequestHold) {
            httpRequest.setCustomHeader(header());
            if (httpRequest.getMessage() != null && httpRequest.getMessage().length() > 0)
                subscribe(httpRequest, httpRequest.getMessage());
            else
                subscribe(httpRequest);
            listRequestHold.remove(httpRequest);
        }

    }


    @Override
    public void onSuccessNull() {
        if (listener != null)
            try {
                listener.onSuccessNull();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public void onMessageSuccess(String s) {
        if (listener != null)
            listener.onMessageSuccess(s);
        if (showError)
            showDialog(s);
    }

    @Override
    public void onError(Object o, HttpRequest httpRequest) {
        String message = getErrorMessage(o);
        if (showError) {
            if (message != null)
                showDialog(message.contains("timed out") ? internalServerError : message);
        }

        if (listener != null)
            listener.onError(o, httpRequest);

    }

    @Override
    public void unAuthorized(HttpRequest httpRequest, String messageError) {
    }

    public void showDialog(final String s) {
        try {
            dialogManager.showError(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sendJsonCrashlytic(String message) {
    }


    public String getErrorMessage(Object o) {
        String err = (String) o;
        return err;
    }


    private Map<String, String> header() {
        Map<String, String> header = new HashMap<String, String>();
        header.put("Accept", "application/json");
        header.put("Content-Type", "application/json");
        return header;
    }


    @Override
    public void log(ModelLog modelLog) {
        if (modelLog.getHttpCode() != 401 && !String.valueOf(modelLog.getHttpCode()).startsWith("2")) {
            String log = "url : " + modelLog.getUrl()
                    + "\nbody : " + modelLog.getBody()
                    + "\nresponse : " + modelLog.getError()
                    + "\nhttpcode : " + modelLog.getHttpCode()
                    + "\nheader : " + modelLog.getHeader();
            sendJsonCrashlytic(log);
        }

    }
}
