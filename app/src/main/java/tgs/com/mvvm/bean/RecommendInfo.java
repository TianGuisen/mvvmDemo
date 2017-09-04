package tgs.com.mvvm.bean;


import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

/**
 * Created by hcc on 16/8/20 12:38
 * 100332338@qq.com
 * <p/>
 * 首页推荐界面数据
 */
@ToString
public class RecommendInfo {
    
    private int code;
    private List<ResultBean> result;
    
    public int getCode() {
        return code;
    }
    
    public void setCode(int code) {
        this.code = code;
    }
    
    public List<ResultBean> getResult() {
        return result;
    }
    
    public void setResult(List<ResultBean> result) {
        this.result = result;
    }
    @ToString
    public static class ResultBean {
        /**
         * type : recommend
         * head : {"param":"","goto":"","style":"gm_av","title":"热门焦点"}
         * body : [{"title":"【碧蓝航线MMD】 为什么到现在才开始爱上你 . . .『 Angelite 』","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/5e82f63a7efd2118759f5a765e7d7b88092b85e1.jpg","param":"11669931","goto":"av","width":350,"height":219,"play":"9.0万","danmaku":"1067","up":"梦亦星逝"},{"title":"【第9回东方NICO童祭】暁Records / 秘封俱乐部【东方PV】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/6872cd625b8eca085084a396659b87531a4bc5f4.jpg","param":"11589549","goto":"av","width":350,"height":219,"play":"4.8万","danmaku":"458","up":"伊吹小秋"},{"title":"【乐正龙牙翻唱】喜剧之王【动点p】【原创PV付】","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/4dc965faec20b058b6805d8713b6d26ac7d081c9.jpg","param":"11713479","goto":"av","width":350,"height":219,"play":"4.3万","danmaku":"1479","up":"动次打次的动点p"},{"title":"【MMD】纯情裙摆[tumi式初音]","style":"gm_av","cover":"http://i0.hdslb.com/bfs/archive/1c9285a6b4348fb00c5a3f0f7f6f982f010f168b.jpg","param":"11612879","goto":"av","width":350,"height":219,"play":"5.1万","danmaku":"613","up":"van狒"}]
         */
        
        private String type;
        private ResultBean.HeadBean head;
        private List<ResultBean.BodyBean> body;
        
        public String getType() {
            return type;
        }
        
        public void setType(String type) {
            this.type = type;
        }
        
        public ResultBean.HeadBean getHead() {
            return head;
        }
        
        public void setHead(ResultBean.HeadBean head) {
            this.head = head;
        }
        
        public List<ResultBean.BodyBean> getBody() {
            return body;
        }
        
        public void setBody(List<ResultBean.BodyBean> body) {
            this.body = body;
        }
        @ToString
        public static class HeadBean {
            /**
             * param :
             * goto :
             * style : gm_av
             * title : 热门焦点
             */
            
            private String param;
            @JSONField(name = "goto")
            private String gotoX;
            private String style;
            private String title;
            private int count;
            private int img;
    
            public int getImg() {
                return img;
            }
    
            public void setImg(int img) {
                this.img = img;
            }
    
            public int getCount() {
                return count;
            }
            
            public void setCount(int count) {
                this.count = count;
            }
            
            public String getParam() {
                return param;
            }
            
            public void setParam(String param) {
                this.param = param;
            }
            
            public String getGotoX() {
                return gotoX;
            }
            
            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }
            
            public String getStyle() {
                return style;
            }
            
            public void setStyle(String style) {
                this.style = style;
            }
            
            public String getTitle() {
                return title;
            }
            
            public void setTitle(String title) {
                this.title = title;
            }
        }
        @ToString
        public static class BodyBean implements Serializable{
            /**
             * title : 【碧蓝航线MMD】 为什么到现在才开始爱上你 . . .『 Angelite 』
             * style : gm_av
             * cover : http://i0.hdslb.com/bfs/archive/5e82f63a7efd2118759f5a765e7d7b88092b85e1.jpg
             * param : 11669931
             * goto : av
             * width : 350
             * height : 219
             * play : 9.0万
             * danmaku : 1067
             * up : 梦亦星逝
             */
            
            private String title;
            private String style;
            private String cover;
            private String param;
            @JSONField(name = "goto")
            private String gotoX;
            private int width;
            private int height;
            private String play;
            private String danmaku;
            private String up;
            @JSONField(name = "up_face")
            private String upFace;
            private int online;
            private String desc1;
            
            public String getUpFace() {
                return upFace;
            }
            
            public void setUpFace(String upFace) {
                this.upFace = upFace;
            }
            
            public int getOnline() {
                return online;
            }
            
            public void setOnline(int online) {
                this.online = online;
            }
            
            public String getDesc1() {
                return desc1;
            }
            
            public void setDesc1(String desc1) {
                this.desc1 = desc1;
            }
            
            public String getTitle() {
                return title;
            }
            
            public void setTitle(String title) {
                this.title = title;
            }
            
            public String getStyle() {
                return style;
            }
            
            public void setStyle(String style) {
                this.style = style;
            }
            
            public String getCover() {
                return cover;
            }
            
            public void setCover(String cover) {
                this.cover = cover;
            }
            
            public String getParam() {
                return param;
            }
            
            public void setParam(String param) {
                this.param = param;
            }
            
            public String getGotoX() {
                return gotoX;
            }
            
            public void setGotoX(String gotoX) {
                this.gotoX = gotoX;
            }
            
            public int getWidth() {
                return width;
            }
            
            public void setWidth(int width) {
                this.width = width;
            }
            
            public int getHeight() {
                return height;
            }
            
            public void setHeight(int height) {
                this.height = height;
            }
            
            public String getPlay() {
                return play;
            }
            
            public void setPlay(String play) {
                this.play = play;
            }
            
            public String getDanmaku() {
                return danmaku;
            }
            
            public void setDanmaku(String danmaku) {
                this.danmaku = danmaku;
            }
            
            public String getUp() {
                return up;
            }
            
            public void setUp(String up) {
                this.up = up;
            }
        }
    }
}
