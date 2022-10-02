package app.test.testtelkom.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.test.testtelkom.R;
import lombok.Getter;
import lombok.Setter;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    protected final int EMPTY = 0;
    protected final int NON_EMPTY = 1;
    protected final int LOAD_MORE = 2;
    protected final RecyclerListener listener;
    @Setter
    @Getter
    protected ArrayList<T> listItem;
    protected ViewDataBinding viewDataBinding;


    public BaseAdapter(ArrayList<T> listItem, RecyclerListener listener) {
        this.listItem = listItem;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case EMPTY:
                return createEmptyHolder(parent);
            case NON_EMPTY:
                return createHolder(parent, viewType);
        }
        return null;
    }

    protected abstract void bindingViewHolder(BaseViewHolder viewHolder, int position);

    protected View createView(ViewGroup parent, int layout) {
        return getLayoutInflater(parent).inflate(layout, parent, false);
    }

    protected LayoutInflater getLayoutInflater(ViewGroup parent) {
        return (LayoutInflater) parent.getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    protected RecyclerView.ViewHolder createHolder(ViewGroup parent, int viewType) {
        return null;
    }

    protected RecyclerView.ViewHolder createEmptyHolder(ViewGroup parent) {
        return new VHEmpty(createView(parent, R.layout.adapter_empty));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BaseViewHolder) {
            bindingViewHolder((BaseViewHolder) holder, position);
            ((BaseViewHolder<?>) holder).bind();
        }
    }

    public T getItem(int position) {
        return listItem.get(position);
    }


    @Override
    public int getItemCount() {
        if (listItem != null && listItem.size() > 0) {
            return listItem.size();
        } else {
            return 1;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (listItem != null && listItem.size() != 0 &&
                listItem.get(position) != null) {
            return NON_EMPTY;
        } else {
            return EMPTY;
        }
    }

}
