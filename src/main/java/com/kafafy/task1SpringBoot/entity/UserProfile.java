package com.kafafy.task1SpringBoot.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.Period;

public class UserProfile {
    private int userId;

    @NotBlank(message = "Name cannot be null or empty.")
    private String name;

    @NotBlank(message = "Username cannot be null or empty.")
    private String userName;

    @NotBlank(message = "Password cannot be null or empty.")
    private String password;

    @NotBlank(message = "Address cannot be null or empty.")
    private String address;

    @NotBlank(message = "National ID cannot be null or empty.")
    @Pattern(regexp = "\\d{14}", message = "National ID must be 14 digits.")
    private String nationalId;

    @NotBlank(message = "Phone number cannot be null or empty.")
    @Pattern(regexp = "^(\\+20|002)\\d{10,11}$", message = "Phone number must start with +20 or 002 and be 13 or 14 digits long.")
    private String phone;

    private int departmentId;

    @NotNull(message = "Salary cannot be null.")
    @Positive(message = "Salary must be positive.")
    private double salary;

    private LocalDate birthDate;

    private int age;

    private String departmentName;

    // Setters and Getters

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
        this.birthDate = extractBirthDateFromNationalId(nationalId);
        this.age = calculateAge(this.birthDate);

        validateAge(this.age); // Perform age validation after calculating it
    }

    private LocalDate extractBirthDateFromNationalId(String nationalId) {
        if (nationalId == null || nationalId.length() != 14 || !nationalId.matches("\\d{14}")) {
            throw new IllegalArgumentException("Invalid national ID format.");
        }

        int centuryIndicator = Character.getNumericValue(nationalId.charAt(0));
        int year = Integer.parseInt(nationalId.substring(1, 3));
        int month = Integer.parseInt(nationalId.substring(3, 5));
        int day = Integer.parseInt(nationalId.substring(5, 7));

        if (centuryIndicator == 2) {
            year += 1900; // 1900-1999
        } else if (centuryIndicator == 3) {
            year += 2000; // 2000-2099
        } else {
            throw new IllegalArgumentException("Invalid century indicator in national ID. It must be 2 or 3.");
        }

        if (!isValidDate(year, month, day)) {
            throw new IllegalArgumentException("The extracted date is not valid.");
        }

        return LocalDate.of(year, month, day);
    }

    private boolean isValidDate(int year, int month, int day) {
        try {
            LocalDate.of(year, month, day);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    private void validateAge(int age) {
        if (age < 18 || age > 100) {
            throw new IllegalArgumentException("The age extracted from the National ID is not acceptable. It should be between 18 and 100 years.");
        }
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", phone='" + phone + '\'' +
                ", departmentId=" + departmentId +
                ", salary=" + salary +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", departmentName='" + departmentName + '\'' +
                '}';
    }
}
