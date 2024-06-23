package by.modsen.practice.group11.mapper;

import by.modsen.practice.group11.model.dto.request.PersonalInfoRequest;
import by.modsen.practice.group11.model.dto.response.PersonalInfoResponse;
import by.modsen.practice.group11.model.entity.PersonalInfo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonalInfoMapper {
    PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo);
    PersonalInfo toPersonalInfo(PersonalInfoRequest personalInfoRequest);
}