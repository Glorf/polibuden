mkdir -p input

for n in 50 100 150 200 250 300 350 400 450 500
do
  echo "$n" > input/n$n.txt
  ./generate $n | shuf >> input/n$n.txt
done
