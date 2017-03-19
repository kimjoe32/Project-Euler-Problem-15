#include <iostream>
#include <stdlib.h>
#include <string>
#include <math.h>
#include <deque>
using namespace std;

int main() {
	unsigned long long t = pow(2, 63);

	string numStr = to_string(t);
	deque<unsigned long> numdq;
	for (auto it = numStr.begin(); it != numStr.end(); it++) {
		numdq.push_back(*it - '0');
	}
	for (int i =0; i < 1000-63; i++) {
		for (auto it = numdq.begin(); it !=  numdq.end(); it++) {
			(*it) += (*it);	
		}
		for (auto it = numdq.rbegin(); it !=  numdq.rend()-1; it++){
			*(it+1) += (*it) / 10;
			(*it) %= 10;
		}
		while (1) {
			auto it = numdq.begin();
			if ((*it) >= 10) {
				numdq.push_front((*it) /10);
				(*it) %= 10;
			} 
			else break;
		}
	}
	string str = "10715086071862673209484250490600018105614048117055336074437503883703510511249361224931983788156958581275946729175531468251871452856923140435984577574698574803934567774824230985421074605062371141877954182153046474983581941267398767559165543946077062914571196477686542167660429831652624386837205668069376";
	t = 0;
	for (auto it = numdq.begin(); it !=  numdq.end(); it++) {
		t += (*it);
	}	

	cout << t <<endl;
}