package com.ruoyi.framework.web.domain;

import java.util.Date;
import java.util.Map;

/**
 * @author sett
 * @date 2023年03月26日 10:42
 * @title
 */
public class MessageVo {
    private String name;
    private Map<String,String> text;
    private String img;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Map<String, String> getText() {
        return text;
    }

    public void setText(Map<String, String> text) {
        this.text = text;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
