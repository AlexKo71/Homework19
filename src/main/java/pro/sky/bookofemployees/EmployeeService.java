package pro.sky.bookofemployees;


import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {


    private final Map<String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee addPersons(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.size() > 10) {
            throw new EmployeeStorageIsFullException("коллекция переполнена");
        }
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removePersons(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee);
        }
        throw new EmployeeNotFoundException("сотрудник не найден");
    }

    public Employee findPersons(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employee;
        }
        throw new EmployeeNotFoundException("такой сотрудник не найден");
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public String getPerson(Integer number) {
        final Employee employee;
        employee = employees.get(number);
        final String personList = " " + employee.getFirstName() + " " + employee.getLastName();
        return personList;
    }

}


