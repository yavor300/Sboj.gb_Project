package app.web.beans;

import app.domain.entities.Sector;
import app.domain.models.binding.JobCreateBinding;
import app.domain.models.service.JobApplicationServiceModel;
import app.service.JobApplicationService;
import app.service.UserService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class JobCreateBean extends BaseBean {
    private JobCreateBinding jobCreateBinding;
    private JobApplicationService jobApplicationService;
    private ModelMapper modelMapper;

    public JobCreateBean() {
    }

    @Inject
    public JobCreateBean(JobApplicationService jobApplicationService, ModelMapper modelMapper) {
        this.jobApplicationService = jobApplicationService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        this.jobCreateBinding = new JobCreateBinding();
    }

    public void create() {
        JobApplicationServiceModel model = this.modelMapper
                .map(
                        this.jobCreateBinding,
                        JobApplicationServiceModel.class
                );
        Sector sector = null;
        try {
            sector = Sector.valueOf(this.jobCreateBinding.getSector().toUpperCase());
        } catch (Exception e) {
            this.redirect("/add-job");
        }
        model.setSector(sector);
        this.jobApplicationService.save(model);
        this.redirect("/home");
    }

    public JobCreateBinding getJobCreateBinding() {
        return jobCreateBinding;
    }

    public void setJobCreateBinding(JobCreateBinding jobCreateBinding) {
        this.jobCreateBinding = jobCreateBinding;
    }

    public JobApplicationService getJobApplicationService() {
        return jobApplicationService;
    }

    public void setJobApplicationService(JobApplicationService jobApplicationService) {
        this.jobApplicationService = jobApplicationService;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
