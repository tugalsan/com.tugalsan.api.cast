package com.tugalsan.api.cast.client;

import com.tugalsan.api.function.client.maythrowexceptions.checked.TGS_FuncMTCUtils;
import java.util.*;
import java.util.stream.*;
import com.tugalsan.api.string.client.*;

public class TGS_CastUtils {

    private TGS_CastUtils() {

    }

    public static byte[] toByte(int in_int) {
        var a = new byte[4];
        IntStream.range(0, 4).parallel().forEach(i -> {
            var b_int = (in_int >> (i * 8)) & 255;
            var b = (byte) (b_int);
            a[i] = b;
        });
        return a;
    }

    public static int toInt(byte[] byte_array_4) {
        return IntStream.range(0, 4).map(i -> {
            var b = (int) byte_array_4[i];
            if (i < 3 && b < 0) {
                b = 256 + b;
            }
            return b << (i * 8);
        }).sum();
    }

    public static Byte[] toByte(byte[] bs) {
        var bytes = new Byte[bs.length];
        IntStream.range(0, bs.length).parallel().forEach(i -> bytes[i] = bs[i]);
        return bytes;
    }

    public static boolean isMail(CharSequence text) {
        var textStr = text.toString();
        var at = textStr.indexOf("@");
        var dt = textStr.lastIndexOf(".");
        return at > 0 && dt > at + 1 && textStr.length() > dt + 1;
    }

    public static String[] toStringArray(int[] integers) {
        var s = new String[integers.length];
        IntStream.range(0, s.length).parallel().forEach(i -> s[i] = String.valueOf(integers[i]));
        return s;
    }

    public static Object[][] replace(Object[][] target, Object from, Object to) {
        Arrays.stream(target).parallel().forEach(row -> {
            IntStream.range(0, row.length).parallel().forEach(j -> {
                if (Objects.equals(row[j], from)) {
                    row[j] = to;
                }
            });
        });
        return target;
    }

    public static boolean isInteger(CharSequence text) {
        return TGS_FuncMTCUtils.call(() -> {
            Integer.valueOf(text.toString());
            return true;
        }, e -> false);
    }

    public static boolean isLong(CharSequence text) {
        return TGS_FuncMTCUtils.call(() -> {
            Long.valueOf(text.toString());
            return true;
        }, e -> false);
    }

    public static boolean isBoolean(CharSequence text) {
        return TGS_FuncMTCUtils.call(() -> {
            Boolean.valueOf(text.toString());
            return true;
        }, e -> false);
    }

    public static boolean isDouble(CharSequence text) {
        return TGS_FuncMTCUtils.call(() -> {
            Double.valueOf(text.toString());
            return true;
        }, e -> false);
    }

    public static String toString(List<String> data, CharSequence delimiter) {
        var sb = new StringJoiner(delimiter);
        data.stream().forEachOrdered(sb::add);
        return sb.toString();
    }

    public static List<String> toArray_tab(List<List<String>> data) {
        List<String> s = new ArrayList();
        if (data == null) {
            return s;
        }
        data.stream().forEachOrdered(d -> s.add(TGS_StringUtils.cmn().toString_tab(d)));
        return s;
    }

    public static List<String> toArray_tab(Object[][] data) {
        List<String> s = new ArrayList();
        if (data == null) {
            return s;
        }
        Arrays.stream(data).forEachOrdered(d -> s.add(TGS_StringUtils.cmn().toString_tab(d)));
        return s;
    }

    public static Integer toInteger(CharSequence s, Integer defValue) {
        var val = toInteger(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static int toInt(CharSequence s, int defValue) {
        var val = toInteger(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static Optional<Integer> toInteger(CharSequence s) {
        return TGS_FuncMTCUtils.call(() -> {
            var val = Integer.valueOf(s.toString().trim());
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    public static Long toLong(CharSequence s, Long defValue) {
        var val = toLong(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static long toLong(CharSequence s, long defValue) {
        var val = toLong(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static Optional<Long> toLong(CharSequence s) {
        return TGS_FuncMTCUtils.call(() -> {
            var val = Long.valueOf(s.toString().trim());
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    public static Optional<Long> toLong(Object o) {
        return TGS_FuncMTCUtils.call(() -> {
            var val = Long.valueOf(o.toString().trim());
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    @Deprecated
    public static Optional<Float> toFloat(CharSequence s) {//ERROR PRONE
        return TGS_FuncMTCUtils.call(() -> {
            var val = Float.valueOf(s.toString().trim().replace(",", "."));
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    public static Double toDouble(CharSequence s, Double defValue) {
        var val = toDouble(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static double toDouble(CharSequence s, double defValue) {
        var val = toDouble(s);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static Optional<Double> toDouble(CharSequence s) {
        return TGS_FuncMTCUtils.call(() -> {
            var val = Double.valueOf(s.toString().trim().replace(",", "."));
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    public static Boolean toBoolean(CharSequence bool, Boolean defValue) {
        var val = toBoolean(bool);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static boolean toBoolean(CharSequence bool, boolean defValue) {
        var val = toBoolean(bool);
        return val.isEmpty() ? defValue : val.orElseThrow();
    }

    public static Optional<Boolean> toBoolean(CharSequence bool) {
        if (bool == null) {
            return Optional.empty();
        }
        var str = bool.toString();
        if (str.equalsIgnoreCase("true")) {//TO TURKISH CHECK NOT NEEDED
            return Optional.of(true);
        }
        if (str.equalsIgnoreCase("false")) {//TO TURKISH CHECK NOT NEEDED
            return Optional.of(false);
        }
        return Optional.empty();
    }

    public static Optional<Integer> toInteger(byte b) {
        return TGS_FuncMTCUtils.call(() -> {
            var val = Integer.valueOf(Byte.toString(b));
            return Optional.of(val);
        }, e -> Optional.empty());
    }

    public static Integer[] toInteger(CharSequence[] from) {
        var i = new Integer[from.length];
        IntStream.range(0, from.length).parallel().forEach(j -> i[j] = TGS_CastUtils.toInteger(from[j].toString()).orElse(null));
        return i;
    }

    public static Integer[] toInteger(int[] in) {
        var i = new Integer[in.length];
        IntStream.range(0, in.length).parallel().forEach(j -> i[j] = in[j]);
        return i;
    }

    public static int[] toInt(Integer[] in) {
        var i = new int[in.length];
        IntStream.range(0, in.length).parallel().forEach(j -> i[j] = in[j]);
        return i;
    }

    public static String[] toString(int[] in) {
        var s = new String[in.length];
        IntStream.range(0, in.length).parallel().forEach(j -> s[j] = String.valueOf(in[j]));
        return s;
    }

    public static String[] toString(boolean[] in) {
        var s = new String[in.length];
        IntStream.range(0, in.length).forEachOrdered(i -> s[i] = String.valueOf(in[i]));
        return s;
    }

    public static String toString(CharSequence[] in) {
        var sb = new StringJoiner(", ", "[", "]");
        Arrays.stream(in).forEachOrdered(sb::add);
        return sb.toString();
    }

    public static String toBinary(int input, Integer minCharSize) {
        var bs = Integer.toBinaryString(input);
        var sb = new StringBuilder();
        while (minCharSize != null && bs.length() + sb.length() < minCharSize) {
            sb.append("0");
        }
        sb.append(bs);
        return sb.toString();
    }
}
