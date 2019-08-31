#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_user .
# 推送到阿里云
sudo docker tag [imagesId] registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_user:latest
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_user:latest
# 运行
docker run --name springcloud_sell_user -p 8083:8083 -d registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_user:latest