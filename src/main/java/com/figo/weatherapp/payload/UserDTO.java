package com.figo.weatherapp.payload;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.figo.weatherapp.enums.PermissionEnum;
import com.figo.weatherapp.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Murtozayev Manguberdi
 * 01.05.2023
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {
    private UUID id;


    private String firstName;


    private String lastName;

    private String fullName;

    private String phoneNumber;

    //USERNING PERMISSIONLAR RO`YXATI
    @JsonIgnore
    private List<String> permissions;

    private boolean enabled;

    //rasm yuklash
    private String photoUrl = CommonUtils.urlBuilder();

    private String photoId;
    private boolean isAdmin;

    private Date birthDate;

    public UserDTO(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDTO(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }




    public boolean haveAccess(PermissionEnum permission) {
        return permissions != null && permissions.contains(permission.name());
    }

    public static Map<UUID, String> userToIdNameMap(List<UserDTO> userDTOList) {
        return userDTOList.stream().collect(Collectors.toMap(UserDTO::getId, UserDTO::getFullName));
    }

}
