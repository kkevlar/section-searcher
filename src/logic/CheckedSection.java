package logic;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class CheckedSection extends Section{
	public Boolean Checked;
	public CheckedSection(String id, TimeBlock[] times, String course, int openSpots) {
		super(id, times, course, openSpots);
		Checked = false;
	}
	public CheckedSection(Section s, boolean checked) {
		super(s.getID(), s.getTimes(), s.getCourseName(), s.getOpenSpots());
		Checked = checked;
	}
	public boolean getChecked() {
		return Checked;
	}
	public void setChecked(Boolean checked) {
		Checked = checked;
	}
}
