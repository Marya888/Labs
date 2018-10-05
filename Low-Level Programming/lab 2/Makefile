FLAGS=-f elf64
ASM=nasm
all: main
	./main
main: main.o dict.o lib.o
	ld -o main main.o dict.o lib.o
main.o: main.asm colon.inc words.inc
	$(ASM) $(FLAGS) main.asm -o main.o
lib.o: lib.inc
	$(ASM) $(FLAGS) lib.inc -o lib.o
dict.o: dict.asm
	$(ASM) $(FLAGS) dict.asm -o dict.o
clean:
	rm -f main.o lib.o dict.o main
	
