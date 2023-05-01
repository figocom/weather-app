package com.figo.weatherapp.utils;

import com.figo.weatherapp.exception.RestException;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.regex.Pattern;

public class TypeChecker {



    /**
     * OBJECT NI LONG[] GA PARSE QILIB BERADI AKS HOLDA THROW
     *
     * @param value
     * @return
     */
    public static Long[] longArrayOrElseThrow(@NonNull Object value) {
        try {

            String replaceBracket = CommonUtils.replaceBracket(value.toString());

            String[] strings = replaceBracket.split(",");

            Long[] longs = new Long[strings.length];

            for (int i = 0; i < strings.length; i++) {
                longs[i] = Long.parseLong(strings[i]);
            }

            return longs;

        } catch (Exception exception) {
            throw RestException.restThrow(ResponseMessage.TYPE_LONG_ARRAY_REQUIRED);
        }
    }
    /**
     * BERILGA OBJECT NI UUID EKANLIGINI TEKSHIRIB STRING[] QAYTARADI
     */
    public static String[] UUIDArrayOrElseThrow(Object value) {

        try {
            String replaceBracket = CommonUtils.replaceBracket(value.toString());

            String[] strings = replaceBracket.split(",");

            //CHECKING TYPE IS UUID
            for (String string : strings)
                //BERILGAN STRING UUID BO'LMASA THROW GA OTADI
                stringIsUUIDOrElseThrow(string);
            return strings;
        } catch (Exception exception) {
            throw RestException.restThrow(ResponseMessage.TYPE_UUID_ARRAY);
        }
    }
/**
     * BERILGAN STRING UUID BO'LMASA THROW GA OTADI
     */
    private static void stringIsUUIDOrElseThrow(String string) {
        if (!Pattern.matches(AppConstant.GLOBAL_UUID_REGEX, string))
            throw RestException.restThrow(ResponseMessage.TYPE_UUID);
    }
 /**
     * STRING TIMESTAMP GA PARSE QILIB KO'RADI O'XSHASA LONG AKS HOLDA THROW TASHLAYDI
     *
     * @param value
     * @return
     */
    public static Long parseTimeStampIfYesReturnLongOrElseThrow(String value) {
        try {
            Timestamp timestamp = Timestamp.valueOf(value);
            return timestamp.getTime();
        } catch (Exception exception) {
            exception.printStackTrace();
            throw RestException.restThrow(ResponseMessage.TYPE_IS_NOT_DATE);
        }
    }

    /**
     * TYPE NUMBER BO'LSA NUMBER QAYTARADI AKS HOLDA THROW GA OTADI
     *
     * @param value
     * @return
     */
    public static Object parseNumberOrElseThrow(String value) {
        try {
            if (value.contains(".") || value.contains("E"))
                return Double.parseDouble(value);
            else
                return Long.parseLong(value);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw RestException.restThrow(ResponseMessage.TYPE_IS_NOT_NUMBER);
        }
    }


}
