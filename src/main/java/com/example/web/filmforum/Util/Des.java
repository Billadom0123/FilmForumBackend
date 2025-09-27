package com.example.web.filmforum.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * DES加密解密
 */


/*
 * encrypt the string to string made up of hex return the encrypted string
 */
public class Des {

    public static String strEnc(String data, String firstKey, String secondKey,
                         String thirdKey) {

        StringBuilder encData = new StringBuilder();
        List<int[]> firstKeyBt = null, secondKeyBt = null, thirdKeyBt = null;
        int firstLength = 0, secondLength = 0, thirdLength = 0;
        if (firstKey != null && !firstKey.isEmpty()) {
            firstKeyBt = getKeyBytes(firstKey);
            firstLength = firstKeyBt.size();
        }
        if (secondKey != null && !secondKey.isEmpty()) {
            secondKeyBt = getKeyBytes(secondKey);
            secondLength = secondKeyBt.size();
        }
        if (thirdKey != null && !thirdKey.isEmpty()) {
            thirdKeyBt = getKeyBytes(thirdKey);
            thirdLength = thirdKeyBt.size();
        }

        int length = data.length();
        if (length > 0) {
            boolean firstKeyNotNull = firstKey != null && !firstKey.isEmpty();
            boolean secondKeyNotNull = secondKey != null && !secondKey.isEmpty();
            boolean thirdKeyNotNull = thirdKey != null && !thirdKey.isEmpty();
            if (length < 4) {
                int[] bt = strToBt(data);
                int[] encByte = null;
                if (firstKeyNotNull && secondKeyNotNull && thirdKeyNotNull) {
                    int[] tempBt;
                    int x, y, z;
                    tempBt = bt;
                    for (x = 0; x < firstLength; x++) {
                        tempBt = enc(tempBt, firstKeyBt.get(x));
                    }
                    for (y = 0; y < secondLength; y++) {
                        tempBt = enc(tempBt, secondKeyBt.get(y));
                    }
                    for (z = 0; z < thirdLength; z++) {
                        tempBt = enc(tempBt, thirdKeyBt.get(z));
                    }
                    encByte = tempBt;
                } else {
                    if (firstKeyNotNull && secondKeyNotNull) {
                        int[] tempBt;
                        int x, y;
                        tempBt = bt;
                        for (x = 0; x < firstLength; x++) {
                            tempBt = enc(tempBt, firstKeyBt.get(x));
                        }
                        for (y = 0; y < secondLength; y++) {
                            tempBt = enc(tempBt, secondKeyBt.get(y));
                        }
                        encByte = tempBt;
                    } else {
                        if (firstKeyNotNull) {
                            int[] tempBt;
                            int x;
                            tempBt = bt;
                            for (x = 0; x < firstLength; x++) {
                                tempBt = enc(tempBt, firstKeyBt.get(x));
                            }
                            encByte = tempBt;
                        }
                    }
                }
                encData = new StringBuilder(bt64ToHex(encByte));
            } else {
                int iterator = (length / 4);
                int remainder = length % 4;
                int i;
                for (i = 0; i < iterator; i++) {
                    String tempData = data.substring(i * 4, i * 4 + 4);
                    int[] tempByte = strToBt(tempData);
                    int[] encByte = null;
                    if (firstKeyNotNull && secondKeyNotNull && thirdKeyNotNull) {
                        int[] tempBt;
                        int x, y, z;
                        tempBt = tempByte;
                        for (x = 0; x < firstLength; x++) {
                            tempBt = enc(tempBt, firstKeyBt.get(x));
                        }
                        for (y = 0; y < secondLength; y++) {
                            tempBt = enc(tempBt, secondKeyBt.get(y));
                        }
                        for (z = 0; z < thirdLength; z++) {
                            tempBt = enc(tempBt, thirdKeyBt.get(z));
                        }
                        encByte = tempBt;
                    } else {
                        if (firstKeyNotNull && secondKeyNotNull) {
                            int[] tempBt;
                            int x, y;
                            tempBt = tempByte;
                            for (x = 0; x < firstLength; x++) {
                                tempBt = enc(tempBt, firstKeyBt.get(x));
                            }
                            for (y = 0; y < secondLength; y++) {
                                tempBt = enc(tempBt, secondKeyBt.get(y));
                            }
                            encByte = tempBt;
                        } else {
                            if (firstKeyNotNull) {
                                int[] tempBt;
                                int x;
                                tempBt = tempByte;
                                for (x = 0; x < firstLength; x++) {
                                    tempBt = enc(tempBt, firstKeyBt
                                            .get(x));
                                }
                                encByte = tempBt;
                            }
                        }
                    }
                    encData.append(bt64ToHex(encByte));
                }
                if (remainder > 0) {
                    String remainderData = data.substring(iterator * 4,
                            length);
                    int[] tempByte = strToBt(remainderData);
                    int[] encByte = null;
                    if (firstKeyNotNull && secondKeyNotNull && thirdKeyNotNull) {
                        int[] tempBt;
                        int x, y, z;
                        tempBt = tempByte;
                        for (x = 0; x < firstLength; x++) {
                            tempBt = enc(tempBt, firstKeyBt.get(x));
                        }
                        for (y = 0; y < secondLength; y++) {
                            tempBt = enc(tempBt, secondKeyBt.get(y));
                        }
                        for (z = 0; z < thirdLength; z++) {
                            tempBt = enc(tempBt, thirdKeyBt.get(z));
                        }
                        encByte = tempBt;
                    } else {
                        if (firstKeyNotNull && secondKeyNotNull) {
                            int[] tempBt;
                            int x, y;
                            tempBt = tempByte;
                            for (x = 0; x < firstLength; x++) {
                                tempBt = enc(tempBt, firstKeyBt.get(x));
                            }
                            for (y = 0; y < secondLength; y++) {
                                tempBt = enc(tempBt, secondKeyBt.get(y));
                            }
                            encByte = tempBt;
                        } else {
                            if (firstKeyNotNull) {
                                int[] tempBt;
                                int x;
                                tempBt = tempByte;
                                for (x = 0; x < firstLength; x++) {
                                    tempBt = enc(tempBt, firstKeyBt
                                            .get(x));
                                }
                                encByte = tempBt;
                            }
                        }
                    }
                    encData.append(bt64ToHex(encByte));
                }
            }
        }
        return encData.toString();
    }

