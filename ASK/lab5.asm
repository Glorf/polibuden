$mod842

org 0
ljmp start

org 03h
call delay
inc R4
cjne R4, #03, koniec 
call chngstr
mov R4, #0
koniec:
reti

org 100h
start:
mov PLLCON, #0
mov R2, #0 ;COUNTER
mov R3, #0 ; kierunek - 0 w gore 1 w dol
mov R4, #0 ; #ZLICZACZ DO INTERRUPTOW
setb ea
setb ex0
inkrementor:
mov R5, #15
wyswietlacz:
call wyswietl
djnz R5, wyswietlacz
call incordec
ljmp inkrementor

delay:
mov R0, #50
del0:
mov R1, #0dfh
djnz R1, $
djnz R0, del0
ret

chngstr:
cjne R4, #0, jeden 
mov R4, #1
ret
jeden:
mov R4, #0
ret

incordec:
cjne R4, #1, dodaj
cjne R2, #0, konodejm
mov R2, #99
ret
konodejm:
dec R2
ret
dodaj:
cjne R2, #99, kondodaj
mov R2, #0
ret
kondodaj:
inc R2
ret

wyswietl:
mov A, R2
mov B, #10
div AB
mov P2, B
call delay
orl A, #00010000b
mov P2, A
call delay
ret 

end
