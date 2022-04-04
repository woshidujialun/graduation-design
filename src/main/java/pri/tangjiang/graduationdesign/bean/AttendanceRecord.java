package pri.tangjiang.graduationdesign.bean;

import java.util.Date;

public class AttendanceRecord {
    private Long id;

    private Date create_time;

    private Date modify_time;

    private Date work_start_time;

    private Date work_end_time;

    private String work_time;

    private Integer user_id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public Date getWork_start_time() {
        return work_start_time;
    }

    public void setWork_start_time(Date work_start_time) {
        this.work_start_time = work_start_time;
    }

    public Date getWork_end_time() {
        return work_end_time;
    }

    public void setWork_end_time(Date work_end_time) {
        this.work_end_time = work_end_time;
    }

    public String getWork_time() {
        return work_time;
    }

    public void setWork_time(String work_time) {
        this.work_time = work_time;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}