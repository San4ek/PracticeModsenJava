package by.modsen.practice.group11.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AccessToken implements Serializable {

    private String id;

    private String token;


}
