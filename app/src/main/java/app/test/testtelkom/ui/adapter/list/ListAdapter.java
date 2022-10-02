package app.test.testtelkom.ui.adapter.list;

import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.test.testtelkom.base.adapter.BaseAdapter;
import app.test.testtelkom.base.adapter.BaseViewHolder;
import app.test.testtelkom.base.adapter.RecyclerListener;
import app.test.testtelkom.databinding.DataListBinding;

public class ListAdapter extends BaseAdapter<Long> {
    public ListAdapter(ArrayList<Long> listItem, RecyclerListener listener) {
        super(listItem, listener);
    }

    @Override
    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        viewDataBinding = DataListBinding.inflate(getLayoutInflater(parent), parent, false);
        return new BaseViewHolder(listener, viewDataBinding);
    }

    @Override
    protected void bindingViewHolder(BaseViewHolder viewHolder, int position) {
        if (viewHolder.getViewDataBinding() instanceof DataListBinding) {
            ((DataListBinding) viewHolder.getViewDataBinding()).setViewModel(
                    new ListItemVM(getItem(position), listener, (DataListBinding) viewDataBinding));
        }
    }
}
