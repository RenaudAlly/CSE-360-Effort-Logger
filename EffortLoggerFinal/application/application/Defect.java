package application;

public class Defect {
	
	private String name;
	private String category;
	private String inject;
	private String remove;
	private String status;
	private String symptoms;
	private String project;
	private String[] defect;
	
	public String[] getArray() {
		
        defect = new String[]{name, category, inject, remove, status, symptoms, project};
        
        return defect;
        
    }
	
	public Defect() {
		
		this.name = "Default";
		this.category = "Not Specified";
		this.inject = "Planning";
		this.remove = "Planning";
		this.status = "Default";
		this.symptoms = "Default";
		this.project = "Default";
		
	}
	
	public Defect(String name, String category, String inject, String remove, String status, String symptoms, String project) {
		
		this.name = name;
		this.category = category;
		this.inject = inject;
		this.remove = remove;
		this.status = status;
		this.symptoms = symptoms;
		this.project = project;
		
	}
	
	public void setName(String s) {
		if (s.isEmpty()) {
			this.name = "Default";
		} else {
			this.name = s;	
		}
		
	}
	public void setCategory(String s) {
		if (s.isEmpty()) {
			this.category = "Not Specified";	
		} else {
			this.name = s;
		}
	}
	public void setInject(String s) {
		if (s.isEmpty()) {
			this.inject = "Planning";	
		} else {
			this.inject = s;
		}	
	}
	public void setRemove(String s) {
		if (s.isEmpty()) {
			this.remove = "Planning";	
		} else {
			this.remove = s;
		}	
	}
	public void setStatus(String s) {
		if (s.isEmpty()) {
			this.status = "Default";	
		} else {
			this.status = s;
		}	
	}
	public void setSymptoms(String s) {
		if (s.isEmpty()) {
			this.symptoms = "Default";	
		} else {
			this.symptoms = s;
		}	
	}
	public void setProject(String s) {
		if (s.isEmpty()) {
			this.project = "Default";	
		} else {
			this.project = s;
		}	
	}
	public String getName() {
		
		return name;
		
	}
	public String getCategory() {
		
		return category;
		
	}
	public String getInject() {
	
		return inject;
	
	}
	public String getRemove() {
	
		return remove;
	
	}
	public String getStatus() {
	
		return status;
	
	}
	public String getSymptoms() {
	
		return symptoms;
	
	}
	public String getProject() {
		
		return project;
	
	}
}
