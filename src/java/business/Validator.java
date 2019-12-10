/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author virajee
 */
public class Validator {

    //this method is for mandatory field validation
    public boolean isPresent(String fieldValue) {
        if (fieldValue.length() == 0 || fieldValue == null) {
            return false;
        }
        return true;
    }

    public boolean isValidDate(String date) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            String after = df.format(df.parse(date));
            if (after.equals(date)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
    }

    //this method is for validating time
    public boolean isValidTime(String time) {
        try {
            String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
            Pattern pattern;
            pattern = Pattern.compile(TIME24HOURS_PATTERN);
            Matcher matcher;
            matcher = pattern.matcher(time);
            return matcher.matches();
        } catch (Exception e) {
            return false;
        }

    }

    //this method is for validating NIC No
    public boolean isValidNic(String nic) {
        if (nic.length() != 10) {
            return false;
        } else if (!nic.substring(9, 10).equalsIgnoreCase("v")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isValidNumber(String number) {
        try {
            int num = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //for email validation 
    public boolean isValidEmail(String fieldValue) {
        Pattern pattern = Pattern.compile("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isValidWithPasswordPolicy(String fieldValue) {
        Pattern pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
        Matcher matcher = pattern.matcher(fieldValue);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }
}
