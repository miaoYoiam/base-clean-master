package com.mine.domain.result;

import java.io.Serializable;
import java.util.List;

public class CompanyInviteInfoListReuslt {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Serializable {
        private static final long serialVersionUID = 4816719980668979775L;
        /**
         * companyDescribe : "北京云网高科技术有限公司（简称“云网高科”） 是一家致力于计算机软件开发、技术管理咨询与服务的高新技术企业。公司成立于北京中关村高新产业开发区，公司注册资金500万，公司前身为北京易赛维技术有限公司，于2006年成立，2011年更名为北京云网高科技术有限公司。云网高科是基于WEB及无线环境下的信息化管理软件领导企业，拥有雄厚的研究开发实力和强大的生产及市场营销能力，奉行通过优异的信息产品和完美的售前售后服务，赢得客户的赞誉。
         * <p>
         * “技术是根，服务为本”是云网高科所有员工坚持的核心价值观，自公司成立之初即十分重视核心技术与自主知识产权的产品研发。“技术为根”，其意为：企业成功的根基是技术的持续创新与进步；“服务为本”，其意为：企业存在的核心价值是通过为客户创造价值而得以实现。
         * 云网高科——秉承诚实守信、开拓创新，为更好的改善和提高全社会的信息化水平而不断努力。
         * 云网高科——将技术经验和创新精神相融合，通过持续不断地加大在技术和管理上的投资，致力于为客户提供最优质的产品和服务，云网高科将与全国的客户共同发展，走向成功。
         * 云网高科——基于广泛深入的行业管理与系统开发经验，凭借丰富的管理能力和雄厚的技术力量，云网高科为客户提供可靠高效的科技服务，同时也为国内外合作伙伴提供系统软件与增值软件开发服务。云网高科在行业软件、云计算、GIS、内容管理、电子政务、协同与电子商务应用方面均有自己独到的产品和解决方案，其技术始终保持着业界领先的地位。云网高科以优异的产品服务于企业和政府，客户群包括：政府部委、中国航天科工集团、中石油，中石化、中国电信、铁道以医药等各行各业。
         * 云网高科以脚踏实地的行动，不断创新的进取精神，促进客户和合作伙伴的价值创新，致力与中国软件产业共同发展。"
         * companyName : 北京云网高科技术有限公司
         * createDate :
         * expectSalary : 15-20k
         * id : 10
         * jobDescribe : 3、能独立完成后台应用、接口服务及底层架构的开发；
         * location : 北京市海淀区上地颐泉汇2号楼2单元723
         * phone : 010-65252526
         * title : Java高级工程师
         * workExperience : 3、熟悉Mysql、Oracle、Sqlserver、Mongodb等数据库，熟练掌握SQL语句；
         * workYears : 13
         */

        private String companyDescribe;
        private String companyName;
        private String createDate;
        private String expectSalary;
        private String id;
        private String jobDescribe;
        private String location;
        private String phone;
        private String title;
        private String workExperience;
        private String workYears;

        public String getCompanyDescribe() {
            return companyDescribe;
        }

        public void setCompanyDescribe(String companyDescribe) {
            this.companyDescribe = companyDescribe;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
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

        public String getJobDescribe() {
            return jobDescribe;
        }

        public void setJobDescribe(String jobDescribe) {
            this.jobDescribe = jobDescribe;
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
