package app.web.beans;

import app.domain.models.binding.UserLoginBinding;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean {
    private UserLoginBinding userLoginBinding;
    private UserService userService;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        this.userLoginBinding = new UserLoginBinding();
    }

    public void login() {
        UserServiceModel user = this.userService.findByUsernameAndPassword(
                this.userLoginBinding.getUsername(),
                DigestUtils.sha256Hex(this.userLoginBinding.getPassword())
        );

        if (user == null) {
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap();
        sessionMap.put("username", user.getUsername());
        this.redirect("/home");
    }

    public UserLoginBinding getUserLoginBinding() {
        return userLoginBinding;
    }

    public void setUserLoginBinding(UserLoginBinding userLoginBinding) {
        this.userLoginBinding = userLoginBinding;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
