import java.util.Scanner;

abstract class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double salary;

    FullTime(String name, int id, double salary) {
        super(name, id);
        this.salary = salary;
    }

    double monthlySalary() {
        return salary;
    }
}

class PartTime extends Employee {
    int hours;
    double rate;

    PartTime(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    double monthlySalary() {
        return hours * rate;
    }
}

class Intern extends Employee {
    double stipend;

    Intern(String name, int id, double stipend) {
        super(name, id);
        this.stipend = stipend;
    }

    double monthlySalary() {
        return stipend;
    }
}

public class PayrollDemo {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Employee[] employees = new Employee[3];

        // Full-Time Employee
        System.out.println("Enter Full-Time Employee Details");

        System.out.print("Enter name: ");
        String name1 = sc.nextLine();

        System.out.print("Enter ID: ");
        int id1 = sc.nextInt();

        System.out.print("Enter monthly salary: ");
        double salary = sc.nextDouble();

        employees[0] = new FullTime(name1, id1, salary);

        sc.nextLine();

        // Part-Time Employee
        System.out.println("\nEnter Part-Time Employee Details");

        System.out.print("Enter name: ");
        String name2 = sc.nextLine();

        System.out.print("Enter ID: ");
        int id2 = sc.nextInt();

        System.out.print("Enter working hours: ");
        int hours = sc.nextInt();

        System.out.print("Enter rate per hour: ");
        double rate = sc.nextDouble();

        employees[1] = new PartTime(name2, id2, hours, rate);

        sc.nextLine();

        // Intern
        System.out.println("\nEnter Intern Details");

        System.out.print("Enter name: ");
        String name3 = sc.nextLine();

        System.out.print("Enter ID: ");
        int id3 = sc.nextInt();

        System.out.print("Enter stipend: ");
        double stipend = sc.nextDouble();

        employees[2] = new Intern(name3, id3, stipend);

        // Calculate salaries
        double total = 0;

        System.out.println("\n--- Payroll Details ---");

        for (Employee e : employees) {

            double salaryAmount = e.monthlySalary();

            System.out.println("Name: " + e.name);
            System.out.println("ID: " + e.id);
            System.out.println("Monthly Salary: " + salaryAmount);

            if (e instanceof Intern) {
                System.out.println("Note: This employee is an Intern");
            }

            System.out.println();

            total = total + salaryAmount;
        }

        System.out.println("Total Salary = " + total);

        sc.close();
    }
}