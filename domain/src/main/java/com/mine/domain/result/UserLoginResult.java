package com.mine.domain.result;

public class UserLoginResult {


    /**
     * user : {"id":2,"password":"e10adc3949ba59abbe56e057f20f883e","roleName":"系统管理员","roleType":"","userName":"admin"}
     */

    private UserBean user;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 2
         * password : e10adc3949ba59abbe56e057f20f883e
         * roleName : 系统管理员
         * roleType :
         * userName : admin
         */

        private int id;
        private String password;
        private String roleName;
        private String roleType;
        private String userName;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRoleName() {
            return roleName;
        }

        public void setRoleName(String roleName) {
            this.roleName = roleName;
        }

        public String getRoleType() {
            return roleType;
        }

        public void setRoleType(String roleType) {
            this.roleType = roleType;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
