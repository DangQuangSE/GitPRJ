package dto;

import java.sql.Date;

public class StartUpProject {

    private int projectId;
    private String projectName;
    private String description;
    private String status;
    private Date estimated_launch;

    public StartUpProject() {
    }

    public StartUpProject(int projectId, String projectName, String description, String status, Date estimated_launch) {
        this.projectId = projectId;
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

    public Date getEstimated_launch() {
        return estimated_launch;
    }

    public void setEstimated_launch(Date estimated_launch) {
        this.estimated_launch = estimated_launch;
    }

    @Override
    public String toString() {
        return "StartUpProject{" + "projectId=" + projectId + ", projectName=" + projectName + ", description=" + description + ", status=" + status + ", estimated_launch=" + estimated_launch + '}';
    }

}
