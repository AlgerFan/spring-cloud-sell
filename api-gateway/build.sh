#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_api_gateway .
# 推送到阿里云
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_api_gateway:latest
# 运行
docker run --name springcloud_sell_api_gateway -p 9000:9000 -d registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_api_gateway:latest