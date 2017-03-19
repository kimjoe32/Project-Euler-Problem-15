#include <iostream>
#include <stdlib.h>
#include <algorithm>
#include <fstream>
using namespace std;
#define GRIDSIZE 100

int arr[GRIDSIZE][GRIDSIZE];
bool visited[GRIDSIZE][GRIDSIZE];
int subValues[GRIDSIZE][GRIDSIZE];

int findMaxLine(int row, int column) {
	int left = 0, right = 0;
	if (visited[row][column]) return subValues[row][column];
	if (row >= GRIDSIZE - 2) 
	 	return arr[row][column] + max(arr[row+1][column], arr[row+1][column+1]);

	//left side
	left = arr[row][column] + findMaxLine(row+1, column);

	//right side
	right = arr[row][column] + findMaxLine(row+1, column+1);
	
	visited[row][column] = 1;
	subValues[row][column] = max(left, right);
	return max(left, right);
}

int main() {
	fstream inputFile;
	inputFile.open("input.txt");
	for(int i = 0; i < GRIDSIZE; i++) {
		for(int j = 0; j < GRIDSIZE; j++) {
			if (j <= i) inputFile >> arr[i][j];
			else arr[i][j] = -1;
			visited[i][j]=0;
			subValues[i][j]=0;
		}
	}

	inputFile.close();
	cout << findMaxLine(0,0) << endl;
}