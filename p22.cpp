#include <iostream>
#include <string>
#include <map>
#include <fstream>
using namespace std;
typedef unsigned long long ullong ;

int main()
{
    fstream inputFile;
    inputFile.open("input.txt");
    map <string, ullong> names;

    string name;
    while (inputFile >> name) 
    {
        names.insert(make_pair(name, 0));
    }
    inputFile.close();

    auto it = names.begin();
    for (int i = 0; i < names.size(); i++)  
    {
        for (auto strit = (it->first).begin(); strit != (it->first).end(); strit++) 
        {
            (it->second) += (*strit) - '@';
        }
        (it->second) *= (i + 1);
        it++;
    }

    ullong total = 0;
    for (it = names.begin(); it != names.end(); it++) 
    {
        total += it->second;
    }

    cout << total <<endl;
}
