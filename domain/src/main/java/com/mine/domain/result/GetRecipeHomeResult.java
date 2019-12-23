package com.mine.domain.result;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetRecipeHomeResult {


    /**
     * header : {"tags":[{"text":"土豆","action_url":"/pages/search/search?keyword=土豆"},{"text":"牛肉","action_url":"/pages/search/search?keyword=牛肉"}]}
     * list : [{"type":1,"recipe":{"stc":0,"sti":0,"an":"豆粉164477","id":1782386,"cookstory":"sdfsdff","img":"http://img.qa.douguo.com/upload/caiku/5/3/a/300_532c8ef9a4558d7989cbffbd1765cb0a.jpg","dc":0,"fc":13,"vu":{"vu":"https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl"},"vfurl":"https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl","ecs":0,"hq":0,"a":{"id":-1,"n":"豆粉164477","p":"http://img.qa.douguo.com/static/img/70.jpg","v":0,"lvl":1,"lv":1,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/5/3/a/600_532c8ef9a4558d7989cbffbd1765cb0a.jpg","vc":338,"n":"fdsff"}},{"type":2,"menu":{"id":8473996,"t":"新建一些菜单吧","b":"http://img.qa.douguo.com/upload/caiku/1/7/8/300_177c2637208b035862086a460ed069f8.jpg","c":3}},{"type":2,"menu":{"id":1022996,"t":"新哈哈哈","b":"http://img.qa.douguo.com/upload/caiku/2/5/b/300_252326e02ee65b9da1140788bf3200fb.jpg","c":4}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1782384,"cookstory":"aaa","img":"http://img.qa.douguo.com/upload/caiku/c/4/b/300_c42c4dd3e28920a8b691e61ccf8c692b.jpg","dc":0,"fc":1,"ecs":0,"hq":0,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/c/4/b/600_c42c4dd3e28920a8b691e61ccf8c692b.jpg","vc":215,"n":"视频菜谱3"}},{"type":2,"menu":{"id":1022973,"t":"444444 222嘎嘎嘎嘎嘎过过过过过","b":"http://img.qa.douguo.com/upload/caiku/9/6/4/300_96587a42ee8ec689522a6448b24dc334.jpg","c":3}},{"type":2,"menu":{"id":1022940,"t":"菜单","b":"http://img.qa.douguo.com/upload/caiku/a/d/a/300_ad5a01b2107008cef903114e702a071a.jpg","c":2}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1782384,"cookstory":"aaa","img":"http://img.qa.douguo.com/upload/caiku/c/4/b/300_c42c4dd3e28920a8b691e61ccf8c692b.jpg","dc":0,"fc":1,"ecs":0,"hq":0,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/c/4/b/600_c42c4dd3e28920a8b691e61ccf8c692b.jpg","vc":215,"n":"视频菜谱3"}},{"type":2,"menu":{"id":1022889,"t":"运营账号创建无标签","b":"http://img.qa.douguo.com/upload/caiku/3/6/8/300_3609f126a5847fd382e5807a985b37b8.jpeg","c":2}},{"type":2,"menu":{"id":399758,"t":"中晚餐","b":"","c":1}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1782384,"cookstory":"aaa","img":"http://img.qa.douguo.com/upload/caiku/c/4/b/300_c42c4dd3e28920a8b691e61ccf8c692b.jpg","dc":0,"fc":1,"ecs":0,"hq":0,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/c/4/b/600_c42c4dd3e28920a8b691e61ccf8c692b.jpg","vc":215,"n":"视频菜谱3"}},{"type":2,"menu":{"id":1022989,"t":"测试菜单","b":"","c":11}},{"type":2,"menu":{"id":399832,"t":"汤品","b":"","c":2}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1782384,"cookstory":"aaa","img":"http://img.qa.douguo.com/upload/caiku/c/4/b/300_c42c4dd3e28920a8b691e61ccf8c692b.jpg","dc":0,"fc":1,"ecs":0,"hq":0,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/c/4/b/600_c42c4dd3e28920a8b691e61ccf8c692b.jpg","vc":215,"n":"视频菜谱3"}},{"type":2,"menu":{"id":399710,"t":"喝的","b":"","c":1}},{"type":2,"menu":{"id":1022986,"t":"发的是多少","b":"http://img.qa.douguo.com/upload/caiku/9/2/7/300_9223a1a731864faacab6d5bfca6f1697.jpg","c":1}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1782384,"cookstory":"aaa","img":"http://img.qa.douguo.com/upload/caiku/c/4/b/300_c42c4dd3e28920a8b691e61ccf8c692b.jpg","dc":0,"fc":1,"ecs":0,"hq":0,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/c/4/b/600_c42c4dd3e28920a8b691e61ccf8c692b.jpg","vc":215,"n":"视频菜谱3"}},{"type":2,"menu":{"id":399709,"t":"点心","b":"","c":1}},{"type":2,"menu":{"id":399748,"t":"苦菊肉丝","b":"","c":1}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1781483,"cookstory":"","img":"http://img.qa.douguo.com/upload/caiku/9/2/0/300_92e2dcc60e5e2f08323f767b7fe446f0.jpg","dc":0,"fc":0,"vu":{"vu":"https://vplay.douguo.com/lpfFWdt-8A5SJY6Jm0nfj52kHLpi"},"vfurl":"https://vplay.douguo.com/lpfFWdt-8A5SJY6Jm0nfj52kHLpi","ecs":0,"hq":1,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/9/2/0/600_92e2dcc60e5e2f08323f767b7fe446f0.jpg","vc":90,"n":"hhh"}},{"type":1,"recipe":{"stc":0,"sti":0,"an":"ly","id":1781489,"cookstory":"sdfsdff","img":"http://img.qa.douguo.com/upload/caiku/5/3/a/300_532c8ef9a4558d7989cbffbd1765cb0a.jpg","dc":0,"fc":0,"ecs":0,"hq":1,"a":{"id":19013769,"n":"ly","v":1,"p":"http://img.qa.douguo.com/upload/photo/e/2/3/70_u100001190152220407115539.jpg","lvl":5,"lv":5,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/5/3/a/600_532c8ef9a4558d7989cbffbd1765cb0a.jpg","vc":120,"n":"fdsff"}}]
     */

    private HeaderBean header;
    private List<ListBean> list;

    public HeaderBean getHeader() {
        return header;
    }

    public void setHeader(HeaderBean header) {
        this.header = header;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class HeaderBean {
        private List<TagsBean> tags;

        public List<TagsBean> getTags() {
            return tags;
        }

        public void setTags(List<TagsBean> tags) {
            this.tags = tags;
        }

        public static class TagsBean {
            /**
             * text : 土豆
             * action_url : /pages/search/search?keyword=土豆
             */

            private String text;
            @SerializedName("action_url")
            private String actionUrl;

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getActionUrl() {
                return actionUrl;
            }

            public void setActionUrl(String actionUrl) {
                this.actionUrl = actionUrl;
            }
        }
    }

    public static class ListBean {
        /**
         * type : 1
         * recipe : {"stc":0,"sti":0,"an":"豆粉164477","id":1782386,"cookstory":"sdfsdff","img":"http://img.qa.douguo.com/upload/caiku/5/3/a/300_532c8ef9a4558d7989cbffbd1765cb0a.jpg","dc":0,"fc":13,"vu":{"vu":"https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl"},"vfurl":"https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl","ecs":0,"hq":0,"a":{"id":-1,"n":"豆粉164477","p":"http://img.qa.douguo.com/static/img/70.jpg","v":0,"lvl":1,"lv":1,"is_prime":false},"p":"http://img.qa.douguo.com/upload/caiku/5/3/a/600_532c8ef9a4558d7989cbffbd1765cb0a.jpg","vc":338,"n":"fdsff"}
         * menu : {"id":8473996,"t":"新建一些菜单吧","b":"http://img.qa.douguo.com/upload/caiku/1/7/8/300_177c2637208b035862086a460ed069f8.jpg","c":3}
         */

        private int type;
        private RecipeBean recipe;
        private MenuBean menu;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public RecipeBean getRecipe() {
            return recipe;
        }

        public void setRecipe(RecipeBean recipe) {
            this.recipe = recipe;
        }

        public MenuBean getMenu() {
            return menu;
        }

        public void setMenu(MenuBean menu) {
            this.menu = menu;
        }

        public static class RecipeBean {
            /**
             * stc : 0
             * sti : 0
             * an : 豆粉164477
             * id : 1782386
             * cookstory : sdfsdff
             * img : http://img.qa.douguo.com/upload/caiku/5/3/a/300_532c8ef9a4558d7989cbffbd1765cb0a.jpg
             * dc : 0
             * fc : 13
             * vu : {"vu":"https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl"}
             * vfurl : https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl
             * ecs : 0
             * hq : 0
             * a : {"id":-1,"n":"豆粉164477","p":"http://img.qa.douguo.com/static/img/70.jpg","v":0,"lvl":1,"lv":1,"is_prime":false}
             * p : http://img.qa.douguo.com/upload/caiku/5/3/a/600_532c8ef9a4558d7989cbffbd1765cb0a.jpg
             * vc : 338
             * n : fdsff
             */

            private int stc;
            private int sti;
            private String an;
            private int id;
            private String cookstory;
            private String img;
            private int dc;
            private int fc;
            private VuBean vu;
            private String vfurl;
            private int ecs;
            private int hq;
            private ABean a;
            private String p;
            private int vc;
            private String n;

            public int getStc() {
                return stc;
            }

            public void setStc(int stc) {
                this.stc = stc;
            }

            public int getSti() {
                return sti;
            }

            public void setSti(int sti) {
                this.sti = sti;
            }

            public String getAn() {
                return an;
            }

            public void setAn(String an) {
                this.an = an;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCookstory() {
                return cookstory;
            }

            public void setCookstory(String cookstory) {
                this.cookstory = cookstory;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getDc() {
                return dc;
            }

            public void setDc(int dc) {
                this.dc = dc;
            }

            public int getFc() {
                return fc;
            }

            public void setFc(int fc) {
                this.fc = fc;
            }

            public VuBean getVu() {
                return vu;
            }

            public void setVu(VuBean vu) {
                this.vu = vu;
            }

            public String getVfurl() {
                return vfurl;
            }

            public void setVfurl(String vfurl) {
                this.vfurl = vfurl;
            }

            public int getEcs() {
                return ecs;
            }

            public void setEcs(int ecs) {
                this.ecs = ecs;
            }

            public int getHq() {
                return hq;
            }

            public void setHq(int hq) {
                this.hq = hq;
            }

            public ABean getA() {
                return a;
            }

            public void setA(ABean a) {
                this.a = a;
            }

            public String getP() {
                return p;
            }

            public void setP(String p) {
                this.p = p;
            }

            public int getVc() {
                return vc;
            }

            public void setVc(int vc) {
                this.vc = vc;
            }

            public String getN() {
                return n;
            }

            public void setN(String n) {
                this.n = n;
            }

            public static class VuBean {
                /**
                 * vu : https://vplay.douguo.com/lvQPcd7mQnr_xXN4j-ghyTCevdrl
                 */

                private String vu;

                public String getVu() {
                    return vu;
                }

                public void setVu(String vu) {
                    this.vu = vu;
                }
            }

            public static class ABean {
                /**
                 * id : -1
                 * n : 豆粉164477
                 * p : http://img.qa.douguo.com/static/img/70.jpg
                 * v : 0
                 * lvl : 1
                 * lv : 1
                 * is_prime : false
                 */

                private int id;
                private String n;
                private String p;
                private int v;
                private int lvl;
                private int lv;
                @SerializedName("is_prime")
                private boolean isPrime;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getN() {
                    return n;
                }

                public void setN(String n) {
                    this.n = n;
                }

                public String getP() {
                    return p;
                }

                public void setP(String p) {
                    this.p = p;
                }

                public int getV() {
                    return v;
                }

                public void setV(int v) {
                    this.v = v;
                }

                public int getLvl() {
                    return lvl;
                }

                public void setLvl(int lvl) {
                    this.lvl = lvl;
                }

                public int getLv() {
                    return lv;
                }

                public void setLv(int lv) {
                    this.lv = lv;
                }

                public boolean isIsPrime() {
                    return isPrime;
                }

                public void setIsPrime(boolean isPrime) {
                    this.isPrime = isPrime;
                }
            }
        }

        public static class MenuBean {
            /**
             * id : 8473996
             * t : 新建一些菜单吧
             * b : http://img.qa.douguo.com/upload/caiku/1/7/8/300_177c2637208b035862086a460ed069f8.jpg
             * c : 3
             */

            private int id;
            private String t;
            private String b;
            private int c;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getT() {
                return t;
            }

            public void setT(String t) {
                this.t = t;
            }

            public String getB() {
                return b;
            }

            public void setB(String b) {
                this.b = b;
            }

            public int getC() {
                return c;
            }

            public void setC(int c) {
                this.c = c;
            }
        }
    }
}
