package org.smart4j.chapter1.frameWork.util;

/**
 * 转型操作工具类
 */
public class CastUtil {

    static {
        System.out.println("CastUtil INit");
    }
    public static String castString(Object object) {

        return CastUtil.castString(object, "");
    }

    private static String castString(Object object, String dvalue) {
        return object == null ? dvalue : String.valueOf(object);
    }


    public static int castInt(Object object) {

        return CastUtil.castInt(object,0);
    }

    public static int castInt(Object object, int dvalue) {

        int ivalue = dvalue;

        if (object != null) {

            String strValue = castString(object);

            if (StringUtil.isNotEmpty(strValue)) {
                try {
                    ivalue = Integer.parseInt(strValue);

                } catch (NumberFormatException e) {
                    ivalue = dvalue;
                }
            }
        }
        return ivalue;
    }


    public static double castDouble(Object object) {
        return CastUtil.castDouble(object, 0);
    }

    private static double castDouble(Object object, double dvalue) {

        double ivalue = dvalue;

        if (object != null) {

            String strValue = castString(object);

            if (StringUtil.isNotEmpty(strValue)) {

                try {
                    ivalue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {

                    ivalue = dvalue;
                }
            }
        }

        return ivalue;
    }


    public static long castLong(Object object) {

        return CastUtil.castLong(object, 0);
    }

    public static long castLong(Object object, long dvalue) {

        long ivalue = dvalue;

        if (object != null) {

            String strValue = castString(object);
            if (StringUtil.isNotEmpty(strValue)) {

                try {
                    ivalue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    ivalue = dvalue;
                }
            }


        }

        return ivalue;
    }


    public static boolean castBoolean(Object object) {

        return CastUtil.castBoolean(object, false);
    }

    private static boolean castBoolean(Object object, boolean dvalue) {

        boolean ivalue = dvalue;

        if (object != null) {

            String strValue = castString(object);

            if (StringUtil.isNotEmpty(strValue)) {
                    ivalue = Boolean.parseBoolean(strValue);

            }
        }

        return ivalue;

    }

}
