#!/bin/bash
rm -rf bin &&
ant debug &&
adb install -r bin/Integral-debug.apk &&
adb shell am start -a android.intent.action.main -n org.beide.integral/org.beide.integral.Integral
