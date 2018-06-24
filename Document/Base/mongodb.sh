#!/bin/sh
#
# mongod - Startup script for mongod
#
# chkconfig: - 85 15
# description: Mongodb database.
# processname: mongod
# Source function library

# Source function library.
. /etc/rc.d/init.d/functions

# Source networking configuration.
. /etc/sysconfig/network

# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0

# Mongod
mongod="/usr/local/src/mongodb/mongodb-3.6.3/bin/mongod"
prog=$(basename $mongod)

# CONFIG_FILE
CONFIG_FILE="/usr/local/src/mongodb/mongodb-3.6.3/conf/mongodb.conf"

lockfile=/var/lock/subsys/mongod

start() {
    echo -n $"Starting $prog: "
    daemon $mongod --config $CONFIG_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}

stop() {
    echo -n $"Stopping $prog: "
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}

restart() {
    stop
    sleep 1
    start
}

rh_status() {
    status $prog
}

rh_status_q() {
    rh_status >/dev/null 2>&1
}

case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart)
        $1
        ;;
    status)
        rh_status
        ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart}"
        exit 2
esac