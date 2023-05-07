package com.figo.weatherapp.service.admin;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserDetailDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.repository.AuthUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
@Service
@Slf4j
public class AdminServiceImpl implements AdminService{
    private final AuthUserRepository authUserRepository;

    public AdminServiceImpl(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    /**
     * The getUsers function returns a Flux of ApiResult&lt;List&lt;UserDTO&gt;&gt;.
     * The function first calls the findAll() method on the authUserRepository, which returns a Flux of AuthUsers.
     * Then, we map each AuthUser to its corresponding UserDTO using the builder pattern and return it as part of an ApiResult object.
     * Finally, we buffer all UserDTOs into one list and wrap them in another ApiResult object before returning them to our client application.

     * @return A flux of apiresult&lt;list&lt;userdto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Flux<ApiResult<List<UserDTO>>> getUsers() {

        return authUserRepository.findAll().map(authUser ->
                        UserDTO.builder().
                                id(authUser.getId()).
                                firstName(authUser.getFirstName())
                                .username(authUser.getUsername())
                                .lastName(authUser.getLastName())
                                .role(authUser.getRole())
                                .enabled(authUser.isEnabled())
                                .build())
                .buffer().map(ApiResult::successResponse);
    }

    /**
     * The getUserDetails function is a function that takes in an id and returns the user details of the user with that id.
     * @param id id Get the user details from the database
     *
     * @return A mono&lt;apiresult&lt;userdetaildto&gt;&gt;
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDetailDTO>> getUserDetails(String id) {

        return authUserRepository.findById(Integer.valueOf(id)).map(authUser ->
                UserDetailDTO.builder().
                        id(authUser.getId()).
                        firstName(authUser.getFirstName())
                        .username(authUser.getUsername())
                        .lastName(authUser.getLastName())
                        .role(authUser.getRole())
                        .enabled(authUser.isEnabled())
                        .accountNonExpired(authUser.isAccountNonExpired())
                        .accountNonLocked(authUser.isAccountNonLocked())
                        .credentialsNonExpired(authUser.isCredentialsNonExpired())
                        .build()).map(ApiResult::successResponse);
    }

    /**
     * The editUser function is used to edit a user's information.
     * @param userEditByAdminDTO userEditByAdminDTO Get the user's id, role, enabled status and account non-expired status
     * @return A mono&lt;apiresult&lt;userdto&gt;&gt;
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDTO>> editUser(UserEditByAdminDTO userEditByAdminDTO) {

        return authUserRepository.findById(Integer.valueOf(userEditByAdminDTO.getId())).flatMap(authUser -> {
            authUser.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
            authUser.setRole(userEditByAdminDTO.getRole());
            authUser.setEnabled(userEditByAdminDTO.isEnabled());
            authUser.setAccountNonExpired(userEditByAdminDTO.isAccountNonExpired());
            authUser.setAccountNonLocked(userEditByAdminDTO.isAccountNonLocked());
            authUser.setCredentialsNonExpired(userEditByAdminDTO.isCredentialsNonExpired());
            return authUserRepository.save(authUser);
        }).map(authUser ->
                UserDTO.builder().
                        id(authUser.getId()).
                        firstName(authUser.getFirstName())
                        .username(authUser.getUsername())
                        .lastName(authUser.getLastName())
                        .role(authUser.getRole())
                        .enabled(authUser.isEnabled())
                        .build()).map(ApiResult::successResponse);
    }
}
