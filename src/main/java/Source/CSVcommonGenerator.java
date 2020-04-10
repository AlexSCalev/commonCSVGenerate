package Source;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CSVcommonGenerator {
    Scanner inputUser = new Scanner(System.in);

    private boolean checkStudentIDNP(String idnp) {
        if (idnp.length() >= 10 || idnp.length() <= 0) {
            System.out.println("Your student indp is very-big or is Empty");
            return true;
        }
        try {
            Integer.parseInt(idnp);
        } catch (NumberFormatException e) {
            System.out.println("Format IDNP not Corespnding {not use string}");
            return true;
        }
        return false;
    }

    private boolean checkStudentName(String name) {
        if (name.length() >= 10) {
            System.out.println("Error you name is Empty");
            return true;
        }
        if (!name.startsWith(String.valueOf(name.charAt(0)).toUpperCase())) {
            System.out.println("Error you  name not start Big-word");
            return true;
        }
        return false;
    }

    private boolean checkStudentFamily(String family) {
        if (family.length() >= 10) {
            System.out.println("Error you family is Empty");
            return true;
        }
        if (!family.startsWith(String.valueOf(family.charAt(0)).toUpperCase())) {
            System.out.println("Error you  family not start Big-word");
            return true;
        }
        return false;
    }

    private boolean checkStudentMidRingths(String midRingths) {
        if (midRingths.length() >= 10) {
            System.out.println("Error you midRingths is Empty");
            return true;
        }
        try {
            Double.parseDouble(midRingths);
        } catch (NumberFormatException e) {
            System.out.println("Error you midRingths not Corespondet");
            return true;
        }

        return false;
    }

    public ArrayList<String[]> getStudentsDate() {
        ArrayList<String[]> listStudents = new ArrayList<>();
        System.out.println("Input student Date in format Idnp,Name,Family,Mid-Ringths or write exit for end programm");

        while (true) {

// Create and check student idnp
            System.out.println("Input student IDNP:");
            String studentidnp = inputUser.nextLine();
            if (studentidnp.equals("exit")) break;
            if (checkStudentIDNP(studentidnp)) break;
// Create and check student name
            System.out.println("Input student Name:");
            String studentName = inputUser.nextLine();
            if (studentName.equals("exit")) break;
            if (checkStudentName(studentName)) break;
// Create and check student family
            System.out.println("Input student Family:");
            String studentFamily = inputUser.nextLine();
            if (studentFamily.equals("exit")) break;
            if (checkStudentFamily(studentFamily)) break;
// Create and cehck student mid Ringths
            System.out.println("Input student Mid Ringhts:");
            String studentMidRingths = inputUser.nextLine();
            if (studentMidRingths.equals("exit")) break;
            if (checkStudentMidRingths(studentMidRingths)) break;
// Add all date in temporary array String

            String[] student = {studentidnp, studentName, studentFamily, studentMidRingths};
// Save array student in ArraysList
            listStudents.add(student);
        }
        if (listStudents.isEmpty()) {
            throw new IllegalArgumentException("You student list is Empty file not create");
        }
        return listStudents;
    }

    public void setUserValue(String awayToFile) {
//        Create name file
        System.out.println("Input name file:");
        String nameFile = inputUser.nextLine();
//        Create Directories
        awayToFile = awayToFile + "\\" + nameFile + ".csv";
//        Input date Students

        generateCSV(awayToFile, getStudentsDate());

    }

    // This function create or recreate file in format CSV
    private void generateCSV(String awayToCreate, ArrayList<String[]> listStudent) {

        try {
            Writer writer = Files.newBufferedWriter(Paths.get(awayToCreate));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            for (String[] value : listStudent) {
                csvPrinter.printRecord(value);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.fillInStackTrace();
        }

    }

}
