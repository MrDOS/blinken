%32.dll: CC := i686-w64-mingw32-gcc
%64.dll: CC := x86_64-w64-mingw32-gcc
CFLAGS := -std=gnu99 -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/win32"

all: mydmx32.dll mydmx64.dll

clean:
	-rm *.dll *.o

%32.o: %.c
	$(CC) -c $(CFLAGS) -o $@ $^

%64.o: %.c
	$(CC) -c $(CFLAGS) -o $@ $^

%32.dll: %32.o
	$(CC) -shared -o $@ $^

%64.dll: %64.o
	$(CC) -shared -o $@ $^