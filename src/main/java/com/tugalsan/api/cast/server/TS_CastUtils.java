package com.tugalsan.api.cast.server;

import com.tugalsan.api.union.client.TGS_UnionExcuse;
import com.tugalsan.api.unsafe.client.TGS_UnSafe;
import java.nio.file.Path;

public class TS_CastUtils {

    public static String toString(Float value, Integer precision) {
        return toString(value.doubleValue(), precision);
    }

    public static String toString(Double value, Integer precision) {
        if (value == null) {
            return "null";
        }
        if (precision == null || precision < 0) {
            return String.valueOf(value);
        }
        return String.format("%." + precision + "f", value);//GWT DOES NOT LIKE U
    }

    public static TGS_UnionExcuse<Path> toPath(CharSequence path) {
        return TGS_UnSafe.call(() -> TGS_UnionExcuse.of(Path.of(path.toString())), e -> TGS_UnionExcuse.ofExcuse(e));
    }
}
