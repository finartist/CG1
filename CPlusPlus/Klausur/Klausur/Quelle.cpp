# include <iostream >
using namespace std;
class Animal {
public:
	~Animal() { cout << "die" << endl; }
	virtual void move() = 0;
};
class Fish : public Animal {
public:
	~Fish() { cout << "turn upside down" << endl; }
	virtual void move() { cout << "swim" << endl; }
};
class Bird : public Animal {
public:
	~Bird() { cout << "fall from tree" << endl; }
	//virtual void move() = 0; //{ cout << " f l y " << endl; }
};
class Penguin : public Bird {
public:
	~Penguin() { cout << "freeze" << endl; }
	virtual void move() { cout << "swim or walk" << endl; }
};

class Tux : public Penguin {
public:
	~Tux() { cout << "iced" << endl; }
	virtual void move() { cout << "tap" << endl; }
};

int main() {
	Animal* tux = new Penguin(); tux->move();
	Fish * nemo = new Fish; nemo->move();
	delete tux;
	delete nemo;
	return 0;
}


//#include <iostream>
//int y[3];
//auto x = y[1];
//auto x2 = &y[1];
//int x3[2];
//void x4(int y) {};
//int x5[2];
//int* px = x5;
//int y1 = 3;
//int& x6 = y1;
//int* px6 = &x6;
//
//int main() {
//	std::cout << typeid(x).name() << "\n";
//	std::cout << typeid(x2).name() << "\n";
//	std::cout << typeid(x3).name() << "\n";
//	std::cout << typeid(x4).name();
//	return 0;
//}

//# include <string >
//# include <iostream >
//# include <memory >
//using namespace std;
//
//struct Cookie{
//	 string type;
// Cookie() : type(" Dough ") {
//	 cout << " Dough p r e p a r e d " << endl;
//	
//}
// Cookie(const string & type) : type(type) {
//	 cout << type << " b a k e d " << endl;
//}
// ~Cookie() {
//	cout << type << " v a n i s h e d " << endl;
//}
//};
//
//Cookie * mix(const Cookie * box1, int num1,
//	 const Cookie * box2, int num2) {
//	 Cookie * kmix = new Cookie[num1 + num2];
//	 for(int i = 0; i< num1; ++i) kmix[i] = box1[i];
//	 for(int i = 0; i< num2; ++i) kmix[i + num1] = box2[i];
//	 return kmix;
//}
//
// bool meeting() {
//	Cookie box1[] = { Cookie(" B i s q u i t "),Cookie(" Ch o c o l a t e ") };
//	 Cookie box2[] = { Cookie(" Ac a c o o k i e ") };
//	 Cookie * bowl = mix(box1, 2, box2, 1);
//	 if(false)
//		 return false;
//	 delete bowl;
//	 return true;
//}
//
// int main() {
//	 meeting();
//	 return 0;
// }