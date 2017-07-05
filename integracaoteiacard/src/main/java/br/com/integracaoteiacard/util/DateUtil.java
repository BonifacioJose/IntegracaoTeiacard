package br.com.integracaoteiacard.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Bonifácio
 */
public abstract class DateUtil {

    public static String getFormatoData(Date data) {
        if (data != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            int mes = calendar.get(Calendar.MONTH) + 1;
            return String.valueOf(dia < 10 ? "0".concat(String.valueOf(dia)) : dia)
                    + String.valueOf(mes < 10 ? "0".concat(String.valueOf(mes)) : mes)
                    + String.valueOf(calendar.get(Calendar.YEAR));
        }
        return "";
    }
}
