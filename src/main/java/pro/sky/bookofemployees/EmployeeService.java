package pro.sky.bookofemployees;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>(Arrays.asList(
            new Employee("Ivan", "Petrov"), new Employee("Иван", "Федоров"), new Employee("Петр", "Сергеев"),
            new Employee("Татьяна", "Петрова"), new Employee("Ирина", "Миронова"), new Employee("Максим", "Заваров"),
            new Employee("Александр", "Тимофеев")
    ));


    public Employee addPersons(String firstName, String lastName) {
        if (employees.size() > 10) {
            throw new EmployeeStorageIsFullException("коллекция переполнена");
        }
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException();
            }
        }
        var a = new Employee(firstName, lastName);
        employees.add(a);
        return a;
    }

    public List<Employee> removePersons(String firstName, String lastName) {
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            Employee nextPerson = employeeIterator.next();
            if (nextPerson.getFirstName().equals(firstName) && nextPerson.getLastName().equals(lastName)) {
                employeeIterator.remove();
                return employees;
            }
        }
        throw new EmployeeNotFoundException("сотрудник не найден");
    }

    public Employee findPersons(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("такой сотрудник не найден");
    }

    public Collection<Employee> getEmployees() {
        return employees;
    }

    public String getPerson(Integer number) {
        final Employee employee;
        employee = employees.get(number);
        final String personList = " " + employee.getFirstName() + " " + employee.getLastName();
        return personList;
    }

}


