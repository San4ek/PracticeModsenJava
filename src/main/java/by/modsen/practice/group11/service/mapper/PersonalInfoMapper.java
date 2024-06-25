package by.modsen.practice.group11.service.mapper;

import by.modsen.practice.group11.model.dto.request.PersonalInfoRequest;
import by.modsen.practice.group11.model.dto.response.PersonalInfoResponse;
import by.modsen.practice.group11.model.entity.PersonalInfo;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PersonalInfoMapper {

    PersonalInfoResponse toPersonalInfoResponse(PersonalInfo personalInfo);

    List<PersonalInfoResponse> toPersonalInfoResponseList(List<PersonalInfo> personalInfoList);

    @Mapping(target = "id", ignore = true)
    PersonalInfo toPersonalInfo(PersonalInfoRequest personalInfoRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    PersonalInfo partialUpdate(PersonalInfoRequest personalInfoRequest, @MappingTarget PersonalInfo personalInfo);
}
