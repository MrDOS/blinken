%32.dll: CC := i686-w64-mingw32-gcc
CFLAGS := -std=gnu99 -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/win32"
LDFLAGS := -lDasHard

all: mydmx32.dll

clean:
	-rm mydmx*.dll mydmx*.o

%32.o: %.c
	$(CC) -c $(CFLAGS) -o $@ $^

%32.dll: %32.o
	$(CC) -shared -o $@ $^ -L. -lDasHard