START:
mov P2, #00000000b
call DELAY
mov P2, #00000000b
call DELAY
mov P2, #00000000b
call DELAY
mov P2, #00000000b
call DELAY
jmp START

DELAY:
mov R1, #0B0h
djnz R1, $
ret

end
