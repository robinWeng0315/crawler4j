package edu.uci.ics.crawler4j.examples;
import java.util.Arrays;

public class Base64 {
    private static final char[] a;
    private static final int[] b;

    static {
        a = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789._".toCharArray();
        Arrays.fill(b = new int[256], -1);
        for (int length = Base64.a.length, i = 0; i < length; ++i) {
            Base64.b[Base64.a[i]] = i;
        }
        Base64.b[61] = 0;
    }

    public static final byte[] decode(final String s) {
        int i;
        if (s != null) {
            i = s.length();
        } else {
            i = 0;
        }
        if (i == 0) {
            return new byte[0];
        }
        int j = 0;
        int n = 0;
        while (j < i) {
            int n2 = n;
            if (Base64.b[s.charAt(j)] < 0) {
                n2 = n + 1;
            }
            ++j;
            n = n2;
        }
        final int n3 = i - n;
        if (n3 % 4 != 0) {
            return null;
        }
        int n4 = 0;
        while (i > 1) {
            final int[] b = Base64.b;
            final int n5 = i - 1;
            if (b[s.charAt(n5)] > 0) {
                break;
            }
            i = n5;
            if (s.charAt(n5) != '=') {
                continue;
            }
            ++n4;
            i = n5;
        }
        final int n6 = (n3 * 6 >> 3) - n4;
        final byte[] array = new byte[n6];
        int k = 0;
        int n7 = 0;
        while (k < n6) {
            final int n8 = 0;
            int n9 = 0;
            int n10 = n7;
            for (int l = n8; l < 4; ++l, ++n10) {
                final int n11 = Base64.b[s.charAt(n10)];
                if (n11 >= 0) {
                    n9 |= n11 << 18 - l * 6;
                } else {
                    --l;
                }
            }
            int n12 = k + 1;
            array[k] = (byte) (n9 >> 16);
            if (n12 < n6) {
                final int n13 = n12 + 1;
                array[n12] = (byte) (n9 >> 8);
                if ((n12 = n13) < n6) {
                    array[n13] = (byte) n9;
                    n12 = n13 + 1;
                }
            }
            k = n12;
            n7 = n10;
        }
        return array;
    }

    public static final byte[] decode(final byte[] array) {
        final int length = array.length;
        int i = 0;
        int n = 0;
        while (i < length) {
            int n2 = n;
            if (Base64.b[array[i] & 0xFF] < 0) {
                n2 = n + 1;
            }
            ++i;
            n = n2;
        }
        final int n3 = length - n;
        if (n3 % 4 != 0) {
            return null;
        }
        int n4 = 0;
        int j = length;
        while (j > 1) {
            final int[] b = Base64.b;
            final int n5 = j - 1;
            if (b[array[n5] & 0xFF] > 0) {
                break;
            }
            j = n5;
            if (array[n5] != 61) {
                continue;
            }
            ++n4;
            j = n5;
        }
        final int n6 = (n3 * 6 >> 3) - n4;
        final byte[] array2 = new byte[n6];
        int k = 0;
        int n7 = 0;
        while (k < n6) {
            final int n8 = 0;
            int n9 = 0;
            int n10 = n7;
            for (int l = n8; l < 4; ++l, ++n10) {
                final int n11 = Base64.b[array[n10] & 0xFF];
                if (n11 >= 0) {
                    n9 |= n11 << 18 - l * 6;
                } else {
                    --l;
                }
            }
            int n12 = k + 1;
            array2[k] = (byte) (n9 >> 16);
            if (n12 < n6) {
                final int n13 = n12 + 1;
                array2[n12] = (byte) (n9 >> 8);
                if ((n12 = n13) < n6) {
                    array2[n13] = (byte) n9;
                    n12 = n13 + 1;
                }
            }
            k = n12;
            n7 = n10;
        }
        return array2;
    }

