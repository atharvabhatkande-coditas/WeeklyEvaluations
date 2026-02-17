package week1;

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