#!/bin/sh

rm -rf .idea/libraries/*.xml
rm -rf .idea_modules/*.iml
./sbt gen-idea
