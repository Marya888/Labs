section .data
 err: db 'lol fail',0
 err2: db 'big buf',0

section .text
	%include "colon.inc"
	%include "words.inc"	
	extern print_string
	extern find_word
	extern print_newline
	extern read_word
	extern read_char
	global _start
	_start:	
		xor rax, rax		
		call read_word   ; adr buf in rdi 
		stop_read:
		
		cmp al, 0
		je .true_word
		push rdi
		mov rdi, err2
		call print_string
		call print_newline
		pop rdi
		.true_word:
		mov rsi, link
		call find_word   ; result in rdi

		stop:
		cmp rdi, 0
		jne .restart
		mov rdi, err
		.restart:
		call print_string
		call print_newline

mov rax, 60 
xor rdi, rdi
syscall
