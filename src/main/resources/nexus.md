1. 创建持久化目录


       $ mkdir /some/dir/nexus-data && chown -R 200 /some/dir/nexus-data

2. 创建镜像并运行


        $ docker run -d -p 8081:8081 --name nexus -v /some/dir/nexus-data:/nexus-data sonatype/nexus
3. 再次运行

          
          $ docker run nexus