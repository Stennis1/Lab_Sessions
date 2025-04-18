package EmployeeManagementSystem.model;
import java.util.*;

public class EmployeeDatabase<T> {
    private final Map<T, Employee<T>> employeeMap;

    public EmployeeDatabase() {
        employeeMap = new HashMap<>();
    }

    public void addEmployee(EmployeeManagementSystem.model.Employee<T> employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
        System.out.println("Added Employee!");
    }

    public void removeEmployee(T employeeId) {
        if (employeeMap.containsKey(employeeId)) {
            employeeMap.remove(employeeId);
            System.out.println("Removed Employee!");
        } else {
            System.out.println("Employee not found!!");
        }
    }

    public boolean updateEmployeeDetails(T employeeId, String field, Object newValue) {
        Employee<T> employee = employeeMap.get(employeeId);

        if (employee == null) {
            System.out.println("Employee not found!");
            return false;
        }
        switch (field.toLowerCase()) {
            case "name" -> employee.setName((String) newValue);
            case "department" -> employee.setDepartment((String) newValue);
            case "salary" -> employee.setSalary((Double) newValue);
            case "performancerating" -> employee.setPerformanceRating((double) newValue);
            case "yearsofexperience" -> employee.setYearsOfExperience((Integer) newValue);
            case "isactive" -> employee.setActive((boolean) newValue);
            default -> {
                return false;
            }
        }
        return true;
    }

    public List<Employee<T>> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

/***
    public static void displayAll(EmployeeDatabase<Integer> db) {
        for (Employee<Integer> emp : db.getAllEmployees()) {
            System.out.println(emp);
        }
    } ***/


    // Search by Fields
    public List<Employee<T>> searchByDepartment(String department) {
        return employeeMap.values().stream().filter(
                emp -> emp.getDepartment().equalsIgnoreCase(department)).toList();
    }

    public List<Employee<T>> searchByName(String keyword) {
        return employeeMap.values().stream().filter(
                emp -> emp.getName().toLowerCase().contains(keyword.toLowerCase())).toList();
    }

    public List<Employee<T>> filterByPerformance(double minRating) {
        return employeeMap.values().stream().filter(
                emp -> emp.getPerformanceRating() >= minRating).toList();
    }

    public List<Employee<T>> filterBySalaryRange(double minSalary, double maxSalary) {
        return employeeMap.values().stream().filter(
                emp -> emp.getSalary() >= minSalary && emp.getSalary() <= maxSalary).toList();
    }

    public Iterator<Employee<T>> getEmployeeIterator() {
        return employeeMap.values().iterator();
    }

    public void giveRaiseToHighPerformers(double minRating, double raisePercent) {
        employeeMap.values().stream().filter(emp -> emp.getPerformanceRating() >= minRating).forEach(
                emp -> {
                    double raiseAmount = emp.getSalary() + (raisePercent / 100);
                    emp.setSalary(emp.getSalary() + raiseAmount);
                }
        );
    }

    public List<Employee<T>> getTopPaidEmployees(int topN){
        return employeeMap.values().stream().sorted((e1, e2) -> Double.compare(
                        e1.getSalary(), e2.getSalary())).limit(topN).toList();
    }

    public double getAverageSalaryByDepartment(String department) {
        return employeeMap.values().stream().filter(
                emp -> emp.getDepartment().equalsIgnoreCase(department)).mapToDouble(
                        Employee::getSalary).average().orElse(0.0);
    }

}
