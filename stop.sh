#!/usr/bin/env bash
kill -9 "$(ps -ef | grep Hause | grep -v 'grep' | awk '{print $2}')"