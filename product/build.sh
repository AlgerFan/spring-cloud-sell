#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
docker build -t springcloud_sell_product .
# 推送到阿里云
sudo docker tag [imagesId] registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_product:latest
sudo docker push registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_product:latest
# 运行
docker run --name springcloud_sell_product -p 8082:8082 -d registry.cn-hangzhou.aliyuncs.com/algerfan/springcloud_sell_product:latest