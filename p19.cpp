#include <iostream>
#include <stdlib.h>
#include <string>
using namespace std;
int number_of_days = (100 * 365) + (100/4); //100years + 25 leap years
int day = 0; //0 == tuesday, 1 == wednesday, ..., 5 == sunday, 6 == monday
int number_of_sundays = 0;

string getDay() {
	switch(day) {
		case 0:
			return "tuesday";

		case 1:
			return "wednesday";

		case 2:
			return "thursday";

		case 3:
			return "friday";

		case 4:
			return "saturday";

		case 5:
			number_of_sundays++;
			return "sunday";

		case 6:
			return "monday";
	}
}

void changeDate(int num) {
	getDay();
	number_of_days -= num;
	day += num%7;
	day %= 7;
}

int main() {
	//1 Jan 1901 to 31 Dec 2000 sunday on the first of a month
	//1 Jan 1901 = tuesday
	int year = 1901;
	while (number_of_days) {
		//january - 31
		changeDate(31);

		//february - 28/29
		if (year%4 == 0) {
			changeDate(29);
		}
		else {
			changeDate(28);
		}

		//march - 31
		changeDate(31);

		//april - 30
		changeDate(30);

		//may - 31
		changeDate(31);

		//june - 30
		changeDate(30);

		//july - 31
		changeDate(31);

		//august - 31
		changeDate(31);

		//september - 30
		changeDate(30);

		//october - 31
		changeDate(31);

		//november - 30
		changeDate(30);

		//december - 31
		changeDate(31);

		year++;
		// cout << "year: " << year << endl;
		// cout << "days left: " << number_of_days <<endl;
	}
	cout << number_of_sundays << endl;	
}