#include <iostream>
#include <stdlib.h>
using namespace std;

#define gridSize 21
unsigned long long grid[gridSize][gridSize];

int main() {
	for(int i = 0; i < gridSize; i++) {
		grid[i][0] = 1;
		grid[0][i] = 1;
	}

	for(int i = 1; i < gridSize; i++) {
		for(int j = 1; j < gridSize; j++) {
			grid[i][j]= grid[i-1][j] + grid[i][j-1];
		}
	}
	
	cout << grid[gridSize-1][gridSize-1] << endl;
}