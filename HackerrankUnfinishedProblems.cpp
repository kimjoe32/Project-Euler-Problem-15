/* Dynamic Array in Data Structures */
#include <cmath>
#include <cstdio>
#include <vector>
#include <iostream>
#include <algorithm>
using namespace std;


int main() {
    int N, Q, lastAns = 0;
    cin >> N >> Q;
    int * lastInd = new int[N];
    int ** seqList = new int*[N];
    for (int i =0; i < N; i++)
        seqList[i] = new int[N];
    
    int num, x, y;
    while (Q--)
    {
        cin >> num >> x >> y;
        int seq = ((x ^ lastAns) % N);
        if (num == 1)
        {
            seqList[seq][lastInd[seq]] = y;
            lastInd[seq]++;
        }
        else
        {
            lastAns = seqList[seq][y%N];
            cout << lastAns << endl;
        }
    }
    
    for (int i = 0; i < N; i++)
        delete[] seqList[i];
    delete[] seqList;
    return 0;
}
