package logic.entity;

public class CheckedSection extends Section{
	private Boolean checked;
	public CheckedSection(String id, TimeBlock[] times, String course, int openSpots) {
		super(id, times, course, openSpots);
		checked = false;
	}
	public CheckedSection(Section s, boolean checked) {
		super(s.getID(), s.getTimes(), s.getCourseName(), s.getOpenSpots());
		this.checked = checked;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
}
