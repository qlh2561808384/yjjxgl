package com.datanew.model;

import javax.persistence.*;

@Entity
@Table(name = "BASE_POST")
public class BasePost {
    private Integer postid;
    private String postname;
    private String remark;

    @Id
    @GeneratedValue( generator = "sequence")
    @SequenceGenerator(name = "sequence", sequenceName = "base_post_sequence")
    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    @Column(name = "POSTNAME")
    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

   @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

   
}
