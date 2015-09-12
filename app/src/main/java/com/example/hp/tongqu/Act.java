package com.example.hp.tongqu;

import java.util.Calendar;
public class Act {
    private
        String actid;
        String name;
        String type;
        String location;
        String poster_id;
        String team_id;
        String source;
        String start_time;
        String end_time;
        String sign_start_time;
        String sign_end_time;
        String member_count;
        String max_number;
        String view_count;
        String comment_count;
        String typename;
        String poster;
        int time_status;
        String time_status_str;

    public String getActid() { return actid; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getLocation() { return location; }
    public String getPoster_id() { return poster_id; }
    public String getTeam_id() { return team_id; }
    public String getSource() { return source; }
    public String getStart_time() { return start_time; }
    public String getEnd_time() { return end_time; }
    public String getSign_start_time() { return sign_start_time; }
    public String getSign_end_time() { return sign_end_time; }
    public String getMember_count() { return member_count; }
    public String getMax_number() { return max_number; }
    public String getView_count() { return view_count; }
    public String getComment_count() { return comment_count; }
    public String getTypename() { return typename; }
    public String getPoster() { return poster; }
    public int getTime_status() { return time_status; }
    public String getTime_status_str() { return time_status_str; }

    public String getTime() { return "时间："+start_time.substring(5,16)+" 至 "+end_time.substring(5,16); }
    public int getLeft_time() {
        Calendar c = Calendar.getInstance();
        int year1= c.get(Calendar.YEAR);
        int mon1 = c.get(Calendar.MONTH)+1;//系统中月份从0开始计算
        int day1 = c.get(Calendar.DATE);
        int year = Integer.parseInt(end_time.substring(0, 4))-year1;
        int mon = Integer.parseInt(end_time.substring(5, 7))-mon1;
        int day = Integer.parseInt(end_time.substring(8, 10))-day1;
        int leftTime = year*365+mon*30+day;
        return leftTime;
    } //一个月天数近似为30天。一年365天.不考虑小时。可以修改完善
}