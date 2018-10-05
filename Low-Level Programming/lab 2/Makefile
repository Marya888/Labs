# цель: зависимости
# [tab] команда

# make -f MyMakefile
# all - это цель по умолчанию для мейкфайла, которая будет выполняться, если никакая другая цель не указана явно
# при вызове make выполнятся все указанные в файле зависимости этой цели (по умолчанию all)

#	Процесс сборки
#   Компилятор берет файлы с исходным кодом и получает из них объектные файлы.
#   Затем линковщик берет объектные файлы и получает из них исполняемый файл. 
#   Сборка = компиляция + линковка.

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
	
