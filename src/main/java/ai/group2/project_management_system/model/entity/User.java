package ai.group2.project_management_system.model.entity;

import ai.group2.project_management_system.model.Enum.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id") // adjust the column name accordingly
    private Department department;

    private String education;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    private String gender;
    private String email;
    private String address;
    private String phone;
    private String profilePictureFileName; // Add this field to store the file name

    @JsonProperty("isActive")
    private boolean isActive;

    private String password;


    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Transient
    private MultipartFile file;

    @ManyToMany(mappedBy = "users")
    private Set<Project> projects;

    @OneToMany(mappedBy = "user")
    private Set<AssignIssue> assignIssues;


    public String getProfilePictureFileName() {
        return profilePictureFileName;
    }

    public void setProfilePictureFileName(String profilePictureFileName) {
        this.profilePictureFileName = profilePictureFileName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<AssignIssue> getAssignIssues() {
        return assignIssues;
    }

    public void setAssignIssues(Set<AssignIssue> assignIssues) {
        this.assignIssues = assignIssues;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                ", Education='" + education + '\'' +
                ", dob=" + dob +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", photo='" + profilePictureFileName + '\'' +
                ", isActive=" +isActive +
                ", password='" + password + '\'' +
                ", file=" + file +
                '}';
    }
}
