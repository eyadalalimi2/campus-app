package com.eyadalalimi.medcurriculum.domain.model;

public class User {
    public long id;
    public String student_number;
    public String name;
    public String email;
    public String phone;
    public String country;
    public String profile_photo_path;
    public String profile_photo_url; // قد يكون null أو يعيده السيرفر
    public Long university_id;
    public Long college_id;
    public Long major_id;
    public Integer level;
    public String gender;
    public String status;
    public String email_verified_at;
    public String created_at;
    public String updated_at;
}
