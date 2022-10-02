package app.test.testtelkom.base.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.recyclerview.widget.RecyclerView;

import lombok.Getter;

/**
 * Created by AndreHF on 6/19/2017.
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder implements LifecycleOwner {
    protected final RecyclerListener listener;
    private boolean  wasPaused;
    @Getter
    private final ViewDataBinding viewDataBinding;
    private final LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);

    public BaseViewHolder(RecyclerListener listener, ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.listener = listener;
        this.viewDataBinding = viewDataBinding;
        lifecycleRegistry.setCurrentState(Lifecycle.State.INITIALIZED);
    }


    public void setData(T data) {
        itemView.setTag(data);
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return lifecycleRegistry;
    }

    void bind() {
        viewDataBinding.executePendingBindings();
        viewDataBinding.setLifecycleOwner(this);
    }

    void markCreated() {
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    void markAttach() {
        if (wasPaused) {
            lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED);
            wasPaused = false;
        } else {
            lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED);
        }
    }

    void markDetach() {
        wasPaused = true;
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
    }

    void markDestroyed() {
        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
    }

}
