package etity;

import java.sql.Date;
import java.sql.Time;

public class Project {
    private int projectID;
    private String name;
    private String type;
    private String record;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "运动项目信息：" +
                "\n\t项目ID: " + projectID +
                "\n\t项目名称: " + name  +
                "\n\t项目类型: " + type  +
                "\n\t最高纪录: " + record +
                "\n\t比赛日期: " + date +
                "\n\t开始时刻: " + time +
                "\n\t比赛时长: " + timeLength + "分钟 ";
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    private Date date;
    private Time time;
    private int timeLength;

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }


}
