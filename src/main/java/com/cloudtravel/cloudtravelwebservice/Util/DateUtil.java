package com.cloudtravel.cloudtravelwebservice.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static final String FORMAT_yMdHMS = "yyyy-MM-dd HH:mm:ss";

    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
