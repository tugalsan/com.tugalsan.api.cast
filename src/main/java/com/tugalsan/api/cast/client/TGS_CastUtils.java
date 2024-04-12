package com.tugalsan.api.cast.client;

import java.util.*;
import java.util.stream.*;
import com.tugalsan.api.string.client.*;
import com.tugalsan.api.union.client.TGS_UnionExcuse;

public class TGS_CastUtils {

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
        if (text == null) {
            return false;
        }
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
        try {
            Integer.valueOf(text.toString());
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isDouble(CharSequence text) {
        try {
            Double.valueOf(text.toString());
            return true;
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
    }

    public static String toString(List<String> data, CharSequence delimiter) {
        var sb = new StringJoiner(delimiter);
        data.stream().forEachOrdered(s -> sb.add(s));
        return sb.toString();
    }

    public static List<String> toArray_tab(List<List<String>> data) {
        List<String> s = new ArrayList();
        if (data == null) {
            return s;
        }
        data.stream().forEachOrdered(d -> s.add(TGS_StringUtils.toString_tab(d)));
        return s;
    }

    public static List<String> toArray_tab(Object[][] data) {
        List<String> s = new ArrayList();
        if (data == null) {
            return s;
        }
        Arrays.stream(data).forEachOrdered(d -> s.add(TGS_StringUtils.toString_tab(d)));
        return s;
    }

    public static TGS_UnionExcuse<Integer> toInteger(CharSequence s) {
        try {
            return TGS_UnionExcuse.of(Integer.valueOf(s.toString().trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    public static TGS_UnionExcuse<Long> toLong(CharSequence s) {
        try {
            return TGS_UnionExcuse.of(Long.valueOf(s.toString().trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    public static TGS_UnionExcuse<Long> toLong(Object o) {
        try {
            return TGS_UnionExcuse.of(Long.valueOf(o.toString().trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    @Deprecated
    public static TGS_UnionExcuse<Float> toFloat(CharSequence s) {//ERROR PRONE
        try {
            return TGS_UnionExcuse.of(Float.valueOf(s.toString().trim().replace(",", ".")));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    public static TGS_UnionExcuse<Double> toDouble(CharSequence s) {
        try {
            return TGS_UnionExcuse.of(Double.valueOf(s.toString().trim().replace(",", ".")));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    public static TGS_UnionExcuse<Boolean> toBoolean(CharSequence s) {
        try {
            return TGS_UnionExcuse.of(Boolean.valueOf(s.toString().trim()));
        } catch (NumberFormatException | NullPointerException e) {
            return TGS_UnionExcuse.ofExcuse(e);
        }
    }

    public static Integer toInteger(byte b) {
        return Integer.valueOf(Byte.toString(b));
    }

    public static TGS_UnionExcuse<Integer[]> toInteger(CharSequence[] from) {
        var arr = new Integer[from.length];
        TGS_UnionExcuse<Integer> cast;
        for (var i = 0; i < from.length; i++) {
            cast = TGS_CastUtils.toInteger(from[i].toString());
            if (cast.isExcuse()) {
                return cast.toExcuse();
            }
            arr[i] = cast.value();
        }
        return TGS_UnionExcuse.of(arr);
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
        Arrays.stream(in).forEachOrdered(s -> sb.add(s));
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
