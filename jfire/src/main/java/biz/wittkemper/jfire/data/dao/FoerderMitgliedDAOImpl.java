package biz.wittkemper.jfire.data.dao;

import java.util.List;

import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import javax.persistence.TypedQuery;
import org.hibernate.Session;

public class FoerderMitgliedDAOImpl extends
		AbstractDAOImpl<FoerderMitglied, Long> implements FoerderMitgliedDAO {

	@Override
	protected Class<FoerderMitglied> getDomainClass() {
		return FoerderMitglied.class;
	}

	@Override
	public int getAll() {
		List<FoerderMitglied> list = getAllList(); 

		if (!list.isEmpty()) {
			return list.size();
		}
		return 0;
	}
        @Override
	public List<FoerderMitglied> getAllList(){
            
            Session session = this.getSession();
            String hsql = "FROM FoerderMitglied f where f.mitglied.geloescht = :trueValue ";
            
            TypedQuery<FoerderMitglied> query = session.createQuery(hsql);
            query.setParameter("trueValue", false);
            
            List<FoerderMitglied> list = query.getResultList();
            
            return list;
	}

	@Override
	public boolean EintragDa(Long id) {
            FoerderMitglied fm = super.load(id);
            if (fm!=null){
		return true;
            }
            return false;
	}

}
