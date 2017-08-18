package tgs.com.mvvm.bean;

import java.util.List;

import lombok.Data;

/**
 * Created by 田桂森 on 2017/8/16.
 */
@Data
public class VideoDetailsInfo {
    private int aid;
    private int attribute;
    private int copyright;
    private int ctime;
    private String desc;
    private int duration;
    private ElecBean elec;
    private OwnerBean owner;
    private OwnerExtBean owner_ext;
    private String pic;
    private int pubdate;
    private ReqUserBean req_user;
    private RightsBean rights;
    private StatBean stat;
    private int state;
    private int tid;
    private String title;
    private String tname;
    private List<PagesBean> pages;
    private List<RelatesBean> relates;
    private List<TagBean> tag;
    private List<String> tags;
    
    @Data
    public static class ElecBean {
        private int count;
        private boolean show;
        private int total;
        private List<ListBean> list;
        
        @Data
        public static class ListBean {
            private String avatar;
            private String message;
            private int mid;
            private int msg_deleted;
            private int pay_mid;
            private int rank;
            private int trend_type;
            private String uname;
            private VipInfoBean vip_info;
            
            @Data
            public static class VipInfoBean {
                private int vipStatus;
                private int vipType;
            }
        }
    }
    
    @Data
    public static class OwnerBean {
        private String face;
        private int mid;
        private String name;
    }
    
    @Data
    public static class OwnerExtBean {
        private VipBean vip;
        
        @Data
        public static class VipBean {
            private int accessStatus;
            private String dueRemark;
            private long vipDueDate;
            private int vipStatus;
            private String vipStatusWarn;
            private int vipType;
        }
    }
    
    @Data
    public static class ReqUserBean {
        private int attention;
        private int favorite;
    }
    
    @Data
    public static class RightsBean {
        private int bp;
        private int download;
        private int elec;
        private int hd5;
        private int movie;
        private int pay;
    }
    
    @Data
    public static class StatBean {
        private int coin;
        private int danmaku;
        private int favorite;
        private int his_rank;
        private int now_rank;
        private int reply;
        private int share;
        private int view;
    }
    
    @Data
    public static class PagesBean {
        private int cid;
        private String from;
        private int has_alias;
        private String link;
        private int page;
        private String part;
        private String rich_vid;
        private String vid;
        private String weblink;
    }
    
    @Data
    public static class RelatesBean {
        private int aid;
        private OwnerBean owner;
        private String pic;
        private StatBean stat;
        private String title;
        
        @Data
        public static class OwnerBean {
            private String face;
            private int mid;
            private String name;
        }
        
        @Data
        public static class StatBean {
            private int coin;
            private int danmaku;
            private int favorite;
            private int his_rank;
            private int now_rank;
            private int reply;
            private int share;
            private int view;
        }
    }
    
    @Data
    public static class TagBean {
        private String cover;
        private int hates;
        private int likes;
        private int tag_id;
        private String tag_name;
    }
}
