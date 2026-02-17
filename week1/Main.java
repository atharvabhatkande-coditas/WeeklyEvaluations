package week1;

abstract class Employee {
    private String name;
    protected double baseSalary;
    protected int attendance;
    private int rating;
    private static final float pf = 0.12f;

    public Employee(String name, double baseSalary, int attendance, int rating) {
        this.name = name;
        this.baseSalary = baseSalary;
        this.attendance = attendance;
        this.rating = rating;
    }

    //Method to calculate the grossSalary
    abstract double grossSalary();

    //Method to calculate absent days
    public double calculateAttendance() {
        double dailySalary = baseSalary / 30;
        return ((30 - attendance) * dailySalary);
    }

    //Method to calculate bonus based on rating
    public double performanceBonus() {
        double gross = grossSalary();
        double bonus = 0;
        switch (rating) {
            case 1:
                bonus = 0;
                break;
            case 2:
                bonus = gross * 0.05;
                break;

            case 3:
                bonus = gross * 0.1;
                break;

            case 4:
                bonus = gross * 0.15;
                break;

            case 5:
                bonus = gross * 0.2;
                break;

            default:
                System.out.println("Enter rating upto 5");
                break;

        }
        return bonus;
    }

    //Method to calculate pf Deduction
    public double pfDeduction() {
        return baseSalary * pf;
    }

    public double taxCalculation() {
        double tax = 0;
        double taxableIncome = grossSalary() + performanceBonus();
        if (taxableIncome <= 50000) {
            tax = taxableIncome * 0.05;
        } else if (taxableIncome > 50000 && taxableIncome <= 100000) {
            tax = taxableIncome * 0.10;
        } else if (taxableIncome > 100000 && taxableIncome <= 150000) {
            tax = taxableIncome * 0.15;
        } else if (taxableIncome > 150000) {
            tax = taxableIncome * 0.2;
        }
        return tax;
    }

    //Method to calculate Net Salary
    public double netSalaryCalculation() {
        return (grossSalary() + performanceBonus() - taxCalculation() - pfDeduction() - calculateAttendance());
    }

    //Method to print the salary breakdown
    public void printSalaryBreakdown() {
        System.out.println("Gross Salary: " + grossSalary());
        System.out.println("Bonus: " + performanceBonus());
        System.out.println("Tax Deduction: " + taxCalculation());
        System.out.println("PF Deduction: " + pfDeduction());
        System.out.println("Attendance Deduction: " + calculateAttendance());
        System.out.println("Net Salary: " + netSalaryCalculation());
    }


}


class Developer extends Employee {
    private int overtime;

    public Developer(String name, double baseSalary, int attendance, int rating, int overtime) {
        super(name, baseSalary, attendance, rating);
        this.overtime = overtime;
    }

    @Override
    double grossSalary() {
        return (baseSalary + (overtime * 500));
    }
}

class Manager extends Employee {
    private int teamSize;

    public Manager(String name, double baseSalary, int attendance, int rating, int teamSize) {
        super(name, baseSalary, attendance, rating);
        this.teamSize = teamSize;
    }

    @Override
    double grossSalary() {
        return (baseSalary + (teamSize * 1000));
    }
}

class Intern extends Employee {

    public Intern(String name, double baseSalary, int attendance, int rating) {
        super(name, baseSalary, attendance, rating);
    }

    @Override
    double grossSalary() {
        float attendancePercent = (float) attendance / 30f;
        if (attendancePercent < 0.7) {
            return (baseSalary - (baseSalary * 0.20));
        } else {
            return baseSalary;
        }
    }
}


public class Main {
    public static void main(String[] args) {
        Employee developer = new Developer("Atharva", 100000, 30, 5, 10);
        System.out.println("Developer");
        developer.printSalaryBreakdown();


        Employee manager = new Manager("Deep", 200000, 25, 3, 5);
        System.out.println("Manager");
        manager.printSalaryBreakdown();

        Employee intern = new Intern("Arya", 50000, 26, 5);
        System.out.println("Intern");
        intern.printSalaryBreakdown();

    }
}