    /*
     * chang the string into the bit array
     *
     * return bit array(it's length % 64 = 0)
     */
    private static List<int[]> getKeyBytes(String key) {
        List<int[]> keyBytes = new ArrayList<>();
        int length = key.length();
        int iterator = (length / 4);
        int remainder = length % 4;
        int i;
        for (i = 0; i < iterator; i++) {
            keyBytes.add(i, strToBt(key.substring(i * 4, i * 4 + 4)));
        }
        if (remainder > 0) {
            // keyBytes[i] = strToBt(key.substring(i*4+0,length));
            keyBytes.add(i, strToBt(key.substring(i * 4, length)));
        }
        return keyBytes;
    }

    /*
     * chang the string(it's length <= 4) into the bit array
     *
     * return bit array(it's length = 64)
     */
    private static int[] strToBt(String str) {
        int length = str.length();
        int[] bt = new int[64];
        if (length < 4) {
            int i, j, p, q;
            for (i = 0; i < length; i++) {
                int k = str.charAt(i);
                for (j = 0; j < 16; j++) {
                    int pow = 1, m;
                    for (m = 15; m > j; m--) {
                        pow *= 2;
                    }
                    // bt.set(16*i+j,""+(k/pow)%2));
                    bt[16 * i + j] = (k / pow) % 2;
                }
            }
            for (p = length; p < 4; p++) {
                for (q = 0; q < 16; q++) {
                    bt[16 * p + q] = 0;
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                int k = str.charAt(i);
                for (int j = 0; j < 16; j++) {
                    int pow = 1;
                    for (int m = 15; m > j; m--) {
                        pow *= 2;
                    }
                    bt[16 * i + j] = (k / pow) % 2;
                }
            }
        }
        return bt;
    }

    /*
     * chang the bit(it's length = 4) into the hex
     *
     * return hex
     */
    private static String bt4ToHex(String binary) {
        return Integer.toHexString(Integer.parseInt(binary, 2)).toUpperCase();
    }

    private static String bt64ToHex(int[] byteData) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            StringBuilder bt = new StringBuilder();
            for (int j = 0; j < 4; j++) {
                bt.append(byteData[i * 4 + j]);
            }
            hex.append(bt4ToHex(bt.toString()));
        }
        return hex.toString();
    }

    /*
     * the 64 bit des core arithmetic
     */

    private static int[] enc(int[] dataByte, int[] keyByte) {
        int[][] keys = generateKeys(keyByte);
        int[] ipByte = initPermute(dataByte);
        int[] ipLeft = new int[32];
        int[] ipRight = new int[32];
        int[] tempLeft = new int[32];
        int i, j, k, m, n;
        for (k = 0; k < 32; k++) {
            ipLeft[k] = ipByte[k];
            ipRight[k] = ipByte[32 + k];
        }
        for (i = 0; i < 16; i++) {
            for (j = 0; j < 32; j++) {
                tempLeft[j] = ipLeft[j];
                ipLeft[j] = ipRight[j];
            }
            int[] key = new int[48];
            for (m = 0; m < 48; m++) {
                key[m] = keys[i][m];
            }
            int[] tempRight = xor(pPermute(sBoxPermute(xor(
                    expandPermute(ipRight), key))), tempLeft);
            for (n = 0; n < 32; n++) {
                ipRight[n] = tempRight[n];
            }

        }

        int[] finalData = new int[64];
        for (i = 0; i < 32; i++) {
            finalData[i] = ipRight[i];
            finalData[32 + i] = ipLeft[i];
        }
        return finallyPermute(finalData);
    }

    private static int[] initPermute(int[] originalData) {
        int[] ipByte = new int[64];
        int i, m, n, j, k;
        for (i = 0, m = 1, n = 0; i < 4; i++, m += 2, n += 2) {
            for (j = 7, k = 0; j >= 0; j--, k++) {
                ipByte[i * 8 + k] = originalData[j * 8 + m];
                ipByte[i * 8 + k + 32] = originalData[j * 8 + n];
            }
        }
        return ipByte;
    }

    private static int[] expandPermute(int[] rightData) {
        int[] epByte = new int[48];
        int i;
        for (i = 0; i < 8; i++) {
            if (i == 0) {
                epByte[i * 6] = rightData[31];
            } else {
                epByte[i * 6] = rightData[i * 4 - 1];
            }
            epByte[i * 6 + 1] = rightData[i * 4];
            epByte[i * 6 + 2] = rightData[i * 4 + 1];
            epByte[i * 6 + 3] = rightData[i * 4 + 2];
            epByte[i * 6 + 4] = rightData[i * 4 + 3];
            if (i == 7) {
                epByte[i * 6 + 5] = rightData[0];
            } else {
                epByte[i * 6 + 5] = rightData[i * 4 + 4];
            }
        }
        return epByte;
    }

    private static int[] xor(int[] byteOne, int[] byteTwo) {
        int[] xorByte = new int[byteOne.length];
        for (int i = 0; i < byteOne.length; i++) {
            xorByte[i] = byteOne[i] ^ byteTwo[i];
        }
        return xorByte;
    }

    private static int[] sBoxPermute(int[] expandByte) {

        // var sBoxByte = new Array(32);
        int[] sBoxByte = new int[32];
        String binary = "";
        int[][] s1 = {
                { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 },
                { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 },
                { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 },
                { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } };

        /* Table - s2 */
        int[][] s2 = {
                { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 },
                { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 },
                { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 },
                { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } };

        /* Table - s3 */
        int[][] s3 = {
                { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 },
                { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 },
                { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 },
                { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } };
        /* Table - s4 */
        int[][] s4 = {
                { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 },
                { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 },
                { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 },
                { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } };

        /* Table - s5 */
        int[][] s5 = {
                { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 },
                { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 },
                { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 },
                { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } };

        /* Table - s6 */
        int[][] s6 = {
                { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 },
                { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 },
                { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 },
                { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } };

        /* Table - s7 */
        int[][] s7 = {
                { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 },
                { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 },
                { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 },
                { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } };

        /* Table - s8 */
        int[][] s8 = {
                { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 },
                { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 },
                { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 },
                { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } };

        int[][][] s = { s1, s2, s3, s4, s5, s6, s7, s8 };

        for (int m = 0; m < 8; m++) {
            int i, j;
            i = expandByte[m * 6] * 2 + expandByte[m * 6 + 5];
            j = expandByte[m * 6 + 1] * 2 * 2 * 2 + expandByte[m * 6 + 2] * 2
                    * 2 + expandByte[m * 6 + 3] * 2 + expandByte[m * 6 + 4];
            binary = switch (m) {
                case 0 -> getBoxBinary(s1[i][j]);
                case 1 -> getBoxBinary(s2[i][j]);
                case 2 -> getBoxBinary(s3[i][j]);
                case 3 -> getBoxBinary(s4[i][j]);
                case 4 -> getBoxBinary(s5[i][j]);
                case 5 -> getBoxBinary(s6[i][j]);
                case 6 -> getBoxBinary(s7[i][j]);
                case 7 -> getBoxBinary(s8[i][j]);
                default -> binary;
            };
            sBoxByte[m * 4] = Integer.parseInt(binary.substring(0, 1));
            sBoxByte[m * 4 + 1] = Integer.parseInt(binary.substring(1, 2));
            sBoxByte[m * 4 + 2] = Integer.parseInt(binary.substring(2, 3));
            sBoxByte[m * 4 + 3] = Integer.parseInt(binary.substring(3, 4));
        }
        return sBoxByte;
    }

    private static int[] pPermute(int[] sBoxByte) {
        int[] pBoxPermute = new int[32];
        int[] pBox = {
                15, 6, 19, 20, 28, 11, 27, 16,
                0, 14, 22, 25, 4, 17, 30, 9,
                1, 7, 23, 13, 31, 26, 2, 8,
                18, 12, 29, 5, 21, 10, 3, 24
        };
        for (int i = 0; i < 32; i++) {
            pBoxPermute[i] = sBoxByte[pBox[i]];
        }
        return pBoxPermute;
    }

    private static int[] finallyPermute(int[] endByte) {
        int[] fpByte = new int[64];
        int[] fpMap = {
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25,
                32, 0, 40, 8, 48, 16, 56, 24
        };
        for (int i = 0; i < 64; i++) {
            fpByte[i] = endByte[fpMap[i]];
        }
        return fpByte;
    }

    private static String getBoxBinary(int i) {
        String binary = Integer.toBinaryString(i);
        // 前面补0
        while (binary.length() < 4) {
            binary = "0" + binary;
        }
        return binary;
    }

    /*
     * generate 16 keys for xor
     *
     */
    private static int[][] generateKeys(int[] keyByte) {

        int[] key = new int[56];
        for (int i = 0; i < 7; i++) {
            for (int j = 0, k = 7; j < 8; j++, k--) {
                key[i * 8 + j] = keyByte[8 * k + i];
            }
        }

        int i;
        int[][] keys = new int[16][48];
        int[] loop = new int[]{1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        for (i = 0; i < 16; i++) {
            int tempLeft;
            int tempRight;
            for (int j = 0; j < loop[i]; j++) {
                tempLeft = key[0];
                tempRight = key[28];
                for (int k = 0; k < 27; k++) {
                    key[k] = key[k + 1];
                    key[28 + k] = key[29 + k];
                }
                key[27] = tempLeft;
                key[55] = tempRight;
            }
            // var tempKey = new Array(48);
            int[] tempKey = new int[48];
            int[] permuteChoice = {
                    13, 16, 10, 23, 0, 4, 2, 27,
                    14, 5, 20, 9, 22, 18, 11, 3,
                    25, 7, 15, 6, 26, 19, 12, 1,
                    40, 51, 30, 36, 46, 54, 29, 39,
                    50, 44, 32, 47, 43, 48, 38, 55,
                    33, 52, 45, 41, 49, 35, 28, 31
            };
            for (int j = 0; j < 48; j++) {
                tempKey[j] = key[permuteChoice[j]];
            }
            int m;
            for (m = 0; m < 48; m++) {
                keys[i][m] = tempKey[m];
            }
        }
        return keys;
    }
}
