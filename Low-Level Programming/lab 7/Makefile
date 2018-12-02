COMPILER=gcc
TARGET=main
CFLAGS= -ansi -pedantic -Wall -Werror
CFLAGS2=-ggdb -Wall -ansi -pedantic
CK= -c
CC=gcc
ASM=nasm
ASMFLAGS=-f elf64
LDK= -o
FILES= main.c bmp.c
OFILES= main.o bmp.o sse.o

all: main
	./main
	
main: main.o bmp.o sse.o
	$(CC) $(CFLAGS2) $(OFILES) -o main

main.o:
	$(CC) $(CFLAGS2) -c main.c -o main.o
	
bmp.o:
	$(CC) $(CFLAGS2) -c bmp.c -o bmp.o

sse.o:
	$(ASM) $(ASMFLAGS) sse.asm -o sse.o

clean: 
	rm -f main.o sse.o bmp.o main 
	

	
