#include <iostream>
#include <stdlib.h>
#include <algorithm>
using namespace std;
#define GRIDSIZE 15

int arr[GRIDSIZE][GRIDSIZE] = {
					// {1,-1,-1,-1},
					// {2,3,-1,-1},
					// {4,5,6,-1},
					// {7,8,9,10}
					{75, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{95, 64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{17, 47, 82, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{18, 35, 87, 10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{20,  4, 82, 47, 65, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{19,  1, 23, 75,  3, 34, -1, -1, -1, -1, -1, -1, -1, -1, -1},
					{88,  2, 77, 73,  7, 63, 67, -1, -1, -1, -1, -1, -1, -1, -1},
					{99, 65,  4, 28,  6, 16, 70, 92, -1, -1, -1, -1, -1, -1, -1},
					{41, 41, 26, 56, 83, 40, 80, 70, 33, -1, -1, -1, -1, -1, -1},
					{41, 48, 72, 33, 47, 32, 37, 16, 94, 29, -1, -1, -1, -1, -1},
					{53, 71, 44, 65, 25, 43, 91, 52, 97, 51, 14, -1, -1, -1, -1},
					{70, 11, 33, 28, 77, 73, 17, 78, 39, 68, 17, 57, -1, -1, -1},
					{91, 71, 52, 38, 17, 14, 91, 43, 58, 50, 27, 29, 48, -1, -1},
					{63, 66,  4, 68, 89, 53, 67, 30, 73, 16, 69, 87, 40, 31, -1},
					{ 4, 62, 98, 27, 23,  9, 70, 98, 73, 93, 38, 53, 60,  4, 23},
				};

int findMaxLine(int row, int column) {
	int left = 0, right = 0;
	// cout << row <<", " << column << "\t" << arr[row][column] << endl;
	if (row >= GRIDSIZE - 2) 
	 	return arr[row][column] + max(arr[row+1][column], arr[row+1][column+1]);

	//left side
	left = arr[row][column] + findMaxLine(row+1, column);

	//right side
	right = arr[row][column] + findMaxLine(row+1, column+1);
	// cout << "\tleft = " << left <<endl;
	// cout << "\tright = " << right <<endl;

	return max(left, right);
}

int main() {
	cout << findMaxLine(0,0) << endl;
}