$mod842

org 0
ljmp start

org 53h
mov sec, #0
inc R0
cjne R0, #6, konint1
mov R0, #0
inc R1
cjne R1, #10, konint1
mov R1, #0
inc R2
cjne R2, #6, konint1
mov R2, #0
inc R3
cjne R3, #9, konint1
mov R3, #0
inc R4
konint1:
cjne R4, #2, konint2
cjne R3, #4, konint2
mov R3, #0
mov R4, #0
konint2:
reti

org 100h
start:
mov R0, #0 ;dziesiatki sekund
mov R1, #9 ;minuty1
mov R2, #5 ;minuty2
mov R3, #3 ;godziny1
mov R4, #2 ;godziny2
setb ea
mov TIMECON, #00010011b
mov IEIP2, #01000100b
mov INTVAL, #10
mov SEC, #0

loop:
mov P2, SEC
call delay
mov A, R0
orl A, #00010000b
mov P2, A
call delay
mov A, R1
orl A, #01100000b
mov P2, A
call delay
mov A, R2
orl A, #00110000b
mov P2, A
call delay
mov R6, SEC
cjne R6, #5, loop
call godziny
jmp loop

godziny:
mov R6, #5
godzloop1:
mov R5, #100
godzloop:
mov P2, R3
call delay
mov A, R4
orl A, #00010000b
mov P2, A
call delay
djnz R5, godzloop
djnz R6, godzloop1
ret

delay:
mov R7, #20
djnz R7, $
ret

end
