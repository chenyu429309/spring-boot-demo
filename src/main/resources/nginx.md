#第一步

    原始 的启动文件 openresty  与配置文件 nginx.conf 做备份防止 openresty 重新编译 并 gmake install 后 原文件被覆盖
#第二步
 
    重新编译 
    ./configure --prefix=/Users/macmini/work/nginx --with-http_gzip_static_module --with-http_realip_module --with-http_addition_module --with-http_sub_module --with-http_v2_module --with-http_postgres_module
#第三步
    
    gmake
    如果 新编译的 文件路径 跟 原路径 一样 则 不用替换
    如何路径不一样：替换 文件
#第四步

    gmake install

#第五步

    kill -USR2 23882  重新开启 新的主线程 23882 为 nginx以及开启 的主进程
    kill -WINCH 23882 停止监听
    kill -QUIT 23882


#重启和指定文件位置
    /data/app/opentresty_nginx/bin/openresty -p /data/projects/nginx_lua -c /data/projects/nginx_lua/conf/nginx_yiye_test.conf -s reload
    
    
#安装nginx

        docker run \
          --name myNginx \
          -d -p 80:80 \
          -v /data/nginx/html:/usr/share/nginx/html \
          -v /data/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:ro \
          -v /data/nginx/conf.d:/etc/nginx/conf.d \
          nginx


#安装openresty同时生成ssl证书
        
        这里需要注意的是在挂载nginx.conf，同时也需要挂载他的子配置文件conf.d，不然会报错
        docker run -d \
        --name="openresty" \
        -p 80:80 \
        -p 443:443 \
        -v  /data/openresty/conf/nginx.conf:/usr/local/openresty/nginx/conf/nginx.conf:ro \
        -v /data/openresty/conf.d:/usr/local/openresty/nginx/conf/conf.d \
        -v   /data/openresty/logs:/usr/local/openresty/nginx/logs \
        -v  /data/openresty/conf.d:/etc/nginx/conf.d \
        -v   /data/openresty/ssl:/usr/local/openresty/nginx/ssl \
        -v  /data/openresty/html:/etc/nginx/html openresty/openresty:alpine
        

# 生成一个RSA密钥 
    
    a.wang.felix.com为要添加的域名
    sudo openssl genrsa -des3 -out a.wang.felix.com 1024
 
# 拷贝一个不需要输入密码的密钥文件
    sudo openssl rsa -in a.wang.felix.com -out felix.key
 
# 生成一个证书请求
    sudo openssl req -new -key a.wang.felix.com -out felix.csr
 
# 自己签发证书
    sudo openssl x509 -req -days 365 -in felix.csr -signkey a.wang.felix.com -out felix.crt 