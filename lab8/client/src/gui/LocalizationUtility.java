package gui;


import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.ResourceBundle;

public class LocalizationUtility {
    private ResourceBundle bundle;

    public LocalizationUtility(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public String getKeyString(String key) {
        return bundle.getString(key);
    }

    public String getDate(LocalDate date) {
        if (date == null) return "null";
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(bundle.getLocale());
        return date.format(formatter);
    }

    public String getDate(LocalDateTime date) {
        if (date == null) return "null";
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(bundle.getLocale());
        return date.format(formatter);
    }

    public String getDate(Date date) {
        if (date == null) return "null";
        DateFormat formatter = DateFormat.getDateInstance(DateFormat.FULL, bundle.getLocale());
        return formatter.format(date);
    }
}

