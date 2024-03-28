// twitter.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#include <cmath>
#include <iostream>
using namespace std;
// משתנים גלובאלים להגדרת אורך ורוחב המגדל
int width = 0, height = 0;

//קלט רוחב וגובה הבניין
void inputWidthAndHeight(string sort) {

    cout << "enter width of " << sort << " tower" << endl;
    cin >> width;
    cout << "enter height of " << sort << " tower" << endl;
    cin >> height;
}

//פונקציה המדפיסה משולש
void printTriangle() {
    //אם בסיס המשולש זוגי או שהבסיס גדול יותר מפי שניים מהגובה או שהרוחב קטן שווה ל3 והגובה גדול מ2 הדפסת הודעה שאין אפשרות להדפיס את המשולש
        if (width % 2 == 0 || width > height * 2|| (width <= 3 && height > 2))
            cout << "The triangle cannot be printed" << endl;
    //הדפסת המשולש אם התנאי לא מתקיים
        else {
            int asterisks = 1, rest = 0, row = 0, numRow = 0;
            // חישוב כמה סוגי שורות יש במשולש 
            numRow = floor((width - 2) / 2);
            //חישוב כמה שורות יודפסו מכל סוג ואת היתר  
                row = floor((height - 2) / numRow);
                if(numRow>1)
                    rest = (height - 2) % numRow;
            //לולאה כל עוד מספר הכוכביות להדפסה קטן מהרוחב
            while (asterisks <= width) {
                //אם זה השורה הראשונה או האחרונה תדפיס לי ממנה שורה אחת בלבד
                if (asterisks == 1 || asterisks == width) 
                    numRow = 1;
                //אם זה השורה של 3 כוכביות אז תדפיס לי אותה כמספר השורות ועוד השארית
                else if (asterisks == 3) 
                    numRow = row+rest;
                //שאר השורות שבאמצע תדפיס לי כמספר השורות
                else 
                    numRow = row;
                //מעבר על השורה הנוכחית והדפסתה מספר הפעמים שלה
                while(numRow>0){
                    for (int i = 1;i <= width;i++) {
                        if (i <= floor((width - asterisks) / 2) || i > (floor((width - asterisks) / 2) + asterisks))
                            cout << " ";
                        else
                            cout << "*";
                    }
                    cout << endl;
                    numRow--;
                }
                asterisks += 2;
            }
        }
}

void triangleMenu() {
    inputWidthAndHeight("traingle");
    int menu = 0;
    double hypotenuse=0;
    cout << "enter 1 to calculate the perimeter of a triangle or 2 to print the triangle " << endl;
    cin >> menu;

    switch (menu)
    {
    //הדפסת היקף המשולש
    case 1: {
        //חישוב צלע המשולש
        cout << "the perimeter of a triangle: " << hypot((width / 2.0), height) * 2 + width << endl;
        break;
    }
    //הדפסת המשולש
    case 2:
        printTriangle();
        break;
    default:
        break;
    }
}

//פונקציה המדפיסה שטח או הוקף מלבן
void areaOrPerimeterRectangle() {
    inputWidthAndHeight("rectangle");
    //אם המלבן ריבוע או שההפרש בין אורכו לרוחבו גדול מ5 הדפסת שטח המלבן
    if (width == height || abs(width - height) > 5) {
        cout << "the area is:" << width * height << endl;
    }
    // הדפסת היקף המלבן אם התנאי לא מתקיים
    else {
        cout << "the perimeter is:" << (width + height) * 2 << endl;
    }
}




int main()
{
    int menu = 0;
    while (menu != 3) {
        cout << "enter 1 to choose rectangle tower 2 to choose triangular tower and 3 to exit"<<endl;
        cin >> menu;
        //הדפסה לפי קלט
        switch (menu)
        {
        // בבחירת במלבן הדפסת שטח או היקף
        case 1: 
            areaOrPerimeterRectangle();
            break;
        // בבחירת משולש פתיחת תפריט מתאים
        case 2: 
            triangleMenu();
            break;
        // יציאה
        case 3:
            break;

        default:
            break;
        } 
    }
}

