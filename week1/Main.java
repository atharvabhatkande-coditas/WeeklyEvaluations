package week1;

public class Main {
    public static void main(String[] args) {
        Employee developer = new Developer("Atharva", 100000, 30, 5, 10);
        System.out.println("Developer");
        developer.printSalaryBreakdown();

        System.out.println("========================");
        Employee manager = new Manager("Deep", 200000, 25, 3, 5);
        System.out.println("Manager");
        manager.printSalaryBreakdown();

        System.out.println("========================");
        Employee intern = new Intern("Arya", 50000, 26, 5);
        System.out.println("Intern");
        intern.printSalaryBreakdown();

    }
}
