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
        this.course = parseStr(data, 0);
        this.sect = parseStr(data, 1);
        this.id = parseStr(data, 2);
        this.type = parseStr(data, 3);
        this.ge = parseStr(data, 4);
        this.req = parseStr(data, 5);
        this.days = parseStr(data, 6);
        this.start = parseStr(data, 7);
        this.end = parseStr(data, 8);
        this.instructor = parseStr(data, 9);
        this.location = parseStr(data, 10);
        this.lcap = parseInte(data, 11);
        this.ecap = parseInte(data, 12);
        this.enrl = parseInte(data, 13);
        this.waitList = parseInte(data, 14);
        this.drop = parseInte(data, 15);
        this.ics = parseStr(data, 16);
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

    public String parseStr(List<String> data, int pos) {
        if (data.size() > pos)
            return data.get(pos);
        else
            return "";
    }

    public String parseCourse(List<String> data) {
        if (!data.isEmpty())
            return data.get(0);
        else
            return "";
    }

    public String parseSect(List<String> data) {
        if (data.size() > 1)
            return data.get(1);
        else
            return "";
    }

    public String parseId(List<String> data) {
        if (data.size() > 2)
            return data.get(2);
        else
            return "";
    }

    public String parseType(List<String> data) {
        if (data.size() > 3)
            return data.get(3);
        else
            return "";
    }

    public String parseGe(List<String> data) {
        if (data.size() > 4)
            return data.get(4);
        else
            return "";
    }

    public String parseReq(List<String> data) {
        if (data.size() > 5)
            return data.get(5);
        else
            return "";
    }

    public String parseDays(List<String> data) {
        if (data.size() > 6)
            return data.get(6);
        else
            return "";
    }

    public String parseStart(List<String> data) {
        if (data.size() > 7)
            return data.get(7);
        else
            return "";
    }

    public String parseEnd(List<String> data) {
        if (data.size() > 8)
            return data.get(8);
        else
            return "";
    }

    public String parseInstructor(List<String> data) {
        if (data.size() > 9)
            return data.get(9);
        else
            return "";
    }

    public String parseLocation(List<String> data) {
        if (data.size() > 10)
            return data.get(10);
        else
            return "";
    }

    public String parseIcs(List<String> data) {
        if (data.size() > 16)
            return data.get(16);
        else
            return "";
    }

    public int parseInte(List<String> data, int pos) {
        if (data.size() > pos)
        {
            try
            {
                if (data.get(pos).length() != 0)
                    return Integer.parseInt(data.get(pos));
                else
                    return -1;
            }
            catch (NumberFormatException e)
            {
                return -1;
            }
        }
        else
            return -1;
    }
    
}