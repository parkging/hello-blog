#!/bin/bash

TIME_NOW=$(date +%c)
PROJECT_ROOT="/home/ec2-user/blog-backend"
JAR_FILE="blog-backend.jar"
APP_LOG_DIR=$PROJECT_ROOT/logs
DEPLOY_LOG=$APP_LOG_DIR/deploy.log

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_PID=$(pgrep -f $JAR_FILE)

# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" | tee -a $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " | tee -a $DEPLOY_LOG
  sudo kill -15 $CURRENT_PID
fi
