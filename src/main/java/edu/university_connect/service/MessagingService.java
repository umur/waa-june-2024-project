package edu.university_connect.service;

import edu.university_connect.model.contract.dto.RoleDto;
import edu.university_connect.model.contract.request.role.RoleCreateRequest;
import edu.university_connect.model.contract.request.role.RoleUpdateRequest;
import edu.university_connect.model.enums.AppStatusCode;
import edu.university_connect.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagingService {
    private final MessageSource messageSource;

    public String getResponseMessage(ServiceException serviceException) {
        return getResponseMessage(serviceException.getStatusCode().getDescription(), serviceException.getArgs());
    }


    public String getResponseMessage(String mainArg,String[] args) {

        String[] arguments = new String[args.length];
        for (int i = 0; i < args.length; i++) {
            arguments[i] = messageSource.getMessage(args[i],
                    new String[]{},
                    LocaleContextHolder.getLocale());
        }

        String[] allArguments = Arrays.copyOf(arguments, arguments.length );

        return messageSource.getMessage(mainArg,
                allArguments,
                LocaleContextHolder.getLocale());
    }

    public String getResponseMessage(AppStatusCode appStatusCode, String[] args) {
        return getResponseMessage(appStatusCode.getDescription(),args);
    }

    public String getResponseMessage(String mainArg) {

        return getResponseMessage(mainArg,new String[]{});
    }

    public String getResponseMessage(AppStatusCode appStatusCode) {

        return getResponseMessage(appStatusCode.getDescription());
    }

    public static interface RoleService {
        Page<RoleDto> getPage(Pageable pageable);
        List<RoleDto> getAll();
        RoleDto getById(Long id);

        RoleDto create(RoleCreateRequest createRequest);

        RoleDto update(Long id, RoleUpdateRequest updateRequest);

        boolean delete(Long id);


    }
}
