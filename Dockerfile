# 以jdk8为基础镜像
FROM openjdk:8
# 描述
LABEL description="Java 8"
# 暴露接口
EXPOSE 8181
# 将主机中的jar包添加到镜像中
COPY /target/ems-boot-0.0.1-SNAPSHOT.jar .
# 运行jar包
CMD ["java", "-jar","ems-boot-0.0.1-SNAPSHOT.jar"]