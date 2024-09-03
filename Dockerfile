## OpenJDK 17을 사용하여 기반 이미지 설정
#FROM openjdk:17-jdk-slim as build
#
## 빌드된 JAR 파일을 복사
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#
## Nginx 설치를 위한 Amazon Linux 2023 이미지 설정
#FROM amazonlinux:2023
#
## 필수 패키지 설치
#RUN yum install -y nginx certbot python3-certbot-nginx
#
## Nginx 설정 파일 복사
#COPY nginx.conf /etc/nginx/nginx.conf
#
## Spring Boot JAR 파일 복사
#COPY --from=build app.jar /app.jar
#
## 인증서 갱신 스크립트 추가
#RUN echo "0 0 * * * root certbot renew --quiet" >> /etc/crontab
#
## Entrypoint 설정: Nginx와 Spring Boot 애플리케이션 실행
#CMD /bin/bash -c "nginx && java -jar -Dspring.profiles.active=prod /app.jar"


# open jdk 17 버전의 환경을 구성한다.
FROM --platform=linux/amd64 openjdk:21

# build가 될 때 JAR_FILE이라는 변수 명에 build/libs/*.jar 선언
# build/libs - gradle로 빌드했을 때 jar 파일이 생성되는 경로임
#ARG JAR_FILE=build/libs/demo2-0.0.1-SNAPSHOT.jar
ARG JAR_FILE=build/libs/*.jar

# JAR_FILE을 agaproject.jar로 복사 (이 부분(.jar)은 개발환경에 따라 다름)
COPY ${JAR_FILE} agagroup.jar

# 운영 및 개발에서 사용되는 환경 설정을 분리한다.
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/agagroup.jar"]