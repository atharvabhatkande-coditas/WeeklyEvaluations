package week1;
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