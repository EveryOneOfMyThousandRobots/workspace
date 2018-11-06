
//constructors
#include "stack.h"


Stack::Stack(int sz) {
	size = sz;
	top = 0;
	stack = new int[size];
	cout << "size:" << size << " top:" << top << endl;

}
//destructors
Stack::~Stack() {
	delete [] stack;
}
//members
void Stack::TestFill() {
	for (int i = 0; i < size; i++) {
		//cout << i << " " << (i * 17) << endl;
		this->Push( (i * 17) % 7);
	}
}
void Stack::Push(int value) {
	//ASSERT(top < 10);
	if (top < size) {
		stack[top++] = value;
	}
}

bool Stack::Full() {
	return (top == size);
}

void Stack::Print() {
	for (int i = 0; i < size; i++) {
		cout << i << ": " << stack[i] << endl;
	}
}

//other

void test() {
	Stack s1(17);
	Stack* s2 = new Stack(23);
}
