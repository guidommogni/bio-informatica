#!/bin/bash


PATH="$PATH:/usr/local/emboss/bin"
export PATH
sudo prosextract ./db/
patmatmotifs -sequence SLC45A2.fasta -outfile SLC45A2_eje5.txt
patmatmotifs -sequence OCA2.fasta -outfile OCA2_eje5.txt
patmatmotifs -sequence TYRP1.fasta -outfile TYRP1_eje5.txt
patmatmotifs -sequence TYR.fasta -outfile TYR_eje5.txt