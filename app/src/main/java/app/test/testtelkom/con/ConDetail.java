package app.test.testtelkom.con;

import android.content.Context;

import app.test.testtelkom.model.ResponseDetail;
import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConDetail extends HttpRequest<Object, ResponseDetail> {
    public ConDetail(Context context,Long id) {
        super(context, URL.detail(id), HttpMethod.GET);
    }
}
