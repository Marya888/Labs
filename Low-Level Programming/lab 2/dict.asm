section .text
	global find_word
	extern string_equals
	find_word:
		xor rax, rax
		.loop:
			cmp rsi, 0		
			je .end0
			;push rsi
			add rsi, 8
			call string_equals
			cmp rax, 1
			je .end1
			sub rsi, 8
			;pop rsi
			mov rsi, [rsi]			
		jmp .loop
		.end1:
			lea rdi, [rsi+rcx+1]
			ret
		.end0:
			mov rdi, 0
			ret
