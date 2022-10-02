package app.test.testtelkom.di.main;


import app.test.testtelkom.ui.main.MainActivity;
import dagger.Subcomponent;

@MainScope
@Subcomponent
public interface MainComponent {


    void inject(MainActivity mainActivity);


    @Subcomponent.Factory
    interface Factory {
        MainComponent create();
    }

}
