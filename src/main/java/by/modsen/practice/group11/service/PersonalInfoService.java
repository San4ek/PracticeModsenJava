package by.modsen.practice.group11.service;

import by.modsen.practice.group11.model.dto.request.PersonalInfoRequest;
import by.modsen.practice.group11.model.dto.response.PersonalInfoResponse;

import java.util.List;
import java.util.UUID;

public interface PersonalInfoService {
//    PersonalInfoResponse getPersonalInfoById(UUID personalInfoId);

    List<PersonalInfoResponse> getAllPersonalInfo();

//    PersonalInfoResponse createPersonalInfo(PersonalInfoRequest personalInfoRequest);

    PersonalInfoResponse updatePersonalInfo(UUID personalInfoId, PersonalInfoRequest personalInfoServiceRequest);

//    void deletePersonalInfo(UUID personalInfoId);
}
