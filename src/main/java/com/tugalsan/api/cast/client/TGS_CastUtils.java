package com.tugalsan.api.cast.client;

import java.util.*;
import java.util.stream.*;
import com.tugalsan.api.pack.client.*;
import com.tugalsan.api.string.client.*;

public class TGS_CastUtils {

    public static byte[] toByte(int in_int) {
        var a = new byte[4];
        IntStream.range(0, 4).parallel().forEach(i -> {
            int b_int = (in_int >> (i * 8)) & 255;
            byte b = (byte) (b_int);
            a[i] = b;
        });
        return a;
    }

    public static int toInt(byte[] byte_array_4) {
        TGS_Pack1<Integer> ret = new TGS_Pack1(0);
        IntStream.range(0, 4).parallel().forEach(i -> {
            var b = (int) byte_array_4[i];
            if (i < 3 && b < 0) {
                b = 256 + b;
            }
            ret.value0 += b << (i * 8);
        });
        return ret.value0;
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
        try {
            Integer.parseInt(text.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static boolean isDouble(CharSequence text) {
        try {
            Double.parseDouble(text.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
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

    public static Integer toInteger(CharSequence s, Integer defValue) {
        var val = toInteger(s);
        return val == null ? defValue : val;
    }

    public static Integer toInteger(CharSequence s) {
        try {
            return Integer.parseInt(s.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Long toLong(CharSequence s, Long defValue) {
        var val = toLong(s);
        return val == null ? defValue : val;
    }

    public static Long toLong(CharSequence s) {
        try {
            return Long.parseLong(s.toString().trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Long toLong(Object o) {
        return o == null ? null : TGS_CastUtils.toLong(o.toString());
    }

    @Deprecated
    public static Float toFloat(CharSequence s) {//ERROR PRONE
        try {
            var sFixedTR = s.toString().trim().replace(",", ".");
            return Float.parseFloat(sFixedTR);
        } catch (Exception e) {
            return null;
        }
    }

    public static Double toDouble(CharSequence s, Double defValue) {
        var val = toDouble(s);
        return val == null ? defValue : val;
    }

    public static Double toDouble(CharSequence s) {
        try {
            return Double.parseDouble(s.toString().replace(",", ".").trim());
        } catch (Exception e) {
            return null;
        }
    }

    public static Boolean toBoolean(CharSequence bool, Boolean defValue) {
        var val = toBoolean(bool);
        return val == null ? defValue : val;
    }

    public static Boolean toBoolean(CharSequence bool) {
//      return Boolean.parseBoolean(bool.toString().trim());//WRING FALSE VALUES!!!
        if (bool == null) {
            return null;
        }
        var str = bool.toString();
        if (str.equalsIgnoreCase("true")) {
            return true;
        }
        if (str.equalsIgnoreCase("false")) {
            return false;
        }
        return null;
    }

    public static Integer toInteger(byte b) {
        try {
            return Integer.parseInt(Byte.toString(b));
        } catch (Exception e) {
            return null;
        }
    }

    public static Integer[] toInteger(CharSequence[] from) {
        var i = new Integer[from.length];
        IntStream.range(0, from.length).parallel().forEach(j -> i[j] = TGS_CastUtils.toInteger(from[j].toString()));
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
