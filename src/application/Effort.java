package application;

public class Effort {
    private String timeLogged, projectName, lifeCycle, effortCategory, plan;
    private String[] effort;
    private int effortID;

    Effort() {
        setEffortID(0);
        timeLogged = "0000-00-00";
        projectName = "Default";
        lifeCycle = "Default";
        effortCategory = "Default";
        plan = "Default";
    }

    Effort(int effortID, String timeLogged, String projectName, String lifeCycle, String effortCategory, String plan) {
        this.setEffortID(0);
        this.timeLogged = timeLogged;
        this.projectName = projectName;
        this.lifeCycle = lifeCycle;
        this.effortCategory = effortCategory;
        this.plan = plan;
    }

    public String getTimeLogged() {
        return timeLogged;
    }

    public void setTimeLogged(String timeLogged) {
        this.timeLogged = timeLogged;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLifeCycle() {
        return lifeCycle;
    }

    public void setLifeCycle(String lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    public String getEffortCategory() {
        return effortCategory;
    }

    public void setEffortCategory(String effortCategory) {
        this.effortCategory = effortCategory;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String[] getArray() {

        effort = new String[]{timeLogged, projectName, effortCategory, plan};

        return effort;

    }

    public int getEffortID() {
        return effortID;
    }

    public void setEffortID(int effortID) {
        this.effortID = effortID;
    }
}