package com.example.administrator.filedemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView tv1;
    private File fPhoneDirectory;
    private File fexternalstoragepublicDiectory;
    private File fexternalstorageDiectory;
    private File fdataStorage;
    private File fdownloadCacheDirectory;
    private File frootDirectory;
    private String name,path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv1 = (TextView) findViewById(R.id.result);
        fPhoneDirectory = this.getFilesDir();
        fexternalstoragepublicDiectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
        fexternalstorageDiectory = Environment.getExternalStorageDirectory();
        fdataStorage = Environment.getDataDirectory();
        fdownloadCacheDirectory = Environment.getDownloadCacheDirectory();
        frootDirectory = Environment.getRootDirectory();

        if(Environment.getExternalStorageState().equals(Environment.MEDIA_REMOVED)){
            Button btn = (Button) findViewById(R.id.externalstorageDiectory);
            btn.setEnabled(false);
        }
    }

    public void PhoneDirectory(View v) {
        path = fPhoneDirectory.getPath();
        try {
            FileOutputStream fos = openFileOutput("test.txt", MODE_PRIVATE);
            fos.write("hello".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listfile(path);
    }
    public void externalstoragepublicDiectory(View v){
        path = fexternalstoragepublicDiectory.getAbsolutePath();
        listfile(path);
    }

    public void externalstorageDiectory(View v){
        path = fexternalstorageDiectory.getAbsolutePath();
        listfile(path);
    }

    public void dataStorage(View v){
        path = fdataStorage.getAbsolutePath();
        listfile(path);
    }

    public void downloadCacheDirectory(View v){
        path = fdownloadCacheDirectory.getAbsolutePath();
        listfile(path);
    }

    public void rootDirectory(View v){
        path = frootDirectory.getAbsolutePath();
        listfile(path);
    }

    private boolean listfile(String path) {
        name = "路径"+path+"\n文件清单\n";
        File file = new File(path);
        if(file.listFiles()!=null&&file.listFiles().length>0){
            for(File f : file.listFiles()) {
                path = f.getAbsolutePath();
                name = name + f.getName() + "\n";
            }
        }
        tv1.setText(name);
        return true;
    }
}

