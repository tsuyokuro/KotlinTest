package com.example.myapp2;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JacocoUtils {
    static String TAG = "JacocoUtils";

    private static String DEFAULT_COVERAGE_FILE_PATH = "coverage.exec";

    /**
     * execファイルを生成する
     *
     * @param isnew execファイルを作り直すか。
     */
    public static void generateEcFile(Context context, boolean isnew) {
        Log.d(TAG, "カバレッジファイル生成: " + DEFAULT_COVERAGE_FILE_PATH);
        OutputStream out = null;
        File mCoverageFilePath = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),DEFAULT_COVERAGE_FILE_PATH);

        try {
            if (isnew && mCoverageFilePath.exists()) {
                Log.d(TAG, "旧execファイルを削除");
                mCoverageFilePath.delete();
            }
            if (!mCoverageFilePath.exists()) {
                mCoverageFilePath.createNewFile();
            }
            out = new FileOutputStream(mCoverageFilePath.getPath(), true);

            Object agent = Class.forName("org.jacoco.agent.rt.RT")
                    .getMethod("getAgent")
                    .invoke(null);

            out.write((byte[]) agent.getClass().getMethod("getExecutionData", boolean.class)
                    .invoke(agent, false));

            Log.d(TAG, "Coverage file createted: " + mCoverageFilePath.getPath());

        } catch (Exception e) {
            Log.e(TAG, "generateEcFile: " + e.getMessage());
        } finally {
            if (out == null)
                return;
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
