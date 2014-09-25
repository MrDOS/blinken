CC := i686-w64-mingw32-gcc
CFLAGS := -std=gnu99 -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/win32"

all: mydmx.dll

clean:
	-rm *.dll *.o

%.dll: %.o
	$(CC) -shared -o $@ $^