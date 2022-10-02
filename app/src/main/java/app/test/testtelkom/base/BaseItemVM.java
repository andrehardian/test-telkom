package app.test.testtelkom.base;

import androidx.lifecycle.ViewModel;

import app.test.testtelkom.base.adapter.RecyclerListener;


public abstract class BaseItemVM<T, K> extends ViewModel {
    protected RecyclerListener listener;
    protected T item;
    protected K viewDataBinding;

    public BaseItemVM(T item, RecyclerListener listener,
                      K viewDataBinding) {
        this.listener = listener;
        this.item = item;
        this.viewDataBinding = viewDataBinding;
    }

    protected abstract void init();

    public void itemClick() {
        listener.onItemClick(item);
    }


}
