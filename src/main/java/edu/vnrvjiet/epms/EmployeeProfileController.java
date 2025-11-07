package edu.vnrvjiet.epms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/epms")
public class EmployeeProfileController {

    @Autowired
    private EmployeeProfileService employeeProfileService;

    // ✅ Health check endpoint
    @GetMapping("/")
    public String home() {
        return "✅ Employee Profile Management Service is running!";
    }

    // ✅ Create a new employee
    @PostMapping("/addOne")
    public ResponseEntity<EmployeeProfile> addOneEmployeeProfile(@RequestBody EmployeeProfile employeeProfile) {
        EmployeeProfile savedProfile = employeeProfileService.save(employeeProfile);
        return new ResponseEntity<>(savedProfile, HttpStatus.CREATED);
    }

    // ✅ Get all employees
    @GetMapping("/findAll")
    public ResponseEntity<List<EmployeeProfile>> findAllEmployeeProfiles() {
        List<EmployeeProfile> list = employeeProfileService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // ✅ Get by ID (use RequestParam)
    @GetMapping("/findById")
    public ResponseEntity<EmployeeProfile> findEmployeeProfileById(@RequestParam Integer employeeProfileId) {
        var employeeProfile = employeeProfileService.findById(employeeProfileId);
        return employeeProfile
                .map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ✅ Delete by ID
    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteEmployeeProfileById(@RequestParam Integer employeeProfileId) {
        employeeProfileService.deleteById(employeeProfileId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // ✅ Update
    @PutMapping("/update")
    public ResponseEntity<EmployeeProfile> updateEmployeeProfile(@RequestBody EmployeeProfile employeeProfile) {
        EmployeeProfile updatedProfile = employeeProfileService.save(employeeProfile);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }
}
