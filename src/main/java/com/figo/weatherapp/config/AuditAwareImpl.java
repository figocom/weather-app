package com.figo.weatherapp.config;

import com.figo.weatherapp.payload.UserDTO;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditAwareImpl implements AuditorAware<Integer> {

    @Override
    public Optional<Integer> getCurrentAuditor() {

        UserDTO userDTOFromRequest = new UserDTO();
        if (userDTOFromRequest != null) {
            return Optional.of(userDTOFromRequest.getId());
        }
        return Optional.empty();
    }

   
}
