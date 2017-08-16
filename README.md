# mvvm
mvvm框架demo。databinding+rxjava2+retrofit2。
看过很多mvvm相关的博客github项目，博客都是介绍databinding的使用，项目都是使用了databinding就说是mvvm。

主要学习项目mvvmLight:https://github.com/Kelin-Hong/MVVMLight
这个项目已经不维护，并且所依赖的另一个库版本过低，修改成新版也有bug。

activity和fragment初始化ui和提供ui控制的方法，vm控制,业务抽取到manager。

封装了基于databinding的baseAdapter，不用去写viewHolder和一些普通adapter必须写的代码。如果不需要item中子view的点击事件，可以直接使用simpleAdaper，传入list，layout，BR。

封装了rxjava2和retrofit2，集成了参数拦截器，日志拦截器，缓存拦截器，对返回结果进行统一封装处理，不用去单独控制loading，在rxSubscribe中进行统一管理。

 RetrofitUtil.getJson().getBannerInfo()
                .compose(RxHelper2.ioMain())
                .subscribe(new MyObserver<List<BannerInfo>>(i.getBaseActivity(), null) {
                    @Override
                    protected void next(List<BannerInfo> bannerInfos) {
                        LogUtils.d(bannerInfos);
                    }
                });