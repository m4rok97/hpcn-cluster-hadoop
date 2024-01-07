# Usa la misma imagen base que para el NameNode
FROM hpcnube-base-image:latest
MAINTAINER Tomas <tf.pena@usc.es>

# Cambia al usuario root para realizar operaciones que requieren permisos de root
USER root

# Define valores de entorno
ENV HADOOP_VERSION 3.3.6
ENV LOG_TAG "[BackupNode Hadoop_${HADOOP_VERSION}]:"
ENV BASE_DIR /opt/bd
ENV HADOOP_HOME ${BASE_DIR}/hadoop/
ENV HADOOP_CONF_DIR ${HADOOP_HOME}/etc/hadoop/
ENV DATA_DIR /var/data/hadoop/hdfs

# Crea directorios para los backups del NameNode y haz que sean propiedad del usuario hdadmin.
RUN echo "$LOG_TAG Crea directorios para los backups del NameNode y haz que sean propiedad del usuario hdadmin" && \
    mkdir -p ${DATA_DIR}/backups/dfs/name && chown -R hdadmin:hadoop ${DATA_DIR}


# Copia los ficheros de configuración
COPY Config-files/core-site-backup.xml ${HADOOP_CONF_DIR}/core-site.xml
COPY Config-files/hdfs-site-backup.xml ${HADOOP_CONF_DIR}/hdfs-site.xml

# Establece permisos
RUN chown -R hdadmin:hadoop ${BASE_DIR}

# Expose ports
EXPOSE 50100 50105

# Cambia al usuario hdadmin
USER hdadmin

# Define valores de entorno
ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV HADOOP_HOME ${BASE_DIR}/hadoop/
ENV HADOOP_CONF_DIR ${HADOOP_HOME}/etc/hadoop/
ENV PATH ${PATH}:${HADOOP_HOME}/bin/:${HADOOP_HOME}/sbin/

WORKDIR ${HADOOP_HOME}

CMD ["hdfs", "namenode", "-backup"]
