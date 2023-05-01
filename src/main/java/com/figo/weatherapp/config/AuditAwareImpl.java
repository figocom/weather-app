package com.figo.weatherapp.config;

import com.figo.weatherapp.payload.UserDTO;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

import static com.figo.weatherapp.utils.CommonUtils.getUserDTOFromRequestForAuditing;


public class AuditAwareImpl implements AuditorAware<UUID> {

    @Override
    public Optional<UUID> getCurrentAuditor() {
        UserDTO userDTOFromRequest = getUserDTOFromRequestForAuditing();
        if (userDTOFromRequest != null) {
            return Optional.of(userDTOFromRequest.getId());
        }
        return Optional.empty();
    }
}
