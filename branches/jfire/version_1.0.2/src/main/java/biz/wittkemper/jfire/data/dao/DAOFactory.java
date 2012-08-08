package biz.wittkemper.jfire.data.dao;


public class DAOFactory {

	private static DAOFactory daoFactory;

	MitgliedDAO mitgliedDAO = new MitgliedDAOImpl();
	MitgliedStatusDAO mitgliedStatusDAO = new MitgliedStatusDAOImpl();
	FoerderMitgliedDAO foerderMitgliedDAO = new FoerderMitgliedDAOImpl();
	ParameterDAO parameterDAO = new ParameterDAOImpl();
	AnredeDAO anredeDAO = new AnredeDAOImpl();

	private DAOFactory() {
	}

	static {
		daoFactory = new DAOFactory();
	}

	public static DAOFactory getInstance() {
		return daoFactory;
	}

	public MitgliedDAO getMitgliedDAO() {
		return mitgliedDAO;
	}

	public void setMitgliedDAO(MitgliedDAO mitgliedDAO) {
		this.mitgliedDAO = mitgliedDAO;
	}

	public MitgliedStatusDAO getMitgliedStatusDAO() {
		return mitgliedStatusDAO;
	}

	public void setMitgliedStatusDAO(MitgliedStatusDAO mitgliedStatusDAO) {
		this.mitgliedStatusDAO = mitgliedStatusDAO;
	}

	public FoerderMitgliedDAO getFoerderMitgliedDAO() {
		return foerderMitgliedDAO;
	}

	public void setFoerderMitgliedDAO(FoerderMitgliedDAO foerderMitgliedDAO) {
		this.foerderMitgliedDAO = foerderMitgliedDAO;
	}

	public ParameterDAO getParameterDAO() {
		return parameterDAO;
	}

	public void setParameterDAO(ParameterDAO parameterDAO) {
		this.parameterDAO = parameterDAO;
	}

	public AnredeDAO getAnredeDAO() {
		return anredeDAO;
	}

	public void setAnredeDAO(AnredeDAO anredeDAO) {
		this.anredeDAO = anredeDAO;
	}

}
