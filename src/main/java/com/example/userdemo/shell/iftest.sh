#!/usr/bin/env bash

 . ./func.sh

a=23
b=24
c=23

tessst

if [ $a != $b ];then
    echo $a
    else
    echo $b
fi
echo ========================
if [ $a == $b ]
then
    echo $a
fi
    echo $b
echo ~~~~~~~~~~~~~~~~~~

while test a != b
do
  printf "ad"
  break
done
echo aaaaaaaaa
int=1
while(( $int<=5 ))
do
    echo $int
    let "int++"
done
echo $int

let  as=1+3
let  ds=1-34
echo $as
echo $ds

ss="asd"
case $ss in
"asd") echo "123"
;;
*) echo "asdss"
;;
esac