package app.web.beans;

import app.domain.models.view.JobViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class DetailsBean {

    private ModelMapper modelMapper;
    private JobApplicationService jobApplicationService;

    public DetailsBean() {
    }

    @Inject
    public DetailsBean(ModelMapper modelMapper, JobApplicationService jobApplicationService) {
        this.modelMapper = modelMapper;
        this.jobApplicationService = jobApplicationService;
    }

    public JobViewModel getJobById(String id) {
        return this.modelMapper.map(
                this.jobApplicationService.getById(id), JobViewModel.class
        );
    }
}
