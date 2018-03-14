$mod842

mov R7, #1

start1: mov R0, #0 ;ekran4
start2: mov R1, #0 ;ekran3
start3: mov R2, #0 ;ekran2
start4: mov R3, #0 ;ekran1
start5: mov R6, #0FFh ;pokazywacz
pokazywacz:

mov A, #10000000b
orl A, R3
mov P2, A

call delay

mov A, #10010000b
orl A, R2
mov P2, A

call delay

mov A, #10100000b
orl A, R1
mov P2, A

call delay

mov A, #10110000b
orl A, R0
mov P2, A

mov R4, #50 ; cokolwiek xd
petla:
setb P2.7
jnb P2.7, setbit
djnz R4, petla
wracam:

cjne R7, #1, pokazywacz
djnz R6, pokazywacz

inc R3
cjne R3, #10, start5
inc R2
cjne R2, #10, start4
inc R1
cjne R1, #10, start3
inc R0
cjne R0, #10, start2
jmp start1

setbit:
mov A, R7
xrl A, #00000001b
mov R7, A
jmp wracam

delay:
mov R5, #0F0h
djnz R5, $
ret

end
