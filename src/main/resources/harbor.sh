#!/usr/bin/env bash
# step 1： 安装docker-compose
sudo curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose
sudo docker-compose --version
# step 2：openldap安装 如果harbor想用ldap进行统一认证的话，需要事先装个ldap
sudo docker run -p 389:389 --env LDAP_ORGANISATION="My Company" --env LDAP_DOMAIN="cmss.com" --env LDAP_ADMIN_PASSWORD="123456" --env LDAP_CONFIG_PASSWORD="123456" --detach osixia/openldap:1.1.8
http://harbor.orientsoft.cn/harbor-v1.4.0/harbor-offline-installer-v1.4.0.tgz
sudo tar -zxvf harbor-offline-installer-v1.5.0.tgz
# step 3：harbor安装（去官方网址下载最新的）
wget http://harbor.orientsoft.cn/harbor-v1.5.0/harbor-offline-installer-v1.5.0.tgz
sudo tar -zxvf harbor-offline-installer-v1.5.0.tgz
# step 4：ldap认证模式
cd harbor
#问题  empty_subj = "/C=/ST=/L=/O=/CN=/" 改为empty_subj = "/"
#相关网址https://github.com/goharbor/harbor/blob/master/docs/configure_https.md
#如果安装一切顺利，通过之前在harbor.cfg配置的hostname即可以访问到前端了，默认登陆用户名密码是admin/Harbor12345
#镜像仓库的用户
$ docker login 192.168.10.10

#将镜像打一个tag,格式必须为 docker push [私库地址]/[项目名]/镜像名称:tag
$ docker tag 镜像id reg.yourdomain.com/myproject/myrepo:mytag

$ docker push reg.yourdomain.com/myproject/myrepo:mytag
