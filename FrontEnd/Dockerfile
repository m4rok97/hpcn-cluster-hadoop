# Dockerfile for FrontEnd container

# Use the same base image as the other containers
FROM hpcnube-base-image:latest

# Switch to root user
USER root

# Define environment variables
ENV HADOOP_VERSION 3.3.6
ENV LOG_TAG "[FrontEnd Hadoop_${HADOOP_VERSION}]:"
ENV BASE_DIR /opt/bd
ENV HADOOP_HOME ${BASE_DIR}/hadoop/
ENV HADOOP_CONF_DIR ${HADOOP_HOME}/etc/hadoop/

# Install Java, Maven, Vim, and OpenSSH server
RUN apt-get update && \
    apt-get install -y --no-install-recommends openjdk-8-jdk maven vim openssh-server && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists

# Create an SSH user
RUN useradd -rm -d /home/luser -s /bin/bash -g root -G sudo -u 1000 luser

# Set the SSH user's password
RUN echo 'luser:password' | chpasswd

# Allow SSH access
RUN mkdir /var/run/sshd

# Expose the SSH port
EXPOSE 22

# Switch to luser
USER luser

# Create .bashrc with environment variables for luser
RUN echo 'export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64/' >> /home/luser/.bashrc && \
    echo 'export HADOOP_HOME=/opt/bd/hadoop/' >> /home/luser/.bashrc && \
    echo 'export PATH=${PATH}:${HADOOP_HOME}/bin' >> /home/luser/.bashrc

# Copy Hadoop configuration files
RUN echo "$LOG_TAG Copia los ficheros de configuracion y el script de inicio"
COPY Config-files/core-site.xml ${HADOOP_CONF_DIR}/core-site.xml
COPY Config-files/hdfs-site.xml ${HADOOP_CONF_DIR}/hdfs-site.xml
COPY Config-files/mapred-site.xml ${HADOOP_CONF_DIR}/mapred-site.xml
COPY Config-files/yarn-site.xml ${HADOOP_CONF_DIR}/yarn-site.xml

# Switch to root 
USER root

# Start SSH server
CMD ["/usr/sbin/sshd", "-D"]
