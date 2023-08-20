package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Chew Lip Sin
 */
public class Course implements Serializable {

    private String courseCode;
    private String title;
    private int creditHours;
    private Sem semester;
//    private static final String[] SEM = {"JAN", "JULY"};

    public enum Sem {
        JAN,
        JUL,
        ALL;

        public String toString(Sem sem) {
            if (sem == JAN) {
                return "JAN";
            } else if (sem == JUL) {
                return "JUL";
            } else {
                return "ALL";
            }
        }
    };
    private LocalDateTime updateTime;

    public Course(String courseCode, String title, int creditHours, Sem semester) {
        this.courseCode = courseCode;
        this.title = title;
        this.creditHours = creditHours;
        this.semester = semester;
        this.updateTime = LocalDateTime.now();
    }

    public Course() {
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.courseCode);
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + this.creditHours;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (this.creditHours != other.creditHours) {
            return false;
        }
        if (!Objects.equals(this.courseCode, other.courseCode)) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }

    public Sem getSemester() {
        return semester;
    }

    public void setSemester(Sem semester) {
        this.semester = semester;
    }

    public String semToString(Sem semester) {
        if (semester == Sem.JAN) {
            return "JAN";
        } else if (semester == Sem.JUL) {
            return "JUL";
        } else if (semester == Sem.ALL) {
            return "ALL";
        } else {
            return null;
    
        }
    }

    @Override
    public String toString() {
        String sems;
        if (semester == Sem.JAN) {
            sems = "JAN";
        } else if (semester == Sem.JUL) {
            sems = "JUL";
        } else {
            sems = "ALL";
        }
        return "Course" + "courseCode=" + courseCode + ", title=" + title + ", creditHours=" + creditHours + ", updateTime=" + updateTime + ", Semester=" + sems;
    }
}
