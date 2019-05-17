package logic;

public class CheckedSection extends Section{
	public boolean Checked;
	public CheckedSection(String id, TimeBlock[] times, Course course, int openSpots) {
		super(id, times, course, openSpots);
		Checked = false;
	}
	public CheckedSection(Section s) {
		super(s.getID(), s.getTimes(), s.getCourseName(), s.getOpenSpots());
		Checked = false;
	}
}
