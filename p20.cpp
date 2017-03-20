#include <iostream>
#include <vector>
using namespace std;

#define ARRAY_SIZE 199
vector<int> result(ARRAY_SIZE);

void multiply(int n, int & index)
{
    int carry = 0;

    for (auto it = result.begin(); it != (result.begin() + index); it++)
    {
        int prod = (*it) * n + carry;
        (*it) = prod % 10;
        carry = prod/10;
    }

    while (carry)
    {
        *(result.begin() + index) = carry % 10;
        carry = carry/10;
        index++;
    }
}

void factorial(int n)
{
    *(result.begin()) = 1;
    int index = 1;

    for (int i = 2; i <= n; i++)
    {
        multiply(i, index);
    }

    unsigned long long answer = 0;
    for (auto it = result.begin() + index; it != result.begin() -1; it--) 
    {
    	answer += *it;
    }
    cout << answer <<endl;
}

int main()
{
	factorial(100);
}