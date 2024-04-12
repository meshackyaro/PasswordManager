package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.Password;
import com.myworkspace.passwordManager.dtos.requests.AddPasswordRequest;
import com.myworkspace.passwordManager.dtos.requests.DeleteRequest;
import com.myworkspace.passwordManager.dtos.requests.UpdatePasswordRequest;
import com.myworkspace.passwordManager.dtos.responses.AddPasswordResponse;
import com.myworkspace.passwordManager.dtos.responses.DeleteResponse;
import com.myworkspace.passwordManager.dtos.responses.UpdatePasswordResponse;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    long count();

    AddPasswordResponse addPassword(AddPasswordRequest addPasswordrequest);

    UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest);

    Password findByWebsite(String website);

    Password findByUsername(String username);

    DeleteResponse deletePassword(DeleteRequest deleteRequest);
}
