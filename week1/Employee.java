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
