package com.eyadalalimi.medcurriculum.data.dto.auth;

public class RegisterRequest {
    public String name;
    public String email;
    public String password;
    public String password_confirmation;
    public String phone;
    public String student_number;
    public String country;
    public Long university_id;
    public Long college_id;
    public Long major_id;

    public RegisterRequest(String name, String email, String password, String phone,
                           String studentNumber, String country, Long universityId, Long collegeId, Long majorId) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.password_confirmation = password;
        this.phone = phone;
        this.student_number = studentNumber;
        this.country = country;
        this.university_id = universityId;
        this.college_id = collegeId;
        this.major_id = majorId;
    }
}
