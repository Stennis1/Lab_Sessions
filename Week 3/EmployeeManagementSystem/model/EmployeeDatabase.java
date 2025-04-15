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

}
