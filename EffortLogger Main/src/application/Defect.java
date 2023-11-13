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
		
		this.name = "";
		this.category = "";
		this.inject = "";
		this.remove = "";
		this.status = "";
		this.symptoms = "";
		this.project = "";
		
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
		
		this.name = s;
		
	}
	public void setCategory(String s) {
		
		this.category = s;
		
	}
	public void setInject(String s) {
	
		this.inject = s;
	
	}
	public void setRemove(String s) {
	
		this.remove = s;
	
	}
	public void setStatus(String s) {
	
		this.status = s;
	
	}
	public void setSymptoms(String s) {
	
		this.symptoms = s;
	
	}
	public void setProject(String s) {
		
		this.project = s;
	
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
