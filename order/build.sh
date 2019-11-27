#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_order .
# 推送到阿里云
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_order:latest
# 运行
docker run --name springcloud_sell_order -p 8081:8081 -d registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_order:latest