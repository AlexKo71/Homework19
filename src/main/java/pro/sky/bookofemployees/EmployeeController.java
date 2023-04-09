package pro.sky.bookofemployees;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public ResponseEntity<Employee> addPerson(@RequestParam(value = "firstName", required = false) String firstName,
                                              @RequestParam(value = "lastName", required = false) String lastName) {
        try {
            return ResponseEntity.ok(service.addPersons(firstName, lastName));
        } catch (EmployeeStorageIsFullException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/remove")
    public Employee removePerson(@RequestParam(value = "firstName", required = false) String firstName,
                                 @RequestParam(value = "lastName", required = false) String lastName) {
      return (Employee) service.removePersons(firstName, lastName);

    }

    @GetMapping("/find")
    public Employee findPersons(@RequestParam(value = "firstName", required = false) String firstName,
                                @RequestParam(value = "lastName", required = false) String lastName) {
        return service.findPersons(firstName, lastName);
    }

    @GetMapping("/print")
    public Collection<Employee> allPrint() {
        return service.getEmployees();
    }

    @GetMapping("/person")
    public String getPerson(@RequestParam("number") Integer number) {
        try {
            return service.getPerson(number);
        } catch (RuntimeException e) {
            return "Попробуйте другой номер";
        }

    }
}

