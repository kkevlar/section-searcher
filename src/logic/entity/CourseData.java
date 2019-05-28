package logic.entity;

import java.util.List;

public class CourseData
{
    private String course;
    private String sect; 
    private String id;
    private String type;
    private String ge;
    private String req; 
    private String days;
    private String start; 
    private String end;
    private String instructor;
    private String location;
    private String ics;
    private int lcap;
    private int ecap;
    private int enrl; 
    private int waitList;
    private int drop;

    public CourseData(List<String> data)
    {
        if (data.size() > 0)
            this.course = data.get(0);
        else
            this.course = "";
        if (data.size() > 1)
            this.sect = data.get(1);
        else
            this.sect = "";
        if (data.size() > 2)
            this.id = data.get(2);
        else
            this.id = "";
        if (data.size() > 3)
            this.type = data.get(3);
        else
            this.type = "";
        if (data.size() > 4)
            this.ge = data.get(4);
        else
            this.ge = "";
        if (data.size() > 5)
            this.req = data.get(5);
        else
            this.req = "";
        if (data.size() > 6)
            this.days = data.get(6);
        else
            this.days = "";
        if (data.size() > 7)
            this.start = data.get(7);
        else
            this.start = "";
        if (data.size() > 8)
            this.end = data.get(8);
        else
            this.end = "";
        if (data.size() > 9)
            this.instructor = data.get(9);
        else
            this.instructor = "";
        if (data.size() > 10)
            this.location = data.get(10);
        else
            this.location = "";
        if (data.size() > 11)
        {
            try
            {
                if (data.get(11).length() != 0)
                    this.lcap = Integer.parseInt(data.get(11));
                else
                    this.lcap = -1;
            }
            catch (NumberFormatException e)
            {
                this.lcap = -1;
            }
        }
        else
            this.lcap = -1;
        if (data.size() > 12)
        {
            try
            {
                if (data.get(12).length() != 0)
                    this.ecap = Integer.parseInt(data.get(12));
                else
                    this.ecap = -1;
            }
            catch (NumberFormatException e)
            {
                this.ecap = -1;
            }
        }
        else
            this.ecap = -1;
        if (data.size() > 13)
        {
            try
            {
                if (data.get(13).length() != 0)
                    this.enrl = Integer.parseInt(data.get(13));
                else
                    this.enrl = -1;
            }
            catch (NumberFormatException e)
            {
                this.enrl = -1;
            }
        }
        else
            this.enrl = -1;
        if (data.size() > 14)
        {
            try
            {
                if (data.get(14).length() != 0)
                    this.waitList = Integer.parseInt(data.get(14));
                else
                    this.waitList = -1;
            }
            catch (NumberFormatException e)
            {
                this.waitList = -1;
            }
        }
        else
            this.waitList = -1;
        if (data.size() > 15)
        {
            try
            {
                if (data.get(15).length() != 0)
                    this.drop = Integer.parseInt(data.get(15));
                else
                    this.drop = -1;
            }
            catch (NumberFormatException e)
            {
                this.drop = -1;
            }
        }
        else
            this.drop = -1;     
        if (data.size() > 16)
            this.ics = data.get(16);
        else
            this.ics = "";
    }

    public String getCourse() {return this.course;}

    public String getSect() {return this.sect;}

    public String getId() {return this.id;}

    public String getType() {return this.type;}

    public String getGe() {return this.ge;}

    public String getReq() {return this.req;}

    public String getDays() {return this.days;}

    public String getStart() {return this.start;}

    public String getEnd() {return this.end;}

    public String getInstructor() {return this.instructor;}

    public String getLocation() {return this.location;}

    public String getIcs() {return this.ics;}

    public int getLcap() {return this.lcap;}

    public int getEcap() {return this.ecap;}

    public int getEnrl() {return this.enrl;}

    public int getWaitList() {return this.waitList;}

    public int getDrop() {return this.drop;}

    public void setCourse(String course) {this.course = course;}

    public void setSect(String sect) {this.sect = sect;}

    public void setId(String id) {this.id = id;}

    public void setType(String type) {this.type = type;}

    public void setGe(String ge) {this.ge = ge;}

    public void setReq(String req) {this.req = req;}

    public void setDays(String days) {this.days = days;}

    public void setStart(String start) {this.start = start;}

    public void setEnd(String end) {this.end = end;}

    public void setInstructor(String instructor) {this.instructor = instructor;}

    public void setLocation(String location) {this.location = location;}

    public void setIcs(String ics) {this.ics = ics;}

    public void setLcap(int lcap) {this.lcap = lcap;}

    public void setEcap(int ecap) {this.ecap = ecap;}

    public void setEnrl(int enrl) {this.enrl = enrl;}

    public void setWaitList(int waitList) {this.waitList = waitList;}

    public void setDrop(int drop) {this.drop = drop;}

    public String str_CourseData()
    {
        String ret_str;
        ret_str = "Course: " + this.course + "\n";
        ret_str += "Section: " + this.sect + "\n";
        ret_str += "ID: " + this.id + "\n";
        ret_str += "Type: " + this.type + "\n";
        ret_str += "GE: " + this.ge + "\n";
        ret_str += "Requirement: " + this.req + "\n";
        ret_str += "Days: " + this.days + "\n";
        ret_str += "Start: " + this.start + "\n";
        ret_str += "End: " + this.end + "\n";
        ret_str += "Instructor: " + this.instructor + "\n";
        ret_str += "Location: " + this.location + "\n";
        ret_str += "lcap: " + String.valueOf(this.lcap) + "\n";
        ret_str += "ecap: " + String.valueOf(this.ecap) + "\n";
        ret_str += "enrl: " + String.valueOf(this.enrl) + "\n";
        ret_str += "waitList: " + String.valueOf(this.waitList) + "\n";
        ret_str += "drop: " + String.valueOf(this.drop) + "\n";
        ret_str += "ICS: " + this.ics + "\n";
        return ret_str;
    }
}