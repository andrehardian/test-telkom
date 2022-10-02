package app.test.testtelkom.di;


import javax.inject.Singleton;

import app.test.testtelkom.di.main.MainComponent;
import app.test.testtelkom.di.main.MainModule;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, MainModule.class})
public interface AppComponent {
    MainComponent.Factory injectMain();
}
