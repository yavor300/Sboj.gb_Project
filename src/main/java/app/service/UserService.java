package app.service;

import app.domain.models.service.UserServiceModel;

public interface UserService {
    void save(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);
}
