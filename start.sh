#!/bin/bash

TIME_NOW=$(date +%c)
PROJECT_ROOT="/home/ec2-user/blog-backend"
APP_LOG_DIR=$PROJECT_ROOT/logs
APP_LOG=$APP_LOG_DIR/application.log
ERROR_LOG=$APP_LOG_DIR/error.log
DEPLOY_LOG=$APP_LOG_DIR/deploy.log
JAR_FILE="blog-backend.jar"
JAR_FILE_WITH_PATH="$PROJECT_ROOT/build/libs/$JAR_FILE"

cd $PROJECT_ROOT

# Check if log dir exists
if [ ! -d $APP_LOG_DIR ]; then
  mkdir $APP_LOG_DIR
fi

# Chekc if env exists
if [ -z "$SPRING_PROFILES_ACTIVE" ]; then
  echo "$TIME_NOW > (ERROR) SPRING_PROFILES_ACTIVE not exists, export system environment like \"export SPRING_PROFILES_ACTIVE=local\" or \"export SPRING_PROFILES_ACTIVE=prod\"" | tee -a DEPLOY_LOG
  exit 1
fi

# jar 파일 실행
chmod +x $JAR_FILE_WITH_PATH
echo "$TIME_NOW > $JAR_FILE 파일 실행" | tee -a $DEPLOY_LOG
sudo nohup java -jar $JAR_FILE_WITH_PATH --spring.profiles.active=$SPRING_PROFILES_ACTIVE >> $APP_LOG 2>> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." | tee -a $DEPLOY_LOG
