#include <iostream>
#include <algorithm>
#include <string>
#include <fstream>
#include <vector>
#include <map>
using namespace std;

void sortAgain(string* arr);

struct hand
{
    string cards[5];
    int wins;
    hand() : cards({"nc","nc","nc","nc","nc"}), wins(0) {}

    friend istream& operator>> (istream& is, hand & h)
    {
        is >> h.cards[0] >> h.cards[1] >> h.cards[2] >> h.cards[3] >> h.cards[4];
        sort(h.cards, h.cards+5);
        sortAgain(h.cards);

        if ( h.cards[0] == "nc" || h.cards[1] == "nc" || 
             h.cards[2] == "nc" || h.cards[3] == "nc" || 
             h.cards[4] == "nc" ) 
        {
            throw "nc detected\n";
        }

        return is;
    }

    friend ostream& operator<< (ostream& os, const hand & h)
    {
        os << h.cards[0] << " " 
            << h.cards[1] << " " 
            << h.cards[2] << " " 
            << h.cards[3] << " " 
            << h.cards[4];
    }
};

template<typename T>
void swap(T &a, T &b);

template <typename T, typename U>
bool allequal(const T &t, const U &u);

template <typename T, typename U, typename... Others>
bool allequal(const T &t, const U &u, Others const &... args);

string checkHand(hand & p);
bool checkSequentialCards(const hand & h);
bool checkSameSuit(const hand & h);

int checkWinner(const string & p1result, const string & p2result, const hand & p1, const hand & p2);
int compare(const char s1, const char s2);


map<char, int> values;

int main()
{
    values['2'] = 0; values['3'] = 1; values['4'] = 2; values['5'] = 3; values['6'] = 4; values['7'] = 5; values['8'] = 6; values['9'] = 7;
    values['T'] = 8; values['J'] = 9; values['Q'] = 10; values['K'] = 11; values['A'] = 12;
    hand player1, player2;

    ifstream inputFile("input.txt", ifstream::in);

    int start =9, end =15;
    int i = 1;
    string p1result, p2result;
    while (inputFile >> player1 >> player2)
    {
        p1result = checkHand(player1);
        p2result = checkHand(player2);

        int result = checkWinner(p1result, p2result, player1, player2);
        if (result == -1) 
        {
            throw "-1 returned\n";
        }

        if (result) player1.wins++;
        else player2.wins++;
        
        i++;        
    }
    cout << player1.wins <<endl;
}

template<typename T>
void swap(T &a, T &b)
{
    T t = a;
    a = b;
    b = t;
}

template <typename T, typename U>
bool allequal(const T &t, const U &u)
{
    return t == u;
}

template <typename T, typename U, typename... Others>
bool allequal(const T &t, const U &u, Others const &... args)
{
    return (t == u) && allequal(u, args...);
}

void sortAgain(string* arr)
{
    for(int i = 0; i < 4; i++)
    {
        if (arr[i][0] == 'T')
        {
            for (int j = i; j< 4; j++)
            {
                if (arr[j][0] == arr[j+1][0] && j+2 < 5) 
                {
                    swap(arr[j], arr[j+2]);;
                }
                else swap(arr[j], arr[j+1]);
            }
        }
    }
    for(int i = 0; i < 4; i++)
    {
        if (arr[i][0] == 'J')
        {
            for (int j = i; j< 4; j++)
            {
                if (arr[j][0] == arr[j+1][0] && j+2 < 5) 
                {
                    swap(arr[j], arr[j+2]);;
                }
                else swap(arr[j], arr[j+1]);
            }
        }
    }
    for(int i = 0; i < 4; i++)
    {
        if (arr[i][0] == 'Q')
        {
            for (int j = i; j< 4; j++)
            {
                if (arr[j][0] == arr[j+1][0] && j+2 < 5) 
                {
                    swap(arr[j], arr[j+2]);;
                }
                else swap(arr[j], arr[j+1]);
            }
        }
    }
    for(int i = 0; i < 4; i++)
    {
        if (arr[i][0] == 'K')
        {
            for (int j = i; j< 4; j++)
            {
                if (arr[j][0] == arr[j+1][0] && j+2 < 5) 
                {
                    swap(arr[j], arr[j+2]);;
                }
                else swap(arr[j], arr[j+1]);
            }
        }
    }
    for(int i = 0; i < 4; i++)
    {
        if (arr[i][0] == 'A')
        {
            for (int j = i; j< 4; j++)
            {
                if (arr[j][0] == arr[j+1][0] && j+2 < 5) 
                {   
                    swap(arr[j], arr[j+2]);;
                }
                else swap(arr[j], arr[j+1]);
            }
        }
    }
}

