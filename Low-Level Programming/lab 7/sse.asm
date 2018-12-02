section .text
global sse
global inc_a
global inc_a_2
global inc_pix
; rdi = x, rsi = y
sse:
	movdqa xmm0, [rdi]
	mulps xmm0, [rsi]
	addps xmm0, [rsi]
	movdqa [rdi], xmm0
	ret


inc_a: 	
	xor rax, rax
	mov rax, [rdi]
	inc rax
	mov [rdi], rax
	ret
	
inc_pix:
	stop:
;	xor rax, rax
;	mov rax, [rdi]
	movdqa xmm0, [rdi]
	movdqa xmm1, [rdi+1*16]
	movdqa xmm2, [rdi+2*16]
	movdqa xmm3, [rsi]
	movdqa xmm4, [rsi+1*16]
	movdqa xmm5, [rsi+2*16]
	mulps xmm0, xmm3
	mulps xmm1, xmm4
	mulps xmm2, xmm5
	addps xmm0, xmm1
	addps xmm0, xmm2
	movdqa [rdi], xmm0
	movdqa [rdi+1*16], xmm1
	movdqa [rdi+2*16], xmm2
	stop1:
	ret
