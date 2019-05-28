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

    //-----------------------------------------------------------

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
    
}