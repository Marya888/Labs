section .data
buf times 256 db 0

section .text
global print_string
global string_equals
global print_newline
global read_word
global read_char
global print_error

string_length:
    xor rax, rax
    .loop:
    cmp byte[rdi + rax], 0
    jz .end_string_length
    inc rax
    jmp .loop
   .end_string_length:  
   ret

print_string:
	call string_length 
	mov rsi, rdi
	mov rdx, rax
	mov rdi, 1
	mov rax, 1
	syscall
    	ret

print_error:
	call string_length 
	mov rsi, rdi
	mov rdx, rax
	mov rdi, 2
	mov rax, 1
	syscall
    ret

print_char:
    push rdi
    mov rdi, rsp
    call print_string
    pop rdi
    ret

print_newline:
    xor rax, rax
    mov rdi, 10
    call print_char
    ret

 print_uint:    
    xor rdx, rdx
    push rdx
    mov rsi, rsp
    times 8 push 0
    mov rax, rdi   
    dec rsi   
   .loop:
   	mov rdx, 0 
   	mov rbx, 10 
   	div rbx 
   	add rdx, 48
   	mov [rsi], dl
   	dec rsi
   	cmp rax, 0
   	jnz .loop
   	inc rsi
   	mov rdi, rsi
   	call print_string   	
   	times 8 pop rax
   	pop rdx
   	xor rax, rax
    ret

print_int:
	push rdx
	mov rax, rdi
	cmp rax, 0
	jl .neg
	call print_uint
	jmp .end
	.neg:
	mov rdi, '-'
	push rax
	call print_char
	pop rax
	neg rax
	mov rdi, rax
	call print_uint
	.end:
	pop rdx
   	 xor rax, rax
    	ret


read_char:
    xor rax, rax
    push rax
    mov rdi, 0
    mov rsi, rsp
    mov rdx, 1
    syscall
    pop rax
    ret 

read_word:
	xor rax, rax
	xor r8, r8
	xor rdi, rdi
	.loopwer:
	call read_char
	cmp al, 0
	je .end_word_0
	cmp al, 10
	je .end_word_0
	cmp r8, 255
	je .end_word_skip	
	
	mov [buf+r8], al
	inc r8
	jmp .loopwer
	
	.end_word_0:
	xor rax, rax
	jmp .end_word
	
	.end_word_skip:	
	.loopwer2:
	call read_char
	cmp al, 0
	je .end_word_1
	cmp al, 10
	je .end_word_1
	jmp .loopwer2
	
	.end_word_1:
	mov rax, 1
	
	.end_word:
	mov [buf+r8+1], al
	mov rdi, buf
	ret
	
    
    


parse_uint:
    push rbx
    xor rax, rax
    xor rsi, rsi
    .loop:
    mov bl, [rdi]
    cmp rbx, 48
    jl .end
    cmp rbx, 57
    jg .end
    inc rdi
    inc rsi
    sub bl, 48
    cmp rsi, 1
    jz .first
    jmp .not_first
    .first:
    mov al, bl
    jmp .loop
    .not_first:
    mov rdx, 10
    mul rdx
    add al, bl
    jmp .loop
    .end:
    mov rdx, rsi
    pop rbx
    ret

parse_int:
    push rbx
    mov bl, [rdi]
    cmp rbx, 45
    jz .int
    call parse_uint
    jmp .end
    .int:
    inc rdi
    call parse_uint
    cmp rdx, 0
    jz .end
    neg rax
    inc rdx
    .end:
    pop rbx
    ret 
    
string_equals:
    push rbx
    xor rcx, rcx
    .loop:
    mov bl, [rdi + rcx]
    mov dl, [rsi + rcx]
    cmp bl, dl
    jz .next_symbol
    jmp .end0
    .next_symbol:    	
    cmp bl, 0
    jz .end1
    inc cl
    jmp .loop
    .end0:
    mov rax, 0
    jmp .end
    .end1:
    mov rax, 1
    .end:
    pop rbx
    ret

string_copy:
	push rbx
	xor rcx, rcx
	.loop:
	mov bl, [rdi + rcx]
	cmp bl, 0
	jz .end
	mov [rsi + rcx], bl
	inc rcx
	jmp .loop
	.end:
	mov [rsi+rcx], bl
	pop rbx
   	ret
