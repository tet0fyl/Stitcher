all: build run

build:
	g++ -O2 -Wall -std=c++11 -o out/out ./src/main.cpp `pkg-config --cflags --libs opencv4`
clean:
	rm -rf out/out
run:
	./out/out