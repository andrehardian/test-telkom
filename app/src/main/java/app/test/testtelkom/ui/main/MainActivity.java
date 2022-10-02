package app.test.testtelkom.ui.main;

import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import app.test.testtelkom.R;
import app.test.testtelkom.base.adapter.RecyclerListener;
import app.test.testtelkom.databinding.ActivityMainBinding;
import app.test.testtelkom.di.AppComponent;
import app.test.testtelkom.di.AppModule;
import app.test.testtelkom.di.DaggerAppComponent;
import app.test.testtelkom.di.main.MainComponent;
import app.test.testtelkom.ui.adapter.list.ListAdapter;

public class MainActivity extends AppCompatActivity implements RecyclerListener {
    @Inject
    protected MainVM viewModel;
    private MainComponent mainComponent;
    private ActivityMainBinding dataBinding;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        AppComponent appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this)).build();
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        mainComponent = appComponent.injectMain().create();
        mainComponent.inject(this);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        listenData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getList();
    }

    private void listenData() {
        viewModel.getData().observe(this, longs -> {
            if (longs != null) {
               listAdapter = new ListAdapter(longs,this);
               dataBinding.setAdapter(listAdapter);
            }
        });
    }


    @Override
    public void onItemClick(Object o) {

    }

    @Override
    public RecyclerView getRoot() {
        return null;
    }
}