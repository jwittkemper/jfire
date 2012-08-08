package biz.wittkemper.jfire.data.dao;

import java.util.List;

import biz.wittkemper.jfire.data.entity.FoerderMitglied;
import biz.wittkemper.jfire.data.entity.Mitglied;

public class FoerderMitgliedDAOImpl extends
		AbstractDAOImpl<FoerderMitglied, Long> implements FoerderMitgliedDAO {

	@Override
	protected Class<FoerderMitglied> getDomainClass() {
		return FoerderMitglied.class;
	}

	@Override
	public int getAll() {
		List<FoerderMitglied> list = getAllList(); 

		if (list.size() > 0) {
			return list.size();
		}
		return 0;
	}
	public List<FoerderMitglied> getAllList(){
		String hsql = "FROM FoerderMitglied f where f.mitglied.geloescht =0 ";
		List<FoerderMitglied> list = super.findByQueryString(hsql);
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
