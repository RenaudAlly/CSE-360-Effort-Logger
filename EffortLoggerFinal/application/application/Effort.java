package application;


public class Effort {
	private String timeLogged, projectName, lifeCycle, effortCategory, plan, startTime, stopTime;
	private String[] effort;
	private String effortName;
	
	Effort() {
		setEffortName("Default");
		timeLogged = "0000-00-00";
		projectName = "Default";
		lifeCycle = "Default";
		effortCategory = "Default";
		plan = "Default";
		startTime = "00:00:00";
		stopTime = "00:00:00";
	}
	
	Effort(String effortName, String timeLogged, String projectName, String lifeCycle, String effortCategory, String plan, String startTime, String stopTime) {
		this.setEffortName(effortName);
		this.timeLogged = timeLogged;
		this.projectName = projectName;
		this.lifeCycle = lifeCycle;
		this.effortCategory = effortCategory;
		this.plan = plan;
		this.startTime = startTime;
		this.stopTime = stopTime;
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
		if (projectName.isEmpty()) {
			this.projectName = "Default";
		} else {
			this.projectName = projectName;
		}
	}

	public String getLifeCycle() {
		return lifeCycle;
	}

	public void setLifeCycle(String lifeCycle) {
		if (lifeCycle.isEmpty()) {
			this.lifeCycle = "Default";
		} else {
			this.lifeCycle = lifeCycle;
		}
	}

	public String getEffortCategory() {
		return effortCategory;
	}

	public void setEffortCategory(String effortCategory) {
		if (effortCategory.isEmpty()) {
			this.effortCategory = "Default";
		} else {
			this.effortCategory = effortCategory;
		}
	}

	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

    public String[] getArray() {

        effort = new String[]{effortName, timeLogged, projectName, lifeCycle, effortCategory, plan, startTime, stopTime};
        return effort;

    }

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getEffortName() {
		return effortName;
	}

	public void setEffortName(String effortName) {
		if (effortName.isEmpty()) {
			this.effortName = "Default";
		} else {
			this.effortName = effortName;
		}
	}
}
