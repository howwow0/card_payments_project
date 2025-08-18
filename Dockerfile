FROM oscarfonts/h2

COPY init.sql /init.sql

CMD java -cp /opt/h2/bin/h2*.jar org.h2.tools.RunScript \
      -url jdbc:h2:/opt/h2-data/keysdb -user sa -password n4DxQ8vTpZ3 -script /init.sql && \
    java -cp /opt/h2/bin/h2*.jar org.h2.tools.Server \
      -tcp -tcpAllowOthers -tcpPort 9094 -web -webAllowOthers -webPort 81
