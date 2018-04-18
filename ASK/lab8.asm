$mod842
org 0
start:
mov P2, #0

loop:
mov P3, #11101111b
call delay
jb P3.2, after1
mov R0, #7
after1:
jb P3.1, after11
mov R0, #4
after11:

mov P3, #11011111b
call delay
jb P3.1, after22
mov R0, #5
after22:
jb P3.2, after2
mov R0, #8
after2:
jb P3.3, after3
mov R0, #0
after3:

mov P3, #10111111b
call delay
jb P3.1, after44
mov R0, #6
after44:
jb P3.2, after4
mov R0, #9
after4:

call pisz
ljmp loop

delay:
mov R6, #50
djnz R6, $
ret

pisz:
mov P2, R0
ret

end
