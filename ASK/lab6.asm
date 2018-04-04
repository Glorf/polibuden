$mod842
org 0
ljmp start

org 03h
mov R0, #1
reti

org 0bh
inc R2
reti

org 100h
start:
setb ea
setb ex0
mov tmod, #01
setb et0
poczatek: 
mov R0, #0
mov P2, #00010010b ;sam ziel, piesi czerw
cjne R0, #1, $ ;czekamy na przerwanie
call delay
mov P2, #01000010b ;sam zolty, piesi czerw
call delay
mov P2, #10000010b ;sam czerw, piesi czerw
call delay
mov P2, #10000001b ;sam czerw piesi ziel

mov R1, #4
del1: call delay
djnz R1, del1

mov R1, #5
miganie:
mov P2, #10000000b ;miga ziel
call delay
mov P2, #10000001b ; pali ziel
call delay
djnz R1, miganie

mov P2, #10000010b ; sam czerw piesi czerw
call delay
mov P2, #11000010b ; sam zolty piesi czerw
call delay
ljmp poczatek

delay:
mov R2, #0
setb tr0
cjne R2, #20, $
clr tr0
ret

end
