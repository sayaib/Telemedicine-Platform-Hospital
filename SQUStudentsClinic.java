import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

//class definition
public class SQUStudentsClinic {
    // variable
    static Scanner inp;
    static ArrayList<Appointment> schedule;

    // showing the menu option to resceptionist
    static void menu() {
        // printing the menu options available
        System.out.println("Which service do you want?");
        System.out.println("\tPress \"1\" to schedule an appointment");
        System.out.println("\tPress \"2\" to check appointment");
        System.out.println("\tPress \"3\" to cancel an appointment");
        System.out.println("\tPress \"4\" to reschedule an appointment");
        System.out.println("\tPress \"#\" to exit");

    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // variables
        char choice;
        // creating object to read the clinic appointment data
        Scanner file = new Scanner(new File("Clinic Appointments.txt"));
        // arrayList to store the appointment information
        schedule = new ArrayList();
        StringTokenizer stk;
        // showing the input file content
        while (file.hasNext()) {
            stk = new StringTokenizer(file.nextLine(), "|");
            while (stk.hasMoreTokens()) {
                if (stk.nextToken().trim().equalsIgnoreCase("Emergency")) {
                    schedule.add(new Appointment("Emergency", Integer.parseInt(stk.nextToken().trim()),
                            stk.nextToken().trim(), stk.nextToken().trim(), stk.nextToken().trim(),
                            stk.nextToken().trim()));
                } else {
                    schedule.add(
                            new Appointment("Routine", Integer.parseInt(stk.nextToken().trim()), stk.nextToken().trim(),
                                    stk.nextToken().trim(), stk.nextToken().trim(), stk.nextToken().trim()));
                }
            }
        }
        // creating object for taking user input
        inp = new Scanner(System.in);
        // clinic message header
        System.out.println("******** Welcome to SQU student's clinic ********");
        // loop until user wish to exit
        do {
            // calling method to show menu
            menu();
            // storing the user choice
            choice = inp.next().charAt(0);
            inp.nextLine();
            // using switch to decide the user operation
            switch (choice) {
                case '1':
                    schedule_appointment();
                    break;
                case '2':
                    check_appointment();
                    break;
                case '3':
                    cancel_appointment();
                    break;
                case '4':
                    reschedule_appointment();
                    break;
                case '#':
                    char wrt = ' ';
                    System.out.print("Do you want to save the data y/n? ");
                    inp.next().charAt(0);
                    inp.nextLine();
                    if (wrt == 'y') {
                        save_data();
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        } while (choice != '#');
    }

    // schedule the appointment
    static void schedule_appointment() {
        int id;
        char typ;
        String name, symp, datetime, rprt;
        System.out.print("Enter the type of appointment (r) Routine or (e) Emergency: ");
        typ = inp.next().charAt(0);
        inp.nextLine();// to consume leftover newline character
        System.out.print("Enter the patient ID: ");
        id = inp.nextInt();
        inp.nextLine();
        System.out.print("Enter the patient name: ");
        name = inp.nextLine();
        if (typ == 'e') {
            System.out.print("Enter the patient current symptoms seperated by comma(,): ");
            symp = inp.nextLine();
            schedule.add(new Appointment("Emergency", id, name, "12-11-2021 09:00", "Dr. Ahmed Al abri", symp));
        } else {
            System.out.print("Enter the required tests seperated by comma(,): ");
            rprt = inp.nextLine();
            System.out.print("Enter appointment date and time as dd-mm-yyyy hh:mm : ");
            datetime = inp.nextLine();
            schedule.add(new Appointment("Routine", id, name, datetime, "Dr. Muna Mousa", rprt));
        }
    }

    // checking for appointment
    static void check_appointment() {
        int id;
        System.out.print("Enter the patient ID: ");
        id = inp.nextInt();
        inp.nextLine();
        for (int i = 0; i < schedule.size(); i++) {
            // checking if the patient details are found
            if (schedule.get(i).patient_id == id)
                System.out.println(schedule.get(i).getType() + "\t" + schedule.get(i).getPatient_id() + "\t"
                        + schedule.get(i).getPatient_name() + "\t" + schedule.get(i).getAppointment_date_time() + "\t"
                        + schedule.get(i).getDoctor_name() + "\t" + schedule.get(i).getpatient_details());
        }
    }

    // cancel the appointment
    static void cancel_appointment() {
        int id;
        System.out.print("Enter the patient ID: ");
        id = inp.nextInt();
        inp.nextLine();
        for (int i = 0; i < schedule.size(); i++) {
            // verify the patient to cancel the appointment
            if (schedule.get(i).patient_id == id) {
                System.out.println("The schedules appointment for " + schedule.get(i).getPatient_name() + "(ID# "
                        + schedule.get(i).getPatient_id() + ") on " + schedule.get(i).getAppointment_date_time()
                        + " is cancelled");
                schedule.remove(i);
                break;
            }
        }
    }

    // changing the appointment
    static void reschedule_appointment() {
        int id;
        String datetime;
        System.out.print("Enter the patient ID: ");
        id = inp.nextInt();
        inp.nextLine();
        for (int i = 0; i < schedule.size(); i++) {
            // verifying the appointment
            if (schedule.get(i).patient_id == id) {
                System.out.println("The coming appointment for " + schedule.get(i).getPatient_name() + "(ID# "
                        + schedule.get(i).getPatient_id() + ") on " + schedule.get(i).getAppointment_date_time());
                System.out.print("Enter the new appointment date and time as dd-mm-yyyy hh:mm : ");
                datetime = inp.nextLine();
                schedule.get(i).setAppointment_date_time(datetime);
                System.out.println("Appointment updated");
                break;
            }
        }
    }

    // saving data before closing
    static void save_data() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("Clinic Appointments.txt"));
        for (int i = 0; i < schedule.size(); i++) {
            bw.write(schedule.get(i).getType() + "|" + schedule.get(i).getPatient_id() + "|"
                    + schedule.get(i).getPatient_name() + "|" + schedule.get(i).getAppointment_date_time() + "|"
                    + schedule.get(i).getDoctor_name() + "|" + schedule.get(i).getpatient_details());
        }
        bw.close();
    }
}