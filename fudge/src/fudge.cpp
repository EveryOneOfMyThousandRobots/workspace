//============================================================================
// Name        : fudge.cpp
// Author      : Fuckstickers
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include "stack.h"
#include "stack.c"
using namespace std;


int main() {
	cout << "!!!Hello World!!!" << endl; // prints !!!Hello World!!!

	//Something s = new Something();
	Stack *s = new Stack(17);
	//s->Push(1);
	s->TestFill();
	s->Print();
	delete(s);
	//test();
	return 0;


}



