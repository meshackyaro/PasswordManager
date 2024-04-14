package com.myworkspace.passwordManager.services;

import com.myworkspace.passwordManager.data.model.Password;
import com.myworkspace.passwordManager.data.repositories.PasswordRepository;
import com.myworkspace.passwordManager.dtos.requests.AddPasswordRequest;
import com.myworkspace.passwordManager.dtos.requests.DeleteRequest;
import com.myworkspace.passwordManager.dtos.requests.UpdatePasswordRequest;
import com.myworkspace.passwordManager.dtos.responses.AddPasswordResponse;
import com.myworkspace.passwordManager.dtos.responses.DeleteResponse;
import com.myworkspace.passwordManager.dtos.responses.UpdatePasswordResponse;
import com.myworkspace.passwordManager.exceptions.PasswordNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordServiceImpl implements PasswordService {
@Autowired
    private PasswordRepository passwordRepository;

    @Override
    public long count() {
        return passwordRepository.count();
    }

    @Override
    public AddPasswordResponse addPassword(AddPasswordRequest addPasswordRequest) {

        Password password = new Password();
        password.setWebsite(addPasswordRequest.getWebsite());
        password.setUsername(addPasswordRequest.getUsername());
        password.setPassword(addPasswordRequest.getPassword());
        passwordRepository.save(password);

        AddPasswordResponse addPasswordResponse = new AddPasswordResponse();
        addPasswordResponse.setWebsite(addPasswordRequest.getWebsite());
        addPasswordResponse.setUsername(addPasswordRequest.getUsername());
        addPasswordResponse.setPassword(addPasswordRequest.getPassword());
        return addPasswordResponse;
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        Password foundPassword = findByWebsite(updatePasswordRequest.getWebsite());
        if(foundPassword == null) throw new PasswordNotFoundException("Not found");
        foundPassword.setWebsite(updatePasswordRequest.getWebsite());
        foundPassword.setUsername(updatePasswordRequest.getUsername());
        foundPassword.setPassword(updatePasswordRequest.getNewPassword());
        passwordRepository.save(foundPassword);

        UpdatePasswordResponse updatePasswordResponse = new UpdatePasswordResponse();
        updatePasswordResponse.setWebsite(updatePasswordRequest.getWebsite());
        updatePasswordResponse.setUsername(updatePasswordRequest.getUsername());
        updatePasswordResponse.setPassword(updatePasswordRequest.getNewPassword());
        updatePasswordResponse.setMessage("Password updated successfully");
        return updatePasswordResponse;
    }

    @Override
    public Password findByWebsite(String website) {
        return passwordRepository.findByWebsite(website);
    }

    @Override
    public Password findByUsername(String username) {
        return passwordRepository.findByUsername(username);
    }

    @Override
    public DeleteResponse deletePassword(DeleteRequest deleteRequest) {
        Password foundPassword = findByUsername(deleteRequest.getUsername());
        if (foundPassword == null) throw new PasswordNotFoundException("Not found");
        passwordRepository.delete(foundPassword);

        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setWebsite(deleteRequest.getWebsite());
        deleteResponse.setUsername(deleteRequest.getUsername());
        deleteResponse.setMessage("Password deleted successfully");
        return deleteResponse;
    }


}
