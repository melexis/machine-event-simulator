# machine-event-simulator

This is dummy project to generate dummy events from fake machines to do some technical tests of new employee

# how to use

## prerequisite

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

From your favorite IDE (like intellij), just run the class GeneratorMain with following arguments:

    tcp://localhost:61616 machine.event 10 150

This will connect to local activemq server you started and generator on queue machine.event 10 messages with random timing between each of few seconds that simulates the normal load expected on production for 150 machines given as parameter (which is roughly the amount of running machines on our production floor).

If add debug at end of command then it will consume the produced message (to confirm setup is working fine):
    
    tcp://localhost:61616 machine.event 10 150 debug

