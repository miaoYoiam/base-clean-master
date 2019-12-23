package com.mine.domain.result;

import java.io.Serializable;
import java.util.List;

public class PersonalResumeListResult {


    private List<PersonalResumeBean> list;

    public List<PersonalResumeBean> getList() {
        return list;
    }

    public void setList(List<PersonalResumeBean> list) {
        this.list = list;
    }

    public static class PersonalResumeBean implements Serializable {

        private static final long serialVersionUID = -1545136719911219208L;
        /**
         * age : 26
         * author : 张磊
         * createDate :
         * expectSalary : 20k
         * id : 100
         * location : 北京
         * phone : 13606394061
         * professionalSkill : 啦啦啦啦
         * selfEvaluation : 棒棒哒
         * sex : 男
         * title : title1
         * workExperience : 哈哈哈哈哈
         * workYears : 3
         */

        private int age;
        private String author;
        private String createDate;
        private String expectSalary;
        private String id;
        private String location;
        private String phone;
        private String professionalSkill;
        private String selfEvaluation;
        private String sex;
        private String title;
        private String workExperience;
        private String workYears;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public String getExpectSalary() {
            return expectSalary;
        }

        public void setExpectSalary(String expectSalary) {
            this.expectSalary = expectSalary;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProfessionalSkill() {
            return professionalSkill;
        }

        public void setProfessionalSkill(String professionalSkill) {
            this.professionalSkill = professionalSkill;
        }

        public String getSelfEvaluation() {
            return selfEvaluation;
        }

        public void setSelfEvaluation(String selfEvaluation) {
            this.selfEvaluation = selfEvaluation;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getWorkExperience() {
            return workExperience;
        }

        public void setWorkExperience(String workExperience) {
            this.workExperience = workExperience;
        }

        public String getWorkYears() {
            return workYears;
        }

        public void setWorkYears(String workYears) {
            this.workYears = workYears;
        }
    }
}
