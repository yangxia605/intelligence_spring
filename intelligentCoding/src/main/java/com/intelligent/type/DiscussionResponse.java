package com.intelligent.type;

import java.sql.Timestamp;

public class DiscussionResponse {
    private Integer id;// 讨论ID
    private Integer parent_id;// 楼对应的ID
    private Integer user_id;// 用户ID
    private Integer topic_id;// 讨论对应用户ID
    private String content;// 讨论内容
    private Timestamp submit_time;// 讨论提交时间
    private Integer like_num;// 点赞数


    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    public int getParentId(){return parent_id;}
    public void setParentId(int parent_id){this.parent_id=parent_id;}

    public int getUserId(){return user_id;}
    public void setUserId(int user_id){this.user_id=user_id;}

    public int getTopicId(){return topic_id;}
    public void setTopicId(int topic_id){this.topic_id=topic_id;}

    public String getContent(){return content;}
    public void setContent(String content){this.content=content;}

    public Timestamp getSubmitTime(){return submit_time;}
    public void setSubmitTime(Timestamp submit_time){this.submit_time=submit_time;}

    public int getLikeNum(){return like_num;}
    public void setLikeNum(int like_num){this.like_num=like_num;}
}
