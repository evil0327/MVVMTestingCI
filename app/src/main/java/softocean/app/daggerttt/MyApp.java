package softocean.app.daggerttt;

import android.app.Application;
import android.os.StrictMode;

import softocean.app.daggerttt.di.AppModule;
import softocean.app.daggerttt.di.DaggerComponentHolder;
import softocean.app.daggerttt.di.DaggerMyDaggerComponent;
import softocean.app.daggerttt.di.MyDaggerComponent;
import softocean.app.daggerttt.di.RepoModule;

public class MyApp extends Application{

    @Override
    public void onCreate() {
        super.onCreate();


        MyDaggerComponent daggerComponent = DaggerMyDaggerComponent.builder()
                .appModule(new AppModule(this)) // This also corresponds to the name of your module: %component_name%Module
                .repoModule(new RepoModule())
                .build();
        DaggerComponentHolder.setAppComponent(daggerComponent);

//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
//                .detectCustomSlowCalls() //API等级11，使用StrictMode.noteSlowCode
//                .detectDiskReads()
//                .detectDiskWrites()
//                .detectNetwork()   // or .detectAll() for all detectable problems
//                .penaltyDialog() //弹出违规提示对话框
//                .penaltyLog() //在Logcat 中打印违规异常信息
//                .penaltyFlashScreen() //API等级11
//                .build());
//
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
//                .detectLeakedSqlLiteObjects()
//                .detectLeakedClosableObjects() //API等级11
//                .penaltyLog()
//                .penaltyDeath()
//                .build());
    }

}
