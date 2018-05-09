$mod842

org 0
ljmp start

org 33h
mov A, ADCDATAL
swap A
anl A, #00001111b
mov R0, A

mov A, ADCDATAH
swap A
anl A, #11110000b
orl A, R0
mov R0, A
reti

org 100h
start:
setb ea
setb eadc
mov ADCCON1, #10000010b
setb tr2

loop:
;wyswietlacz1
call delay
mov B, #10
mov A, R0
div AB
mov R1, A
mov P2, B

;wyswietlacz2
call delay
mov B, #10
mov A, R1
div AB
mov R1, A
mov A, B
orl A, #00010000b
mov P2, A

;wyswietlacz3
call delay
mov A, R1
orl A, #00100000b
mov P2, A

ljmp loop

delay:
mov R6, #1
lp1: mov R5, #01Bh
lp2: mov R4, #100
djnz R4, $
djnz R5, lp2
djnz R6, lp1
ret

end
