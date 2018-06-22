package lapr.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;
/**
 * Represents a Date.
 * <p>
 * Created by Rafael Marques (1090441@isep.ipp.pt) on 04/06/2018.
 */
@XmlRootElement
public class Date implements Comparable<Date> {

    private static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Instance variables
    private int year;
    private int month;
    private int day;

    public Date() {
    }

    public Date(int year, int month, int day) {
        setDate(year, month, day);
    }

    public Date(Date otherDate) {
        setDate(otherDate.year, otherDate.month, otherDate.day);
    }

    public Date(String dateString) {
        int[] result = validateString(dateString);
        setDate(result[0], result[1], result[2]);
    }

    /* Getters & Setters
    ===================================== */

    @XmlElement
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year < 1) {
            throw new IllegalArgumentException(String.format("Year %d is Invalid!!", year));
        } else {
            this.year = year;
        }
    }

    @XmlElement
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException(String.format("Month %d is Invalid!!", month));
        } else {
            this.month = month;
        }
    }

    @XmlElement
    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setDay(int day, int month, int year) {
        if (day < 1 || day > numberOfMonthDays(month, year)) {
            throw new IllegalArgumentException(String.format("Day %d for the month %d is invalid!!", day, month));
        } else {
            this.day = day;
        }
    }

    public static boolean isLeepYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /* toString, equals & hashCode
    ===================================== */

    @Override
    public String toString() {
        return String.format("%04d/%02d/%02d", this.year, this.month, this.day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Date date = (Date) o;
        return year == date.year &&
                month == date.month &&
                day == date.day;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /* Custom
    ===================================== */

    public static int numberOfMonthDays(int month, int year) {
        int leepYearDay = ((month == 2 && Date.isLeepYear(year)) ? 1 : 0);
        return MONTH_DAYS[month - 1] + leepYearDay;
    }

    public static Date currentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;
        int day = today.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, day);
    }

    public static int[] validateString(String dateString) {
        String[] tmp = dateString.split("/");
        int[] result;
        if (tmp.length != 3) {
            throw new IllegalArgumentException("Please supply a date in the following format 'yyyy/MM/dd'!");
        } else {
            result = new int[tmp.length];
            try {
                result[0] = Integer.parseInt(tmp[0]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The value in the year field is not numerical");
            }

            try {
                result[1] = Integer.parseInt(tmp[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The value in the month field is not numerical");
            }

            try {
                result[2] = Integer.parseInt(tmp[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("The value in the day field is not numerical");
            }
        }
        return result;
    }

    @Override
    public int compareTo(Date otherDate) {
        return (otherDate.isGreater(this)) ? -1 : (isGreater(otherDate)) ? 1 : 0;
    }

    private int countDays() {
        int totalDays = 0;

        for (int i = 1; i < year; i++) {
            totalDays += isLeepYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month; i++) {
            totalDays += numberOfMonthDays(i, year);
        }
        totalDays += day;

        return totalDays;
    }

    public final void setDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day, month, year);
    }

    public boolean isGreater(Date otherDate) {
        int totalDays = countDays();
        int totalDays1 = otherDate.countDays();

        return totalDays > totalDays1;
    }

    public int difference(Date otherDate) {
        int totalDays = countDays();
        int totalDays1 = otherDate.countDays();

        return Math.abs(totalDays - totalDays1);
    }

    public int difference(int year, int month, int day) {
        int totalDays = countDays();
        Date otherDate = new Date(year, month, day);
        int totalDays1 = otherDate.countDays();

        return Math.abs(totalDays - totalDays1);
    }

}


