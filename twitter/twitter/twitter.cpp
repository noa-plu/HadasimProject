// twitter.cpp : This file contains the 'main' function. Program execution begins and ends there.
//
#include <cmath>
#include <iostream>
using namespace std;
// ������ �������� ������ ���� ����� �����
int width = 0, height = 0;

//��� ���� ����� ������
void inputWidthAndHeight(string sort) {

    cout << "enter width of " << sort << " tower" << endl;
    cin >> width;
    cout << "enter height of " << sort << " tower" << endl;
    cin >> height;
}

//������� ������� �����
void printTriangle() {
    //�� ���� ������ ���� �� ������ ���� ���� ��� ����� ������ �� ������ ��� ���� �3 ������ ���� �2 ����� ����� ���� ������ ������ �� ������
        if (width % 2 == 0 || width > height * 2|| (width <= 3 && height > 2))
            cout << "The triangle cannot be printed" << endl;
    //����� ������ �� ����� �� ������
        else {
            int asterisks = 1, rest = 0, row = 0, numRow = 0;
            // ����� ��� ���� ����� �� ������ 
            numRow = floor((width - 2) / 2);
            //����� ��� ����� ������ ��� ��� ��� ����  
                row = floor((height - 2) / numRow);
                if(numRow>1)
                    rest = (height - 2) % numRow;
            //����� �� ��� ���� �������� ������ ��� ������
            while (asterisks <= width) {
                //�� �� ����� ������� �� ������� ����� �� ���� ���� ��� ����
                if (asterisks == 1 || asterisks == width) 
                    numRow = 1;
                //�� �� ����� �� 3 ������� �� ����� �� ���� ����� ������ ���� ������
                else if (asterisks == 3) 
                    numRow = row+rest;
                //��� ������ ������ ����� �� ����� ������
                else 
                    numRow = row;
                //���� �� ����� ������� ������� ���� ������ ���
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
    //����� ���� ������
    case 1: {
        //����� ��� ������
        cout << "the perimeter of a triangle: " << hypot((width / 2.0), height) * 2 + width << endl;
        break;
    }
    //����� ������
    case 2:
        printTriangle();
        break;
    default:
        break;
    }
}

//������� ������� ��� �� ���� ����
void areaOrPerimeterRectangle() {
    inputWidthAndHeight("rectangle");
    //�� ����� ����� �� ������ ��� ����� ������ ���� �5 ����� ��� �����
    if (width == height || abs(width - height) > 5) {
        cout << "the area is:" << width * height << endl;
    }
    // ����� ���� ����� �� ����� �� ������
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
        //����� ��� ���
        switch (menu)
        {
        // ������ ����� ����� ��� �� ����
        case 1: 
            areaOrPerimeterRectangle();
            break;
        // ������ ����� ����� ����� �����
        case 2: 
            triangleMenu();
            break;
        // �����
        case 3:
            break;

        default:
            break;
        } 
    }
}

