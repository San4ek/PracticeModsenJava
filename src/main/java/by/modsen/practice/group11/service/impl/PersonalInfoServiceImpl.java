package by.modsen.practice.group11.service.impl;

import by.modsen.practice.group11.model.dto.request.PersonalInfoRequest;
import by.modsen.practice.group11.model.dto.response.PersonalInfoResponse;
import by.modsen.practice.group11.model.entity.PersonalInfo;
import by.modsen.practice.group11.repository.PersonalInfoRepository;
import by.modsen.practice.group11.service.PersonalInfoService;
import by.modsen.practice.group11.service.exception.ResourceNotFoundException;
import by.modsen.practice.group11.service.mapper.PersonalInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements PersonalInfoService {

    private final PersonalInfoRepository personalInfoRepository;
    private final PersonalInfoMapper personalInfoMapper;


    @Override
    @Transactional(readOnly = true)
    public PersonalInfoResponse getPersonalInfoById(UUID personalInfoId) {

        return personalInfoMapper.toPersonalInfoResponse(getPersonalInfoOrThrow(personalInfoId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfoResponse> getAllPersonalInfo() {

        return personalInfoMapper.toPersonalInfoResponseList(personalInfoRepository.findAll());
    }

    @Override
    @Transactional
    public PersonalInfoResponse createPersonalInfo(PersonalInfoRequest personalInfoRequest) {

        return personalInfoMapper.toPersonalInfoResponse(personalInfoRepository.save(personalInfoMapper.
                toPersonalInfo(personalInfoRequest)));
    }

    @Override
    @Transactional
    public PersonalInfoResponse updatePersonalInfo(UUID personalInfoId, PersonalInfoRequest personalInfoRequest) {

        return personalInfoMapper.toPersonalInfoResponse(personalInfoRepository.save(personalInfoMapper.
                partialUpdate(personalInfoRequest, getPersonalInfoOrThrow(personalInfoId))));
    }

    @Override
    @Transactional
    public void deletePersonalInfo(UUID personalInfoId) {

        getPersonalInfoOrThrow(personalInfoId);
        personalInfoRepository.deleteById(personalInfoId);
    }

    private PersonalInfo getPersonalInfoOrThrow(UUID personalInfoId) {

        return personalInfoRepository.findById(personalInfoId).orElseThrow(() -> new ResourceNotFoundException(HttpStatus.NOT_FOUND.value() * 100 + 51, "Can't find order by id = " + personalInfoId));
    }
}
