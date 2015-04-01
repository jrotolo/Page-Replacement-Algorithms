#!/bin/bash
#
#  Name:       Rotolo, Jarrod
#  Project:    PA-2 (Page Replacement Algorithms)
#  File:       run.sh
#  Instructor: Feng Chen
#  Class:      cs4103-sp15
#  LogonID:    cs410331
#

javac *.java
clear
java prog2.Main -s $1 -n $2
rm -rf *.class
