package dates;

import java.util.Date;

public class dates {

    static public void main(String[] args) {
        Date date = new Date();

        System.out.printf("The time is %tH:%tM:%tS\n", date, date, date);
        System.out.printf("The date is %tm/%td/%tY\n", date, date, date);
        System.out.print("The date time is " + date.toString() + "\n");
        System.out.print("The amount of seconds since the \"UNIX EPOCH\" is " + date.getTime());
    }
}
