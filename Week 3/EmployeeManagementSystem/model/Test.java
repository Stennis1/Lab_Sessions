package EmployeeManagementSystem.model;
import java.util.*;

public class Test {
    public static void main(String[] args) {
        // Create sample employees
        Employee<Integer> emp1 = new Employee<>(101, "Alice Smith", "IT", 75000, 4.6, 5, true);
        Employee<Integer> emp2 = new Employee<>(102, "Bob Johnson", "HR", 65000, 4.1, 3, true);
        Employee<Integer> emp3 = new Employee<>(103, "Charlie Davis", "Finance", 82000, 4.9, 7, false);

        // Store them in a list
        List<Employee<Integer>> employeeList = new ArrayList<>();
        employeeList.add(emp1);
        employeeList.add(emp2);
        employeeList.add(emp3);

        // Display all employees
        System.out.println("All Employees:");
        for (Employee<Integer> emp : employeeList) {
            System.out.println(emp);
        }

        // Sort by years of experience (uses Comparable)
        Collections.sort(employeeList);

        System.out.println("\nEmployees Sorted by Experience (Descending):");
        for (Employee<Integer> emp : employeeList) {
            System.out.println(emp);
        }

        EmployeeDatabase<Integer> db = new EmployeeDatabase<>();


        // Add employees to database
        db.addEmployee(emp1);
        db.addEmployee(emp2);
        db.addEmployee(emp3);

        // Print all employees
        System.out.println("\nAll Employees:");
        for (Employee<Integer> e : db.getAllEmployees()) {
            System.out.println(e);
        }

        // Update an employee's salary
        db.updateEmployeeDetails(102, "salary", 70000.0);

        // Remove an employee
        db.removeEmployee(103);

        // Print after update and removal
        System.out.println("\nUpdated Employee List:");
        for (Employee<Integer> e : db.getAllEmployees()) {
            System.out.println(e);
        }

        System.out.println("\n🔍 Employees in IT Department:");
        for (Employee<Integer> emp : db.searchByDepartment("IT")) {
            System.out.println(emp);
        }

        System.out.println("\n🔍 Employees with Name containing 'Ali':");
        for (Employee<Integer> emp : db.searchByName("Ali")) {
            System.out.println(emp);
        }

        System.out.println("\n🔍 Employees with Rating ≥ 4.5:");
        for (Employee<Integer> emp : db.filterByPerformance(4.5)) {
            System.out.println(emp);
        }

        System.out.println("\n🔍 Employees with Salary between $60,000 and $90,000:");
        for (Employee<Integer> emp : db.filterBySalaryRange(60000, 90000)) {
            System.out.println(emp);
        }

        // Using Iterator
        System.out.println("\n🔁 Traversing All Employees using Iterator:");
        Iterator<Employee<Integer>> iterator = db.getEmployeeIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}
