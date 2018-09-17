#!/bin/zsh
# 环境初始化：指定用户需要有sudo权限
# Author:   Daniel
# Date:     2017/12/20
# Version:  1.0

# step 1: 移除旧的docker
sudo apt remove docker-ce
sudo rm -rf /var/lib/docker
# step 1: 安装相关组件和配置yum源
sudo apt update -Y
# step 2: 配置缓存
sudo apt install apt-transport-https ca-certificates curl software-properties-common
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu bionic stable"
sudo apt update
# step 3: 执行安装
sudo apt install docker-ce
# step 4: 配置镜像下载加速
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://3itj1ym2.mirror.aliyuncs.com"],
  "insecure-registries": ["192.168.0.7:5000","192.168.168.131:5000"],
  "hosts": [
    "tcp://0.0.0.0:2375",
    "unix:///var/run/docker.sock"
  ]
}
EOF
# step 5: 重启daemon-reload
sudo systemctl daemon-reload
# step 6: 启动docker并配置开机启动
sudo systemctl start docker
sudo systemctl enable docker
# step 7: 配置当前用户对docker命令的执行权限
sudo groupadd docker
sudo gpasswd -a ${USER} docker
sudo usermod -aG docker ${USER}
sudo systemctl restart docker


