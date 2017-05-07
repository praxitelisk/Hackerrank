import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeComparison {

    public static void main(String[] args) {
        String time1 = "12:49AM";
        String time2 = "1:48AM";

        String[] strArray1 = time1.split(":");
        String[] strArray2 =  time2.split(":");

        String amPMtime1 = strArray1[strArray1.length-1].substring(2);
        System.out.println(amPMtime1);

        String amPMtime2 = strArray2[strArray2.length-1].substring(2);
        System.out.println(amPMtime2);

        if (!amPMtime1.equals(amPMtime2)) {
            if (amPMtime1.equals("PM"))
                System.out.println("Second");
            else
                System.out.println("First");
        }
        else {
            int hour1 = Integer.parseInt(strArray1[0]);
            int hour2 = Integer.parseInt(strArray2[0]);
            hour1 = hour1 % 12;
            hour2 = hour2 % 12;

            if(hour1 > hour2)
                System.out.println("Second");
            else if (hour1 < hour2)
                System.out.println("First");
            else {

                int minutes1 = Integer.parseInt(strArray1[1].substring(0,2));
                int minutes2 = Integer.parseInt(strArray2[1].substring(0,2));

                if(minutes1 > minutes2)
                    System.out.println("Second");
                else if (minutes1 < minutes2)
                    System.out.println("First");
            }


        }
    }
}
