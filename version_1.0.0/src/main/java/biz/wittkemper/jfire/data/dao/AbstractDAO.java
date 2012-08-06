package biz.wittkemper.jfire.data.dao;

import java.util.List;

import org.hibernate.LockMode;

public interface AbstractDAO <DomainObject , KeyTyp> {
	
	public DomainObject load(KeyTyp id);
	public void update (DomainObject object);
	
	public void merge (DomainObject object);
	public void save (DomainObject object);
	public void delete (DomainObject object);
	public List<DomainObject> findByQueryString(String string);
}
