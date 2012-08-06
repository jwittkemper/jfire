package biz.wittkemper.jfire.forms.feinsatzliste;

public class EinsatzBlatt {
	String text;
	int startwert;

	public EinsatzBlatt(String text, int startwert) {
		super();
		this.text = text;
		this.startwert = startwert;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getStartwert() {
		return startwert;
	}

	public void setStartwert(int startwert) {
		this.startwert = startwert;
	}

	@Override
	public String toString() {
		return text;
	}

}
