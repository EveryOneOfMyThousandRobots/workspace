#include <iostream>

#ifndef STACK_H_
#define STACK_H_

using namespace std;

class Stack {
	public:
		//construtors
		Stack(int sz);
		~Stack();
		//members
		void Push(int value);
		bool Full();
		void Print();
		void TestFill();
	private:
		int size;
		int top;
		int* stack;

};

#endif //STACK_H_
