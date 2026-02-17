package week1;

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