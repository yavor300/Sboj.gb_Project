package app.web.beans;

import app.domain.models.binding.UserRegisterBinding;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserRegisterBinding userRegisterBinding;
    private UserService userService;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.userRegisterBinding = new UserRegisterBinding();
    }

    public void register() {
        if (!this.userRegisterBinding.getPassword().equals(this.userRegisterBinding.getConfirmPassword())) {
            return;
        }

        userRegisterBinding.setPassword(DigestUtils.sha256Hex(userRegisterBinding.getPassword()));

        this.userService.save(this.modelMapper.map(this.userRegisterBinding, UserServiceModel.class));

        this.redirect("/login");
    }

    public UserRegisterBinding getUserRegisterBinding() {
        return userRegisterBinding;
    }

    public void setUserRegisterBinding(UserRegisterBinding userRegisterBinding) {
        this.userRegisterBinding = userRegisterBinding;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
