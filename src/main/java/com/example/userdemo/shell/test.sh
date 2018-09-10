#!/usr/bin/env bash
echo "hellp word"
you_name="wangxiao"
my_name="hha"
readonly  you_name
echo $you_name

for file in `ls /home`;do
   echo $file
done

for skill in Ada Coffe Action Java; do
    echo "I am good at ${skill}Script"
done

echo ${my_name}
unset my_name
echo ${my_name}

you_new_name="hahha"
you_def_name='ummm ${you_new_name}'
you_sess_name="hhhh ${you_new_name}"
echo $you_def_name
echo $you_sess_name

echo ${#you_sess_name}
echo ${you_sess_name:5:3}


names=($you_name $you_sess_name)
echo ${names[0]}
echo ${names[2]}
echo ${names[@]}
echo ${#names[1]}
:<<EOF
asdasd
EOF
echo ${#names[@]}


echo "执行的文件名：$0"
echo "this is my name $1"
echo "this is my name $2"
echo "this is my name $3"
echo "参数个数为：$#";
echo "传递的参数作为一个字符串显示：$*";
echo "传递的参数显示：$@";
echo "传递的参数显示：$*";

:<<EOF
不同点：只有在双引号中体现出来。假设在脚本运行时写了三个参数 1、2、3，，则 " * " 等价于 "1 2 3"（传递了一个参数）
，而 "@" 等价于 "1" "2" "3"（传递了三个参数）。
EOF
for lss in "$*"; do
  echo $lss
done
for lss in "$@"; do
  echo $lss
done


#echo `expr index ${you_sess_name} hhhh`
var=`expr 2 == 2`
echo $var

a=23
b=24
c=23

if [ $a != $b ];then
    echo $a
fi
    echo $b
if [ $a == $b ]
then
    echo $a
fi
    echo $b

read named
echo $named "dddasdasd"

echo -e  "OK \n"
echo -e "hhh \c"
echo "aaa"
echo "It is a test" > myfile
echo `date`

if test a == b; then
    echo "asd"
   else
    echo "dasd"
fi
