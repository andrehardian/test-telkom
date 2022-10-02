package app.test.testtelkom.di;

import android.content.Context;

import javax.inject.Singleton;

import app.test.testtelkom.manager.DialogManager;
import app.test.testtelkom.manager.ServiceManager;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
    private final Context context;

    public AppModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    public Context provideContext() {
        return context;
    }


    @Singleton
    @Provides
    public DialogManager provideDialogManager() {
        return new DialogManager(context);
    }


    @Singleton
    @Provides
    public ServiceManager provideServiceManager() {
        return new ServiceManager(context, provideDialogManager());
    }


}