bool checkSequentialCards(const hand & h)
{
    return (values[h.cards[1][0]] - values[h.cards[0][0]]) == 1 &&
           (values[h.cards[2][0]] - values[h.cards[1][0]]) == 1 &&
           (values[h.cards[3][0]] - values[h.cards[2][0]]) == 1 &&
           (values[h.cards[4][0]] - values[h.cards[3][0]]) == 1 ;
}

bool checkSameSuit(const hand & h)
{
    return  h.cards[0][1] == h.cards[1][1] &&
            h.cards[1][1] == h.cards[2][1] &&
            h.cards[2][1] == h.cards[3][1] &&
            h.cards[3][1] == h.cards[4][1];
}

string checkHand(hand & p)
{   
    //royal flush
    if (checkSequentialCards(p) && checkSameSuit(p) && p.cards[0][0] == 'T') return "RF";

    //straight flush
    if (checkSequentialCards(p) && checkSameSuit(p)) return "SF";

    //4 of a kind
    if (allequal(p.cards[0][0], p.cards[1][0], p.cards[2][0], p.cards[3][0]))
    {
        return "4K";
    }
    if (allequal(p.cards[1][0], p.cards[2][0], p.cards[3][0], p.cards[4][0])) 
    {
        swap(p.cards[4], p.cards[0]);
        return "4K";
    }
    //full house -- check for 2 + 3 or 3 + 2
    if (allequal(p.cards[0][0], p.cards[1][0], p.cards[2][0]) &&
        allequal(p.cards[3][0], p.cards[4][0]))
    {
        return "FH"; 
    }
    if (allequal(p.cards[0][0], p.cards[1][0]) &&
        allequal( p.cards[2][0], p.cards[3][0], p.cards[4][0]))
    {
        swap(p.cards[0], p.cards[4]);
        swap(p.cards[1], p.cards[3]);
        return "FH";
    }
    //flush
    if (checkSameSuit(p)) return "F";

    //straight
    if (checkSequentialCards(p)) return "S";

    //3 of a kind -- check first three or last 3 cards
    if (allequal(p.cards[0][0], p.cards[1][0], p.cards[2][0])) return "3K";
    if (allequal(p.cards[1][0], p.cards[2][0], p.cards[3][0])) 
    {
        swap(p.cards[0], p.cards[3]);
        return "3K";
    }
    if (allequal(p.cards[2][0], p.cards[3][0], p.cards[4][0])) 
    {
        swap(p.cards[0], p.cards[4]);
        swap(p.cards[1], p.cards[3]);
        return "3K";
    }

    //2 of a kind and two pair -- return where pair/2pair occurs
    if (allequal(p.cards[0][0], p.cards[1][0]))
    {   
        if (allequal(p.cards[2][0], p.cards[3][0])) 
        {
            return "2P";
        }
        if (allequal(p.cards[3][0], p.cards[4][0])) 
        {   
            swap(p.cards[2], p.cards[4]);
            return "2P";
        }
        return "P";
    }
    if (allequal(p.cards[1][0], p.cards[2][0]))
    {
        if (allequal(p.cards[3][0], p.cards[4][0])) 
        {
            swap(p.cards[0], p.cards[2]);
            swap(p.cards[2], p.cards[4]);
            return "2P";
        }
        swap(p.cards[0], p.cards[2]);
        return "P";
    }
    if (allequal(p.cards[2][0], p.cards[3][0]))
    {
        swap(p.cards[0], p.cards[2]);
        swap(p.cards[1], p.cards[3]);
        return "P";
    }
    if (allequal(p.cards[3][0], p.cards[4][0]))
    {
        swap(p.cards[0], p.cards[3]);
         swap(p.cards[1], p.cards[4]);
        return "P";
    }

    //high card
    return "HC";

}
int compare(const char s1, const char s2) 
{
    if (values[s1] == values[s2]) return -1;
    return (values[s1] > values[s2]);
}

