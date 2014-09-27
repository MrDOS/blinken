%.dll: CC := i686-w64-mingw32-gcc
CFLAGS := -std=gnu99 -I"${JAVA_HOME}/include" -I"${JAVA_HOME}/include/win32"

all: DasJni.dll

clean:
	-rm DasJni.dll

%.o: %.c
	$(CC) -c $(CFLAGS) -o $@ $^

%.dll: %.o
	$(CC) -shared -o $@ $^ -L. -lDasHard2006