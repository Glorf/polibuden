$mod842
org 0
ljmp start

org 1bh
inc R0
reti

org 100h
start:
setb ea
setb et1
setb tr1
mov tmod, #0
;mov pllcon, #0

mov R3, #0
godziny:
mov R2, #0 ; minuty
minuty:
mov R1, #0 ; sekundy
petla:
mov R0, #0 ; przepelnienia
wyswietl:
mov A, R1
mov B, #10
div AB
orl A, #00010000b
mov P2, A
call delay
mov P2, B
call delay

mov A, R2
mov B, #10
div AB
orl A, #00110000b
mov P2, A
call delay

mov A, B
orl A, #00100000b
mov P2, A
call delay

cjne R0, #255, wyswietl
inc R1
cjne R1, #60, petla
inc R2
inc R4
cjne R2, #60, minuty
inc R3

jmp godziny




delay:
mov R7, #100
djnz R7, $
ret

end