int checkWinner(const string & p1result, const string & p2result, const hand & p1, const hand & p2) 
{
    // 0 = p wins
    // 1 = p2 wins

    //royal flush
    if (p1result == "RF") return 1;
    if (p2result == "RF") return 0;

    //straight flush
    if (p1result == "SF" && p2result == "SF")
    {
        return (compare(p1.cards[0][0], p2.cards[0][0]));
    }

    if (p1result == "SF") return 1;
    if (p2result == "SF") return 0;

    //4 of a kind
    if ((p1result == "4K") && (p2result == "4K"))
    {
        int result = (compare(p1.cards[0][0], p2.cards[0][0]));
        if (result == -1)
        {
            return (compare(p1.cards[4][0], p2.cards[4][0]));
        }
        return result;
    }

    if (p1result == "4K") return 1;
    if (p2result == "4K") return 0;

    //full house
    if ((p1result == "FH") && (p2result == "FH"))
    {
        int result = compare(p1.cards[0][0], p2.cards[0][0]);
        if (result == -1) 
        {
            return (compare(p1.cards[3][0], p2.cards[3][0]));
        }
        return result;
    }

    if (p1result == "FH") return 1;
    if (p2result == "FH") return 0;

    //flush
    if (p1result == "F" && p2result == "F")
    {
        for (int index = 4; index >= 0; index--)
        {
            int result = compare(p1.cards[index][0], p2.cards[index][0]);
            if (result != -1) return result;
        }
    }
    if (p1result == "F") return 1;
    if (p2result == "F") return 0;

    //straight
    if (p1result == "S" && p2result == "S")
    {
        return compare(p1.cards[4][0], p2.cards[4][0]);
    }
    if (p1result == "S") return 1;
    if (p2result == "S") return 0;

    //3 of a kind
    if ((p1result == "3K") && (p2result == "3K"))
    {
        int result = compare(p1.cards[4][0], p2.cards[4][0]);
        if (result == -1)
        {
            return compare(p1.cards[3][0], p2.cards[3][0]);
        }
        return result;
    }

    if ((p1result == "3K" || p1result == "3K")) return 1;
    if ((p2result == "3K" || p2result == "3K")) return 0;

    if ((p1result == "2P") && (p2result == "2P") )
    {
        int result = compare(p1.cards[2][0], p2.cards[2][0]);
        if (result == -1)
        {
            result = compare(p1.cards[0][0], p2.cards[0][0]);
            if (result == -1) return compare(p1.cards[4][0], p2.cards[4][0]);
        }
        return result;
    }
    if (p1result == "2P") return 1;
    if (p2result == "2P") return 0;

    if ((p1result == "P") && (p2result == "P"))
    {
        int result = compare(p1.cards[0][0], p2.cards[0][0]);
        if (result == -1){
            for (int index = 4; index > 0; index--)
            {
                result = compare(p1.cards[index][0], p2.cards[index][0]);
                if (result != -1) return result;
            }
        }
        return result;
    }

    if (p1result == "P") return 1;
    if (p2result == "P") return 0;    

    //high card
    for (int index = 4; index >=0 ; index--)
    {
        int result = compare(p1.cards[index][0], p2.cards[index][0]);
        if (result != -1) return result;
    }

    throw "failed to get winner\n";
}


