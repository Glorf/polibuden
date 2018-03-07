$mod842
start: mov R0, #10000000b ;tutaj kolejne wyswietlacze
startwys: mov R1, #10000000b ;tutaj wypisuje cyferki kolejne
startcyf:

mov A, R0
orl A, R1
anl A, #00111111b
mov P2, A

call delay

inc R1
cjne R1, #10001010b, startcyf ;jeśli 10 to wyczyść!
mov A, R0
add A, #00010000b ;zwiększ ekran
mov R0, A
cjne R0, #00000000b, startwys ;jeśli przejdzie poza zakres
jmp start


delay:
mov R4, #002
del1: mov R2, #0ffh
del0: mov R3, #0ffh
djnz R3, $
djnz R2, del0
djnz R4, del1
ret

end
