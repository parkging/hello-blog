#!/bin/bash

PROJECT_ROOT="/home/ec2-user/prod-blog-backend"
JAR_FILE="blog-backend-0.0.1-SNAPSHOT-plain.jar"
JAR_FILE_WITH_PATH="$PROJECT_ROOT/prod-blog-backend/build/libs/$JAR_FILE"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# jar 파일 실행
chmod +x $JAR_FILE_WITH_PATH
echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE_WITH_PATH --spring.profiles.active=prod > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG