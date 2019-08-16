# machine-event-simulator

This is dummy project to generate dummy events from hypothetic machines to do some technical tests of new employee

# how to use

## prerequesite

You need to have [java8](https://openjdk.java.net/install/) or above installed on your computer. 

You need to have [maven](https://maven.apache.org/install.html) installed.

You need to have [docker](https://docs.docker.com/install/) installed

You need then to start activemq server by running following command in a terminal:

    docker run --name='activemq' -p61616:61616 -it --rm -P webcenter/activemq:5.14.3

If ok you should see the following:

    my-prompt$ docker run --name='activemq' -p61616:61616 -it --rm -P webcenter/activemq:5.14.3
    2019-08-16 14:24:12,209 CRIT Supervisor running as root (no user in config file)
    2019-08-16 14:24:12,209 WARN Included extra file "/etc/supervisor/conf.d/cron.conf" during parsing
    2019-08-16 14:24:12,209 WARN Included extra file "/etc/supervisor/conf.d/activemq.conf" during parsing
    2019-08-16 14:24:12,224 INFO RPC interface 'supervisor' initialized
    2019-08-16 14:24:12,224 CRIT Server 'unix_http_server' running without any HTTP authentication checking
    2019-08-16 14:24:12,224 INFO supervisord started with pid 1
    2019-08-16 14:24:13,228 INFO spawned: 'cron' with pid 16
    2019-08-16 14:24:13,232 INFO spawned: 'activemq' with pid 17
    2019-08-16 14:24:14,610 INFO success: cron entered RUNNING state, process has stayed up for > than 1 seconds (startsecs)
    2019-08-16 14:24:14,610 INFO success: activemq entered RUNNING state, process has stayed up for > than 1 seconds (startsecs)

## how to run

Machines are generating event and publish them on JMS topic called machine.event.

