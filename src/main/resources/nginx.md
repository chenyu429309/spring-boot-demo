第一步

    原始 的启动文件 openresty  与配置文件 nginx.conf 做备份防止 openresty 重新编译 并 gmake install 后 原文件被覆盖
第二步
 
    重新编译 
    ./configure --prefix=/Users/macmini/work/nginx --with-http_gzip_static_module --with-http_realip_module --with-http_addition_module --with-http_sub_module --with-http_v2_module --with-http_postgres_module
第三步
    
    gmake
    如果 新编译的 文件路径 跟 原路径 一样 则 不用替换
    如何路径不一样：替换 文件
第四步

    gmake install

第五步

    kill -USR2 23882  重新开启 新的主线程 23882 为 nginx以及开启 的主进程
    kill -WINCH 23882 停止监听
    kill -QUIT 23882
