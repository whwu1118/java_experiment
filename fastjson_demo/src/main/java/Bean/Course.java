package Bean;


import java.util.ArrayList;

/**
 * @Description: TODO
 * @Author: whwu6
 * @Date:2022/4/27} 16:35
 */
public class Course {
    private String courseName;


    private Object stus;

    public Course(String courseName, Object stus) {
        this.courseName = courseName;
        this.stus = stus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Object getStus() {
        return stus;
    }

    public void setStus(Object stus) {
        this.stus = stus;
    }
}
