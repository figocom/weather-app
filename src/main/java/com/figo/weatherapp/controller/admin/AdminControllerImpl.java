package com.figo.weatherapp.controller.admin;

import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserDetailDTO;
import com.figo.weatherapp.payload.UserEditByAdminDTO;
import com.figo.weatherapp.service.admin.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
@RestController
@Slf4j
public class AdminControllerImpl implements AdminController{

    private final AdminService adminService;

    /**
     * The AdminControllerImpl function is a RESTful API that allows the user to perform CRUD operations on the Admin table.
     * The function takes in an HTTP request and returns an HTTP response.
     *
     *
     * @param adminService adminService Call the adminserviceimpl class
     *
     * @return A new instance of admincontrollerimpl
     *
     * @docauthor Manguberdi
     */
    public AdminControllerImpl(AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * The getUsers function is a reactive function that returns a Flux of ApiResult&lt;List&lt;UserDTO&gt;&gt;.
     * The getUsers function calls the adminService's getUsers() method, which returns an ApiResult&lt;List&lt;UserDTO&gt;&gt;.
     * This means that the adminService's getUsers() method will return either:
     * 1) A successful result containing a List of UserDTOs (ApiResult.success(userDTOList)) or
     * 2) An unsuccessful result containing an error message (ApiResult.error(errorMessage)).
     *
     * @return A flux&lt;apiresult&lt;list&lt;userdto&gt;&gt;&gt;
     *
     * {@code @docauthor} Manguberdi
     */
    @Override
    public Flux<ApiResult<List<UserDTO>>> getUsers() {
        return adminService.getUsers();
    }

    /**
     * The getUserDetails function is used to retrieve the details of a user.
     *
     * @param id id Get the user details of a specific user
     *
     * @return An apiresult&lt;userdetaildto&gt; object
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDetailDTO>> getUserDetails(String id) {
        return adminService.getUserDetails(id);
    }

    /**
     * The editUser function is used to edit a user's information.
     *
     *
     * @param @Valid UserEditByAdminDTO Pass the usereditbyadmindto object to the edituser function
     *
     * @return An apiresult&lt;userdto&gt; object
     *
     * @docauthor Manguberdi
     */
    @Override
    public Mono<ApiResult<UserDTO>> editUser(@Valid UserEditByAdminDTO userEditByAdminDTO) {
        return adminService.editUser(userEditByAdminDTO);
    }
}
