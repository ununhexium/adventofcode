#!/usr/bin/env bash


cat "../resources/1.input.sh" | tr -d '\n' | awk '{print $1"\n"}' | bc
