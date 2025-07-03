package com.app;
import java.sql.*;
import java.util.Scanner;

public class EmployeeDBApp {
    static final String DB_URL = "jdbc:mysql://localhost:3306/assignment";
    static final String USER = "root"; // change as per your DB config
    static final String PASS = "";     // change as per your DB config

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            while (true) {
                System.out.println("\nEmployee DB App - Choose Operation:");
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee");
                System.out.println("4. Delete Employee");
                System.out.println("5. Exit");
                System.out.print("Choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> addEmployee(conn, scanner);
                    case 2 -> viewEmployees(conn);
                    case 3 -> updateEmployee(conn, scanner);
                    case 4 -> deleteEmployee(conn, scanner);
                    case 5 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void addEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter name: ");
        String name = scanner.next();
        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        String sql = "INSERT INTO employees (name, salary) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, salary);
            pstmt.executeUpdate();
            System.out.println("Employee added!");
        }
    }

    static void viewEmployees(Connection conn) throws SQLException {
        String sql = "SELECT * FROM employees";
        try (PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("ID\tName\tSalary");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%.2f\n", rs.getInt("id"), rs.getString("name"), rs.getDouble("salary"));
            }
        }
    }

    static void updateEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        System.out.print("Enter new salary: ");
        double salary = scanner.nextDouble();

        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, salary);
            pstmt.setInt(2, id);
            int updated = pstmt.executeUpdate();
            System.out.println(updated > 0 ? "Employee updated!" : "Employee not found!");
        }
    }

    static void deleteEmployee(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();

        String sql = "DELETE FROM employees WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            System.out.println(deleted > 0 ? "Employee deleted!" : "Employee not found!");
        }
    }
}

