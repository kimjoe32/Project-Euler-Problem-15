#include <iostream>
#include <fstream>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
using namespace std;

#define DEBUG 0

int main() {
	int cache[1000001]; cache[1]=1;
	int bestNumber = 0;
	int longestSequence = 0;
	long sequence =0;

	for(int i = 2; i < 1000000; i++) {
		sequence = i;
		int steps = 0;
		while (sequence != 1 && sequence >= i) {
			steps++;
			sequence = (sequence %2 )? sequence = 3*sequence +1 : sequence /=2;
		}

		cache[i] = steps + cache[sequence];
		
		if (cache[i] >= longestSequence ) {
			bestNumber = i;
			longestSequence = cache[i];
		}
	}

	cout << bestNumber << " " << longestSequence << endl;
}