$mod842

LCD4 equ P2.1
LCD5 equ P2.0
LCD6 equ P2.3
LCD7 equ P2.2
LCDE equ P2.5
LCDRW equ P2.4
LCDRS equ P2.7
LCD_PORT equ P2
LCD_DATA equ 30h

org 0h
ljmp start

org 100h
    mov R0, #0
start:
        MOV LCD_PORT,#00h        
        
        MOV LCD_DATA,#33h ;Pierwsze słowo dodatkowe progr. LCD 
        ACALL LCD_WRITE4  ;Przslanie 4 bit starszych slowa
        ACALL DELAY  

        ACALL LCD_WRITE4  ;Przslanie 4 bit mlodszych slowa 
        ACALL DELAY

        MOV LCD_DATA,#32h ;Drugie słowo dodatkowe progr. LCD
        ACALL LCD_WRITE4
        ACALL DELAY
        ACALL LCD_WRITE4
        ACALL DELAY  
                
        MOV LCD_DATA,#28h ;Cterobitowy interfejs (F) 
        ACALL LCD_WRITE4                   
        ACALL LCD_WRITE4
        ACALL DELAY  
                
        MOV LCD_DATA,#01h ;Czyszcz. ekranu, kursor na 0
        ACALL LCD_WRITE4  ;      (A)
        ACALL LCD_WRITE4
        ACALL DELAY
        
        MOV LCD_DATA,#0Eh ;Wlaczenie ekranu i kursora  
        ACALL LCD_WRITE4   ;mrugajacego        (D)
        ACALL LCD_WRITE4
        ACALL DELAY
		
        MOV LCD_DATA,#06h ;Czyszcz. ekranu, kursor na 0
        ACALL LCD_WRITE4  ;      (A)
        ACALL LCD_WRITE4
        ACALL DELAY	

mov A, R0
mov R1, A
spacja:
mov  LCD_DATA, #' '
call LCD_DISPLAY_CHAR
djnz R1, spacja

mov LCD_DATA, #'1'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'3'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'2'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'1'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'9'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'1'
call LCD_DISPLAY_CHAR

call LCD_NEXT_LINE

mov A, R0
mov R1, A
spacja2:
mov  LCD_DATA, #' '
call LCD_DISPLAY_CHAR
djnz R1, spacja2

mov LCD_DATA, #'B'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'i'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'e'
call LCD_DISPLAY_CHAR
mov LCD_DATA, #'n'
call LCD_DISPLAY_CHAR

mov R3, #10
del2: mov R4, #255
del: mov R5, #255
djnz R5, $
djnz R4, del
djnz R3, del2
inc R0
cjne R0, #15, drop
mov R0, #0
drop:
ljmp start

lcd_write4:
push acc
clr lcde
mov A, LCD_DATA
rlc A
mov LCD7, C
rlc A
mov LCD6, C
rlc A
mov LCD5, C
rlc A
mov LCD4, C
setb LCDE
clr LCDE
mov LCD_DATA, A

pop acc
ret

delay:
push P2        ; Odczyt BF
        clr     LCDRS
        setb    lcd7
        setb    lcdrw
        setb    lcde
        jb      LCD7,$  ;Oczekiwanie na zgłoszenie gotowosci

        pop     LCD_PORT
        ret	

LCD_DISPLAY_CHAR:
setb LCDRS
acall lcd_write4
acall lcd_write4
acall delay

ret

LCD_NEXT_LINE:
mov P2, #0h
mov LCD_DATA, #0C0h
acall lcd_write4
acall lcd_write4
acall delay

ret

end
