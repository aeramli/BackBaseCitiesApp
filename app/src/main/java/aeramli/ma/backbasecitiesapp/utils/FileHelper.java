package aeramli.ma.backbasecitiesapp.utils;

import android.content.Context;
import android.support.annotation.RawRes;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileHelper {
    private final Context context;

    public FileHelper(Context context) {
        this.context = context;
    }

    public String read(@RawRes int rawFile) {
        StringBuilder output = new StringBuilder();
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        try {
            inputStream = context.getResources().openRawResource(rawFile);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                output.append(line);
                output.append("\n");
            }
        } catch (Exception e) {
            Log.e(this.getClass().getCanonicalName(), "Error while reading file from raw", e);
        } finally {
            StreamUtils.safeCloseCloseable(inputStream);
            StreamUtils.safeCloseCloseable(inputStreamReader);
            StreamUtils.safeCloseCloseable(bufferedReader);
        }
        return output.toString();
    }
}
