#include <iostream>
#include <vector>
#define ARRAY_SIZE 1005
using namespace std;

struct bigInt{
    vector<int> result;
    int index = 0;

    bigInt(int n);

    bigInt& operator*(int rhs);
    bigInt& operator+(const bigInt & rhs);
    friend ostream& operator<< (ostream & os, const bigInt & b)
    {
        for (int i = b.index -1; i >= 0 ; i--) 
        {
            os << b.result[i]; 
        }
        return os;
    }

    private:
    bigInt() {}
};

ostream & operator<<(ostream & os, const bigInt bigArr[][2]);
void revert(bigInt lhs[][2], bigInt rhs[][2]);
void revert(bigInt & lhs, bigInt & rhs);

int main()
{
    int base_arr [2][2] = 
    {
        {1, 1},
        {1, 0}
    };

    bigInt result_arr [2][2] = 
    {
        {1, 1},
        {1, 0}
    };

    unsigned int i = 2;
    while ( i++ ) {
        bigInt temp_arr[2][2] = result_arr;
        bigInt result00 = result_arr[0][0]*base_arr[0][0] + result_arr[0][1]*base_arr[1][0];
        revert(result_arr, temp_arr);

        bigInt result01 = result_arr[0][0]*base_arr[0][1] + result_arr[0][1]*base_arr[1][1];
        revert(result_arr, temp_arr);

        bigInt result10 = result_arr[1][0]*base_arr[0][0] + result_arr[1][1]*base_arr[1][0];
        revert(result_arr, temp_arr);

        bigInt result11 = result_arr[1][0]*base_arr[0][1] + result_arr[1][1]*base_arr[1][1];
    
        revert(result_arr[0][0], result00);
        revert(result_arr[0][1], result01);
        revert(result_arr[1][0], result10);
        revert(result_arr[1][1], result11);
        if (result_arr[0][0].index==1000) 
        {
            cout << i <<endl;
            break;
        }
    }
}


bigInt::bigInt(int n) : result(ARRAY_SIZE)
{
    while (n) 
    {
        result[index] = n%10;
        n /= 10;
        index++;
    }
}

bigInt& bigInt::operator*(int rhs)
{
    int carry = 0;

    for (auto it = result.begin(); it != (result.begin() + index); it++)
    {
        int prod = (*it) * rhs + carry;
        (*it) = prod % 10;
        carry = prod/10;
    }
    while (carry)
    {
        *(result.begin() + index) = carry % 10;
        carry = carry/10;
        index++;
    }
    return *this;
}

bigInt& bigInt::operator+(const bigInt & rhs) 
{
    for (int i =0; i< index; i++) 
    {
        result[i] = result[i] + rhs.result[i];
        int temp = result[i]/10;
        if (temp) 
        {
            result[i+1] += temp;
            if (i == index-1) index++;
        }
        result[i] %= 10;
    }
    return *this;
}

ostream & operator<<(ostream & os, const bigInt bigArr[][2]) 
{
    return os << bigArr[0][0] << " " << bigArr[0][1] << endl 
              << bigArr[1][0] << " " << bigArr[1][1] << endl;
}

void revert(bigInt lhs[][2], bigInt rhs[][2])
{
    lhs[0][0] = rhs[0][0];
    lhs[0][1] = rhs[0][1];
    lhs[1][0] = rhs[1][0];
    lhs[1][1] = rhs[1][1];
    lhs[0][0].index = rhs[0][0].index;
    lhs[0][1].index = rhs[0][1].index;
    lhs[1][0].index = rhs[1][0].index;
    lhs[1][1].index = rhs[1][1].index;    
}

void revert(bigInt & lhs, bigInt & rhs) {
    lhs = rhs;
    lhs.index = rhs.index;
}


