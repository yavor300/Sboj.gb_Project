package app.web.beans;

import app.domain.models.view.JobViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DeleteBean extends BaseBean {
    private JobApplicationService jobApplicationService;

    public DeleteBean() {
    }

    @Inject
    public DeleteBean(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public void delete(String id) {
        this.jobApplicationService.delete(id);

        this.redirect("/home");
    }
}
