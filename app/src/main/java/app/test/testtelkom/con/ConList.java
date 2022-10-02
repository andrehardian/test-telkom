package app.test.testtelkom.con;

import android.content.Context;

import connection.rxconnection.connection.HttpMethod;
import connection.rxconnection.connection.HttpRequest;

public class ConList extends HttpRequest<Object, Long[]> {
    public ConList(Context context) {
        super(context, Long[].class, URL.LIST, HttpMethod.GET);
    }
}
