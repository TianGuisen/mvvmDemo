# mvvm  
mvvm版的bilibili。
    
看过很多mvvm相关的博客，都是介绍databinding的使用，或者是讲mvvm的概念，看过的github项目也仅仅是使用了基础的databinding，当做butterknife在用。

主要学习项目mvvmLight:https://github.com/Kelin-Hong/MVVMLight  
这个项目已经不维护，并且它所依赖的另一个库版本过低，修改成新版也有其他问题。

# 主要集成框架  
**fragmentation：为"单Activity ＋ 多Fragment","多模块Activity + 多Fragment"架构而生，简化开发，轻松解决动画、嵌套、事务相关等问题。**  
**ImmersionBar:完成多fragment页面不同沉浸**  
**glide：谷歌推荐用的图片加载框架**  
**rxjava2：主要配合retrofit2**  
**retrofit2：网络**  
**easypermissions：权限框架**    
**SmartRefreshLayout：非常好用的刷新框架**  
**autolayout：张鸿洋的屏幕适配**   
还有其他一些不一一列举

# 特性
封装了基于databinding的baseAdapter，不用去写viewHolder和一些普通adapter必须写的代码。如果不需要item中子view的点击事件，可以直接使用simpleAdaper，传入list，layout。  **xml中绑定的实体类必须命名为item**。  

封装了rxjava2和retrofit2，集成了参数拦截器，日志拦截器，缓存拦截器，对返回结果进行统一封装处理，不用去单独控制loading，在rxSubscribe中进行统一管理。
  
    

##### 网络请求示例
```
        RetrofitUtil.getJson().getBannerInfo()
                .compose(RxHelper.ioMain())
                .subscribe(new MyObserver<List<BannerInfo>>(true, null) {
                    @Override
                    protected void next(List<BannerInfo> bannerInfos) {
                    //...
                    }
                });
```  

##### adatper写法详见demo


