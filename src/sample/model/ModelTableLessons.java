package sample.model;

public class ModelTableLessons {
    private String id;
    private String teacher;
    private String course;
    private String room;
    private String date;

    public ModelTableLessons(String id, String teacher, String course, String room, String date){
        this.id = id;
        this.teacher = teacher;
        this.course = course;
        this.room = room;
        this.date = date;
    }

    public String getId(){ return id; }
    public String getTeacher(){ return teacher; }
    public String getCourse(){ return course; }
    public String getRoom(){ return room; }
    public String getDate(){ return date; }
}