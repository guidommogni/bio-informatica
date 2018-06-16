#!/usr/bin/env bash

PATH="$PATH:/usr/local/emboss/bin"
export PATH
sudo prosextract ./db/
patmatmotifs -sequence $2 -outfile $3