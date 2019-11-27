#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_eureka .
# 推送到阿里云
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_eureka:latest
# 运行
docker run -p 8761:8761 --name springcloud_sell_eureka registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_eureka:latest