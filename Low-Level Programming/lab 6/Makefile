COMPILER=gcc
TARGET=main
CFLAGS= -ansi -pedantic -Wall -Werror
CK= -c
LDK= -o
FILES= main.c mem.c mblocks.c
OFILES= main.o mem.o mblocks.o

main: main.c
	$(COMPILER) $(CK) $(FILES)
	$(COMPILER) $(LDK) $(TARGET) $(OFILES)
	rm -f $(OFILES)
clean:
	rm -f $(TARGET)
