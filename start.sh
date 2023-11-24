#!/bin/bash

PROJECT_ROOT="/home/ec2-user/blog-backend"
APP_LOG_DIR=$PROJECT_ROOT/logs
APP_LOG_FILE=$APP_LOG_DIR/application.log

# 임시 추가
echo $BLOG_OP_ENV >> blog_op_env.log
# 임시 추가

#if [ -z "$BLOG_OP_ENV" ]; then
#  export BLOG_OP_ENV=local;
#fi

cd $PROJECT_ROOT

# Check if application log file exists
if [ ! -f APP_LOG_FILE ]; then
  mkdir $APP_LOG_DIR
  touch $APP_LOG_FILE
fi

docker-compose up -d
tail -f ./logs/application.log

#### jar 배포 주석처리 start ####

#PROJECT_ROOT="/home/ec2-user/prod-blog-backend"
#JAR_FILE="blog-backend.jar"
#JAR_FILE_WITH_PATH="$PROJECT_ROOT/build/libs/$JAR_FILE"

#APP_LOG="$PROJECT_ROOT/application.log"
#ERROR_LOG="$PROJECT_ROOT/error.log"
#DEPLOY_LOG="$PROJECT_ROOT/deploy.log"
#
#TIME_NOW=$(date +%c)
#
## jar 파일 실행
#chmod +x $JAR_FILE_WITH_PATH
#echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG
#sudo nohup java -jar $JAR_FILE_WITH_PATH --spring.profiles.active=prod > $APP_LOG 2> $ERROR_LOG &
#
#CURRENT_PID=$(pgrep -f $JAR_FILE)
#echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG

#### jar 배포 주석처리 end ####