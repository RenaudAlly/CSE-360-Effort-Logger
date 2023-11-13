
public class Project {
	private String projectName, lifeCycle, plan, effortCategory, timeLogged;
	
	Project() {
		projectName = "Default Project";
		lifeCycle = "Default Life Cycle";
		plan = "Default Plan";
		effortCategory = "Default Effort Category";
		timeLogged = "0000-00-00";
	}
	
	Project(String projectName, String lifeCycle, String plan, String effortCategory) {
		this.projectName = projectName;
		this.lifeCycle = lifeCycle;
		this.plan = plan;
		this.effortCategory = effortCategory;
	}
	
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String name) {
		this.projectName = name;
	}
	
	public String getLifeCycle() {
		return lifeCycle;
	}
	
	public void setLifeCycle(String lifeCycle) {
		this.lifeCycle = lifeCycle;
	}
	
	public String getPlan() {
		return plan;
	}
	
	public void setPlan(String plan) {
		this.plan = plan;
	}
	
	public String getEffortCategory() {
		return effortCategory;
	}
	
	public void setEffortCategory(String effortCategory) {
		this.effortCategory = effortCategory;
	}

	public String getTimeLogged() {
		return timeLogged;
	}

	public void setTimeLogged(String timeLogged) {
		this.timeLogged = timeLogged;
	}
}
