package dto;

import java.time.LocalDate;

public class StartUpProjectDTO {

    private int projectId;
    private String projectName;
    private String description;
    private String status;
    private LocalDate estimated_launch;

    public StartUpProjectDTO() {
        this.projectId = 0;
        this.projectName = "";
        this.description = "";
        this.status = "";
        this.estimated_launch = null;
    }

    public StartUpProjectDTO(int projectId, String projectName, String description, String status, LocalDate estimated_launch) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimated_launch = estimated_launch;
    }

    public StartUpProjectDTO(String projectName, String description, String status, LocalDate estimated_launch) {
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.estimated_launch = estimated_launch;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEstimated_launch() {
        return estimated_launch;
    }

    public void setEstimated_launch(LocalDate estimated_launch) {
        this.estimated_launch = estimated_launch;
    }

    @Override
    public String toString() {
        return "StartUpProject{" + "projectId=" + projectId + ", projectName=" + projectName + ", description=" + description + ", status=" + status + ", estimated_launch=" + estimated_launch + '}';
    }

}
