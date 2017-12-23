package aeramli.ma.backbasecitiesapp.utils;

import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

public final class StreamUtils {
    private StreamUtils() {
        //util class
    }

    /**
     * Safely close different types of streams and readers.
     *
     * @param closeable to close
     */
    static void safeCloseCloseable(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                Log.e(StreamUtils.class.getCanonicalName(), "IOException while closing stream", e);
            }
        }
    }
}
