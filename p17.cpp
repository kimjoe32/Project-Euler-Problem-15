#include <iostream>
#include <fstream>
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
using namespace std;

#define DEBUG 0

int getOnes(char c);
int getTens(char c);
int getTeens(char c);
int getHundreds(char c);
int getCount(string str);

int main() {
	unsigned long totalCount =0 ;
	for(int i = 1; i <1000; i++)
	{
		int t = getCount(to_string(i));
		totalCount+= t;
	}
	totalCount += (3 + string("thousand").size());

	cout << totalCount << endl;
}

int one = string("one").size(),
	two = string("two").size(),
	three = string("three").size(),
	four = string("four").size(),
	five = string("five").size(),
	six = string("six").size(),
	seven = string("seven").size(),
	eight = string("eight").size(),
	nine = string("nine").size(),
	ten = string("ten").size(),
	eleven = string("eleven").size(),
	twelve = string("twelve").size(),
	thirteen = string("thirteen").size(),
	fourteen = string("fourteen").size(),
	fifteen = string("fifteen").size(),
	sixteen = string("sixteen").size(),
	seventeen = string("seventeen").size(),
	eighteen = string("eighteen").size(),
	nineteen = string("nineteen").size(),
	twenty = string("twenty").size(),
	thirty = string("thirty").size(),
	forty = string("forty").size(),
	fifty = string("fifty").size(),
	sixty = string("sixty").size(),
	seventy = string("seventy").size(),
	eighty = string("eighty").size(),
	ninety = string("ninety").size(),
	hundred = string("hundred").size(),
	thousand = string("thousand").size();

int getOnes(char c) {
	switch (c){
		case '0': return 0;
		case '1': return one;
		case '2': return two;
		case '3': return three;
		case '4': return four;
		case '5': return five;
		case '6': return six;
		case '7': return seven;
		case '8': return eight;
		case '9': return nine;
	}
}
int getTens(char c) {
	switch (c){
		case '0': return 0;
		case '1': return ten;
		case '2': return twenty;
		case '3': return thirty;
		case '4': return forty;
		case '5': return fifty;
		case '6': return sixty;
		case '7': return seventy;
		case '8': return eighty;
		case '9': return ninety;
	}
}
int getTeens(char c) {
	switch (c){
		case '1': return eleven;
		case '2': return twelve;
		case '3': return thirteen;
		case '4': return fourteen;
		case '5': return fifteen;
		case '6': return sixteen;
		case '7': return seventeen;
		case '8': return eighteen;
		case '9': return nineteen;
	}
}
int getHundreds(char c) {
	switch (c){
		case '1': return one+hundred+3;
		case '2': return two+hundred+3;
		case '3': return three+hundred+3;
		case '4': return four+hundred+3;
		case '5': return five+hundred+3;
		case '6': return six+hundred+3;
		case '7': return seven+hundred+3;
		case '8': return eight+hundred+3;
		case '9': return nine+hundred+3;
	}
}

int getCount(string str) {
	int count = 0;
	if (str.size() >= 3)
	{
		count = getHundreds(str[0]);
		if (str == "100" ||str == "200" || str == "300" || str == "400" || str == "500" ||
			str == "600" || str == "700" || str == "800" || str == "900" )
		{
			count -=3;
		}	
		return count + getCount(str.substr(1, string::npos));
	}

	else if (str.size() ==2) 
	{
		if (str != "11" && str != "12" && str != "13" &&
			str != "14" && str != "15" && str != "16" &&
			str != "17" && str != "18" && str != "19" )
		{
			count = getTens(str[0]);
			return count + getCount(str.substr(1, string::npos));
		}

		else 
		{
			return getTeens(str[1]);
		}

	}

	else if (str.size() == 1) 
	{
		return getOnes(str[0]);
	}

	else return 0;
}
