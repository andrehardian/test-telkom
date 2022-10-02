package app.test.testtelkom.ui.adapter.list;


import androidx.databinding.ObservableField;

import app.test.testtelkom.base.BaseItemVM;
import app.test.testtelkom.base.adapter.RecyclerListener;
import app.test.testtelkom.databinding.DataListBinding;
import lombok.Getter;

public class ListItemVM extends BaseItemVM<Long, DataListBinding> {
    @Getter
    private ObservableField<Long> idData = new ObservableField<>();

    public ListItemVM(Long item, RecyclerListener listener, DataListBinding viewDataBinding) {
        super(item, listener, viewDataBinding);
        init();
    }

    @Override
    protected void init() {
        idData.set(item);
    }


}
