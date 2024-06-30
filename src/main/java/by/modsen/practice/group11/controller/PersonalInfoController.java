package by.modsen.practice.group11.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/personalInfo")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "PersonalInfo Controller")
public class PersonalInfoController {

}
