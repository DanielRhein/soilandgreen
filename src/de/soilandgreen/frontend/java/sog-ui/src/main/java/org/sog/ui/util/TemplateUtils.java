package net.artelis.wita.ui.util;

import java.text.DecimalFormatSymbols;

import org.springframework.context.i18n.LocaleContextHolder;

public class TemplateUtils {

    public static String getCurrencyRegexPattern() {
        String decimalSep = "" + DecimalFormatSymbols.getInstance(LocaleContextHolder.getLocale()).getDecimalSeparator();
        if (decimalSep.equals(".")) {
            decimalSep = "\\" + decimalSep;
        }
        return "\\d*(" + decimalSep + "\\d\\d)?";
    }

}