    public static final byte[] decode(final char[] array) {
        int i;
        if (array != null) {
            i = array.length;
        } else {
            i = 0;
        }
        if (i == 0) {
            return new byte[0];
        }
        int j = 0;
        int n = 0;
        while (j < i) {
            int n2 = n;
            if (Base64.b[array[j]] < 0) {
                n2 = n + 1;
            }
            ++j;
            n = n2;
        }
        final int n3 = i - n;
        if (n3 % 4 != 0) {
            return null;
        }
        int n4 = 0;
        while (i > 1) {
            final int[] b = Base64.b;
            final int n5 = i - 1;
            if (b[array[n5]] > 0) {
                break;
            }
            i = n5;
            if (array[n5] != '=') {
                continue;
            }
            ++n4;
            i = n5;
        }
        final int n6 = (n3 * 6 >> 3) - n4;
        final byte[] array2 = new byte[n6];
        int k = 0;
        int n7 = 0;
        while (k < n6) {
            final int n8 = 0;
            int n9 = 0;
            int n10 = n7;
            for (int l = n8; l < 4; ++l, ++n10) {
                final int n11 = Base64.b[array[n10]];
                if (n11 >= 0) {
                    n9 |= n11 << 18 - l * 6;
                } else {
                    --l;
                }
            }
            int n12 = k + 1;
            array2[k] = (byte) (n9 >> 16);
            if (n12 < n6) {
                final int n13 = n12 + 1;
                array2[n12] = (byte) (n9 >> 8);
                if ((n12 = n13) < n6) {
                    array2[n13] = (byte) n9;
                    n12 = n13 + 1;
                }
            }
            k = n12;
            n7 = n10;
        }
        return array2;
    }

