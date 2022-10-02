package app.test.testtelkom.base.adapter;

import androidx.recyclerview.widget.RecyclerView;

public interface RecyclerListener {
    void onItemClick(Object o);

    RecyclerView getRoot();
}
