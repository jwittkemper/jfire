package biz.wittkemper.jfire.data.entity;


public class Report {
	Integer id;
	String name;
	String titel;
	boolean viewOnly;
	String filename;
	String sqlWhere;
	
	public Report(){};
	
	
	public Report(String name, String titel, boolean viewOnly, String filename, String sqlWhere) {
		super();
		this.name = name;
		this.titel = titel;
		this.viewOnly = viewOnly;
		this.filename = filename;
		this.sqlWhere = sqlWhere;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public boolean isViewOnly() {
		return viewOnly;
	}
	public void setViewOnly(boolean viewOnly) {
		this.viewOnly = viewOnly;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getSqlWhere() {
		return sqlWhere;
	}


	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

}
