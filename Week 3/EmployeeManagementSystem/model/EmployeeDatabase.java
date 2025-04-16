package EmployeeManagementSystem.model;
import java.util.*;

public class EmployeeDatabase<T> {
    private Map<T, Employee<T>> employeeMap;

    public EmployeeDatabase() {
        employeeMap = new HashMap<>();
    }

    public void addEmployee(Employee<T> employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
        System.out.println("Added Employee!");
    }

    public void removeEmployee(T employeeId) {
        if (employeeMap.containsKey(employeeId)) {
            employeeMap.remove(employeeId);
            System.out.println("Removed Employee!");
        } else {
            System.out.println("Employee not fouund!!");
        }
    }

    public void updateEmployeeDetails(T employeeId, String field, Object newValue) {
        Employee<T> employee = employeeMap.get(employeeId);

        if (employee == null) {
            System.out.println("Employee not found!");
            return;
        }
        switch (field.toLowerCase()) {
            case "name":
                employee.setName((String) newValue);
                break;
            case "department":
                employee.setDepartment((String) newValue);
                break;
            case "salary":
                employee.setSalary((Double) newValue);
                break;
            case "performancerating":
                employee.setPerformanceRating((double) newValue);
                break;
            case "yearsofexperience":
                employee.setYearsOfExperience((Integer) newValue);
                break;
            case "isactive":
                employee.setActive((boolean) newValue);
                break;
            default:
                System.out.println("Invalid field");
                return;
        }
        System.out.println("Employee details updated successfully!");
    }

    public List<Employee<T>> getAllEmployees() {
        return new ArrayList<>(employeeMap.values());
    }

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

}