    public static final byte[] decodeFast(final String s) {
        final int length = s.length();
        final int n = 0;
        if (length == 0) {
            return new byte[0];
        }
        final int n2 = length - 1;
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n2;
            if (n3 >= n2) {
                break;
            }
            n4 = n2;
            if (Base64.b[s.charAt(n3) & '\u00ff'] >= 0) {
                break;
            }
            ++n3;
        }
        while (n4 > 0 && Base64.b[s.charAt(n4) & '\u00ff'] < 0) {
            --n4;
        }
        int n5;
        if (s.charAt(n4) == '=') {
            if (s.charAt(n4 - 1) == '=') {
                n5 = 2;
            } else {
                n5 = 1;
            }
        } else {
            n5 = 0;
        }
        final int n6 = n4 - n3 + 1;
        int n8;
        if (length > 76) {
            int n7;
            if (s.charAt(76) == '\r') {
                n7 = n6 / 78;
            } else {
                n7 = 0;
            }
            n8 = n7 << 1;
        } else {
            n8 = 0;
        }
        final int n9 = ((n6 - n8) * 6 >> 3) - n5;
        final byte[] array = new byte[n9];
        final int n10 = n9 / 3;
        final int n11 = 0;
        int n12 = 0;
        int i = n3;
        int j;
        int n21;
        for (j = n11; j < n10 * 3; j = n21 + 1) {
            final int[] b = Base64.b;
            final int n13 = i + 1;
            final int n14 = b[s.charAt(i)];
            final int[] b2 = Base64.b;
            final int n15 = n13 + 1;
            final int n16 = b2[s.charAt(n13)];
            final int[] b3 = Base64.b;
            final int n17 = n15 + 1;
            final int n18 = b3[s.charAt(n15)];
            final int[] b4 = Base64.b;
            i = n17 + 1;
            final int n19 = n14 << 18 | n16 << 12 | n18 << 6 | b4[s.charAt(n17)];
            final int n20 = j + 1;
            array[j] = (byte) (n19 >> 16);
            n21 = n20 + 1;
            array[n20] = (byte) (n19 >> 8);
            array[n21] = (byte) n19;
            int n22 = n12;
            if (n8 > 0 && (n22 = n12 + 1) == 19) {
                i += 2;
                n12 = 0;
            } else {
                n12 = n22;
            }
        }
        if (j < n9) {
            int n23 = 0;
            int n24 = n;
            while (i <= n4 - n5) {
                n24 |= Base64.b[s.charAt(i)] << 18 - n23 * 6;
                ++n23;
                ++i;
            }
            int n25 = 16;
            while (j < n9) {
                array[j] = (byte) (n24 >> n25);
                n25 -= 8;
                ++j;
            }
        }
        return array;
    }

    public static final byte[] decodeFast(final byte[] array) {
        final int length = array.length;
        final int n = 0;
        if (length == 0) {
            return new byte[0];
        }
        final int n2 = length - 1;
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n2;
            if (n3 >= n2) {
                break;
            }
            n4 = n2;
            if (Base64.b[array[n3] & 0xFF] >= 0) {
                break;
            }
            ++n3;
        }
        while (n4 > 0 && Base64.b[array[n4] & 0xFF] < 0) {
            --n4;
        }
        int n5;
        if (array[n4] == 61) {
            if (array[n4 - 1] == 61) {
                n5 = 2;
            } else {
                n5 = 1;
            }
        } else {
            n5 = 0;
        }
        final int n6 = n4 - n3 + 1;
        int n8;
        if (length > 76) {
            int n7;
            if (array[76] == 13) {
                n7 = n6 / 78;
            } else {
                n7 = 0;
            }
            n8 = n7 << 1;
        } else {
            n8 = 0;
        }
        final int n9 = ((n6 - n8) * 6 >> 3) - n5;
        final byte[] array2 = new byte[n9];
        final int n10 = n9 / 3;
        final int n11 = 0;
        int n12 = 0;
        int i = n3;
        int j;
        int n21;
        for (j = n11; j < n10 * 3; j = n21 + 1) {
            final int[] b = Base64.b;
            final int n13 = i + 1;
            final int n14 = b[array[i]];
            final int n15 = n13 + 1;
            final int n16 = b[array[n13]];
            final int n17 = n15 + 1;
            final int n18 = b[array[n15]];
            i = n17 + 1;
            final int n19 = n14 << 18 | n16 << 12 | n18 << 6 | b[array[n17]];
            final int n20 = j + 1;
            array2[j] = (byte) (n19 >> 16);
            n21 = n20 + 1;
            array2[n20] = (byte) (n19 >> 8);
            array2[n21] = (byte) n19;
            int n22 = n12;
            if (n8 > 0 && (n22 = n12 + 1) == 19) {
                i += 2;
                n12 = 0;
            } else {
                n12 = n22;
            }
        }
        if (j < n9) {
            int n23 = 0;
            int n24 = n;
            while (i <= n4 - n5) {
                n24 |= Base64.b[array[i]] << 18 - n23 * 6;
                ++n23;
                ++i;
            }
            int n25 = 16;
            while (j < n9) {
                array2[j] = (byte) (n24 >> n25);
                n25 -= 8;
                ++j;
            }
        }
        return array2;
    }

    public static final byte[] decodeFast(final char[] array) {
        final int length = array.length;
        final int n = 0;
        if (length == 0) {
            return new byte[0];
        }
        final int n2 = length - 1;
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n2;
            if (n3 >= n2) {
                break;
            }
            n4 = n2;
            if (Base64.b[array[n3]] >= 0) {
                break;
            }
            ++n3;
        }
        while (n4 > 0 && Base64.b[array[n4]] < 0) {
            --n4;
        }
        int n5;
        if (array[n4] == '=') {
            if (array[n4 - 1] == '=') {
                n5 = 2;
            } else {
                n5 = 1;
            }
        } else {
            n5 = 0;
        }
        final int n6 = n4 - n3 + 1;
        int n8;
        if (length > 76) {
            int n7;
            if (array[76] == '\r') {
                n7 = n6 / 78;
            } else {
                n7 = 0;
            }
            n8 = n7 << 1;
        } else {
            n8 = 0;
        }
        final int n9 = ((n6 - n8) * 6 >> 3) - n5;
        final byte[] array2 = new byte[n9];
        final int n10 = n9 / 3;
        final int n11 = 0;
        int n12 = 0;
        int i = n3;
        int j;
        int n21;
        for (j = n11; j < n10 * 3; j = n21 + 1) {
            final int[] b = Base64.b;
            final int n13 = i + 1;
            final int n14 = b[array[i]];
            final int n15 = n13 + 1;
            final int n16 = b[array[n13]];
            final int n17 = n15 + 1;
            final int n18 = b[array[n15]];
            i = n17 + 1;
            final int n19 = n14 << 18 | n16 << 12 | n18 << 6 | b[array[n17]];
            final int n20 = j + 1;
            array2[j] = (byte) (n19 >> 16);
            n21 = n20 + 1;
            array2[n20] = (byte) (n19 >> 8);
            array2[n21] = (byte) n19;
            int n22 = n12;
            if (n8 > 0 && (n22 = n12 + 1) == 19) {
                i += 2;
                n12 = 0;
            } else {
                n12 = n22;
            }
        }
        if (j < n9) {
            int n23 = 0;
            int n24 = n;
            while (i <= n4 - n5) {
                n24 |= Base64.b[array[i]] << 18 - n23 * 6;
                ++n23;
                ++i;
            }
            int n25 = 16;
            while (j < n9) {
                array2[j] = (byte) (n24 >> n25);
                n25 -= 8;
                ++j;
            }
        }
        return array2;
    }

    public static final byte[] encodeToByte(final byte[] array, final boolean b) {
        final boolean b2 = false;
        int length;
        if (array != null) {
            length = array.length;
        } else {
            length = 0;
        }
        if (length == 0) {
            return new byte[0];
        }
        final int n = length / 3 * 3;
        final int n2 = length - 1;
        final int n3 = n2 / 3 + 1 << 2;
        int n4;
        if (b) {
            n4 = (n3 - 1) / 76 << 1;
        } else {
            n4 = 0;
        }
        final int n5 = n3 + n4;
        final byte[] array2 = new byte[n5];
        int i = 0;
        int n6 = 0;
        int n7 = 0;
        while (i < n) {
            final int n8 = i + 1;
            final byte b3 = array[i];
            final int n9 = n8 + 1;
            final int n10 = (b3 & 0xFF) << 16 | (array[n8] & 0xFF) << 8 | (array[n9] & 0xFF);
            final int n11 = n6 + 1;
            final char[] a = Base64.a;
            array2[n6] = (byte) a[n10 >>> 18 & 0x3F];
            final int n12 = n11 + 1;
            array2[n11] = (byte) a[n10 >>> 12 & 0x3F];
            final int n13 = n12 + 1;
            array2[n12] = (byte) a[n10 >>> 6 & 0x3F];
            final int n14 = n13 + 1;
            array2[n13] = (byte) a[n10 & 0x3F];
            n6 = n14;
            int n15 = n7;
            if (b) {
                final int n16 = n7 + 1;
                n6 = n14;
                if ((n15 = n16) == 19) {
                    n6 = n14;
                    n15 = n16;
                    if (n14 < n5 - 2) {
                        final int n17 = n14 + 1;
                        array2[n14] = 13;
                        n6 = n17 + 1;
                        array2[n17] = 10;
                        n15 = 0;
                    }
                }
            }
            i = n9 + 1;
            n7 = n15;
        }
        final int n18 = length - n;
        if (n18 > 0) {
            final byte b4 = array[n];
            int n19 = b2 ? 1 : 0;
            if (n18 == 2) {
                n19 = (array[n2] & 0xFF) << 2;
            }
            final int n20 = (b4 & 0xFF) << 10 | n19;
            final char[] a2 = Base64.a;
            array2[n5 - 4] = (byte) a2[n20 >> 12];
            array2[n5 - 3] = (byte) a2[n20 >>> 6 & 0x3F];
            byte b5;
            if (n18 == 2) {
                b5 = (byte) a2[n20 & 0x3F];
            } else {
                b5 = 61;
            }
            array2[n5 - 2] = b5;
            array2[n5 - 1] = 61;
        }
        return array2;
    }

    public static final char[] encodeToChar(final byte[] array, final boolean b) {
        final boolean b2 = false;
        int length;
        if (array != null) {
            length = array.length;
        } else {
            length = 0;
        }
        if (length == 0) {
            return new char[0];
        }
        final int n = length / 3 * 3;
        final int n2 = length - 1;
        final int n3 = n2 / 3 + 1 << 2;
        int n4;
        if (b) {
            n4 = (n3 - 1) / 76 << 1;
        } else {
            n4 = 0;
        }
        final int n5 = n3 + n4;
        final char[] array2 = new char[n5];
        int i = 0;
        int n6 = 0;
        int n7 = 0;
        while (i < n) {
            final int n8 = i + 1;
            final byte b3 = array[i];
            final int n9 = n8 + 1;
            final int n10 = (b3 & 0xFF) << 16 | (array[n8] & 0xFF) << 8 | (array[n9] & 0xFF);
            final int n11 = n6 + 1;
            final char[] a = Base64.a;
            array2[n6] = a[n10 >>> 18 & 0x3F];
            final int n12 = n11 + 1;
            array2[n11] = a[n10 >>> 12 & 0x3F];
            final int n13 = n12 + 1;
            array2[n12] = a[n10 >>> 6 & 0x3F];
            final int n14 = n13 + 1;
            array2[n13] = a[n10 & 0x3F];
            n6 = n14;
            int n15 = n7;
            if (b) {
                final int n16 = n7 + 1;
                n6 = n14;
                if ((n15 = n16) == 19) {
                    n6 = n14;
                    n15 = n16;
                    if (n14 < n5 - 2) {
                        final int n17 = n14 + 1;
                        array2[n14] = '\r';
                        n6 = n17 + 1;
                        array2[n17] = '\n';
                        n15 = 0;
                    }
                }
            }
            i = n9 + 1;
            n7 = n15;
        }
        final int n18 = length - n;
        if (n18 > 0) {
            final byte b4 = array[n];
            int n19 = b2 ? 1 : 0;
            if (n18 == 2) {
                n19 = (array[n2] & 0xFF) << 2;
            }
            final int n20 = (b4 & 0xFF) << 10 | n19;
            final char[] a2 = Base64.a;
            array2[n5 - 4] = a2[n20 >> 12];
            array2[n5 - 3] = a2[n20 >>> 6 & 0x3F];
            char c;
            if (n18 == 2) {
                c = a2[n20 & 0x3F];
            } else {
                c = '=';
            }
            array2[n5 - 2] = c;
            array2[n5 - 1] = '=';
        }
        return array2;
    }

    public static final String encodeToString(final byte[] array, final boolean b) {
        return new String(encodeToChar(array, b));
    }
}
