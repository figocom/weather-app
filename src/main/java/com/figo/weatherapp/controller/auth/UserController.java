package com.figo.weatherapp.controller.auth;


import com.figo.weatherapp.net.ApiResult;
import com.figo.weatherapp.payload.ResetPasswordDTO;
import com.figo.weatherapp.payload.UserDTO;
import com.figo.weatherapp.payload.UserEditDTO;
import com.figo.weatherapp.utils.AppConstant;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserNING MALUMOTLARINI TAHRIRLAMOQCHI VA O'CHIRMOQCHI BO'LSA HAMDA <p>
 * TIZIMDAGI USER O'ZINING MALUMOTLARINI VA PAROLINI TAHRIRLAMOQCHI BO'LSA, VA<p>
 * O'ZI TO'G'RISIDAGI MA'LUMOTLARNI
 */
@RequestMapping(path = UserController.USER_CONTROLLER_PATH)
public interface UserController {

    String USER_CONTROLLER_PATH = AppConstant.BASE_PATH_V1 + "user/";

    String USER_APPLICATION_ME_PATH = "application/me";
    String LOGOUT_PATH = "logout";
    String CHANGE_ACTIVE_LANGUAGE_PATH = "change-language";
    String EDIT_USER_PASSWORD_PATH = "edit-user-password";
    String EDIT_USER_SELF_PATH_PATH = "edit-self";

    /**
     * TIZIMDAGI (YA`NI SECURITY_CONTEXTDAGI) USER O`ZINING MALUMOTLARINI OLISHI
     *
     * @return
     */
    @GetMapping(path = USER_APPLICATION_ME_PATH)
    ApiResult<UserDTO> getApplicationUserMe();


    /**
     * USER O'ZINI EDIT QILSA SHU YO'LGA KELADI
     */

    @PutMapping(EDIT_USER_SELF_PATH_PATH)
    ApiResult<String> editUserSelf(@RequestBody UserEditDTO userEditDTO);

    /**
     * USER O'ZINI PAROLINI EDIT QILSA SHU YO'LGA KELADI
     */
    @PutMapping(EDIT_USER_PASSWORD_PATH)
    ApiResult<String> editUserPassword(@RequestBody ResetPasswordDTO passwordEditDTO);



    /**
     * TIZIMDAGI (YA`NI SECURITY_CONTEXTDAGI) USER TIZIMDAN CHIQISH QILGANDA, UNING TOKENINI HAM QAYTA FOYDALANILMAYDIGAN QILISH UCHUN YO'L
     *
     * @return {@link ApiResult}<{@link Boolean}>
     */
    @GetMapping(path = LOGOUT_PATH)
    ApiResult<Boolean> logOut();

    /**
     * TIZIMDAGI (YA`NI SECURITY_CONTEXTDAGI) USER ACTIVE TURGAN TILNI O'ZGARTIRGANDA YO'L
     *
     * @return {@link ApiResult}<{@link Boolean}>
     */
    @GetMapping(path = CHANGE_ACTIVE_LANGUAGE_PATH)
    void changeActiveLanguage();

}
