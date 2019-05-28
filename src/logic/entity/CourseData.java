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
        this.course = parseCourse(data);
        this.sect = parseSect(data);
        this.id = parseId(data);
        this.type = parseType(data);
        this.ge = parseGe(data);
        this.req = parseReq(data);
        this.days = parseDays(data);
        this.start = parseStart(data);
        this.end = parseEnd(data);
        this.instructor = parseInstructor(data);
        this.location = parseLocation(data);
        this.lcap = parseLcap(data);
        this.ecap = parseEcap(data);
        this.enrl = parseEnrl(data);
        this.waitList = parseWaitList(data);
        this.drop = parseDrop(data);
        this.ics = parseIcs(data);
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

    public int parseLcap(List<String> data) {
        if (data.size() > 11)
        {
            try
            {
                if (data.get(11).length() != 0)
                    return Integer.parseInt(data.get(11));
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

    public int parseEcap(List<String> data) {
        if (data.size() > 12)
        {
            try
            {
                if (data.get(12).length() != 0)
                    return Integer.parseInt(data.get(12));
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

    public int parseEnrl(List<String> data) {
        if (data.size() > 13)
        {
            try
            {
                if (data.get(13).length() != 0)
                    return Integer.parseInt(data.get(13));
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

    public int parseWaitList(List<String> data) {
        if (data.size() > 14)
        {
            try
            {
                if (data.get(14).length() != 0)
                    return Integer.parseInt(data.get(14));
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

    public int parseDrop(List<String> data) {
        if (data.size() > 15)
        {
            try
            {
                if (data.get(15).length() != 0)
                    return Integer.parseInt(data.get(15));
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