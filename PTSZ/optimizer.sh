#!/bin/bash

function score {
    TOTAL=0
    for names in "aw" "dg" "mb" "mf" "mz" "pp" "rr" "td" "wt" "js"
    do
        for numbers in 50 100 150 200 250 300 350 400 450 500
        do
            RESULT=$(./list_that_hurts/cmake-build-debug/list_that_hurts input/$names/n$numbers.txt $1 $2 $3 $4 $5 $6 | head -1)
            RESULT=$(($RESULT*50/$numbers))
            TOTAL=$((TOTAL+RESULT))
        done
    done
    echo $TOTAL
}

score $1 $2 $3 $4 $5 $6

if false; then
BEST=2147483647

ALL=$((20*20*20))
STEP=0
for pj in `seq -1 .1 1`
do
for dj in `seq -1 .1 1`
do
for rj in `seq -1 .1 1`
do
    STEP=$(($STEP + 1))
    RESULT=$(score $dj $rj $pj 0 0 0)
    if (( $RESULT < $BEST ))
    then
        BEST=$RESULT
        echo "$dj $rj $pj $RESULT $(echo "$STEP/$ALL*100" | bc -l)%"
    fi
    if (( $STEP % 500 == 0)) ; then
        echo "$(echo "$STEP/$ALL*100" | bc -l)%"
    fi
done
done
done
fi
