#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_config .
# 推送到阿里云
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_config:latest
# 运行
docker run -p 8080:8080 --name springcloud_sell_config registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_config:latest