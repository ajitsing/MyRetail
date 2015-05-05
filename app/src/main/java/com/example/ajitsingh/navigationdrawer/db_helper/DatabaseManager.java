package com.example.ajitsingh.navigationdrawer.db_helper;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

public class DatabaseManager {
    private static String backupDBPath = "retail_backup.db";

    public static boolean isBackupAvailable() {
        return new File("/storage/emulated/0/" + backupDBPath).exists();
    }

    public static void restoreDB() {
        File backupDB = new File("/storage/emulated/0/" + backupDBPath);
        String databasePath = "//data//com.myretail.myretail//databases//retail.db";
        File currentDB = new File(databasePath);

        try {
            if (backupDB.exists() && currentDB.exists()) {
                Log.i("Restore DB:", "restoring db from backup");
                FileChannel src = new FileInputStream(backupDB).getChannel();
                FileChannel dst = new FileOutputStream(currentDB).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            }
        } catch (Exception e) {
            Log.e("restore db failed:", e.getMessage());
        }
    }

    public static void backupDB() {
        File data = Environment.getDataDirectory();
        File externalStorage = Environment.getExternalStorageDirectory();

        try {
            if (externalStorage.canWrite()) {
                String currentDBPath = "//data//com.myretail.myretail//databases//retail.db";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(externalStorage, backupDBPath);

                Log.i("backup db path:", backupDB.getAbsolutePath());

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
            }
        } catch (Exception e) {
            Log.e("BackupDB Failed:", e.getMessage());
        }
    }
}
