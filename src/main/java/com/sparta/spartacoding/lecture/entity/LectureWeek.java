package com.sparta.spartacoding.lecture.entity;

import java.util.List;

public class LectureWeek {
    private String weekTitle;
    private List<WeekItem> weekList;

    // Getter 및 Setter 메서드 추가
    public String getWeekTitle() {
        return weekTitle;
    }
    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }
    public List<WeekItem> getWeekList() {
        return weekList;
    }
    public void setWeekList(List<WeekItem> weekList) {
        this.weekList = weekList;
    }
}
