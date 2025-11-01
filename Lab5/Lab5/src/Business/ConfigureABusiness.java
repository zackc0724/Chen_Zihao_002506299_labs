/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Business.Person.Person;
import Business.Person.PersonDirectory;
import Business.Profiles.EmployeeProfile;
import Business.Profiles.StudentProfile;
import Business.Profiles.FacultyProfile;
import Business.UserAccounts.UserAccountDirectory;

/**
 *
 * @author 80424
 */
public class ConfigureABusiness {
    
    public static Business initialize() {
        Business b = new Business("University");

        PersonDirectory pd = b.getPersonDirectory();

        Person adminPerson   = pd.newPerson("System Admin");
        Person studentPerson = pd.newPerson("Student One");
        Person facultyPerson = pd.newPerson("Prof. Ada Faculty");

        EmployeeProfile adminProfile  = b.getEmployeeDirectory().newEmployeeProfile(adminPerson);
        StudentProfile  studentProfile = b.getStudentDirectory().newStudentProfile(studentPerson);
        FacultyProfile  facultyProfile = b.getFacultyDirectory().newFacultyProfile(facultyPerson);

        UserAccountDirectory ua = b.getUserAccountDirectory();
        ua.newUserAccount(adminProfile,  "admin",   "admin");
        ua.newUserAccount(studentProfile,"student", "student");
        ua.newUserAccount(facultyProfile,"faculty", "faculty");

        return b;
    }
    
}
