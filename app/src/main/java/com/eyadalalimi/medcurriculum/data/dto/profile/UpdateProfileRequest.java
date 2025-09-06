package com.eyadalalimi.medcurriculum.data.dto.profile;

public class UpdateProfileRequest {
    public String name;
    public String phone;
    public String country;
    public Long university_id;
    public Long college_id;
    public Long major_id;

    public UpdateProfileRequest(String name, String phone, String country,
                                Long universityId, Long collegeId, Long majorId) {
        this.name = name;
        this.phone = phone;
        this.country = country;
        this.university_id = universityId;
        this.college_id = collegeId;
        this.major_id = majorId;
    }
}
